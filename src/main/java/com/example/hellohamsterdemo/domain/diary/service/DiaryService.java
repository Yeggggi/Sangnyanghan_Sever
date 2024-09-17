package com.example.hellohamsterdemo.domain.diary.service;

import com.example.hellohamsterdemo.domain.diary.dto.*;
import com.example.hellohamsterdemo.domain.diary.entity.Diary;
import com.example.hellohamsterdemo.domain.diary.repository.DiaryRepository;
import com.example.hellohamsterdemo.domain.diary.dto.DiaryCreateDTO;
import com.example.hellohamsterdemo.domain.group.entity.TodoGroup;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@RequiredArgsConstructor
@Service // Service annotation
public class DiaryService {

    private final DiaryRepository diaryRepository;

    public Diary saveDiary(DiaryCreateDTO dto) {
        // repository에 있는 기능을 이용한다 불러야 함
        // saveBoard를 해라고 하야 하니까

        Diary diary = dto.toEntity();
        // dto를 entity로 바꾸고
        diaryRepository.save(diary);

        return diary;
    }


}
