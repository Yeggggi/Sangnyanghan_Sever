package com.example.hellohamsterdemo.domain.task.dto;

import com.example.hellohamsterdemo.domain.task.entity.Task;

import java.util.List;

public record TaskReadDTO(Long taskId, String title, String content, Boolean isDaily, List<String> images) {
}