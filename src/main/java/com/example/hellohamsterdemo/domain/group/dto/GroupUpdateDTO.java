package com.example.hellohamsterdemo.domain.group.dto;

import java.util.Date;

public record  GroupUpdateDTO(Date startDate, Date endDate, Long maxDay) {
}

