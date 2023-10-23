package com.example.noticeBoard.reply.dto;

import com.example.noticeBoard.board.entity.Board;
import com.example.noticeBoard.reply.entity.Reply;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReplyResponse {
    private Long id;
    private String content;
    private String password;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private Board board;

    public static ReplyResponse of(Reply reply) {
        return ReplyResponse.builder()
                .id(reply.getId())
                .content(reply.getContent())
                .password(reply.getPassword())
                .createAt(reply.getCreateAt())
                .updateAt(reply.getUpdateAt())
                .board(reply.getBoard())
                .build();
    }
}
