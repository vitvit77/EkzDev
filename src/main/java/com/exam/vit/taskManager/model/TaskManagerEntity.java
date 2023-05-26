package com.exam.vit.taskManager.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity(name = "task_manager")
@Getter
@Setter
public class TaskManagerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false, unique = true)
    private long id;

    @Column(name="date_from", nullable = false)
    private Timestamp date_from;

    @Column(name="date_to", nullable = false)
    private Timestamp date_to;

    @Column(name="title", nullable = false)
    private String title;

    @Column(name="description")
    private String description;
}
