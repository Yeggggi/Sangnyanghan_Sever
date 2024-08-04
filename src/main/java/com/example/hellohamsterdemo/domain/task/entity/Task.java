package com.example.hellohamsterdemo.domain.task.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)

@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="task_id")
    private Long taskId;


    @Column(name="member_id", nullable = false )
    private Long memberId;

    @Column(name="title" )
    private String title;

    @Column(name="content" )
    private String content;

    @Column(name = "is_daily")
    private Boolean isDaily;


    @Builder //Setter역할 한다
    public Task(Long memberId, String title, String content, Boolean isDaily) {
        this.memberId = memberId;
        this.title = title;
        this.content = content;
        this.isDaily = isDaily;
    }

    public void update(String title, String content, Boolean isDaily) {
        this.title = title;
        this.content = content;
        this.isDaily = isDaily;
    }
}
