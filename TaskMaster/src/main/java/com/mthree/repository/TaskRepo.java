package com.mthree.repository;

import com.mthree.entity.Category;
import com.mthree.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepo extends JpaRepository<Task, Long> {

    //Find tasks by category method not included in JPA
    List<Task> findByCategory(Category category);
}
