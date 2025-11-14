package com.mthree.service

import com.mthree.entity.Category
import com.mthree.repository.CategoryRepository
import com.mthree.exception.ResourceNotFoundException
import org.springframework.stereotype.Service

import java.util.Optional
import scala.jdk.CollectionConverters._

@Service
class CategoryService(private val categoryRepository: CategoryRepository) {

  def createCategory(name: String): Category = {
    val category = new Category()
    category.setName(name)
    categoryRepository.save(category)
  }

  def getAllCategories(): List[Category] = {
    categoryRepository.findAll().asScala.toList
  }

  def getCategoryById(id: Long): Category = {
    categoryRepository.findById(id).orElseThrow(
      () => new ResourceNotFoundException(s"Category with ID $id not found")
    )
  }

  def updateCategory(id: Long, newName: String): Category = {
    val category = getCategoryById(id)
    category.setName(newName)
    categoryRepository.save(category)
  }

  def deleteCategory(id: Long): Unit = {
    val category = getCategoryById(id)

    // Bonus requirement: prevent deleting if tasks exist
    if (category.getTasks != null && !category.getTasks.isEmpty) {
      throw new IllegalStateException("Cannot delete category with existing tasks")
    }

    categoryRepository.delete(category)
  }
}

