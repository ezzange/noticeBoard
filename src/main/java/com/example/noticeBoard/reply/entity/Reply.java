package com.example.noticeBoard.reply.entity;

import com.example.noticeBoard.board.entity.Board;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "Reply")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column
    private String content;

    @Column
    private String password;

    @Column(nullable = false,insertable = false, updatable = false,
            columnDefinition = "datetime default CURRENT_TIMESTAMP")
    @CreatedDate
    private LocalDateTime createAt;
    @Column(nullable = false,insertable = false,updatable = false,
            columnDefinition = "datetime default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP")
    @LastModifiedDate
    private LocalDateTime updateAt;

    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "BOARD_ID")
    private Board board;

    //게시글이 댓글을 참조할 수 있는 메서드
    public void addBoard(Board board) {
        this.board = board;
        board.addReply(this); //게시글에 현재 댓글을 추가
    }


}
