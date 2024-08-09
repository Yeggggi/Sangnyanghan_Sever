package com.example.hellohamsterdemo.domain.task.service;

import com.example.hellohamsterdemo.domain.image.entity.Image;
import com.example.hellohamsterdemo.domain.image.repository.ImageRepository;
import com.example.hellohamsterdemo.domain.task.dto.TaskAllReadDTO;
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
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service // Service annotation
public class TaskService {

    private final TaskRepository taskRepository;
    private final ImageRepository imageRepository;

    public Task saveTask(TaskCreateDTO dto) {

        Task task = dto.toEntity();
        taskRepository.save(task);

        return task;
    }

    public List<TaskReadDTO> getTaskByMemberId(Long memberId){
        List<TaskAllReadDTO> tasks = taskRepository.findTasksByMemberId(memberId);

        return tasks.stream().map(task -> {
            List<Image> images = imageRepository.findByTaskId(task.taskId());
            List<String> imageUrls = images.stream().map(Image::getUrl).collect(Collectors.toList());
            return new TaskReadDTO(task.taskId(), task.title(), task.content(), task.isDaily(), imageUrls);
        }).collect(Collectors.toList());
    }

    @Transactional
    public Task updateTask(Long id, TaskUpdateDTO dto) {

        Optional<Task> optionalTask = taskRepository.findById(id);

        Task task = optionalTask.get();

        task.update(dto.title(), dto.content(), dto.isDaily());
        return taskRepository.save(task);
    }

    public Task expireTask(Long taskId) {
        Task optionalTask = taskRepository.findTaskById(taskId);
        Task task = optionalTask.get();

        task.setTaskExpire(!task.getExpire());

        return taskRepository.save(task);
    }
}
