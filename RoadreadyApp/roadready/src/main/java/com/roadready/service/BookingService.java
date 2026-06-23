package com.roadready.service;

import com.roadready.Mapper.AgentMapper;
import com.roadready.Mapper.BookingMapper;
import com.roadready.dto.*;
import com.roadready.enums.BookingStatus;
import com.roadready.enums.DeliveryType;
import com.roadready.enums.KycStatus;
import com.roadready.exceptions.IllegalBookingStatusException;
import com.roadready.exceptions.ResourceNotFoundException;
import com.roadready.exceptions.InvalidOwnerShipException;
import com.roadready.model.*;
import com.roadready.repository.BookingRepository;
import com.roadready.util.BookingDateUtility;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;
    private final CarService carService;
    private final BookingDateUtility bookingDateUtility;
    private final CouponService couponService;
    private final CustomerService customerService;
    private final AgentService agentService;
    private final AgentMapper agentMapper;

    public BookingPaginationResponse getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        LocalDateTime today = LocalDateTime.now();

        Page<Booking> bookingPage = bookingRepository.findUnassignedBookings(today, pageable);

        List<BookingDetailsDto> bookingDto = bookingPage.stream()
                .map(bookingMapper::mapBookingDetailsEntityToDto)
                .toList();

        return bookingMapper.mapPaginationResponse(bookingPage, bookingDto);


    }

    public Booking getById(int bookingId) {
        return bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid Booking ID"));

    }


    public List<Booking> getAllBooking() {
        return bookingRepository.findAll();
    }

    public BookingByIdRespDto getBookingById(int bookingId) {

        Booking booking = getById(bookingId);
        return bookingMapper.mapbookingByIdentityToDto(booking);
    }


    public List<BookingStatus> getBookingStatus() {
        return Arrays.stream(BookingStatus.values()).toList();
    }


    public void updateKycStatus(int bookingID, String kycStatus, String name) {
        //Kyc Status
        KycStatus status = KycStatus.valueOf(kycStatus);
        // get the booking
        Booking booking = getById(bookingID);
        //fetch the agent
        Agent agent = agentService.findByUsername(name);
        //check the booking status is confirmed
        if (!booking.getBookingStatus().equals(BookingStatus.CONFIRMED))
            throw new IllegalBookingStatusException("Kyc is updated only to the confirmed booking");
        //check the kyc status
        if (status.equals(KycStatus.VERIFIED)) {
            booking.setKycStatus(KycStatus.VERIFIED);
            booking.setBookingStatus(BookingStatus.ACTIVE);
        } else if (status.equals(KycStatus.REJECTED)) {
            booking.setKycStatus(KycStatus.REJECTED);
            booking.setBookingStatus(BookingStatus.CONFIRMED);
        }
        // add the details - agent
        booking.setAgent(agent);
        save(booking);
    }

    public BookingByIdRespDto addBooking(int carId, String username, AddBookingDto dto) {

        Booking booking = new Booking();

        // Check car id and its availability
        Car car = carService.getAvailableCarById(carId, dto.pickupDateTime(), dto.dropdownDateTime());

        // get Customer
        Customer customer = customerService.findByUsername(username);

        // Check the date are in future and compute the total no of hours
        LocalDateTime pickupDateTime = dto.pickupDateTime();
        LocalDateTime dropdownDateTime = dto.dropdownDateTime();
        long hours = bookingDateUtility.calculateTime(pickupDateTime, dropdownDateTime);

        // fetch the coupon
        Coupon coupon = null;
        if (dto.couponCode() != null) {
            coupon = couponService.getCouponByCouponCode(dto.couponCode());
        }

        // compute original amount, discounted amount, total amount
        // Note : * , + operators cannot work with BigDecimal
        /*
            calculate original price
            if coupon valid = get discount value
            if HOME_DELIVERY = add amount of 50
            calculate total amount
         */
        BigDecimal originalAmount = car.getPricePerHour().multiply(BigDecimal.valueOf(hours));
        BigDecimal discountedAmount = BigDecimal.ZERO;
        if (coupon != null) {
            discountedAmount = coupon.getDiscountValue();
            coupon.setUsedCount(coupon.getUsedCount() + 1);
            booking.setCoupon(coupon);

        }

        BigDecimal totalAmount = originalAmount.subtract(discountedAmount);

        if ("HOME_DELIVERY".equals(dto.deliveryType()))
            totalAmount = totalAmount.add(BigDecimal.valueOf(50));

        //set booking status and add booking
        booking.setPickupDateTime(pickupDateTime);
        booking.setDropDownDateTime(dropdownDateTime);
        booking.setPickupLocation(dto.pickupLocation());
        booking.setDropdownLocation(dto.dropdownLocation());
        booking.setDeliveryType(DeliveryType.valueOf(dto.deliveryType()));
        booking.setOriginalAmount(originalAmount);
        booking.setDiscountedAmount(discountedAmount);
        booking.setTotalAmount(totalAmount);
        booking.setBookingStatus(BookingStatus.PENDING);
        booking.setKycStatus(KycStatus.PENDING);
        booking.setCar(car);
        booking.setCustomer(customer);

        // Save the Booking
        save(booking);
        return bookingMapper.mapbookingByIdentityToDto(booking);
    }

    public List<BookingCustomerDto> getAllBookings(String username, String status) {
        BookingStatus bookingStatus = null;
        if (!status.equals("ALL"))
            bookingStatus = BookingStatus.valueOf(status);
        List<Booking> bookings = bookingRepository.findBookings(username, bookingStatus);
        List<BookingCustomerDto> mappedBookings = bookings.stream().map((b) -> bookingMapper.mapBookingCustomerToDto(username, b))
                .toList();
        return mappedBookings;
    }

    public void cancelBooking(int id, String username, CancellationRedDto dto) {
        //get customer
        Customer customer = customerService.findByUsername(username);
        // get booking
        Booking booking = getById(id);
        if (booking.getCustomer().getId() != customer.getId()) {
            throw new InvalidOwnerShipException("You do not have permission to cancel this booking.");
        }
        if (booking.getBookingStatus() == BookingStatus.CONFIRMED || booking.getBookingStatus() == BookingStatus.PENDING) {
            // update booking
            booking.setBookingStatus(BookingStatus.CANCELLED);
            booking.setCancellationInfo(dto.cancellationInfo());
        }
        // save booking
        save(booking);
    }

    public void save(Booking booking) {
        bookingRepository.save(booking);
    }


    public List<Booking> findAll() {
        return bookingRepository.findAll();
    }

    public long getHours(LocalDateTime pickupDate, LocalDateTime dropdownDate) {
        return bookingDateUtility.calculateTime(pickupDate, dropdownDate);
    }


    public List<AgentDetailsRespDto> getAgents(int bookingId) {
        List<Agent> agent = bookingRepository.findAgents(bookingId);
        return agent.stream().map(agentMapper::mapAgentEntityToDto).toList();
    }

    public void assignAgent(int bookingId, int agentId) {
        Agent agent = agentService.getById(agentId);
        Booking booking = getById(bookingId);
        booking.setAgent(agent);
        bookingRepository.save(booking);

    }


    public BookingAgentPaginaation getPreRent(int pages, int size, String name) {
        LocalDateTime today = LocalDateTime.now();
        Pageable pageable = PageRequest.of(pages, size);
        Page<Booking> bookingPage = bookingRepository.getPreRent(today, name, pageable);

        List<BookingAgentDto> bookingDto = bookingPage.stream()
                .map(bookingMapper::mapBookingAgentDto)
                .toList();

        return bookingMapper.mapAgentPagination(bookingPage, bookingDto);
    }


    public BookingAgentPaginaation getPostRent(int pages, int size, String name) {
        LocalDateTime today = LocalDateTime.now();
        Pageable pageable = PageRequest.of(pages, size);
        Page<Booking> bookingPage = bookingRepository.getPostRent(today, name, pageable);

        List<BookingAgentDto> bookingDto = bookingPage.stream()
                .map(bookingMapper::mapBookingAgentDto)
                .toList();

        return bookingMapper.mapAgentPagination(bookingPage, bookingDto);
    }

    public BookingByIdRespDto setConfirm(int id, String name) {
        Booking booking = getById(id);
        if (!booking.getCustomer().getUser().getUsername().equals(name))
            throw new InvalidOwnerShipException("You does not own this booking.");
        booking.setBookingStatus(BookingStatus.CONFIRMED);
        save(booking);
        return bookingMapper.mapbookingByIdentityToDto(booking);
    }

    public List<StatDto> getAgentStats() {

        return bookingRepository.getAgentStats();
    }

}
