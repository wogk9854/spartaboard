package com.sparta.board.controller;

import com.sparta.board.dto.BoardListResponseDto;
import com.sparta.board.dto.BoardRequestDto;
import com.sparta.board.dto.BoardResponseDto;
import com.sparta.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;


    @GetMapping("/")
    public ModelAndView home() {
        return new ModelAndView("board");
    }
    //생성
    @PostMapping("/board")
    public BoardResponseDto createBoard(@RequestBody BoardRequestDto requestDto) {

        return boardService.createBoard(requestDto);
    }

    //조회
    @GetMapping("/board")
    public BoardListResponseDto readBoard() {

        return (BoardListResponseDto) boardService.readBoard();
    }

    //수정
    @PutMapping("/update/{id}")
    public BoardResponseDto update(@PathVariable Long id, @RequestBody BoardRequestDto requestDto) {
        return boardService.update(id, requestDto);
    }

    //삭제
    @DeleteMapping("/delete/{id}")
    public String deleteBoard(@PathVariable Long id, @RequestBody BoardRequestDto requestDto) {
        return boardService.deleteBoard(id, requestDto);
    }

    //상세조회
    @GetMapping("/detail/{id}")
    public BoardResponseDto detailBoard(@PathVariable Long id) {
        return boardService.detailBoard(id);
    }


}
