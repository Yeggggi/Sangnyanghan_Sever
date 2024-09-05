package com.example.hellohamsterdemo.domain.diary.dto;

import com.example.hellohamsterdemo.domain.diary.entity.Diary;
import com.example.hellohamsterdemo.domain.group.entity.TodoGroup;

import java.util.Date;

public record DiaryCreateDTO(Long id, Long memberId, Long sitterId, Long day, Long groupId, String title, String content, Date date, Boolean expire) {
    public Diary toEntity() {
        return Diary.builder().
                id(id).
                sitterId(sitterId).
                memberId(memberId).
                day(day).
                groupId(groupId).
                title(title).
                content(content).
                date(date).
                expire(false).
                build();
    }
}


