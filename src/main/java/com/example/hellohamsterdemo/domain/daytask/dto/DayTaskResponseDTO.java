package com.example.hellohamsterdemo.domain.daytask.dto;

import com.example.hellohamsterdemo.domain.daytask.entity.DayTask;
import com.example.hellohamsterdemo.domain.task.entity.Task;

import java.time.LocalDateTime;
import java.util.List;

public record DayTaskResponseDTO(
        Long dayTaskId,
        Long groupId,
        Long day,
        Integer number,
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