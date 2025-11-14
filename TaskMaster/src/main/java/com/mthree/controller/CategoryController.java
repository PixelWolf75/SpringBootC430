package com.mthree.controller;

import com.mthree.entity.Category;
import com.mthree.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class CategoryController {

    @Autowired
    CategoryRepo categoryRepo;

    @PostMapping("/categories")
    public String addCategory(Category category){
        return "redirect:/";
    }

    @GetMapping("/categories")
    public String getCategories(){
        return "redirect:/";
    }

    @GetMapping("/categories/{id}")
    public String getCategoryById(@PathVariable("id") Long id){
        return "redirect:/";
    }

    @PutMapping("/categories/{id}")
    public String updateCategory(@PathVariable("id") Long id){
        return "redirect:/";
    }

    @DeleteMapping("/category/{id}")
    public String deleteCategory(@PathVariable("id") Long id){
        return "redirect:/";
    }
}
