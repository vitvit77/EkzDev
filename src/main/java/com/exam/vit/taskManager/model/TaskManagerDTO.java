package com.exam.vit.taskManager.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Setter
@Getter
public class TaskManagerDTO {
    private String title;
    private String description;
    private Timestamp date_from;
    private Timestamp date_to;
}
