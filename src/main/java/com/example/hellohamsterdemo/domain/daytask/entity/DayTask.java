package com.example.hellohamsterdemo.domain.daytask.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.example.hellohamsterdemo.domain.task.entity.Task;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class DayTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "day_task_id")
    private Long dayTaskId;

    @Column(name = "group_id", nullable = false)
    private Long groupId;

    @Column(name = "day", nullable = false)
    private Long day;

    @OneToMany(mappedBy = "dayTask", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<DayTaskTask> dayTaskTasks = new ArrayList<>();

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "is_checked", nullable = false)
    private Boolean isChecked = false;

    @Builder
    public DayTask(Long groupId, Long day, List<DayTaskTask> dayTaskTasks, Boolean isChecked) {
        this.groupId = groupId;
        this.day = day;
        this.dayTaskTasks = dayTaskTasks != null ? dayTaskTasks : new ArrayList<>();
        this.isChecked = isChecked;
    }

    public void addDayTaskTask(DayTaskTask dayTaskTask) {
        dayTaskTasks.add(dayTaskTask);
        dayTaskTask.setDayTask(this);
    }

    public void removeDayTaskTask(DayTaskTask dayTaskTask) {
        dayTaskTasks.remove(dayTaskTask);
        dayTaskTask.setDayTask(null);
    }

    public void toggleTaskChecked(int taskIndex) {
        if (taskIndex >= 0 && taskIndex < dayTaskTasks.size()) {
            DayTaskTask dayTaskTask = dayTaskTasks.get(taskIndex);
            dayTaskTask.toggleChecked();
        }
    }

    public void setChecked(Boolean isChecked) {
        this.isChecked = isChecked;
    }
    /*
    @Builder
    public DayTask(Long groupId, Long day, List<Task> tasks, Boolean isChecked) {
        this.groupId = groupId;
        this.day = day;
        this.tasks = tasks != null ? tasks : new ArrayList<>();
        this.isChecked = isChecked;
    }

    public void addTask(Task task) {
        if (tasks == null) {
            tasks = new ArrayList<>();
        }
        tasks.add(task);
    }

    public void removeTask(Task task) {
        if (tasks != null) {
            tasks.remove(task);
        }
    }

    public void setChecked(Boolean isChecked) {
        this.isChecked = isChecked;
    }
    public void toggleChecked() {
        this.isChecked = !this.isChecked;
    }
    public void checkedupdate(Boolean isChecked) {
        this.isChecked = isChecked;
    }

     */
}
