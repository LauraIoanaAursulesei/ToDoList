package com.example.ToDoList.Services;

import com.example.ToDoList.Dtos.SearchResultDto;
import com.example.ToDoList.Dtos.TaskListDto;
import com.example.ToDoList.Exceptions.NotFoundException;
import com.example.ToDoList.Models.Task;
import com.example.ToDoList.Repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service

public class TaskService {

    TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Optional<Task> getTaskById(Long id) throws NotFoundException {
        Optional<Task> task;
        if (taskRepository.findById(id).isEmpty())
            throw new NotFoundException("Id not found");
        else
            task = taskRepository.findById(id);
        return task;
    }

    public List<Task> getTasksByDeadline(Date deadline) {
        return taskRepository.getAllByDeadline(deadline);
    }

    public Task createTask(Task newTask) {
        Task taskToBeSaved = Task.builder()
                .subject(newTask.getSubject())
                .deadline(newTask.getDeadline())
                .status(newTask.getStatus())
                .build();
        taskRepository.save(taskToBeSaved);
        return taskToBeSaved;
    }

    public Task updateTask(Task newTask) throws NotFoundException {
        Task task = taskRepository.findById(newTask.getId()).orElseThrow(() -> new NotFoundException("Id not found"));

        if (newTask.getSubject() != null)
            task.setSubject(newTask.getSubject());
        if (newTask.getDeadline() != null)
            task.setDeadline(newTask.getDeadline());
        if (newTask.getStatus() != null)
            task.setStatus(newTask.getStatus());

        return taskRepository.save(task);
    }

    public void deleteTask(Long id) throws NotFoundException {
        Task task = taskRepository.findById(id).orElseThrow(() -> new NotFoundException("Id not found"));
        taskRepository.delete(task);
    }

    public List<TaskListDto> getTaskList(String username) {
        return taskRepository.findTaskListByUserUsername(username);
    }

    public List<TaskListDto> getUnfinishedTaskList(String username) {
        return taskRepository.findUnfinishedTaskListByUserUsername(username);
    }

    public List<SearchResultDto> getAllTasksByUsername(String username) {
        return taskRepository.findAllTasksByUsername(username);
    }

    public List<SearchResultDto> getAllTasksBySubject(String subject) {
        return taskRepository.findAllTasksBySubject(subject);
    }

    public List<SearchResultDto> getAllTasksByDeadline(Date deadline) {
        return taskRepository.findAllTasksByDeadline(deadline);
    }

    public List<SearchResultDto> getAllTasksByStatus(Boolean status) {
        return taskRepository.findAllTasksByStatus(status);
    }



}
