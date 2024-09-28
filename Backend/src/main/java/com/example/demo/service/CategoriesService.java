package com.example.demo.service;

import com.example.demo.model.Categories;
import com.example.demo.repository.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriesService {
    @Autowired
    private CategoriesRepository categoriesRepository;

    public CategoriesService() {
    }

    public List<Categories> getAllCategories() {
        return this.categoriesRepository.findAll();
    }

    public Categories getCategoryById(Long id) {
        return (Categories)this.categoriesRepository.findById(id).orElse((Categories) null);
    }

    public Categories createCategory(Categories categories) {
        return (Categories)this.categoriesRepository.save(categories);
    }

    public void deleteCategory(Long id) {
        this.categoriesRepository.deleteById(id);
    }

    public Categories updateCategory(Long id, Categories categories) {
        Categories category = (Categories)this.categoriesRepository.findById(id).orElse((Categories) null);
        if (category != null) {
            category.setName(categories.getName());
            return (Categories)this.categoriesRepository.save(category);
        } else {
            return (Categories) null;
        }
    }
}