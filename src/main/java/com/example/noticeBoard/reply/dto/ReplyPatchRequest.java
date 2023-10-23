package com.example.noticeBoard.reply.dto;

import com.example.noticeBoard.board.entity.Board;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReplyPatchRequest {

    @NotNull
    @Positive
    private Board board;
    @NotNull
    @Positive
    private Long id;

    private String content;

    @NotBlank
    private String password;

}
