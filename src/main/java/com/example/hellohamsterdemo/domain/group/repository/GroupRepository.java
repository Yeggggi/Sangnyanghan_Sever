package com.example.hellohamsterdemo.domain.group.repository;

import com.example.hellohamsterdemo.domain.group.dto.GroupReadDTO;
import com.example.hellohamsterdemo.domain.group.entity.TodoGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.Optional;

// JpaRepository 관계형 디비만 사용가능
// java persistent api
// 자바는 자바 일만, 백엔드가 데이터베이스가 아니므로
// 자바 문법에만 집중하도로 ㄱ연결
//ORM-> 객체 매핑, 자바 코드만 신경 쓰면 디비에 맞게 자바 문법을 바꿔주는 역할
// ORM 안에 JpaRepository라이브러리가 있고
// jpaRepository 중에 가장 많이 쓰는게 hibernate -> 실질적으로 바꿔준다



// JpaRepository<엔티티 이름, Pk의 타입>
// Jpa단점 -> mapping 자바 문법을 다 sql쿼리로 mapping하지만
// 복잡한건 mapping 못해주고 다 커스텀 해야함
// 커스텀 해야 하면 안에서 만들어야 됨


//Optional<Entity> 함수 형태
// null 을 포함한 값을 리턴해서 에러가 아니라 없다고 리턴 가능

@Repository
public interface GroupRepository extends JpaRepository<TodoGroup,Long> {

//    Optional<TodoGroup> findGroupByMemberId(Long memberId);

    @Query("SELECT new com.example.hellohamsterdemo.domain.group.dto.GroupReadDTO(g.startDate, g.endDate, g.maxDay) FROM TodoGroup g WHERE g.id = :id")
    Optional<GroupReadDTO> findGroupByGroupId(@Param("id") Long id);

}




