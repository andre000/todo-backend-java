package com.andre.todo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.andre.todo.domain.Task;
import com.andre.todo.domain.TaskStatus;
import com.andre.todo.repo.TaskRepository;

@Service
public class TaskService {
  private final TaskRepository repo;

  public TaskService(TaskRepository repo) {
    this.repo = repo;
  }

  public List<Task> listAll() {
    return repo.findAll();
  }

  public Task create(String title, TaskStatus status) {
    Task t = new Task();
    t.setTitle(title);
    t.setStatus(status != null ? status : TaskStatus.TODO);
    return repo.save(t);
  }

  public boolean delete(Long id) {
    if (!repo.existsById(id)) {
      return false;
    }
    repo.deleteById(id);
    return true;
  }
}
