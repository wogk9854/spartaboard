package com.sparta.board.service;

import com.sparta.board.dto.BoardListResponseDto;
import com.sparta.board.dto.BoardRequestDto;
import com.sparta.board.dto.BoardResponseDto;
import com.sparta.board.entity.Board;
import com.sparta.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional(readOnly = true)
    public BoardResponseDto detailBoard(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("없는글번호입니다.")
        );
        return new BoardResponseDto(board);
    }


    @Transactional(readOnly = true)
    public BoardListResponseDto readBoard() {
        BoardListResponseDto boardListResponseDto = new BoardListResponseDto();

        List<Board> boards = boardRepository.findAll();

        for(Board board : boards){
            boardListResponseDto.addBoard(new BoardResponseDto(board));
        }


        return boardListResponseDto;
    }

    @Transactional
    public BoardResponseDto createBoard(BoardRequestDto requestDto) {
        Board board = new Board(requestDto);
        boardRepository.save(board);

        return new BoardResponseDto(board);
    }

    @Transactional
    public BoardResponseDto update(Long id, BoardRequestDto requestDto) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 없습니다.")
        );
        boolean result = requestDto.getPassword().equals(board.getPassword());
        System.out.println(result);
        if (result) {
            board.update(requestDto);
        } else {
            System.out.println("비밀번호가일치하지않습니다.");
        }
        return new BoardResponseDto(board);
    }

    public String deleteBoard(Long id, BoardRequestDto requestDto) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 없습니다.")
        );
        boolean result = requestDto.getPassword().equals(board.getPassword());
        System.out.println(result);
        String re = "";
        if (result) {
            boardRepository.deleteById(id);
            re = "삭제완료";
        } else {
            System.out.println("비밀번호가일치하지않습니다.");
            re = "비밀번호가일치하지않습니다.";
        }
        return re;
    }
}
