package io.github.burymydeadhoreses.tasks.controllers;

import io.github.burymydeadhoreses.tasks.dtos.TaskDto;
import io.github.burymydeadhoreses.tasks.entities.Task;
import io.github.burymydeadhoreses.tasks.enums.Status;
import io.github.burymydeadhoreses.tasks.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController()
@RequestMapping("/tasks")
public class TaskController {
    TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping()
    public List<Task> list() {
        return taskService.list();
    }

    @GetMapping("/status/{status}")
    public List<Task> listByStatus(@PathVariable int status) {
        return taskService.listByStatus(Status.values()[status]);
    }

    @GetMapping("/{id}")
    public Task get(@PathVariable UUID id) {
        return taskService.get(id);
    }

    @PostMapping()
    public void create(@RequestBody TaskDto taskDto) {
        var task = new Task();
        task.setDescription(taskDto.getDescription());
        taskService.create(task);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable UUID id, @RequestBody TaskDto taskDto) {
        var task = new Task();
        task.setStatus(Status.values()[taskDto.getStatus()]);
        taskService.update(id, task);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        taskService.delete(id);
    }
}
