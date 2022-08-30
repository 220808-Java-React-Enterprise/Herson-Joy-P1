package com.revature.P1.services;

import com.revature.P1.daos.ReviewDAO;
import com.revature.P1.models.Review;

import java.util.List;

public class ReviewService {
    private final ReviewDAO reviewDAO;

    public ReviewService(ReviewDAO reviewDAO) {
        this.reviewDAO = reviewDAO;
    }

    public void saveReview(Review review) {
        reviewDAO.save(review);
    }

    public List<Review> getAllReviewsByRestaurantId(String id) {
        return reviewDAO.getAllByRestaurantId(id);
    }
}
