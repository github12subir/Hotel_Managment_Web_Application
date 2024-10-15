package com.AirBnb.service.Impl;

import com.AirBnb.entity.AppUser;
import com.AirBnb.entity.Property;
import com.AirBnb.entity.Review;
import com.AirBnb.exception.ReviewExist;
import com.AirBnb.exception.ReviewNotExist;
import com.AirBnb.payload.ReviewDto;
import com.AirBnb.repository.PropertyRepository;
import com.AirBnb.repository.ReviewRepository;
import com.AirBnb.service.Interface.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository reviewRepository;
    private PropertyRepository propertyRepository;
    public ReviewServiceImpl(ReviewRepository reviewRepository, PropertyRepository propertyRepository) {
        this.reviewRepository = reviewRepository;
        this.propertyRepository = propertyRepository;
    }


    @Override
    public ReviewDto createReview(ReviewDto reviewDto, AppUser appUser, long propertyId) {
        //Check property Exist or not
        Property property = propertyRepository.findById(propertyId).orElseThrow(() -> new RuntimeException("Property is not Exist!"));
        //Checked that this User gives any review or Not in this property
        Review existReview = reviewRepository.findUserAndProperty(appUser, property);
        if (existReview!=null){
            throw new ReviewExist("This User Already give one Review in This Property!");

        }
        //ReviewDto-> Review
        Review review = mapToEntity(reviewDto);
        //Set appUser into review
        review.setAppUser(appUser);
        //Set property into review
        review.setProperty(property);
        Review saveEntity = reviewRepository.save(review);
        ReviewDto saveDto = mapToDto(saveEntity);
        saveDto.setMessage("Your review is Successfully Added!");
        return saveDto;

    }

    @Override
    public List<ReviewDto> getReviewsByUser(AppUser appUser) {
        List<Review> reviews = reviewRepository.getReviewSByUser(appUser);
        List<ReviewDto> reviewsDto = reviews.stream().map(r -> mapToDto(r)).collect(Collectors.toList());
      return reviewsDto;

    }

    @Override
    public void deleteReviewById(long reviewId) {
        Optional<Review> byId = reviewRepository.findById(reviewId);
        if (byId.isEmpty()){
            throw new ReviewNotExist("Review Not Exist!");
        }

        reviewRepository.deleteById(reviewId);
    }

    @Override
    public void deleteAllReviewsByOfUser(AppUser appUser) {
        List<Review> reviews = reviewRepository.getReviewSByUser(appUser);
        if (reviews.isEmpty()){
            throw new ReviewNotExist("Have not exist any reviews!");
        }
        reviewRepository.deleteAll(reviews);
    }

    Review mapToEntity(ReviewDto dto){
        Review review=new Review();
        review.setId(dto.getId());
        review.setRating(dto.getRating());
        review.setDescription(dto.getDescription());
        return review;
    }

    ReviewDto mapToDto(Review entity){
        ReviewDto dto=new ReviewDto();
        dto.setId(entity.getId());
        dto.setRating(entity.getRating());
        dto.setDescription(entity.getDescription());
        dto.setProperty(entity.getProperty());
        dto.setAppUser(entity.getAppUser());

        return dto;
    }
}
