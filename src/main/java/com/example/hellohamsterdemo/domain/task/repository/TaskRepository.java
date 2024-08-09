package com.example.hellohamsterdemo.domain.task.repository;

import com.example.hellohamsterdemo.domain.task.dto.TaskAllReadDTO;
import com.example.hellohamsterdemo.domain.task.dto.TaskReadDTO;
import com.example.hellohamsterdemo.domain.task.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{
//    List<Task> findTaskByMemberId(Long memberId);
    Task findTaskById(Long taskId);
    @Query("SELECT new com.example.hellohamsterdemo.domain.task.dto.TaskAllReadDTO(tg.taskId, tg.title, tg.content, tg.isDaily) FROM Task tg WHERE tg.memberId = :memberId AND tg.expire = false")
    List<TaskAllReadDTO> findTasksByMemberId(Long memberId);
}