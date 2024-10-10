package com.example.demo.repository;

import com.example.demo.model.Articles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticlesRepository extends JpaRepository<Articles, Long> {
    List<Articles> findByCategoryId(Long categoryId);
}