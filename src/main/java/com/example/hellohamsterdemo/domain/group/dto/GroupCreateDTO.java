package com.example.hellohamsterdemo.domain.group.dto;

import com.example.hellohamsterdemo.domain.group.entity.TodoGroup;

import java.time.LocalDateTime;
import java.util.Date;


public record GroupCreateDTO(Long memberId, Long sitterId, String startDate, String endDate, Long maxDay) {
    public TodoGroup toEntity() {
        return TodoGroup.builder().
                memberId(memberId).
                sitterId(sitterId).
                startDate(startDate).
                endDate(endDate).
                maxDay(maxDay).
                build();
    }
}

