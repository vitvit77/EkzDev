package com.exam.vit.taskManager.exception;

public class TaskManagerTaskNotFoundException extends Exception{
    public TaskManagerTaskNotFoundException(long id) {
        super(String.valueOf(id));
    }
}
