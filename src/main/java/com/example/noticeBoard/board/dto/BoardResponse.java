package com.example.noticeBoard.board.dto;

import com.example.noticeBoard.reply.dto.ReplyResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardResponse {
    private Long id;
    private String title;
    private String content;
    private String password;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private ReplyResponse replyResponse;
    private List<ReplyResponse> replyList;

}
