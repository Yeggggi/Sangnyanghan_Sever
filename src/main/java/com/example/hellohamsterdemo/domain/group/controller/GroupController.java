package com.example.hellohamsterdemo.domain.group.controller;

import com.example.hellohamsterdemo.domain.group.dto.GroupReadDTO;
import com.example.hellohamsterdemo.domain.group.entity.TodoGroup;
import com.example.hellohamsterdemo.domain.group.repository.GroupRepository;
import com.example.hellohamsterdemo.domain.group.service.GroupService;
import com.example.hellohamsterdemo.domain.group.dto.GroupCreateDTO;
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

    @PostMapping("/post") //새 그룹 등록
    public ResponseEntity<TodoGroup> saveGroup(@RequestBody GroupCreateDTO dto) {

        TodoGroup savedGroup = groupService.saveGroup(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedGroup);
    }

    @GetMapping("/id/{member_id}") // 그룹 아이디 요청
    public ResponseEntity<Optional<TodoGroup>> readGroupId(@PathVariable("member_id") Long memberId) {

        Optional<TodoGroup> group = groupService.getGroupByMemberId(memberId);

        return ResponseEntity.status(HttpStatus.CREATED).body(group);
    }

    @GetMapping("/{group_id}/get")
    public ResponseEntity<Optional<GroupReadDTO>> getGroupInfo(@PathVariable("group_id") Long id) {

        Optional<GroupReadDTO> group = groupService.getGroupByGroupId(id);

        return ResponseEntity.status(HttpStatus.CREATED).body(group);
    }

//    @PutMapping("/{group_id}/update") // 그룹 날짜 업데이트
//    public ResponseEntity<Group> updateGroup(@PathVariable("group_id") Long groupId, @RequestBody GroupCreateDTO dto) {
//
//        try {
//            log.debug("Updating group with ID: {}", groupId); // 디버그 로그 추가
//            Group updateGroup = groupService.updateGroup(groupId, dto);
//            log.debug("Updated group details: {}", updateGroup); // 디버그 로그 추가
//            return ResponseEntity.ok(updateGroup);
//        } catch (RuntimeException e) {
//            return ResponseEntity.notFound().build();
//        }
//    }
}