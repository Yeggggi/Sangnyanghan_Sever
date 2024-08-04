package com.example.hellohamsterdemo.domain.daytask.dto;

import com.example.hellohamsterdemo.domain.daytask.entity.DayTask;
import com.example.hellohamsterdemo.domain.task.entity.Task;

import java.time.LocalDateTime;

public record DayTaskResponseDTO(
        Long dayTaskId,
        Long groupId,
        Long day,
        TaskDTO task,
        Boolean isChecked
) {
    public record TaskDTO(
            Long taskId,
            Long memberId,
            String title,
            String content,
            Boolean isDaily,
            LocalDateTime createdAt,
            LocalDateTime updateAt
    ) {
    }
}