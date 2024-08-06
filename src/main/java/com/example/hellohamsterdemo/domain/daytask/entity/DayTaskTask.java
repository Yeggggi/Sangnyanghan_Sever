package com.example.hellohamsterdemo.domain.daytask.entity;

import com.example.hellohamsterdemo.domain.task.entity.Task;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class DayTaskTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "day_task_task_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "day_task_id")
    @JsonBackReference
    private DayTask dayTask;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")
    private Task task;

    @Column(name = "is_checked", nullable = false)
    private Boolean isChecked = false;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @Column(name = "task_order", nullable = false)
    private int order;

    @Builder
    public DayTaskTask(DayTask dayTask, Task task, Boolean isChecked, int order) {
        this.dayTask = dayTask;
        this.task = task;
        this.isChecked = isChecked;
        this.order = order;
    }
    public void toggleChecked() {
        this.isChecked = !this.isChecked;
    }

    public void setChecked(Boolean isChecked) {
        this.isChecked = isChecked;
    }
    public void setDayTask(DayTask dayTask) {
        this.dayTask = dayTask;
    }
    public void setOrder(int order) {
        this.order = order;
    }
}
