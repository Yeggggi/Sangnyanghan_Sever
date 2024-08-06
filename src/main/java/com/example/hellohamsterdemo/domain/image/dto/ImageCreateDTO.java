package com.example.hellohamsterdemo.domain.image.dto;

import com.example.hellohamsterdemo.domain.image.entity.Image;


public record ImageCreateDTO(Long memberId, String url, String location, Long taskId, Long groupId, Long day) {
    public Image toEntity() {
        return Image.builder().
                memberId(memberId).
                url(url).
                location(location).
                taskId(taskId).
                groupId(groupId).
                day(day).
                build();
    }
}

