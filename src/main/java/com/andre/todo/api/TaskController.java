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

import jakarta.validation.Valid;

/**
 * Controller for managing tasks.
 */
@RestController
@RequestMapping("/api/tasks")
public class TaskController {
  private final TaskRepository repo;
  public TaskController(TaskRepository repo) { this.repo = repo; }

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
    return repo.findAll().stream()
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

    Task saved = repo.save(t);
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
    if (!repo.existsById(id)) {
      return ResponseEntity.notFound().build();
    }
    repo.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
