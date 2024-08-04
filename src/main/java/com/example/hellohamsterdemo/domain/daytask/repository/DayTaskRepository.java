package com.example.hellohamsterdemo.domain.daytask.repository;



import com.example.hellohamsterdemo.domain.daytask.entity.DayTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DayTaskRepository extends JpaRepository<DayTask, Long> {

    List<DayTask> findByGroupIdAndDay(Long groupId, Long day);
}