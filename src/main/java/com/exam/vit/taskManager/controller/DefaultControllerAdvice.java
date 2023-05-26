package com.exam.vit.taskManager.controller;

import com.exam.vit.taskManager.exception.TaskManagerDateFromAndDateToErrorException;
import com.exam.vit.taskManager.exception.TaskManagerExistsTasksByDateRangeException;
import com.exam.vit.taskManager.exception.TaskManagerTaskNotFoundException;
import com.exam.vit.taskManager.model.Response;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;

@ControllerAdvice
public class DefaultControllerAdvice {
    @ExceptionHandler({SQLException.class})
    public @ResponseBody Response sqlError() {
        Response response = new Response("SQL Exception", 500);
        return response;
    }

    @ExceptionHandler({TaskManagerTaskNotFoundException.class})
    public @ResponseBody Response notFoundDataOnDate() {
        Response response = new Response("Task not found by id", 422);
        return response;
    }

    @ExceptionHandler({TaskManagerDateFromAndDateToErrorException.class})
    public @ResponseBody Response dateRangeIsInvalid() {
        Response response = new Response("Date from and date to - incorrect", 422);
        return response;
    }
    @ExceptionHandler({TaskManagerExistsTasksByDateRangeException.class})
    public @ResponseBody Response taskExistsByDateRange() {
        Response response = new Response("The assignment already exists for this date time", 422);
        return response;
    }

    @ExceptionHandler({Exception.class})
    public @ResponseBody Response errorDB() {
        Response response = new Response("Unknown Exception", 500);
        return response;
    }
}
