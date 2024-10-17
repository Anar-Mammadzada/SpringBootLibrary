package com.company.controller;

import com.company.requestModels.ReviewRequest;
import com.company.service.ReviewService;
import com.company.utils.ExtractJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")
@CrossOrigin("https://localhost:3000")
public class ReviewController {

    private ReviewService service;

    @Autowired
    public ReviewController(ReviewService service) {
        this.service = service;
    }

    @GetMapping("/secure/user/book")
    public Boolean reviewBookByUser(@RequestHeader(value = "Authorization") String token, @RequestParam Long bookId)throws Exception{
        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
        if (userEmail == null){
            throw new Exception("User email is missing");
        }

        return service.userReviewListed(userEmail, bookId);
    }


    @PostMapping("/secure")
    public void postReview(@RequestHeader(value = "Authorization") String token, @RequestBody ReviewRequest request) throws Exception{
        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
        if (userEmail == null){
            throw new Exception("User email is missing");
        }

        service.postReview(userEmail, request);
    }


}
