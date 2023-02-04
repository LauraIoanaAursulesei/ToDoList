package com.example.ToDoList.Services;

import com.example.ToDoList.Repositories.TaskRepository;
import org.springframework.stereotype.Service;

@Service

public class TaskService {

    TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
}
