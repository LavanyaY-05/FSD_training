package com.roadready.controller;

import com.roadready.dto.ReviewCarPaginationDto;
import com.roadready.dto.ReviewReqDto;
import com.roadready.dto.ReviewRespDto;
import com.roadready.service.ReviewService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/review")
@CrossOrigin(origins = "http://localhost:5173/")

public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/car/{id}")
    public ReviewCarPaginationDto getCarReview(@PathVariable int id,
                                               @RequestParam(required = false, defaultValue = "0") int page,
                                               @RequestParam(required = false, defaultValue = "10") int size) {
        return reviewService.getCarReview(id, page, size);
    }

    @GetMapping("/avgRating/{id}")
    public Double getAvgReview(@PathVariable int id
    ) {
        return reviewService.getAvgRating(id);
    }

    @GetMapping("/featured")
    public List<ReviewRespDto> getCarReviewFeatured() {
        return reviewService.getCarReviewFeatured();
    }


    @GetMapping("/by-customer")
    public ReviewCarPaginationDto getCustomerReview(Principal principal,
                                                    @RequestParam(required = false, defaultValue = "0") int page,
                                                    @RequestParam(required = false, defaultValue = "10") int size) {
        return reviewService.getCustomerReview(principal.getName(), page, size);
    }

    @PostMapping("/add/{id}")
    public void addReview(
            @RequestBody @Valid ReviewReqDto dto,
            @PathVariable int id,
            Principal principal) {
        reviewService.addReview(dto, id, principal.getName());
    }

}
