package com.mthree.service;

import com.mthree.entity.Category;
import com.mthree.exception.BadRequestException;
import com.mthree.exception.InvalidDataEnteredException;
import com.mthree.exception.ResourceAlreadyExistsException;
import com.mthree.exception.ResourceNotFoundException;
import com.mthree.repository.CategoryRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryServiceTest {

    private CategoryRepo categoryRepo;
    private CategoryService categoryService;

    @BeforeEach
    void setup() {
        categoryRepo = Mockito.mock(CategoryRepo.class);
        categoryService = new CategoryService(categoryRepo);
    }

    @Test
    void testCreateCategory() {
        Category saved = new Category();
        saved.setName("Work");

        when(categoryRepo.save(any(Category.class))).thenReturn(saved);

        Category result = categoryService.createCategory("Work");
        assertEquals("Work", result.getName());
    }

    @Test
    void testGetCategoryById() {
        Category c = new Category();
        c.setId(1L);
        c.setName("Personal");

        when(categoryRepo.findById(1L)).thenReturn(Optional.of(c));

        Category result = categoryService.getCategoryById(1L);
        assertEquals("Personal", result.getName());
    }

    @Test
    void testGetCategoryByIdNotFound() {
        when(categoryRepo.findById(99L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> categoryService.getCategoryById(99L));
    }

    @Test
    void testCreateCategory_EmptyName() {
        assertThrows(BadRequestException.class, () -> categoryService.createCategory(""));
    }


    @Test
    void testCreateCategory_InvalidCharacters() {
        assertThrows(InvalidDataEnteredException.class, () -> categoryService.createCategory("##$$"));
    }

    @Test
    void testCreateCategory_AlreadyExists() {
        Category c = new Category();
        c.setName("Work");

        when(categoryRepo.findByName("Work")).thenReturn(Optional.of(c));

        assertThrows(ResourceAlreadyExistsException.class,
                () -> categoryService.createCategory("Work"));
    }


}
