package com.example.hellohamsterdemo.domain.diary.controller;

import com.example.hellohamsterdemo.domain.diary.dto.*;
import com.example.hellohamsterdemo.domain.diary.entity.Diary;
import com.example.hellohamsterdemo.domain.diary.service.DiaryService;
import com.example.hellohamsterdemo.domain.diary.repository.DiaryRepository;


import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/diary")
@CrossOrigin(origins = "http://localhost:") // 연결할 프론트의 주소
@Slf4j
public class DiaryController {

    private final DiaryService diaryService;

    private final DiaryRepository diaryRepository;

    //새 그룹 등록, 응답으로 그룹 아이디를 반환함
    @PostMapping("/post")
    public ResponseEntity<Long> saveDiary(@RequestBody DiaryCreateDTO dto) {

        Diary savedGroup = diaryService.saveDiary(dto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedGroup.getId())
                .toUri();

        Long diary_id = savedGroup.getId();
        return ResponseEntity.created(location).body(diary_id);
    }



}
