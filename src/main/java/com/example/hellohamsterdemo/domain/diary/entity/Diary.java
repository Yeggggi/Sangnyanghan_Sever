package com.example.hellohamsterdemo.domain.diary.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)

@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class Diary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name = "day")
    private Long day;

    @Column(name = "group_id")
    private Long groupId;

    @Column(name="member_id")
    private Long memberId;

    @Column(name="sitter_id")
    private Long sitterId;

    @Column(name="title" )
    private String title;

    @Column(name="content" )
    private String content;

    @Column(name = "expire")
    private Boolean expire;

    @Column(name = "date")
    private Date date;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate // 수정시간
    @Column(name = "updated_at")
    private LocalDateTime updateAt;


    @Builder
    public Diary(Long id, Long memberId, Long sitterId, Long day, Long groupId, String title, String content, Date date, Boolean expire) {
        this.id = id;
        this.memberId = memberId;
        this.sitterId = sitterId;
        this.groupId = groupId;
        this.day = day;
        this.title = title;
        this.content = content;
        this.date = date;
        this.expire = expire;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

}

