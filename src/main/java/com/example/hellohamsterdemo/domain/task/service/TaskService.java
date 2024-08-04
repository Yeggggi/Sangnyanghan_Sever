package com.example.hellohamsterdemo.domain.task.service;

import com.example.hellohamsterdemo.domain.task.dto.TaskCreateDTO;
import com.example.hellohamsterdemo.domain.task.dto.TaskReadDTO;
import com.example.hellohamsterdemo.domain.task.dto.TaskUpdateDTO;
import com.example.hellohamsterdemo.domain.task.entity.Task;
import com.example.hellohamsterdemo.domain.task.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service // Service annotation
public class TaskService {

    private final TaskRepository taskRepository;

    public Task saveTask(TaskCreateDTO dto) {

        Task task = dto.toEntity();
        taskRepository.save(task);

        return task;
    }

    public List<TaskReadDTO> getTaskByMemberId(Long memberId){
        return taskRepository.findTaskByMemberId(memberId);
    }

    @Transactional
    public Task updateTask(Long id, TaskUpdateDTO dto) {

        Optional<Task> optionalTask = taskRepository.findById(id);

        Task task = optionalTask.get();

        task.update(dto.title(), dto.content(), dto.isDaily());
        return taskRepository.save(task);
    }

}
