package com.example.hellohamsterdemo.domain.daytask.service;



import com.example.hellohamsterdemo.domain.daytask.dto.DayTaskCreateDTO;
import com.example.hellohamsterdemo.domain.daytask.dto.DayTaskResponseDTO;
import com.example.hellohamsterdemo.domain.daytask.entity.DayTask;
import com.example.hellohamsterdemo.domain.daytask.entity.DayTaskTask;
import com.example.hellohamsterdemo.domain.image.entity.Image;
import com.example.hellohamsterdemo.domain.image.repository.ImageRepository;
import com.example.hellohamsterdemo.domain.task.dto.TaskReadDTO;
import com.example.hellohamsterdemo.domain.task.entity.Task;
import com.example.hellohamsterdemo.domain.task.repository.TaskRepository;
import com.example.hellohamsterdemo.domain.daytask.repository.DayTaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class DayTaskService {

    private final DayTaskRepository dayTaskRepository;
    private final TaskRepository taskRepository;
    private final ImageRepository imageRepository;

    @Transactional
    public DayTask saveDayTask(DayTaskCreateDTO dto) {
        List<DayTask> existingDayTasks = dayTaskRepository.findByGroupIdAndDay(dto.groupId(), dto.day());
        if (!existingDayTasks.isEmpty()) {
            dayTaskRepository.deleteAll(existingDayTasks);
        }

        // Create new DayTask
        DayTask dayTask = dto.toEntity();
        DayTask savedDayTask = dayTaskRepository.save(dayTask);



        // Create DayTaskTask for each Task ID
        int order = 1;
        for (Long taskId : dto.taskIds()) {
            Optional<Task> optionalTask = taskRepository.findById(taskId);
            if (optionalTask.isPresent()) {
                Task task = optionalTask.get();

                // Retrieve images for the task
                List<Image> images = imageRepository.findByTaskId(task.getId());
                List<String> imageUrls = images.stream().map(Image::getUrl).collect(Collectors.toList());

                // Create a copy of the task with images
                Task taskWithImages = Task.builder()
                        .taskId(task.getTaskId()) // Set the task ID to maintain the reference
                        .memberId(task.getMemberId())
                        .title(task.getTitle())
                        .content(task.getContent())
                        .isDaily(task.getIsDaily())
                        .images(imageUrls)
                        .build();

                DayTaskTask dayTaskTask = DayTaskTask.builder()
                        .dayTask(savedDayTask)
                        .task(taskWithImages)
                        .isChecked(false)
                        .order(order++)
                        .build();
                savedDayTask.addDayTaskTask(dayTaskTask);
            }
        }

        return dayTaskRepository.save(savedDayTask);
    }
/*
    public List<DayTask> getAllDayTasks() {
        return dayTaskRepository.findAll();
    }

 */


    public List<DayTaskResponseDTO> getAllDayTasks() {
        List<DayTask> dayTasks = dayTaskRepository.findAll();
        return dayTasks.stream()
                .flatMap(dayTask -> dayTask.getDayTaskTasks().stream()
                        .sorted(Comparator.comparingInt(DayTaskTask::getOrder))
                        .map(dayTaskTask -> toDayTaskResponseDTO(dayTask, dayTaskTask.getTask(), dayTaskTask.getIsChecked(), dayTaskTask.getOrder())))
                .collect(Collectors.toList());
    }

    public DayTaskResponseDTO getDayTaskById(Long id) {
        DayTask dayTask = dayTaskRepository.findById(id).orElse(null);
        if (dayTask == null) return null;

        AtomicInteger counter = new AtomicInteger(1);
        return dayTask.getDayTaskTasks().stream()
                .map(dayTaskTask -> toDayTaskResponseDTO(dayTask, dayTaskTask.getTask(), dayTaskTask.getIsChecked(), counter.getAndIncrement()))
                .findFirst().orElse(null);
    }
    /*
    @Transactional
    public DayTaskResponseDTO updateIsCheckedByGroupDayAndNumber(Long groupId, Long day, Integer number) {
        List<DayTask> dayTasks = dayTaskRepository.findByGroupIdAndDay(groupId, day);
        AtomicInteger counter = new AtomicInteger(1);
        DayTaskResponseDTO result = null;

        for (DayTask dayTask : dayTasks) {
            for (Task task : dayTask.getTasks()) {
                int currentNumber = counter.getAndIncrement();
                if (currentNumber == number) {
                    dayTask.toggleChecked();
                    dayTaskRepository.save(dayTask);
                    result = toDayTaskResponseDTO(dayTask, task, currentNumber);
                    break; // 지정된 태스크를 업데이트한 후 루프 종료
                }
            }
            if (result != null) {
                break; // 태스크를 찾고 업데이트한 후 외부 루프 종료
            }
        }
        return result;
    }*/

    @Transactional
    public DayTaskResponseDTO updateIsCheckedByGroupDayAndNumber(Long groupId, Long day, Integer number) {
        List<DayTask> dayTasks = dayTaskRepository.findByGroupIdAndDay(groupId, day);
        DayTaskResponseDTO result = null;

        for (DayTask dayTask : dayTasks) {
            List<DayTaskTask> orderedTasks = dayTask.getDayTaskTasks().stream()
                    .sorted(Comparator.comparingInt(DayTaskTask::getOrder))
                    .collect(Collectors.toList());

            for (DayTaskTask dayTaskTask : orderedTasks) {
                if (dayTaskTask.getOrder() == number) {
                    dayTaskTask.setChecked(!dayTaskTask.getIsChecked()); // Toggle isChecked
                    dayTaskRepository.save(dayTask);
                    Task task = dayTaskTask.getTask();
                    result = toDayTaskResponseDTO(dayTask, task, dayTaskTask.getIsChecked(), dayTaskTask.getOrder());
                    break; // Break after updating the correct task
                }
            }
        }
        return result;
    }


    public boolean deleteDayTasksByGroupAndDay(Long groupId, Long day) {
        List<DayTask> dayTasks = dayTaskRepository.findByGroupIdAndDay(groupId, day);
        if (!dayTasks.isEmpty()) {
            dayTaskRepository.deleteAll(dayTasks);
            return true;
        }
        return false;
    }


    public List<DayTaskResponseDTO> getDayTasksByGroupAndDay(Long groupId, Long day) {
        List<DayTask> dayTasks = dayTaskRepository.findByGroupIdAndDay(groupId, day);
        return dayTasks.stream()
                .flatMap(dayTask -> dayTask.getDayTaskTasks().stream()
                        .sorted(Comparator.comparingInt(DayTaskTask::getOrder))
                        .map(dayTaskTask -> toDayTaskResponseDTO(dayTask, dayTaskTask.getTask(), dayTaskTask.getIsChecked(), dayTaskTask.getOrder())))
                .collect(Collectors.toList());
    }

    private DayTaskResponseDTO toDayTaskResponseDTO(DayTask dayTask, Task task, Boolean isChecked, int order) {
        return new DayTaskResponseDTO(
                dayTask.getDayTaskId(),
                dayTask.getGroupId(),
                dayTask.getDay(),
                order,
                new DayTaskResponseDTO.TaskDTO(
                        task.getTaskId(),
                        task.getMemberId(),
                        task.getTitle(),
                        task.getContent(),
                        task.getIsDaily(),
                        task.getCreatedAt(),
                        task.getUpdateAt()
                ),
                isChecked
        );
    }
}

    /*
    @Transactional
    public DayTask saveDayTask(DayTaskCreateDTO dto) {
        DayTask dayTask = dto.toEntity();

        List<Long> taskIds = dto.taskIds() != null ? dto.taskIds() : List.of();


        for (Long taskId : taskIds) {
            Optional<Task> taskOptional = taskRepository.findById(taskId);
            if (taskOptional.isPresent()) {
                Task task = taskOptional.get();
                dayTask.addTask(task);
            }
        }

        return dayTaskRepository.save(dayTask);
    }

    public List<DayTask> getAllDayTasks() {
        return dayTaskRepository.findAll();
    }

    public DayTask getDayTaskById(Long id) {
        Optional<DayTask> dayTaskOptional = dayTaskRepository.findById(id);
        return dayTaskOptional.orElse(null);
    }

     */
