package com.example.demo.controller;
import com.example.demo.model.Articles;
import com.example.demo.service.ArticlesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/articles")
public class ArticlesController {

    @Autowired
    private ArticlesService articlesService;

    // 1. Ajouter un article
    @PostMapping("/add")
    public ResponseEntity<Articles> addArticle(@RequestBody Articles article) {
        try {
            Articles newArticle = articlesService.createArticle(article);
            return new ResponseEntity<>(newArticle, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 2. Obtenir la liste de tous les articles
    @GetMapping("")
    public ResponseEntity<List<Articles>> getAllArticles() {
        try {
            List<Articles> articles = articlesService.getAllArticles();
            if (articles.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(articles, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 3. Obtenir un article par ID
    @GetMapping("/{id}")
    public ResponseEntity<Articles> getArticleById(@PathVariable("id") Long id) {
        try {
            Articles article = articlesService.getArticleById(id);
            if (article != null) {
                return new ResponseEntity<>(article, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 4. Mettre à jour un article par ID
    @PutMapping("/update/{id}")
    public ResponseEntity<Articles> updateArticle(@PathVariable("id") Long id, @RequestBody Articles articleDetails) {
        try {
            Articles updatedArticle = articlesService.updateArticle(id, articleDetails);
            return new ResponseEntity<>(updatedArticle, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 5. Supprimer un article par ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteArticle(@PathVariable("id") Long id) {
        try {
            articlesService.deleteArticle(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<List<Articles>> getArticlesByCategory(@PathVariable("id") Long id) {
        try {
            List<Articles> articles = articlesService.getArticlesByCategory(id);
            if (articles.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(articles, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
