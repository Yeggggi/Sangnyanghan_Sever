package com.example.hellohamsterdemo.domain.group.controller;

import com.example.hellohamsterdemo.domain.group.dto.GroupReadDTO;
import com.example.hellohamsterdemo.domain.group.dto.GroupUpdateDTO;
import com.example.hellohamsterdemo.domain.group.entity.TodoGroup;
import com.example.hellohamsterdemo.domain.group.repository.GroupRepository;
import com.example.hellohamsterdemo.domain.group.service.GroupService;
import com.example.hellohamsterdemo.domain.group.dto.GroupCreateDTO;
import com.example.hellohamsterdemo.domain.task.entity.Task;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/group")
@CrossOrigin(origins = "http://localhost:") // 연결할 프론트의 주소
@Slf4j
public class GroupController {

    private final GroupService groupService;
    private final GroupRepository groupRepository;

    //새 그룹 등록
    @PostMapping("/post")
    public ResponseEntity<TodoGroup> saveGroup(@RequestBody GroupCreateDTO dto) {

        TodoGroup savedGroup = groupService.saveGroup(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedGroup);
    }

    // 그룹 아이디 요청
    @GetMapping("/id/{member_id}")
    public ResponseEntity<Optional<TodoGroup>> readGroupId(@PathVariable("member_id") Long memberId) {

        Optional<TodoGroup> group = groupService.getGroupByMemberId(memberId);

        return ResponseEntity.status(HttpStatus.CREATED).body(group);
    }

    //group 정보 요청
    @GetMapping("/{group_id}")
    public ResponseEntity<Optional<GroupReadDTO>> getGroupInfo(@PathVariable("group_id") Long id) {

        Optional<GroupReadDTO> group = groupService.getGroupByGroupId(id);

        return ResponseEntity.status(HttpStatus.CREATED).body(group);
    }

    // 그룹의 날짜 수정
    @PatchMapping("/update/{group_id}") // 그룹 날짜 업데이트
    public ResponseEntity<TodoGroup> updateGroup(@PathVariable("group_id") Long groupId, @RequestBody GroupUpdateDTO dto) {

        TodoGroup updatedGroup = groupService.updateGroup(groupId, dto);
            return ResponseEntity.ok(updatedGroup);
    }
}