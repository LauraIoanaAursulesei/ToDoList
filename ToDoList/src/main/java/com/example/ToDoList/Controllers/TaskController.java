package com.example.ToDoList.Controllers;

import com.example.ToDoList.Dtos.SearchResultDto;
import com.example.ToDoList.Dtos.TaskListDto;
import com.example.ToDoList.Exceptions.NotFoundException;
import com.example.ToDoList.Models.Task;
import com.example.ToDoList.Services.TaskService;
import org.springframework.format.annotation.DateTimeFormat;
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

    @GetMapping("/taskListByUsername")
    public List<TaskListDto> getTaskList(@RequestParam String username) {
        return taskService.getTaskList(username);
    }

    @GetMapping("/unfinishedTaskListByUsername")
    public List<TaskListDto> getUnfinishedTaskList(@RequestParam String username) {
        return taskService.getUnfinishedTaskList(username);
    }

    @GetMapping("/allTasksByUsername")
    public List<SearchResultDto> getAllTasksByUsername(@RequestParam String username) {
        return taskService.getAllTasksByUsername(username);
    }

    @GetMapping("/allTasksBySubject")
    public List<SearchResultDto> getAllTasksBySubject(@RequestParam String subject) {
        return taskService.getAllTasksBySubject(subject);
    }

    @GetMapping("/allTasksByDeadline")
    public List<SearchResultDto> getAllTasksByDeadline(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date deadline) {
        return taskService.getAllTasksByDeadline(deadline);
    }

    @GetMapping("/allTasksByStatus")
    public List<SearchResultDto> getAllTasksByStatus(@RequestParam Boolean status) {
        return taskService.getAllTasksByStatus(status);
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
