package com.example.noticeBoard.board.controller;

import com.example.noticeBoard.board.dto.BoardPatchRequest;
import com.example.noticeBoard.board.dto.BoardRequest;
import com.example.noticeBoard.board.dto.BoardResponse;
import com.example.noticeBoard.board.service.BoardService;
import com.example.noticeBoard.dto.ResponseDto;
import com.example.noticeBoard.dto.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    public ResponseEntity<ResponseDto<BoardResponse>> postBoard(
            @RequestBody BoardRequest request
    ){
        BoardResponse response = boardService.postBoard(request);
        System.out.println("service 정상 처리");
        Result result = Result.create();
        ResponseDto<BoardResponse> dto = ResponseDto.of(response,result);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(dto);
    }
    //ResponseEntity<?> -> data타입을 정확히 명명
    @GetMapping
    public ResponseEntity<ResponseDto<List<BoardResponse>>> getAllBoard() {
        List<BoardResponse> responseList = boardService.getAllBoard();
        ResponseDto<List<BoardResponse>> dto = ResponseDto.of(responseList, Result.ok());
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<BoardResponse>> getBoard(
            // URI에 변수명으로 처리하기 위해 사용
            @PathVariable Long id
    ) {
        BoardResponse response = boardService.getBoard(id);
        ResponseDto<BoardResponse> dto = ResponseDto.of(response, Result.ok());
        return ResponseEntity.ok(dto);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<ResponseDto<BoardResponse>> patchBoard(
            @PathVariable Long id,
            BoardPatchRequest request) {

        BoardResponse response = boardService.patchBoard(id, request);
        Result result = Result.create();
        ResponseDto<BoardResponse> dto = ResponseDto.of(response, result);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(dto);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto<BoardResponse>> deleteBoard(
            @PathVariable Long id,
            String password
    ) {
        boardService.deleteBoard(id,password);
        return ResponseEntity.ok(ResponseDto.of(Result.ok()));
    }


}
