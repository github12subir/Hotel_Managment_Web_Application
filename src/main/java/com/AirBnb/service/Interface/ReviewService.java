package com.AirBnb.service.Interface;

import com.AirBnb.entity.AppUser;
import com.AirBnb.payload.ReviewDto;

import java.util.List;

public interface ReviewService {

    ReviewDto createReview(ReviewDto reviewDto, AppUser appUser, long propertyId);

    List<ReviewDto> getReviewsByUser(AppUser appUser);

    void deleteReviewById(long reviewId);

    void deleteAllReviewsByOfUser(AppUser appUser);
}
