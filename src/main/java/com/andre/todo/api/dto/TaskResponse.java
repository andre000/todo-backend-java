package com.andre.todo.api.dto;

import java.time.LocalDateTime;

import com.andre.todo.domain.Task;
import com.andre.todo.domain.TaskStatus;

public record TaskResponse(
  Long id,
  String title,
  TaskStatus status,
  LocalDateTime createdAt,
  LocalDateTime updatedAt
) {
  /**
   * Constructs a TaskResponse from a Task entity.
   *
   * @param task the Task entity
   * @return a TaskResponse instance
   */
  public static TaskResponse from(Task task) {
    return new TaskResponse(
      task.getId(),
      task.getTitle(),
      task.getStatus(),
      task.getCreatedAt(),
      task.getUpdatedAt()
    );
  }
}
