package com.roadready.Mapper;

import com.roadready.dto.*;
import com.roadready.model.Booking;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookingMapper {

    public BookingDetailsDto mapBookingDetailsEntityToDto(Booking booking) {
        return new BookingDetailsDto(
                booking.getId(),
                booking.getCustomer().getId(),
                booking.getCar().getId(),
                booking.getCar().getBrand(),
                booking.getCar().getModel(),
                booking.getPickupDateTime(),
                booking.getDropDownDateTime(),
                booking.getTotalAmount(),
                booking.getBookingStatus().toString(),
                booking.getKycStatus().toString()
        );
    }

    public BookingAgentDto mapBookingAgentDto(Booking booking) {
        return new BookingAgentDto(
                booking.getId(),
                booking.getCar().getBrand(),
                booking.getCar().getModel(),
                booking.getCar().getType().toString(),
                booking.getPickupDateTime(),
                booking.getDropDownDateTime());
    }


    public BookingAgentPaginaation mapAgentPagination(Page<Booking> booking, List<BookingAgentDto> dto) {

        return new BookingAgentPaginaation(
                booking.getTotalElements(),
                booking.getTotalPages(),
                dto
        );

    }

    public BookingPaginationResponse mapPaginationResponse(Page<Booking> booking, List<BookingDetailsDto> dto) {

        return new BookingPaginationResponse(
                booking.getTotalElements(),
                booking.getTotalPages(),
                dto
        );

    }

    public BookingByIdRespDto mapbookingByIdentityToDto(Booking booking) {
        return new BookingByIdRespDto(
                booking.getId(),
                booking.getCustomer().getId(),
                booking.getCar().getId(),
                booking.getCar().getBrand(),
                booking.getCar().getModel(),
                booking.getPickupDateTime(),
                booking.getDropDownDateTime(),
                booking.getPickupLocation(),
                booking.getDropdownLocation(),
                booking.getDeliveryType().toString(),
                booking.getCoupon() != null ? booking.getCoupon().getCouponCode() : "NONE",
                booking.getOriginalAmount(),
                booking.getDeliveryCharge(),
                booking.getDiscountedAmount(),
                booking.getTotalAmount(),
                booking.getBookingStatus().toString(),
                booking.getKycStatus().toString()
        );
    }


    public BookingCustomerDto mapBookingCustomerToDto(String username, Booking booking) {
        return new BookingCustomerDto(
                username,
                booking.getId(),
                booking.getCar().getBrand(),
                booking.getCar().getModel(),
                booking.getCar().getType().toString(),
                booking.getCar().getModelYear(),
                booking.getPickupDateTime(),
                booking.getDropDownDateTime(),
                booking.getPickupLocation(),
                booking.getDropdownLocation(),
                booking.getDeliveryType().toString(),
                booking.getTotalAmount(),
                booking.getBookingStatus().toString(),
                booking.getKycStatus().toString()
        );
    }
}
