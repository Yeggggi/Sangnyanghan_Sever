package com.example.hellohamsterdemo.domain.task.dto;

import com.example.hellohamsterdemo.domain.task.entity.Task;

public record TaskCreateDTO(Long memberId, String title, String content, Boolean isDaily) {
    public Task toEntity() {
        return Task.builder().
                memberId(memberId).
                title(title).
                content(content).
                isDaily(isDaily).
                build();
    }
}