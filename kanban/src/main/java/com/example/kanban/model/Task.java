package com.example.kanban.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private LocalDateTime createdAt;
    private String status;
    private String priority;
    private LocalDateTime dueDate;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        if (status == null) status = "A Fazer";
    }


}
