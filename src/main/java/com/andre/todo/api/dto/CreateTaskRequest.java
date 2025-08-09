package com.andre.todo.api.dto;

import com.andre.todo.domain.TaskStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO for creating a new task.
 *
 * <pre>
 *  Example:
 *
 *  &#064;Autowired
 *  private TaskService taskService;
 *
 *  CreateTaskRequest request = new CreateTaskRequest("New Task", TaskStatus.TODO);
 *  taskService.createTask(request);
 * </pre>
 */
public record CreateTaskRequest(
  @NotBlank @Size(max = 255) String title,
  TaskStatus status) {
}
