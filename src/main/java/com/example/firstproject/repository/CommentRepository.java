package com.example.firstproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.firstproject.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
	// 특정 게시글의 모든 댓글 조회
	//@Query(value =
	//       "SELECT * " +
	//        "FROM comment " +
	//       "WHERE article_id = :articleId", 
	//      nativeQuery = true)  이렇게 +연산자로 작성도 가능 
	@Query(value = "SELECT * FROM comment WHERE article_id = :articleId" , nativeQuery = true) // value 속성에 실행하려는 쿼리 작성
	List<Comment> findByArticleId(Long articleId);
	// 특정 닉네임의 모든 댓글 조회
	List<Comment> findByNickname(String nickname);
}
