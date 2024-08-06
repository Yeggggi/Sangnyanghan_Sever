package com.example.hellohamsterdemo.domain.image.repository;


import com.example.hellohamsterdemo.domain.image.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findByTaskId(Long taskId);
}
