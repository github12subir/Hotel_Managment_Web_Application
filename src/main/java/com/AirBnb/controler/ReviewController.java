package com.AirBnb.controler;

import com.AirBnb.entity.AppUser;
import com.AirBnb.payload.ReviewDto;
import com.AirBnb.repository.ReviewRepository;
import com.AirBnb.service.Interface.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/airbnb/review")
public class ReviewController {

    private ReviewService reviewService;
    private ReviewRepository reviewRepository;
    public ReviewController(ReviewService reviewService, ReviewRepository reviewRepository) {
        this.reviewService = reviewService;
        this.reviewRepository = reviewRepository;
    }

    // Add a Review on a property
    //http://localhost:8080/api/airbnb/review/createReview/{propertyId}
    @PostMapping("/createReview/{propertyId}")
    public ResponseEntity<ReviewDto> createReview(
            @RequestBody ReviewDto reviewDto,
            @AuthenticationPrincipal AppUser appUser,
            @PathVariable long propertyId
            ){

       
        ReviewDto dto = reviewService.createReview(reviewDto, appUser, propertyId);
        return new ResponseEntity<>(dto, CREATED);
    }

    // Get all reviews of a particular User
    //http://localhost:8080/api/airbnb/review/getReviews
    @GetMapping("getReviews")
    public ResponseEntity<List<ReviewDto>> getReviewsByUser(
            @AuthenticationPrincipal AppUser appUser
    ){
        List<ReviewDto> dtos = reviewService.getReviewsByUser(appUser);
        return new ResponseEntity<>(dtos,HttpStatus.OK);
    }

    // Delete review by reviewId
    //http://localhost:8080/api/airbnb/review/deleteReview/{reviewId}
    @DeleteMapping("/deleteReview/{reviewId}")
    public ResponseEntity<String> deleteReviewById(
            @PathVariable long reviewId,
            @AuthenticationPrincipal AppUser appUser
    ){
        reviewService.deleteReviewById(reviewId);
        return new ResponseEntity<>("your Review successfully deleted ", OK);
    }

    //Delete all reviews by a particular user
    //http://localhost:8080/api/airbnb/review
    @DeleteMapping("/deleteReviews")
    public ResponseEntity<String> deleteAllReviewsByUser(
            @AuthenticationPrincipal AppUser appUser
    ){
        reviewService.deleteAllReviewsByOfUser(appUser);
        return new ResponseEntity<>("Your all reviews successfully deleted!", OK);
    }

}
