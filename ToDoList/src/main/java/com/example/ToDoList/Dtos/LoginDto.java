package com.example.ToDoList.Dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class LoginDto {
    private String username;
    private String password;
}
