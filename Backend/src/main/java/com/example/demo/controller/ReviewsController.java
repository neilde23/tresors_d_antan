package com.example.demo.controller;

import com.example.demo.model.Reviews;
import com.example.demo.service.ReviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewsController {

    @Autowired
    private ReviewsService reviewsService;

    // 1. Ajouter un avis
    @PostMapping("/add")
    public ResponseEntity<Reviews> addReview(@RequestBody Reviews review) {
        try {
            Reviews newReview = reviewsService.createReview(review);
            return new ResponseEntity<>(newReview, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 2. Obtenir la liste de tous les avis
    @GetMapping("")
    public ResponseEntity<List<Reviews>> getAllReviews() {
        try {
            List<Reviews> reviews = reviewsService.getAllReviews();
            if (reviews.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(reviews, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 3. Obtenir un avis par ID
    @GetMapping("/{id}")
    public ResponseEntity<Reviews> getReviewById(@PathVariable("id") Long id) {
        try {
            Reviews review = reviewsService.getReviewById(id);
            if (review != null) {
                return new ResponseEntity<>(review, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 4. Mettre à jour un avis par ID
    @PutMapping("/update/{id}")
    public ResponseEntity<Reviews> updateReview(@PathVariable("id") Long id, @RequestBody Reviews reviewDetails) {
        try {
            Reviews updatedReview = reviewsService.updateReview(id, reviewDetails);
            if (updatedReview != null) {
                return new ResponseEntity<>(updatedReview, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 5. Supprimer un avis par ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteReview(@PathVariable("id") Long id) {
        try {
            reviewsService.deleteReview(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
