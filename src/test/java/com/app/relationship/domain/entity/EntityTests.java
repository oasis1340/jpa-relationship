package com.app.relationship.domain.entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Slf4j
@Transactional @Commit
class EntityTests {

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void saveBoardTest(){
        User user = new User();
        user.setUserEmail("test123@gmail.com");
        user.setUserPassword("1234");
        user.setUserName("고길동");
        user.setUserAge(17);

        Board board1 = new Board();
        board1.setBoardTitle("Hello JPA!😎");
        board1.setBoardContent("JPA 1:N 관계 맺기");
        board1.setUser(user);

        Board board2 = new Board();
        board2.setBoardTitle("오늘 점심 뭐 먹지?");
        board2.setBoardContent("답정 떡볶이!");
        board2.setUser(user);

        entityManager.persist(user);
        entityManager.persist(board1);
        entityManager.persist(board2);
    }

//    게시글을 통해 유저정보를 조회
    @Test
    public void findBoardTest(){
        Board board = entityManager.find(Board.class, 1L);
        log.info("게시글 전체 내용: {}", board.toString());

//    객체 그래프 탐색
        log.info("게시글 작성자의 이름: {}", board.getUser().getUserName());
    }

    @Test
    public void findBoardListTest(){
        List<User> userList = entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
        log.info("userList: {}", userList.toString());
        userList.stream().forEach(u -> u.getBoard().stream().forEach(board -> board.getBoardTitle()));
    }

//    1번 회원이 작성한 게시글 전체를 조회
//    JPA QL
    @Test
    public void findTest2(){
//        ON절 제한
        List<Board> foundBoardList = entityManager.createQuery(
                        "SELECT b FROM Board b JOIN b.user u WHERE u.userName = :userName", Board.class
                )
                .setParameter("userName", "고길동")
                .getResultList();

        foundBoardList.stream().map(Board::toString).forEach(log::info);
    }


//    작성자 변경
    @Test
    public void updateTest(){
        User newUser = new User();
        newUser.setUserEmail("ksh123@gmail.com");
        newUser.setUserPassword("1234");
        newUser.setUserName("김길동");
        newUser.setUserAge(17);
        entityManager.persist(newUser);

        Board foundBoard = entityManager.find(Board.class, 2L);
        foundBoard.setUser(newUser);
    }

//    새로운 유저가 작성한 게시글을 조회하기
    @Test
    public void updatedFindBoardTest(){
        String query = "SELECT b FROM Board b JOIN b.user u WHERE u.userName = :userName";
        List<Board> resultList = entityManager.createQuery(query, Board.class).setParameter("userName", "김길동").getResultList();
        log.info("결과 : {}", resultList.toString());
    }

//    삭제
    @Test
    public void deleteTest(){
//        유저 조회(영속)
        User user = entityManager.find(User.class, 1L);
        String query = "SELECT b FROM Board b WHERE b.user.id = :userId";
        Board board = entityManager.createQuery(query, Board.class).setParameter("userId", 1L).getSingleResult();
        log.info("결과: {}", board.toString());

//        관계 해제
        board.setUser(null);
//        삭제
        entityManager.remove(user);
    }


}












