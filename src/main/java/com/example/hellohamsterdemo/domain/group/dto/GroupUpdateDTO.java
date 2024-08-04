//package com.example.hellohamsterdemo.domain.group.dto;
//
//import com.example.hellohamsterdemo.domain.group.entity.Group;
//
//import java.util.Date;
//
//public record  GroupUpdateDTO(Date startDate, Date endDate) {
//    public Group toEntity() {
//        return Group.builder().
//                startDate(startDate).
//                endDate(endDate).
//                build();
//    }
//}
