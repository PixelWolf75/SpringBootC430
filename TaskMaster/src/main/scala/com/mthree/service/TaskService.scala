package com.mthree.service

import com.mthree.entity.{Category, Task}
import com.mthree.repository.{CategoryRepo, TaskRepo}
import com.mthree.exception._
import org.springframework.stereotype.Service

import scala.jdk.CollectionConverters._

@Service
class TaskService(
                   private val taskRepository: TaskRepo,
                   private val categoryRepository: CategoryRepo
                 ) {

  def createTask(task: Task, categoryId: Long): Task = {

    if (task.getTitle == null || task.getTitle.trim.isEmpty)
      throw new BadRequestException("Task title cannot be empty")

    if (task.getDueDate == null)
      throw new InvalidDataEnteredException("Due date cannot be null")

    val category = categoryRepository.findById(categoryId).orElseThrow(
      () => new ResourceNotFoundException(s"Category with ID $categoryId not found")
    )

    task.setCategory(category)
    taskRepository.save(task)
  }

  //changed this to return java list as I was getting errors in Postman as controller expects java list
  def getAllTasks(): java.util.List[Task] = {
    taskRepository.findAll()
  }

  def getTaskById(id: Long): Task = {
    taskRepository.findById(id).orElseThrow(
      () => new ResourceNotFoundException(s"Task with ID $id not found")
    )
  }

  //changed this to return java list as I was getting errors in Postman as controller expects java list
  def getTasksByCategory(categoryId: Long): java.util.List[Task] = {
    val category = categoryRepository.findById(categoryId).orElseThrow(
      () => new ResourceNotFoundException(s"Category with ID $categoryId not found")
    )

    category.getTasks
  }

  def updateTask(id: Long, updatedTask: Task): Task = {
    val existingTask = getTaskById(id)

    if (updatedTask.getTitle == null || updatedTask.getTitle.trim.isEmpty)
      throw new BadRequestException("Task title cannot be empty")

    if (updatedTask.getDueDate == null)
      throw new InvalidDataEnteredException("Due date cannot be null")

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
