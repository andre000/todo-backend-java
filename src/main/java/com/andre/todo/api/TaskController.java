package com.andre.todo.api;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andre.todo.api.dto.CreateTaskRequest;
import com.andre.todo.api.dto.TaskResponse;
import com.andre.todo.domain.Task;
import com.andre.todo.domain.TaskStatus;
import com.andre.todo.repo.TaskRepository;
import com.andre.todo.service.TaskService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

/**
 * Controller for managing tasks.
 */
@Slf4j
@RestController
@RequestMapping("/api/tasks")
public class TaskController {
  private final TaskService service;

  public TaskController(TaskService service) { this.service = service; }

  /**
   * List all tasks.
   * <pre>
   * GET /api/tasks
   * </pre>
   *
   * @return a list of tasks
   */
  @GetMapping
  public List<TaskResponse> list() {
    log.info(":: Listing all tasks");
    return service.listAll().stream()
      .map(TaskResponse::from)
      .toList();
  }

  /**
   * Create a new task.
   * <pre>
   * POST /api/tasks
   * </pre>
   *
   * @param req the request body
   * @return the created task
   */
  @PostMapping
  public ResponseEntity<TaskResponse> create(@Valid @RequestBody CreateTaskRequest req) {
    Task t = new Task();
    t.setTitle(req.title());
    t.setStatus(Optional.ofNullable(req.status()).orElse(TaskStatus.TODO));
    log.info(":: Creating task: {}", t);

    Task saved = service.create(t.getTitle(), t.getStatus());
    return ResponseEntity.created(URI.create("api/tasks/" + saved.getId())).body(TaskResponse.from(saved));
  }

  /**
   * Delete a task by ID.
   * <pre>
   * DELETE /api/tasks/{id}
   * </pre>
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    log.info(":: Deleting task: {}", id);
    if (!service.delete(id)) {
      log.info(":: Task not found: {}", id);
      return ResponseEntity.notFound().build();
    }
    log.info(":: Task deleted: {}", id);
    return ResponseEntity.noContent().build();
  }
}
