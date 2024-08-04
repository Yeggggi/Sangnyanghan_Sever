package com.example.hellohamsterdemo.domain.daytask.service;



import com.example.hellohamsterdemo.domain.daytask.dto.DayTaskCreateDTO;
import com.example.hellohamsterdemo.domain.daytask.entity.DayTask;
import com.example.hellohamsterdemo.domain.task.entity.Task;
import com.example.hellohamsterdemo.domain.task.repository.TaskRepository;
import com.example.hellohamsterdemo.domain.daytask.repository.DayTaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class DayTaskService {

    private final DayTaskRepository dayTaskRepository;
    private final TaskRepository taskRepository;
    public DayTask saveDayTask(DayTaskCreateDTO dto) {
        // taskIds로 Task 엔티티 조회
        List<Task> tasks = taskRepository.findAllById(dto.taskIds());

        // DayTask 엔티티 생성 및 저장
        DayTask dayTask = dto.toEntity(tasks);
        return dayTaskRepository.save(dayTask);
    }

    public List<DayTask> getAllDayTasks() {
        return dayTaskRepository.findAll();
    }

    public DayTask getDayTaskById(Long id) {
        Optional<DayTask> dayTask = dayTaskRepository.findById(id);
        return dayTask.orElse(null);
    }

    public List<DayTask> getDayTasksByGroupAndDay(Long groupId, Long day) {
        return dayTaskRepository.findByGroupIdAndDay(groupId, day);
    }

    public DayTask updateIsChecked(Long id, Boolean isChecked) {
        Optional<DayTask> optionalDayTask = dayTaskRepository.findById(id);
        if (optionalDayTask.isPresent()) {
            DayTask dayTask = optionalDayTask.get();
            dayTask.checkedupdate(isChecked);
            return dayTaskRepository.save(dayTask);
        } else {
            return null;
        }
    }

    public boolean deleteDayTasksByGroupAndDay(Long groupId, Long day) {
        List<DayTask> dayTasks = dayTaskRepository.findByGroupIdAndDay(groupId, day);
        if (!dayTasks.isEmpty()) {
            dayTaskRepository.deleteAll(dayTasks);
            return true;
        } else {
            return false;
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
}