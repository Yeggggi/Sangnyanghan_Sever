package com.example.hellohamsterdemo.domain.daytask.dto;



import com.example.hellohamsterdemo.domain.daytask.entity.DayTask;
import com.example.hellohamsterdemo.domain.daytask.entity.DayTaskTask;
import com.example.hellohamsterdemo.domain.task.entity.Task;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public record DayTaskCreateDTO(boolean isChecked, List<Long> taskIds, Long groupId, Long day) {
    public DayTaskCreateDTO {
        taskIds = taskIds != null ? taskIds : new ArrayList<>();
    }

    public DayTask toEntity() {
        DayTask dayTask = DayTask.builder()
                .groupId(groupId)
                .day(day)
                .isChecked(false)
                .build();
        return dayTask;
    }
}
