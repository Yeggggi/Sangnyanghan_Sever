package com.example.hellohamsterdemo.domain.daytask.controller;

import com.example.hellohamsterdemo.domain.daytask.dto.DayTaskCreateDTO;
import com.example.hellohamsterdemo.domain.daytask.entity.DayTask;
import com.example.hellohamsterdemo.domain.daytask.service.DayTaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/daytask")
@CrossOrigin(origins = "http://localhost:") // 연결할 프론트의 주소
@Slf4j
public class DayTaskController {

private final DayTaskService dayTaskService;

    // 해당 day의 할 일들 추가
    @PostMapping("/post")
    public ResponseEntity<DayTask> saveDayTask(@RequestBody DayTaskCreateDTO dto) {
        DayTask savedDayTask = dayTaskService.saveDayTask(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDayTask);
    }


    // TODO: 해당 그룹의 해당 day의 tasks 요청
    @GetMapping("/all")
    public ResponseEntity<List<DayTask>> getAllDayTasks() {
        List<DayTask> dayTasks = dayTaskService.getAllDayTasks();
        return ResponseEntity.status(HttpStatus.OK).body(dayTasks);
    }


    // 해당 그룹의 해당 day의 tasks 요청
    @GetMapping("/group/{groupId}/day/{day}")
    public ResponseEntity<List<DayTask>> getDayTasksByGroupAndDay(@PathVariable Long groupId, @PathVariable Long day) {
        List<DayTask> dayTasks = dayTaskService.getDayTasksByGroupAndDay(groupId, day);
        return ResponseEntity.status(HttpStatus.OK).body(dayTasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DayTask> getDayTaskById(@PathVariable Long id) {
        DayTask dayTask = dayTaskService.getDayTaskById(id);
        if (dayTask != null) {
            return ResponseEntity.status(HttpStatus.OK).body(dayTask);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    // TODO: update 수행 여부 day_task의 is_checked 여부 수정
    // TODO: delete. group_id와 day를 통해 해당 day의 task 모두 삭제



    // update 수행 여부 day_task의 is_checked 여부 수정
    @PatchMapping("/update/{id}/isChecked")
    public ResponseEntity<DayTask> updateIsChecked(@PathVariable Long id, @RequestBody Boolean isChecked) {
        DayTask updatedDayTask = dayTaskService.updateIsChecked(id, isChecked);
        if (updatedDayTask != null) {
            return ResponseEntity.status(HttpStatus.OK).body(updatedDayTask);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // delete. group_id와 day를 통해 해당 day의 task 모두 삭제
    @DeleteMapping("/delete/group/{groupId}/day/{day}")
    public ResponseEntity<Void> deleteDayTasksByGroupAndDay(@PathVariable Long groupId, @PathVariable Long day) {
        boolean deleted = dayTaskService.deleteDayTasksByGroupAndDay(groupId, day);
        if (deleted) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}