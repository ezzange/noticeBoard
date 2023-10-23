package com.example.noticeBoard.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Result {
    private Integer status;

    private String message;

    public static Result ok() {
        return Result.builder()
                .status(HttpStatus.OK.value())
                .message("Operation successful : 작업을 성공적으로 처리")
                .build();
    }

    public static Result create() {
        return Result.builder()
                .status(HttpStatus.CREATED.value())
                .message("successfully created : 성공적으로 생성")
                .build();
    }
}
