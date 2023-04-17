package com.example.ToDoList.Dtos;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class TaskListDto {
    private Date deadline;
    private String subject;
    private Boolean status;
}
