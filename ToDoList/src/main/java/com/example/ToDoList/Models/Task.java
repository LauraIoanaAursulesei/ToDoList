package com.example.ToDoList.Models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "Tasks")

public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "subject")
    private String subject;

    @Column(name = "deadline")
    private Date deadline;

    @Column(name = "status")
    private Boolean status;

    @ManyToOne(cascade = {CascadeType.MERGE})
    private User user;
}
