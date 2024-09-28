package com.example.demo.service;

import com.example.demo.model.Reviews;
import com.example.demo.repository.ReviewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewsService {
    @Autowired
    private ReviewsRepository reviewsRepository;

    public ReviewsService() {
    }

    public List<Reviews> getAllReviews() {
        return this.reviewsRepository.findAll();
    }

    public Reviews getReviewById(Long id) {
        return (Reviews)this.reviewsRepository.findById(id).orElse((Reviews) null);
    }

    public List<Reviews> getReviewsByArticleId(Long articleId) {
        return this.reviewsRepository.findByArticleId(articleId);
    }

    public Reviews createReview(Reviews reviews) {
        return (Reviews)this.reviewsRepository.save(reviews);
    }

    public void deleteReview(Long id) {
        this.reviewsRepository.deleteById(id);
    }

    public Reviews updateReview(Long id, Reviews reviews) {
        Reviews review = (Reviews)this.reviewsRepository.findById(id).orElse((Reviews) null);
        if (review != null) {
            review.setRating(reviews.getRating());
            review.setComment(reviews.getComment());
            return (Reviews)this.reviewsRepository.save(review);
        } else {
            return (Reviews) null;
        }
    }
}