package com.mthree.controller;

import com.mthree.entity.Category;
import com.mthree.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import com.mthree.service.CategoryService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping("/categories")
    public ResponseEntity<Void> addCategory(@RequestBody  Category category){
        categoryService.createCategory(category.getName());
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getCategories(){
        List<Category> categories = (List<Category>) categoryService.getAllCategories();
        return ResponseEntity.status(HttpStatus.OK).body(categories);
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("id") Long id){
        Category category = categoryService.getCategoryById(id);
        return new ResponseEntity<Category>(category, HttpStatus.OK);
    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable("id") Long id, @RequestBody Category category){
        categoryService.updateCategory(id, category.getName());
        return new ResponseEntity<Category>(category, HttpStatus.OK);
    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable("id") Long id){
        categoryService.deleteCategory(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}
