package com.example.noticeBoard.reply.controller;

import com.example.noticeBoard.dto.ResponseDto;
import com.example.noticeBoard.dto.Result;
import com.example.noticeBoard.reply.dto.ReplyPatchRequest;
import com.example.noticeBoard.reply.dto.ReplyResponse;
import com.example.noticeBoard.reply.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.noticeBoard.reply.dto.ReplyRequest;

import java.util.List;

@RestController
@RequestMapping("/api/reply")
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping
    public ResponseEntity<ResponseDto<ReplyResponse>> postReply(ReplyRequest request) {
        ReplyResponse response = replyService.postReply(request);
        Result result = Result.create();
        ResponseDto<ReplyResponse> dto = ResponseDto.of(response, result);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(dto);
    }

    @GetMapping
    public ResponseEntity<ResponseDto<List<ReplyResponse>>> getAllReply() {
        List<ReplyResponse> responseList = replyService.getAllReply();
        ResponseDto<List<ReplyResponse>> dto = ResponseDto.of(responseList, Result.ok());
        return ResponseEntity.ok(dto);
    }

    @GetMapping("{/id}")
    public ResponseEntity<ResponseDto<ReplyResponse>> getReply(
            @PathVariable Long id
    ) {
        ReplyResponse response = replyService.getReply(id);
        ResponseDto<ReplyResponse> dto = ResponseDto.of(response, Result.ok());
        return ResponseEntity.ok(dto);
    }

    @PatchMapping("{/id}")
    public ResponseEntity<ResponseDto<ReplyResponse>> patchReply(ReplyPatchRequest request) {
        ReplyResponse response= replyService.patchReply(request);
        Result result = Result.create();
        ResponseDto<ReplyResponse> dto = ResponseDto.of(response, result);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(dto);
    }

    @DeleteMapping("{/id}")
    public ResponseEntity<ResponseDto<ReplyResponse>> deleteReply(
            @PathVariable Long id
    ) {
        replyService.deleteReply(id);
        ResponseDto<ReplyResponse> dto = ResponseDto.of(Result.ok());
        return ResponseEntity.ok(dto);
    }

}
