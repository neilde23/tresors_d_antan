package com.example.demo.controller;

import com.example.demo.model.Categories;
import com.example.demo.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoriesController {

    @Autowired
    private CategoriesService categoriesService;

    // 1. Ajouter une catégorie
    @PostMapping("/add")
    public ResponseEntity<Categories> addCategory(@RequestBody Categories category) {
        try {
            Categories newCategory = categoriesService.createCategory(category);
            return new ResponseEntity<>(newCategory, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 2. Obtenir la liste de toutes les catégories
    @GetMapping("")
    public ResponseEntity<List<Categories>> getAllCategories() {
        try {
            List<Categories> categories = categoriesService.getAllCategories();
            if (categories.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(categories, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 3. Obtenir une catégorie par ID
    @GetMapping("/{id}")
    public ResponseEntity<Categories> getCategoryById(@PathVariable("id") Long id) {
        try {
            Categories category = categoriesService.getCategoryById(id);
            if (category != null) {
                return new ResponseEntity<>(category, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 4. Mettre à jour une catégorie par ID
    @PutMapping("/update/{id}")
    public ResponseEntity<Categories> updateCategory(@PathVariable("id") Long id, @RequestBody Categories categoryDetails) {
        try {
            Categories updatedCategory = categoriesService.updateCategory(id, categoryDetails);
            return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 5. Supprimer une catégorie par ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteCategory(@PathVariable("id") Long id) {
        try {
            categoriesService.deleteCategory(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
