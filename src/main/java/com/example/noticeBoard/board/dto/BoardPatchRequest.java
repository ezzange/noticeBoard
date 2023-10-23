package com.example.noticeBoard.board.dto;

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
public class BoardPatchRequest {
    @NotNull
    @Positive
    private Long id;
    private String title;
    private String content;
    @NotBlank
    private String password;
}
