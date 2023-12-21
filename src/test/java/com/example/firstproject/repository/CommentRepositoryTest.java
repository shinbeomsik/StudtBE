package com.example.firstproject.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;

@DataJpaTest // 해당 클래스를 JPA와 연동해 테스팅
public class CommentRepositoryTest {
	
	@Autowired
	CommentRepository commentRepository; // commentRepository 객체 주입
	
	@Test
	@DisplayName("특정 게시글의 모든 댓글 조회")
	void findByArticleId() {
		/* Case 1: 4번 게시글의 모든 댓글 조회 */
		{
			// 1. 입력 데이터 준비
			Long articleId = 4L; // 조회할 id
			// 2. 실제 데이터
			List<Comment> comments = commentRepository.findByArticleId(articleId);
			// 3. 예상 데이터 
			Article article = new Article(4L, "당신의 취미는?", "댓글 작성해 주세요"); //부모 게시글 객체 새성
			
			Comment a = new Comment(1L, article, "이제", "수다"); //댓글 객체 생성
			Comment b = new Comment(2L, article, "빨리", "운동"); //댓글 객체 생성
			Comment c = new Comment(3L, article, "오네", "게임"); //댓글 객체 생성
			
			List<Comment> expected = Arrays.asList(a,b,c); //댓글 객체 합치기
			// 4. 비교 및 검증
			assertEquals(expected.toString(), comments.toString() , "4번 글의 모든 댓글을 출력!" );
		}
		/* Case 2: 1번 게시글의 모든 댓글 조회 */
		{
			// 1. 입력 데이터 준비
			Long articleId = 1L; // 조회할 id
			// 2. 실제 데이터
			List<Comment> comments = commentRepository.findByArticleId(articleId);
			// 3. 예상 데이터 
			Article article = new Article(1L, "가가가가", "1111"); //부모 게시글 객체 새성
			List<Comment> expected = Arrays.asList(); 
			// 4. 비교 및 검증
			assertEquals(expected.toString(), comments.toString() , "1번 글의  댓글이 없음" );
		}
		
		/* Case 3: 9번 게시글의 모든 댓글 조회 */
		{
			// 1. 입력 데이터 준비
			Long articleId = 9L; // 조회할 id
			// 2. 실제 데이터
			List<Comment> comments = commentRepository.findByArticleId(articleId);
			// 3. 예상 데이터 
			Article article = null; //부모 게시글 객체 새성
			List<Comment> expected = Arrays.asList(); 
			// 4. 비교 및 검증
			assertEquals(expected.toString(), comments.toString() , "9번 게시글이 존재하지않아서 댓글 없음" );
		}
		
		/* Case 4: 999번 게시글의 모든 댓글 조회 */
		{
			// 1. 입력 데이터 준비
			Long articleId = 999L; // 조회할 id
			// 2. 실제 데이터
			List<Comment> comments = commentRepository.findByArticleId(articleId);
			// 3. 예상 데이터 
			Article article = null; //부모 게시글 객체 새성
			List<Comment> expected = Arrays.asList(); 
			// 4. 비교 및 검증
			assertEquals(expected.toString(), comments.toString() , "999번 게시글이 존재하지않아서 댓글 없음" );
		}
		
		/* Case 4: -1번 게시글의 모든 댓글 조회 */
		{
			// 1. 입력 데이터 준비
			Long articleId = -1L; // 조회할 id
			// 2. 실제 데이터
			List<Comment> comments = commentRepository.findByArticleId(articleId);
			// 3. 예상 데이터 
			Article article = null; //부모 게시글 객체 새성
			List<Comment> expected = Arrays.asList(); 
			// 4. 비교 및 검증
			assertEquals(expected.toString(), comments.toString() , "-1번 게시글이 존재하지않아서 댓글 없음" );
		}
		
		
	}
	
	@Test
	@DisplayName("특정 닉네임의 모든 댓글 조회")
	void findByNickname() {
	    /* Case 1: "이제"의 모든 댓글 조회 */
	    {
	        // 1. 입력 데이터 준비
	    	String nickname = "이제";
	        // 2. 실제 데이터
	    	List<Comment> comments = commentRepository.findByNickname(nickname); 
	        // 3. 예상 데이터
	    	
	    	Comment a = new Comment(1L, new Article(4L, "당신의 취미는?", "댓글 작성해 주세요"), nickname, "수다"); //댓글 객체 생성(부모 객체는 각 필드에 따로 생성)
	    	Comment b = new Comment(4L, new Article(5L, "당신이 좋아하는 음식은?", "댓글 작성해 주세요"), nickname, "스시");
	    	Comment c = new Comment(7L, new Article(6L, "당신의 좋아하는 색깔은?", "댓글 작성해 주세요"), nickname, "파랑");
	    	
	    	List<Comment> expected = Arrays.asList(a,b,c); //댓글 객체 합치기
	        // 4. 비교 및 검증
	    	assertEquals(expected.toString(),comments.toString(), "이제의 모든 댓글을 출력!" );
	    }
	    
	    /* Case 2: "빨리"의 모든 댓글 조회 */
	    {
	        // 1. 입력 데이터 준비
	    	String nickname = "빨리";
	        // 2. 실제 데이터
	    	List<Comment> comments = commentRepository.findByNickname(nickname); 
	        // 3. 예상 데이터
	    	
	    	Comment a = new Comment(2L, new Article(4L, "당신의 취미는?", "댓글 작성해 주세요"), nickname, "운동"); //댓글 객체 생성(부모 객체는 각 필드에 따로 생성)
	    	Comment b = new Comment(5L, new Article(5L, "당신이 좋아하는 음식은?", "댓글 작성해 주세요"), nickname, "치킨");
	    	Comment c = new Comment(8L, new Article(6L, "당신의 좋아하는 색깔은?", "댓글 작성해 주세요"), nickname, "노랑");
	    	
	    	List<Comment> expected = Arrays.asList(a,b,c); //댓글 객체 합치기
	        // 4. 비교 및 검증
	    	assertEquals(expected.toString(),comments.toString(), "이제의 모든 댓글을 출력!" );
	    }
	    
	    /* Case 3: null 의 모든 댓글 조회 */
	    {
	        // 1. 입력 데이터 준비
	    	String nickname = null;
	        // 2. 실제 데이터
	    	List<Comment> comments = commentRepository.findByNickname(nickname); 
	        // 3. 예상 데이터
	    	List<Comment> expected = Arrays.asList(); //댓글 객체 합치기
	        // 4. 비교 및 검증
	    	assertEquals(expected.toString(),comments.toString(), "null 의 댓글은 존재하지않음" );
	    }
	    
	    /* Case 4: "" 의 모든 댓글 조회 */
	    {
	        // 1. 입력 데이터 준비
	    	String nickname = "";
	        // 2. 실제 데이터
	    	List<Comment> comments = commentRepository.findByNickname(nickname); 
	        // 3. 예상 데이터
	    	List<Comment> expected = Arrays.asList(); //댓글 객체 합치기
	        // 4. 비교 및 검증
	    	assertEquals(expected.toString(),comments.toString(), "\"\" 의 댓글은 존재하지않음" );
	    	
	    }
	}
	
	
	
}
