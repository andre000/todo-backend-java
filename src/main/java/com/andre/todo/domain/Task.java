package com.andre.todo.domain;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Represents a task in the todo application.
 *
 * <pre>
 *  Example:
 *  Task task = new Task();
 *  task.setTitle("New Task");
 *  task.setStatus(TaskStatus.PENDING);
 * </pre>
 */
@Data
@Entity @Table(name = "tasks")
public class Task {
  /**
   * The unique identifier of the task.
   */
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * The title of the task.
   */
  @Column(nullable = false, length = 255)
  private String title;

  /**
   * The status of the task.
   * <p>
   * This field is used to track the current status of the task, such as
   * "PENDING", "IN_PROGRESS", or "COMPLETED".
   * </p>
   */
  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private TaskStatus status;

  @CreationTimestamp @Column(nullable = false)
  private LocalDateTime createdAt;

  @UpdateTimestamp
  private LocalDateTime updatedAt;
}
