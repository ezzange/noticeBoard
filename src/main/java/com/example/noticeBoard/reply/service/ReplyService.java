package com.example.noticeBoard.reply.service;

import com.example.noticeBoard.reply.entity.Reply;
import com.example.noticeBoard.reply.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.noticeBoard.reply.converter.ReplyConverter;
import com.example.noticeBoard.reply.dto.ReplyPatchRequest;
import com.example.noticeBoard.reply.dto.ReplyRequest;
import com.example.noticeBoard.reply.dto.ReplyResponse;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReplyService {
    private final ReplyRepository replyRepository;
    private final ReplyConverter replyConverter;


    public ReplyResponse postReply(ReplyRequest request) {
        Reply reply = replyConverter.toEntity(request);
        Reply saveReply = replyRepository.save(reply);
        return replyConverter.toResponse(saveReply);
    }

    public List<ReplyResponse> getAllReply() {
        List<Reply> findAllReply = replyRepository.findAll();
        return replyConverter.toResponse(findAllReply);
    }

    public ReplyResponse getReply(Long id) {
        Reply findReply = replyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not Found"));
        return replyConverter.toResponse(findReply);
    }

    public ReplyResponse patchReply(ReplyPatchRequest request) {
        Reply reply = replyConverter.toEntity(request);
//        replyRepository.findAllById(request.getBoard())
//                .orElseThrow(() -> new RuntimeException("Not Found"));

        Reply updateReply = replyRepository.save(reply);
        return replyConverter.toResponse(updateReply);
    }

    public void deleteReply(Long id) {
        replyRepository.deleteById(id);
    }
}
