package com.example.demo.service;

import com.example.demo.model.Recommendations;
import com.example.demo.repository.RecommendationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RecommendationsService {
    @Autowired
    private RecommendationsRepository recommendationsRepository;

    public RecommendationsService() {
    }

    public List<Recommendations> getAllRecommendations() {
        return this.recommendationsRepository.findAll();
    }

    public Recommendations getRecommendationById(Long id) {
        return (Recommendations)this.recommendationsRepository.findById(id).orElse((Recommendations) null);
    }

    public List<Recommendations> getRecommendationsByUserId(Long userId) {
        return this.recommendationsRepository.findByUserId(userId);
    }

    public Recommendations createRecommendation(Recommendations recommendations) {
        return (Recommendations)this.recommendationsRepository.save(recommendations);
    }

    public void deleteRecommendation(Long id) {
        this.recommendationsRepository.deleteById(id);
    }

}