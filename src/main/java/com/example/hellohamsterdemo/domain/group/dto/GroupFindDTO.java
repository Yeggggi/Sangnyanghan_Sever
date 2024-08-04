package com.example.hellohamsterdemo.domain.group.dto;

import java.util.Date;

public record GroupFindDTO(Long sitterId, String startDate, String endDate, Long maxDay) {
}

