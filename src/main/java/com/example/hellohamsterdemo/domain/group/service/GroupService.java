package com.example.hellohamsterdemo.domain.group.service;

import com.example.hellohamsterdemo.domain.group.dto.*;
import com.example.hellohamsterdemo.domain.group.entity.TodoGroup;
import com.example.hellohamsterdemo.domain.group.repository.GroupRepository;
import com.example.hellohamsterdemo.domain.task.dto.TaskUpdateDTO;
import com.example.hellohamsterdemo.domain.task.entity.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service // Service annotation
public class GroupService {


    private final GroupRepository groupRepository;
    // 불변 객체 final로 명시 꼭

    public TodoGroup saveGroup(GroupCreateDTO dto) {
        // repository에 있는 기능을 이용한다 불러야 함
        // saveBoard를 해라고 하야 하니까

        TodoGroup group = dto.toEntity();
        // dto를 entity로 바꾸고
        groupRepository.save(group);

        return group;
    }

    public Optional<GroupFindDTO> getGroupByMemberId(Long memberId){
        return groupRepository.findGroupByMemberId(memberId);
    }

    public Optional<GroupFindDTO> getGroupBySitterId(Long sitterId) {
        return groupRepository.findGroupBySitterId(sitterId);
    }
    public Optional<GroupReadDTO> getGroupByGroupId(Long id){
        return groupRepository.findGroupByGroupId(id);
    }


    @Transactional
    public TodoGroup updateGroup(Long id, GroupUpdateDTO dto) {

        Optional<TodoGroup> optionalGroup = groupRepository.findById(id);

        TodoGroup group = optionalGroup.get();

        group.update(dto.startDate(), dto.endDate(), dto.maxDay());
        return groupRepository.save(group);
    }


    public TodoGroup updateSitter(Long id, SitterUpdateDTO dto) {

        Optional<TodoGroup> optionalGroup = groupRepository.findById(id);

        TodoGroup group = optionalGroup.get();

        group.updateSitter(dto.sitterId());
        return groupRepository.save(group);
    }


}