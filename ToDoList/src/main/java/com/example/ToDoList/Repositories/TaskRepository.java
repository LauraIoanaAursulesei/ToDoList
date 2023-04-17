package com.example.ToDoList.Repositories;

import com.example.ToDoList.Dtos.TaskListDto;
import com.example.ToDoList.Models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {

    Optional<Task> findById(Long id);

    List<Task> getAllByDeadline(Date deadline);

    @Query("SELECT new com.example.ToDoList.Dtos.TaskListDto(t.deadline, t.subject, t.status ) FROM Task t JOIN User u ON t.user.id = u.id WHERE u.username=:givenUsername ORDER BY t.deadline")
    List<TaskListDto> findTaskListByUserUsername(@Param("givenUsername") String givenUsername);

    @Query("SELECT new com.example.ToDoList.Dtos.TaskListDto(t.deadline, t.subject, t.status ) FROM Task t JOIN User u ON t.user.id = u.id WHERE u.username=:givenUsername AND t.status=false ORDER BY t.deadline")
    List<TaskListDto> findUnfinishedTaskListByUserUsername(@Param("givenUsername") String givenUsername);

}