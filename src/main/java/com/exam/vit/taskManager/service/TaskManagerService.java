package com.exam.vit.taskManager.service;

import com.exam.vit.taskManager.model.TaskManagerDTO;
import com.exam.vit.taskManager.model.TaskManagerEntity;
import com.exam.vit.taskManager.repository.TaskManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class TaskManagerService {
    @Autowired
    private TaskManagerRepository taskManagerRepository;

    /**
     *
     * @param taskManagerDTO
     * @return
     */
    public TaskManagerEntity createTask(TaskManagerDTO taskManagerDTO) {
        TaskManagerEntity taskManagerEntity = new TaskManagerEntity();
        taskManagerEntity.setDate_from(taskManagerDTO.getDate_from());
        taskManagerEntity.setDate_to(taskManagerDTO.getDate_to());
        taskManagerEntity.setTitle(taskManagerDTO.getTitle());
        taskManagerEntity.setDescription(taskManagerDTO.getDescription());
        return taskManagerRepository.save(taskManagerEntity);
    }

    /**
     *
     * @return
     */
    public List<TaskManagerEntity> getAllTasks() {
        return taskManagerRepository.findAll();
    }

    /**
     *
     * @param id
     * @return
     */
    public void deleteTask(Long id) {
        taskManagerRepository.deleteById(id);
    }

    /**
     *
     * @param id
     * @param taskManagerDTO
     * @return
     */
    public TaskManagerEntity updateTask(Long id, TaskManagerDTO taskManagerDTO) {
        Optional<TaskManagerEntity> optionalTaskManagerEntity = Optional.ofNullable(this.findById(id));
        if (optionalTaskManagerEntity.isPresent()) {
            TaskManagerEntity taskManagerEntity = optionalTaskManagerEntity.get();
            taskManagerEntity.setDate_from(taskManagerDTO.getDate_from());
            taskManagerEntity.setDate_to(taskManagerDTO.getDate_to());
            taskManagerEntity.setTitle(taskManagerDTO.getTitle());
            taskManagerEntity.setDescription(taskManagerDTO.getDescription());
            return taskManagerRepository.save(taskManagerEntity);
        }
        return null;
    }

    /**
     *
     * @param id
     * @return
     */
    public TaskManagerEntity findById(long id) {
        Optional<TaskManagerEntity> optionalPGDocumentsEntity =  taskManagerRepository.findById(id);
        if (optionalPGDocumentsEntity.isPresent()) {
            TaskManagerEntity taskManagerEntity = optionalPGDocumentsEntity.get();
            return taskManagerEntity;
        }
        return null;
    }

    public boolean checkDateRange(Timestamp dateFrom, Timestamp dateTo) {
        if (dateTo.after(dateFrom))
            return false;
        return true;
    }

    public boolean checkTasksWithDateIntersection(Timestamp dateFrom, Timestamp dateTo) {
        List<TaskManagerEntity> taskManagerEntities = taskManagerRepository.findTasksWithDateIntersection(dateFrom, dateTo);
        if (taskManagerEntities.size() > 0)
            return true;
        return false;
    }
}
