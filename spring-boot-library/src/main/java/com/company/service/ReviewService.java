package com.company.service;

import com.company.dao.BookRepository;
import com.company.dao.ReviewRepository;
import com.company.entity.Review;
import com.company.requestModels.ReviewRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;

@Service
@Transactional
public class ReviewService {

    private ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public void postReview(String userEmail, ReviewRequest request)throws Exception{
        Review validateReview = reviewRepository.findByUserEmailAndBookId(userEmail, request.getBookId());
        if (validateReview != null){
            throw new Exception("Review already created");
        }

        Review review = new Review();
        review.setBookId(request.getBookId());
        review.setRating(request.getRating());
        review.setUserEmail(userEmail);
        if (request.getReviewDescription().isPresent()){
            review.setReviewDescription(request.getReviewDescription().map(Object :: toString).orElse(null));
        }
        review.setDate(Date.valueOf(LocalDate.now()));
        reviewRepository.save(review);
    }

    public Boolean userReviewListed(String userEmail, Long bookId){
        Review validateReview = reviewRepository.findByUserEmailAndBookId(userEmail,bookId);
        if (validateReview != null){
            return true;
        }else{
            return false;
        }
    }

}
