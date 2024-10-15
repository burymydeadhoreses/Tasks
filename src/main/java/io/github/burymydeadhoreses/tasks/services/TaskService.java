package io.github.burymydeadhoreses.tasks.services;

import io.github.burymydeadhoreses.tasks.entities.Task;
import io.github.burymydeadhoreses.tasks.enums.Status;
import io.github.burymydeadhoreses.tasks.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void create(Task task) {
        taskRepository.save(task);
    }

    public void update(UUID id, Task task) {
        var searchResult = taskRepository.findById(id);

        if(searchResult.isEmpty())
            throw new RuntimeException("Task not found");

        var originalTask = searchResult.get();

        originalTask.setStatus(task.getStatus());

        taskRepository.save(originalTask);
    }

    public void delete(UUID id) {
        taskRepository.deleteById(id);
    }

    public Task get(UUID id) {
        var task = taskRepository.findById(id);
        if(task.isEmpty())
            throw new RuntimeException("Task not found");

        return task.get();
    }

    public List<Task> list() {
        return taskRepository.findAll();
    }

    public List<Task> listByStatus(Status status) {
        return taskRepository.findByStatus(status);
    }
}
