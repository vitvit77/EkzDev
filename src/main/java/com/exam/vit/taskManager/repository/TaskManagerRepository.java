package com.exam.vit.taskManager.repository;

import com.exam.vit.taskManager.model.TaskManagerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface TaskManagerRepository extends JpaRepository<TaskManagerEntity, Long> {
    @Query("SELECT t FROM task_manager t WHERE t.date_from <= :endDateTime AND t.date_to >= :startDateTime")
    List<TaskManagerEntity> findTasksWithDateIntersection(Timestamp startDateTime, Timestamp endDateTime);
}
