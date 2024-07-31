package com.example.hellohamsterdemo.domain.test.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;  //lombok -> annotation만드는거
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.sql.Update;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
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

public class Board {

    //생성자
    /*
    public Board(String title,String content, String author){
        this.title=title;
        this.content=content;
        this.author=author;

    }
    */



    //캡슐화로 인해서 여기서만 수정되야 하므로
    //private

    @Id // primary key로 선언해주겠다. id로 해주었으므로 null이 안된다
    @GeneratedValue(strategy = GenerationType.IDENTITY) //instance가 하나 생성될때 마다
    // 프론트에서 지정안해줘도 +1되서 값을 지정해주겠다.
    private long id;

    // 명시 안해줘도 같은거를 찾는다
    // title이라는 컬럼을 찾아서 null이 아니도록 해준다.
    // db테이블이랑 mapping,
    // 이런 상황에서 title 안 보내면 db error
    @Column(name="title", nullable = false )
    private String title;
    @Column(name="content", nullable = false )
    private String content;


    //우리는 db 신경 안쓴다
    // db랑 entity랑 이름 다를 때 찾기 위해서
    // name="author"로
    //mapping author를 한다.
    private String author;

//    @CreatedDate //생성날짜 구나 라는 것을 암
//    @Column(name = "created_at")
//    private LocalDateTime createdAt;
//    //camelCase하면 db에서 언더 바로 읽는다(_);
//
//    @LastModifiedDate // 수정시간
//    @Column(name = "updated_at")
//    private LocalDateTime updateAt;


    @Builder //Setter역할 한다
    public Board(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }


    //수정 함수, DB안에 있는 객체를 수정 하니까
    // 객체 안에 수정은 이 안에서 이루어져야 한다.
    public void update(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;

    }


}