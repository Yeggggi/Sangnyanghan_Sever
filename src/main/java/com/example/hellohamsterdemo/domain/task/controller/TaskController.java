package com.example.hellohamsterdemo.domain.task.controller;

import com.example.hellohamsterdemo.domain.task.dto.TaskCreateDTO;
import com.example.hellohamsterdemo.domain.task.dto.TaskReadDTO;
import com.example.hellohamsterdemo.domain.task.dto.TaskUpdateDTO;

import com.example.hellohamsterdemo.domain.task.entity.Task;
import com.example.hellohamsterdemo.domain.task.repository.TaskRepository;
import com.example.hellohamsterdemo.domain.task.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/task")
@CrossOrigin(origins = "http://localhost:") // 연결할 프론트의 주소
@Slf4j
public class TaskController {

    private final TaskService taskService;
    private final TaskRepository taskRepository;

    //새 task 등록
    @PostMapping("/post")
    public ResponseEntity<Task> saveTask(@RequestBody TaskCreateDTO dto) {

        Task savedTask = taskService.saveTask(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedTask);
    }

    //해당 멤버의 모든 task 보기
    @GetMapping("/all/{member_id}") // 그룹 아이디 요청
    public ResponseEntity<List<TaskReadDTO>> readAllTask(@PathVariable("member_id") Long memberId) {

        List<TaskReadDTO> task = taskService.getTaskByMemberId(memberId);

        return ResponseEntity.status(HttpStatus.CREATED).body(task);
    }

    //Task 내용 업데이트
    @PatchMapping("/update/{task_id}")
    public ResponseEntity<Task> updateTaskDetail(@PathVariable("task_id") Long id,  @RequestBody TaskUpdateDTO taskUpdateDto) {

        Task updatedTask = taskService.updateTask(id, taskUpdateDto);
        return ResponseEntity.ok(updatedTask);
    }

}