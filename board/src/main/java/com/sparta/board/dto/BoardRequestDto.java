package com.sparta.board.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@NoArgsConstructor
@Setter
public class BoardRequestDto {
    private String title;
    private String username;
    private String content;
    private String password;


}
