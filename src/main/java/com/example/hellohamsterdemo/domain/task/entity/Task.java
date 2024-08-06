package com.example.hellohamsterdemo.domain.task.entity;

import com.example.hellohamsterdemo.domain.daytask.entity.DayTask;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

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

    @ElementCollection
    @CollectionTable(name = "task_images", joinColumns = @JoinColumn(name = "task_id"))
    @Column(name = "image_url")
    private List<String> images;

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
    public Task(Long taskId, Long memberId, String title, String content, Boolean isDaily, List<String> images) {
        this.taskId = taskId;
        this.memberId = memberId;
        this.title = title;
        this.content = content;
        this.isDaily = isDaily;
        this.images = images;
    }

    public void update(String title, String content, Boolean isDaily) {
        this.title = title;
        this.content = content;
        this.isDaily = isDaily;
    }

    public void setDayTask(DayTask dayTask) {
        this.dayTask = dayTask;
    }

    public Long getId() {
        return taskId;
    }
}
