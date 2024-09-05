package com.example.hellohamsterdemo.domain.diary.repository;

import com.example.hellohamsterdemo.domain.diary.dto.DiaryCreateDTO;
import com.example.hellohamsterdemo.domain.diary.dto.DiaryReadDTO;
import com.example.hellohamsterdemo.domain.diary.entity.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface DiaryRepository extends JpaRepository<Diary, Long>{
    @Query("SELECT new com.example.hellohamsterdemo.domain.diary.dto.DiaryReadDTO(dr.id, dr.groupId, dr.day, dr.title, dr.content, dr.date) FROM Diary dr WHERE dr.groupId = :groupId AND dr.day = :day")
    Diary findTasksByMemberId(Long groupId, Long day);

}
