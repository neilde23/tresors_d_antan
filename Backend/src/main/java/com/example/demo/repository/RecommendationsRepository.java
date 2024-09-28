package com.example.demo.repository;

import com.example.demo.model.Recommendations;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecommendationsRepository extends JpaRepository<Recommendations, Long> {

    List<Recommendations> findByUserId(Long userId);
}