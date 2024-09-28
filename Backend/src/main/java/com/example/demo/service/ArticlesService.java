package com.example.demo.service;

import com.example.demo.model.Articles;
import com.example.demo.repository.ArticlesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticlesService {
    @Autowired
    private ArticlesRepository articlesRepository;

    public ArticlesService() {
    }

    public List<Articles> getAllArticles() {
        return this.articlesRepository.findAll();
    }

    public Articles getArticleById(Long id) {
        return (Articles)this.articlesRepository.findById(id).orElse((Articles) null);
    }

    public Articles createArticle(Articles articles) {
        return (Articles)this.articlesRepository.save(articles);
    }

    public void deleteArticle(Long id) {
        this.articlesRepository.deleteById(id);
    }

    public Articles updateArticle(Long id, Articles articles) {
        Articles article = (Articles)this.articlesRepository.findById(id).orElse((Articles) null);
        if (article != null) {
            article.setName(articles.getName());
            article.setCategory(articles.getCategory());
            article.setPrice(articles.getPrice());
            article.setYear(articles.getYear());
            article.setStock(articles.getStock());
            article.setAvailable(articles.isAvailable());
            article.setDescription(articles.getDescription());
            article.setImageUrl(articles.getImageUrl());
            return (Articles)this.articlesRepository.save(article);
        } else {
            return (Articles) null;
        }
    }
}