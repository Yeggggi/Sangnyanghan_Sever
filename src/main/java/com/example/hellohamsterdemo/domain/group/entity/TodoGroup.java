package com.example.hellohamsterdemo.domain.group.entity;

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
@Getter  //@Data getter, setter 둘다 포함하고 다른 것도 포함하여 무거워지므로 , 성능 최적화 시에
//getter 함수 안만들어도 get을 쓰면 된다
@EntityListeners(AuditingEntityListener.class)
//Entity가 바뀔때 마다 감시해주겠다
// 수정된 내용만 보내주면 이 친구가 알아서 수정 날짜를 알아서 해주겠다.

@NoArgsConstructor(access = AccessLevel.PROTECTED)
// 생성자를 만들 필요 없이 접근 레벨을 protected로 해서 클래스에서만 선언
// 매개 변수 없는 생성자

public class TodoGroup { // group 정보를 post 할 때 사용


    @Id // primary key로 선언해주겠다. id로 해주었으므로 null이 안된다
    @GeneratedValue(strategy = GenerationType.IDENTITY) //instance가 하나 생성될때 마다
    // 프론트에서 지정안해줘도 +1되서 값을 지정해주겠다.
    private long id;


    @Column(name="member_id", nullable = false )
    private Long memberId;

    @Column(name = "sitter_id")
    private Long sitterId;

    @Column(name="start_date" )
    private String startDate;

    @Column(name="end_date" )
    private String endDate;

    @Column(name = "max_day")
    private Long maxDay;

    @CreatedDate //생성날짜 구나 라는 것을 암
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    //camelCase하면 db에서 언더 바로 읽는다(_);

    @LastModifiedDate // 수정시간
    @Column(name = "updated_at")
    private LocalDateTime updateAt;




    @Builder //Setter역할 한다
    public TodoGroup(Long memberId, Long sitterId, String startDate, String endDate, Long maxDay) {
        this.memberId = memberId;
        this.sitterId = sitterId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.maxDay = maxDay;
    }

//    public void update(String startDate, String endDate, Long maxDay) {
//        this.startDate = startDate;
//        this.endDate = endDate;
//        this.maxDay = maxDay;
//    }

    public void TodoGroupRead(String startDate, String endDate, Long maxDay) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.maxDay = maxDay;
    }
}