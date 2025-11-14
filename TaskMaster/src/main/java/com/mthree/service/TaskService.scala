package com.mthree.service

import com.mthree.entity.{Category, Task}
import com.mthree.repository.{CategoryRepository, TaskRepository}
import com.mthree.exception.ResourceNotFoundException
import org.springframework.stereotype.Service

import scala.jdk.CollectionConverters._

@Service
class TaskService(
                   private val taskRepository: TaskRepository,
                   private val categoryRepository: CategoryRepository
                 ) {

  def createTask(task: Task, categoryId: Long): Task = {
    val category = categoryRepository.findById(categoryId).orElseThrow(
      () => new ResourceNotFoundException(s"Category with ID $categoryId not found")
    )

    task.setCategory(category)
    taskRepository.save(task)
  }

  def getAllTasks(): List[Task] = {
    taskRepository.findAll().asScala.toList
  }

  def getTaskById(id: Long): Task = {
    taskRepository.findById(id).orElseThrow(
      () => new ResourceNotFoundException(s"Task with ID $id not found")
    )
  }

  def getTasksByCategory(categoryId: Long): List[Task] = {
    val category = categoryRepository.findById(categoryId).orElseThrow(
      () => new ResourceNotFoundException(s"Category with ID $categoryId not found")
    )

    category.getTasks.asScala.toList
  }

  def updateTask(id: Long, updatedTask: Task): Task = {
    val existingTask = getTaskById(id)

    existingTask.setTitle(updatedTask.getTitle)
    existingTask.setDescription(updatedTask.getDescription)
    existingTask.setDueDate(updatedTask.getDueDate)
    existingTask.setPriority(updatedTask.getPriority)
    existingTask.setCompleted(updatedTask.isCompleted)

    taskRepository.save(existingTask)
  }

  def deleteTask(id: Long): Unit = {
    val task = getTaskById(id)
    taskRepository.delete(task)
  }
}

