package com.example.hellohamsterdemo.domain.group.controller;

import com.example.hellohamsterdemo.domain.group.dto.*;
import com.example.hellohamsterdemo.domain.group.entity.TodoGroup;
import com.example.hellohamsterdemo.domain.group.repository.GroupRepository;
import com.example.hellohamsterdemo.domain.group.service.GroupService;
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
@RequestMapping("/api/group")
@CrossOrigin(origins = "http://localhost:") // 연결할 프론트의 주소
@Slf4j
public class GroupController {

    private final GroupService groupService;
    private final GroupRepository groupRepository;

    //새 그룹 등록, 응답으로 그룹 아이디를 반환함
    @PostMapping("/post")
    public ResponseEntity<Long> saveGroup(@RequestBody GroupCreateDTO dto) {

        TodoGroup savedGroup = groupService.saveGroup(dto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedGroup.getId())
                .toUri();

        Long group_id = savedGroup.getId();
        return ResponseEntity.created(location).body(group_id);
    }

    // 그룹 정보 요청, 멤버아이디가 같고 expire이 false인 가장 최근에 만들어진 그룹 반환함
    @GetMapping("/member/{member_id}")
    public ResponseEntity<Optional<GroupFindDTO>> readGroupId(@PathVariable("member_id") Long memberId) {

        Optional<GroupFindDTO> group = groupService.getGroupByMemberId(memberId);

        return ResponseEntity.status(HttpStatus.CREATED).body(group);
    }

    // 그룹 정보 요청, sitter 아이디가 같고 expire이 false인 가장 최근에 만들어진 그룹 반환함
    @GetMapping("/sitter/{sitter_id}")
    public ResponseEntity<Optional<GroupFindDTO>> readGroupBySitter(@PathVariable("sitter_id") Long sitterId) {

        Optional<GroupFindDTO> group = groupService.getGroupBySitterId(sitterId);

        return ResponseEntity.status(HttpStatus.CREATED).body(group);
    }

    // group 정보 요청, group_id로 요청함
    @GetMapping("/{group_id}")
    public ResponseEntity<Optional<GroupReadDTO>> getGroupInfo(@PathVariable("group_id") Long id) {

        Optional<GroupReadDTO> group = groupService.getGroupByGroupId(id);

        return ResponseEntity.status(HttpStatus.CREATED).body(group);
    }

    // 그룹의 날짜 수정
    @PatchMapping("/update/{group_id}")
    public ResponseEntity<TodoGroup> updateGroup(@PathVariable("group_id") Long groupId, @RequestBody GroupUpdateDTO dto) {

        TodoGroup updatedGroup = groupService.updateGroup(groupId, dto);
            return ResponseEntity.ok(updatedGroup);
    }

    // group에 sitter_id 등록
    @PatchMapping("/update/{group_id}/sitter")
    public ResponseEntity<TodoGroup> updateSitterId(@PathVariable("group_id") Long id, @RequestBody SitterUpdateDTO dto) {

        TodoGroup updatedGroup = groupService.updateSitter(id, dto);
        return ResponseEntity.ok(updatedGroup);
    }


}