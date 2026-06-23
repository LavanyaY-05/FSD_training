package com.roadready.service;

import com.roadready.Mapper.ReviewMapper;
import com.roadready.dto.ReviewCarPaginationDto;
import com.roadready.dto.ReviewReqDto;
import com.roadready.dto.ReviewRespDto;
import com.roadready.enums.BookingStatus;
import com.roadready.exceptions.IllegalBookingStatusException;
import com.roadready.exceptions.InvalidOwnerShipException;
import com.roadready.model.Booking;
import com.roadready.model.Customer;
import com.roadready.model.Review;
import com.roadready.repository.ReviewRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;
    private final CarService carService;
    private final BookingService bookingService;

    private final CustomerService customerService;

    public Double getAvgRating(int carId) {
        Double value = reviewRepository.getAvgRating(carId);
        return value == null ? 0.0 : value;
    }

    public ReviewCarPaginationDto getCarReview(int id, int page, int size) {

        carService.getById(id);

        Pageable pageable = PageRequest.of(page, size);

        List<Review> reviews = reviewRepository.findByBookingCarId(id, pageable).getContent();

        List<ReviewRespDto> mappedReviews = reviews.stream().map(reviewMapper::mapReviewToDto)
                .toList();

        return new ReviewCarPaginationDto(mappedReviews);
    }

    public List<ReviewRespDto> getCarReviewFeatured() {

        Pageable pageable = PageRequest.of(0, 3);
        List<Review> reviews = reviewRepository.getCarReviewFeatured(pageable);
        return reviews.stream()
                .map(reviewMapper::mapReviewToDto)
                .toList();

    }

    public ReviewCarPaginationDto getCustomerReview(String name, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<Review> reviews = reviewRepository.findAllByBookingCustomerUserUsername(name, pageable).getContent();
        List<ReviewRespDto> mappedReviews = reviews.stream().map(reviewMapper::mapReviewToDto)
                .toList();

        return new ReviewCarPaginationDto(mappedReviews);
    }

    public void addReview(@Valid ReviewReqDto dto, int id, String name) {

        Customer customer = customerService.findByUsername(name);
        Booking booking = bookingService.getById(id);
        if (!booking.getCustomer().getUser().getUsername().equals(name)) {
            throw new InvalidOwnerShipException("You are not authorized to review this booking");
        }

        // check if booking is completed
        if (!booking.getBookingStatus().equals(BookingStatus.COMPLETED)) {
            throw new IllegalBookingStatusException("You can only review completed bookings");
        }

        Review review = new Review();
        review.setRating(dto.rating());
        review.setComment(dto.comment());
        review.setBooking(booking);

        reviewRepository.save(review);


    }
}
