package com.example.hellohamsterdemo.domain.group.dto;

import com.example.hellohamsterdemo.domain.group.entity.TodoGroup;

import java.time.LocalDateTime;
import java.util.Date;


public record GroupCreateDTO(Long memberId, Long sitterId, Date startDate, Date endDate, Long maxDay, Boolean expire) {
    public TodoGroup toEntity() {
        return TodoGroup.builder().
                memberId(memberId).
                sitterId(sitterId).
                startDate(startDate).
                endDate(endDate).
                maxDay(maxDay).
                expire(expire).
                build();
    }
}

