package com.example.hellohamsterdemo.domain.task.entity;

import com.example.hellohamsterdemo.domain.daytask.entity.DayTask;
import com.fasterxml.jackson.annotation.JsonBackReference;
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


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "day_task_id")
    @JsonBackReference
    private DayTask dayTask;

    @CreatedDate //생성날짜 구나 라는 것을 암
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    //camelCase하면 db에서 언더 바로 읽는다(_);

    @LastModifiedDate // 수정시간
    @Column(name = "updated_at")
    private LocalDateTime updateAt;


    @Builder
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

    public void setDayTask(DayTask dayTask) {
        this.dayTask = dayTask;
    }
}
