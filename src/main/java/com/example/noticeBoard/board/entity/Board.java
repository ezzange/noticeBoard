package com.example.noticeBoard.board.entity;

import com.example.noticeBoard.reply.entity.Reply;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "boards")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column
    private String title;
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
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("id")
    private Set<Reply> replySet = new LinkedHashSet<>();

    //댓글이 게시글을 참조할 수 있는 메서드
    public void addReply(Reply reply) {
        this.replySet.add(reply);

    }
}
