package com.example.hellohamsterdemo.domain.test.service;

import com.example.hellohamsterdemo.domain.test.entity.Board;
import com.example.hellohamsterdemo.domain.test.dto.BoardCreateDTO;
import com.example.hellohamsterdemo.domain.test.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

@RequiredArgsConstructor
@Service // Service annotation
public class BoardService {


    private final BoardRepository boardRepository;
    // 불변 객체 final로 명시 꼭

    public Board saveBoard(BoardCreateDTO dto) {
        // repository에 있는 기능을 이용한다 불러야 함
        // saveBoard를 해라고 하야 하니까

        Board board = dto.toEntity();
        // dto를 entity로 바꾸고
        boardRepository.save(board);

        return board;
    }

    public Optional<Board> getBoardById(Long id){
        return boardRepository.findById(id);
    }

    public List<Board> readBoardAll(){
        return boardRepository.findAll();
    }
}

