package com.example.demo.controller;

import com.example.demo.model.Recommendations;
import com.example.demo.service.RecommendationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recommendations")
public class RecommendationsController {

    @Autowired
    private RecommendationsService recommendationsService;

    // 1. Ajouter une recommandation
    @PostMapping("/add")
    public ResponseEntity<Recommendations> addRecommendation(@RequestBody Recommendations recommendation) {
        try {
            Recommendations newRecommendation = recommendationsService.createRecommendation(recommendation);
            return new ResponseEntity<>(newRecommendation, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 2. Obtenir la liste de toutes les recommandations
    @GetMapping("")
    public ResponseEntity<List<Recommendations>> getAllRecommendations() {
        try {
            List<Recommendations> recommendations = recommendationsService.getAllRecommendations();
            if (recommendations.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(recommendations, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 3. Obtenir une recommandation par ID
    @GetMapping("/{id}")
    public ResponseEntity<Recommendations> getRecommendationById(@PathVariable("id") Long id) {
        try {
            Recommendations recommendation = recommendationsService.getRecommendationById(id);
            if (recommendation != null) {
                return new ResponseEntity<>(recommendation, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    // 4. Mettre à jour une recommandation par ID
//    @PutMapping("/update/{id}")
//    public ResponseEntity<Recommendations> updateRecommendation(@PathVariable("id") Long id, @RequestBody Recommendations recommendationDetails) {
//        try {
//
//            return new ResponseEntity<>(updatedRecommendation, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    // 5. Supprimer une recommandation par ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteRecommendation(@PathVariable("id") Long id) {
        try {
            recommendationsService.deleteRecommendation(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
