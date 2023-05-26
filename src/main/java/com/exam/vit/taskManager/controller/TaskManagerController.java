package com.exam.vit.taskManager.controller;

import com.exam.vit.taskManager.exception.TaskManagerDateFromAndDateToErrorException;
import com.exam.vit.taskManager.exception.TaskManagerExistsTasksByDateRangeException;
import com.exam.vit.taskManager.exception.TaskManagerTaskNotFoundException;
import com.exam.vit.taskManager.model.SuccessDTOResponse;
import com.exam.vit.taskManager.model.TaskManagerDTO;
import com.exam.vit.taskManager.model.TaskManagerEntity;
import com.exam.vit.taskManager.service.TaskManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TaskManagerController {
    @Autowired
    private TaskManagerService taskManagerService;

    @PostMapping("/task-manager")
    public @ResponseBody TaskManagerEntity createDocument(@RequestBody TaskManagerDTO taskManagerDTO) throws TaskManagerDateFromAndDateToErrorException, TaskManagerExistsTasksByDateRangeException {
        if (taskManagerService.checkDateRange(taskManagerDTO.getDate_from(), taskManagerDTO.getDate_to()))
            throw new TaskManagerDateFromAndDateToErrorException();
        if (taskManagerService.checkTasksWithDateIntersection(taskManagerDTO.getDate_from(), taskManagerDTO.getDate_to()))
            throw new TaskManagerExistsTasksByDateRangeException();
        return taskManagerService.createTask(taskManagerDTO);
    }

    @GetMapping("/task-manager")
    public @ResponseBody List<TaskManagerEntity> getAllTasks() {
        return taskManagerService.getAllTasks();
    }

    @DeleteMapping("/task-manager/{id}")
    public SuccessDTOResponse deleteTask(@PathVariable Long id) throws TaskManagerTaskNotFoundException {
        if (taskManagerService.findById(id)==null)
            throw new TaskManagerTaskNotFoundException(id);
        taskManagerService.deleteTask(id);
        return new SuccessDTOResponse(true);
    }

    @PutMapping("/task-manager/{id}")
    public @ResponseBody TaskManagerEntity updateTask(@PathVariable Long id, @RequestBody TaskManagerDTO taskManagerDTO) throws TaskManagerTaskNotFoundException, TaskManagerDateFromAndDateToErrorException, TaskManagerExistsTasksByDateRangeException {
        if (taskManagerService.findById(id)==null)
            throw new TaskManagerTaskNotFoundException(id);
        if (taskManagerService.checkDateRange(taskManagerDTO.getDate_from(), taskManagerDTO.getDate_to()))
            throw new TaskManagerDateFromAndDateToErrorException();
        if (taskManagerService.checkTasksWithDateIntersection(taskManagerDTO.getDate_from(), taskManagerDTO.getDate_to()))
            throw new TaskManagerExistsTasksByDateRangeException();
        return taskManagerService.updateTask(id, taskManagerDTO);
    }
}
