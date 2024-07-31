package com.example.hellohamsterdemo.domain.test.dto;

import com.example.hellohamsterdemo.domain.test.entity.Board;

import java.util.ArrayList;
import java.util.List;

//record를 통해 불변인거를 표시
//getter, setter, toString 등 모든 걸 다 알아서 해줌
//dto는 전달만 하니까, 불변이니까 이렇게 써도 됨
//다른 거는 변하고 하니까 안됨
public record BoardCreateDTO(String title, String author, String content) {


    /*
    원래라면

    public class BoardCreateDTO{
    private final String title;
    private final String author;
    private final String content;

       public BoardCreateDTO(String title, String content, String author) {
          this.title = title;
          this.content = content;
          this.author = author;



      }
    }
    */
    // Board를 dto로 변환
    // builder는 생성자와 다르게 순서 상관없다 -> 지정하기 때문
    public Board toEntity() {
        return Board.builder().
                title(title).
                author(author).
                content(content).
                build();
    }


}
