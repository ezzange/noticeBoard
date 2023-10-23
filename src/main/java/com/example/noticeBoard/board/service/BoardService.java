package com.example.noticeBoard.board.service;

import com.example.noticeBoard.board.converter.BoardConverter;
import com.example.noticeBoard.board.dto.BoardPatchRequest;
import com.example.noticeBoard.board.dto.BoardRequest;
import com.example.noticeBoard.board.dto.BoardResponse;
import com.example.noticeBoard.board.entity.Board;
import com.example.noticeBoard.board.repository.BoardRepository;
import com.example.noticeBoard.reply.entity.Reply;
import com.example.noticeBoard.reply.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final BoardConverter boardConverter;
    private final ReplyRepository replyRepository;
    public BoardResponse postBoard(BoardRequest request) {
        Board board = boardConverter.toEntity(request);
        System.out.println("Entity로 변환완료");
        Board saveBoard = boardRepository.save(board);
        System.out.println("board 저장완료");
        return boardConverter.toResponse(saveBoard);

    }
    public List<BoardResponse> getAllBoard() {
        List<Board> findAllBoard = boardRepository.findAll();
        return boardConverter.toResponse(findAllBoard);
    }

    public BoardResponse getBoard(Long id) {
        Board findBoard = boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not Found"));
        return boardConverter.toResponse(findBoard);
    }

    public BoardResponse patchBoard(Long id, BoardPatchRequest request) {
        Board updateBoard = boardConverter.toEntity(request);
        //디비에 값이 있는지 체크하기 위한 예외처리
        Optional<Board> optionalBoard = Optional.ofNullable(boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not Found")));
        // 원래의 내용 값 저장
        Board board = optionalBoard.get();
        //비밀 번호 일치 여부 확인

        //요청(request) 객체의 title과 content을 확인 하여 빈 문자열이 아닌 경우(변경된 내용이 있을 경우)에만 해당 값을 게시글 객체에 설정
        if (request.getTitle() != null && !request.getTitle().isEmpty()) {
            //널값과 빈 문자열 인지 확인
            board.setTitle(request.getTitle());
        } else {
            // 요청 객체의 내용이 비어 있는 경우, 원래의 내용 값으로 설정
            board.setTitle(board.getTitle());
        };
        if (request.getContent() != null && !request.getContent().isEmpty()) {
            board.setContent(request.getContent());
        } else {
            board.setContent(board.getContent());
        }
        Set<Reply> replySet = board.getReplySet();
        for (Reply reply : replySet) {
            replyRepository.save(reply);
        }
        Board saveBoard = boardRepository.save(board);

        return boardConverter.toResponse(saveBoard);
    }

    public void deleteBoard(Long id,String password) {
        Optional<Board> optionalBoard = boardRepository.findById(id);
        //// 게시글 존재 여부 확인
        if (optionalBoard.isPresent()) {
            Board board = optionalBoard.get();
            // 비밀 번호 일치 여부 확인
            if (board.getPassword().equals(password)) {
                boardRepository.deleteById(id);
            } else { // 비밀 번호가 일치 하지 않을 경우 예외 처리
                throw new RuntimeException("Password does not match");
            }
        } else {
            throw new RuntimeException("Board not found");
        }
    }
}
