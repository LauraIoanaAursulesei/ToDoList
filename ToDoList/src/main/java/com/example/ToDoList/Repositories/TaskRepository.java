package com.example.ToDoList.Repositories;

import com.example.ToDoList.Models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {

    Optional<Task> findById(Long id);
    List<Task> getAllByDeadline(Date deadline);
}
