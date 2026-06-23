package com.roadready.Mapper;

import com.roadready.dto.ReviewRespDto;
import com.roadready.model.Review;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {


    public ReviewRespDto mapReviewToDto(Review review) {

        return new ReviewRespDto(
                review.getBooking().getCustomer().getUser().getUsername(),
                review.getRating(),
                review.getComment(),
                review.getCreatedAt()
        );

    }
}
