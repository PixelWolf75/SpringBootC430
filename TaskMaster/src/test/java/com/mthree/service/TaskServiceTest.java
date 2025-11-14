package com.mthree.service;

import com.mthree.entity.Category;
import com.mthree.entity.Task;
import com.mthree.exception.ResourceNotFoundException;
import com.mthree.repository.CategoryRepo;
import com.mthree.repository.TaskRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskServiceTest {

    private TaskRepo taskRepo;
    private CategoryRepo categoryRepo;
    private TaskService taskService;

    @BeforeEach
    void setup() {
        taskRepo = Mockito.mock(TaskRepo.class);
        categoryRepo = Mockito.mock(CategoryRepo.class);
        taskService = new TaskService(taskRepo, categoryRepo);
    }

    @Test
    void testCreateTask() {
        Task t = new Task();
        t.setTitle("Finish project");

        Category c = new Category();
        c.setId(1L);

        when(categoryRepo.findById(1L)).thenReturn(Optional.of(c));
        when(taskRepo.save(any(Task.class))).thenReturn(t);

        Task result = taskService.createTask(t, 1L);
        assertEquals("Finish project", result.getTitle());
    }

    @Test
    void testGetTaskById() {
        Task t = new Task();
        t.setId(2L);
        t.setTitle("Clean room");

        when(taskRepo.findById(2L)).thenReturn(Optional.of(t));

        Task result = taskService.getTaskById(2L);
        assertEquals("Clean room", result.getTitle());
    }

    @Test
    void testGetTaskByIdNotFound() {
        when(taskRepo.findById(99L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> taskService.getTaskById(99L));
    }
}
