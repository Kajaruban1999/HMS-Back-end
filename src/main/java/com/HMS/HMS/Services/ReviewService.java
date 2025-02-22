package com.HMS.HMS.Services;

import com.HMS.HMS.Entities.Reviews;
import com.HMS.HMS.dto.ReviewDto;

import java.util.List;
import java.util.Optional;

public interface ReviewService {
    Reviews saveReview(ReviewDto reviewDto);
    Optional<Reviews> getReviewById(Long id);
    List<Reviews>getAllReview();
    void deleteReview(Long id);
}

