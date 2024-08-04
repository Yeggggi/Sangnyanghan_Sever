package com.example.hellohamsterdemo.domain.task.dto;

import com.example.hellohamsterdemo.domain.task.entity.Task;

public record TaskReadDTO(Long taskId, String title, String content, Boolean isDaily) {
}