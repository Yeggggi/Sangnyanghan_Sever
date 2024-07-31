package com.example.hellohamsterdemo.domain.test.controller;

import com.example.hellohamsterdemo.domain.test.entity.Board;
import com.example.hellohamsterdemo.domain.test.dto.BoardCreateDTO;
import com.example.hellohamsterdemo.domain.test.repository.BoardRepository;
import com.example.hellohamsterdemo.domain.test.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
@CrossOrigin(origins = "http://localhost:") // 연결할 프론트의 주소
public class BoardController {

    private final BoardService boardService;
    private final BoardRepository boardRepository;

    @PostMapping("/save")
    public ResponseEntity<Board> saveBoard(@RequestBody BoardCreateDTO dto) {

        Board savedBoard = boardService.saveBoard(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedBoard);
    }

    // 프론트로 모든 게시물 정보를 보낼 때 사용하는 것
// service에서 readBoardAll함수를 통해서 값을 모두 불러온다
    @GetMapping("/read")

    public ResponseEntity<List<Board>> readBoard() {

        List<Board> board = boardService.readBoardAll();

        return ResponseEntity.status(HttpStatus.CREATED).body(board);
    }

    //특정 게시물을 id별로 하여 불러오는 것
//@PathVariable  -> {}로 둘러싸인 경로 변수를 표시하기 위해 메서드에 매개변수에서 사용된다
// requestBody가 아니라 pathvariable을 쓴 이유는
//requestBody를 쓰면 dto 형태로 담아서 모든 변수를 다 가지고 와서 그중에 id를 또 찾아서 해야하지만 pathVariable을 통해서 id만 특정해서 할 수 있도록 하기 위해
// service에서 getBoardById 함수를 통해서 특정 게시물 id를 불러옴
    @GetMapping("/read/{id}")

    public ResponseEntity<Optional<Board>> readBoard(@PathVariable Long id) {

        Optional<Board> board = boardService.getBoardById(id);

        return ResponseEntity.status(HttpStatus.CREATED).body(board);
    }
}
