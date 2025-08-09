package com.andre.todo.repo;

import com.andre.todo.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing Task entities.
 *
 * <pre>
 *  Example:
 *
 *  &#064;Autowired
 *  private TaskRepository taskRepository;
 *
 *  Task task = new Task();
 *  task.setTitle("New Task");
 *  task.setStatus(TaskStatus.TODO);
 *  taskRepository.save(task);
 * </pre>
 */
public interface TaskRepository extends JpaRepository<Task, Long> {}
