package com.example.noticeBoard.reply.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.noticeBoard.reply.entity.Reply;

@Repository
public interface ReplyRepository extends JpaRepository<Reply,Long> {
}
