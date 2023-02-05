package com.example.ToDoList.Controllers;

import com.example.ToDoList.Exceptions.NotFoundException;
import com.example.ToDoList.Models.Task;
import com.example.ToDoList.Services.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/task")

public class TaskController {

    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/taskById")
    public Optional<Task> getTaskById(@RequestParam Long id) throws NotFoundException {
        return taskService.getTaskById(id);
    }

    @GetMapping("/tasksByDeadline")
    public List<Task> getTasksByDeadline(@RequestParam Date deadline) {
        return taskService.getTasksByDeadline(deadline);
    }

    @PostMapping()
    public Task createTask(@RequestBody Task newTask) {
        return taskService.createTask(newTask);
    }

    @PutMapping()
    public Task updateTask(@RequestBody Task newTask) throws NotFoundException {
        return taskService.updateTask(newTask);
    }

    @DeleteMapping()
    public void deleteTask(Long id) throws NotFoundException {
        taskService.deleteTask(id);
    }
}
