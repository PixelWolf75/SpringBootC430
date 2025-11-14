package com.mthree.service

import com.mthree.entity.Category
import com.mthree.repository.CategoryRepo
import com.mthree.exception._
import org.springframework.stereotype.Service


import scala.jdk.CollectionConverters._

@Service
class CategoryService(private val categoryRepository: CategoryRepo) {

  def createCategory(name: String): Category = {
    if (name == null || name.trim.isEmpty)
      throw new BadRequestException("Category name cannot be empty")

    if (!name.matches("^[A-Za-z0-9 ]+$"))
      throw new InvalidDataEnteredException("Category name contains invalid characters")

    // require repo method: findByName(String name)
    categoryRepository.findByName(name).ifPresent(_ =>
      throw new ResourceAlreadyExistsException(s"Category '$name' already exists")
    )

    val category = new Category()
    category.setName(name)
    categoryRepository.save(category)
  }

  //changed this to return java list as I was getting errors in Postman as controller expects java list
  def getAllCategories: java.util.List[Category] = {
    categoryRepository.findAll()
  }

  def getCategoryById(id: Long): Category = {
    categoryRepository.findById(id).orElseThrow(
      () => new ResourceNotFoundException(s"Category with ID $id not found")
    )
  }

  def updateCategory(id: Long, newName: String): Category = {
    if (newName == null || newName.trim.isEmpty)
      throw new BadRequestException("Category name cannot be empty")

    val category = getCategoryById(id)
    category.setName(newName)
    categoryRepository.save(category)
  }

  def deleteCategory(id: Long): Unit = {
    val category = getCategoryById(id)

    if (category.getTasks != null && !category.getTasks.isEmpty)
      throw new IllegalStateException("Cannot delete category with existing tasks")

    categoryRepository.delete(category)
  }
}
