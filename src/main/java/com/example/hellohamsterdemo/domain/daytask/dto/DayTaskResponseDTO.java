package com.example.hellohamsterdemo.domain.daytask.dto;

import com.example.hellohamsterdemo.domain.daytask.entity.DayTask;
import com.example.hellohamsterdemo.domain.task.entity.Task;
public record DayTaskResponseDTO(
        Long dayTaskId,
        Long groupId,
        Long day,
        Task task,
        Boolean isChecked
) {
    public DayTaskResponseDTO(DayTask dayTask, Task task) {
        this(dayTask.getDayTaskId(), dayTask.getGroupId(), dayTask.getDay(), task, dayTask.getIsChecked());
    }
}