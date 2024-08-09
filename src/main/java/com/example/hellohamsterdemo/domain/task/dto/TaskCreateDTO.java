package com.example.hellohamsterdemo.domain.task.dto;

import com.example.hellohamsterdemo.domain.task.entity.Task;

import java.util.List;

public record TaskCreateDTO(Long memberId, String title, String content, Boolean isDaily, List<String> images, Boolean expire) {
    public Task toEntity() {
        return Task.builder().
                memberId(memberId).
                title(title).
                content(content).
                isDaily(isDaily).
                expire(false).
                build();
    }
}