package com.example.noticeBoard.board.converter;

import com.example.noticeBoard.board.dto.BoardPatchRequest;
import com.example.noticeBoard.board.dto.BoardRequest;
import com.example.noticeBoard.board.dto.BoardResponse;
import com.example.noticeBoard.board.entity.Board;
import com.example.noticeBoard.reply.dto.ReplyResponse;
import com.example.noticeBoard.reply.entity.Reply;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class BoardConverter {
    public Board toEntity(BoardRequest request) {
        return Board.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .password(request.getPassword())
                .build();
    }
    public Board toEntity(BoardPatchRequest request) {
        return Board.builder()
                .id(request.getId())
                .title(request.getTitle())
                .content(request.getContent())
                .password(request.getPassword())
                .build();
    }
    public BoardResponse toResponse(Board board) {
        //비어 있는 리스트 replyResponseList를 먼저 생성 하여 board.getReplySet()이 null인 경우 에는 이를 사용 하도록 하여 NullPointerException 방지
        List<ReplyResponse> replyResponseList = new ArrayList<>();
        if (board.getReplySet() != null) {
            Set<Reply> replySet = board.getReplySet();
            List<Reply> replyList = new ArrayList<>(replySet);
            replyResponseList = replyList.stream()
                    .map(ReplyResponse::of)
                    .toList();
        }
        return BoardResponse.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .password(board.getPassword())
                .createAt(board.getCreateAt())
                .updateAt(board.getUpdateAt())
                .replyList(replyResponseList)
                .build();
    }
    public List<BoardResponse> toResponse(List<Board> list) {
        return list.stream()
                .map(
                        board -> toResponse(board)
                ).toList();
    }

}
