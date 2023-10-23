package com.example.noticeBoard.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseDto<T> {
    private Result result;
    private T data;

    public static <T> ResponseDto<T> of(T data, Result result) {
        ResponseDto<T> response = new ResponseDto<>();
        response.result = result;
        response.data = data;
        return response;
    }

    public static <T> ResponseDto<T> of(Result result) {
        ResponseDto<T> response = new ResponseDto<>();
        response.result = result;
        response.data = null;
        return response;

    }
}
