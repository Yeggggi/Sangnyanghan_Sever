package com.example.hellohamsterdemo.domain.daytask.dto;



import com.example.hellohamsterdemo.domain.daytask.entity.DayTask;
import com.example.hellohamsterdemo.domain.task.entity.Task;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public record DayTaskCreateDTO(List<Task> tasks, boolean isChecked, List<Long> taskIds, Long groupId, Long day) {
    public DayTaskCreateDTO {
        taskIds = taskIds != null ? taskIds : new ArrayList<>();
    }

    public DayTask toEntity(List<Task> tasks) {
        return DayTask.builder()
                .groupId(groupId)
                .day(day)
                .tasks(tasks)
                .isChecked(false)
                .build();
    }
}
