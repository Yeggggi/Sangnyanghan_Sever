package com.example.hellohamsterdemo.domain.diary.dto;

import java.util.Date;

public record DiaryReadDTO (Long id, Long day, Long groupId, String title, String content, Date date){
}
