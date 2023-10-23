package com.example.noticeBoard.reply.converter;

import com.example.noticeBoard.reply.entity.Reply;
import org.springframework.stereotype.Component;
import com.example.noticeBoard.reply.dto.ReplyPatchRequest;
import com.example.noticeBoard.reply.dto.ReplyRequest;
import com.example.noticeBoard.reply.dto.ReplyResponse;

import java.util.List;

@Component
public class ReplyConverter {


    public Reply toEntity(ReplyRequest request) {

        return Reply.builder()
                .content(request.getContent())
                .password(request.getPassword())
                .board(request.getBoard())
                .build();
    }

    public Reply toEntity(ReplyPatchRequest request) {
        return Reply.builder()
                .id(request.getId())
                .content(request.getContent())
                .password(request.getPassword())
                .board(request.getBoard())
                .build();
    }

    public ReplyResponse toResponse(Reply reply) {

        return ReplyResponse.builder()
                .id(reply.getId())
                .content(reply.getContent())
                .password(reply.getPassword())
                .createAt(reply.getCreateAt())
                .updateAt(reply.getUpdateAt())
                .board(reply.getBoard())
                .build();

    }

    public List<ReplyResponse> toResponse(List<Reply> replyList) {
        return replyList.stream()
                .map(
                        reply -> toResponse(reply)
                ).toList();
    }
}
