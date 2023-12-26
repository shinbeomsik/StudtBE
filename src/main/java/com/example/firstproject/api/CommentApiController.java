package com.example.firstproject.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.service.CommentService;

import lombok.experimental.PackagePrivate;

@RestController //REST 컨트롤러 선언
public class CommentApiController {
	
	@Autowired
	private CommentService commentService; // 댓글 서비스 객체 주입
	
	// 1.댓글 조회
	@GetMapping("/api/articles/{articleId}/comments") //댓글 조회 요청 접수
	public ResponseEntity<List<CommentDto>> comments(@PathVariable Long articleId){ //메서드 생성
		// 서비스의 위임
		List<CommentDto> dtos = commentService.comments(articleId);
		// 결과 응답
		return ResponseEntity.status(HttpStatus.OK).body(dtos); //null 반환
		//return null; //null 반환
	}
    // 2.댓글 생성
	@PostMapping("/api/articles/{articleId}/comments") //댓글 생성 요청 접수
	public ResponseEntity<CommentDto> create(@PathVariable Long articleId,@RequestBody CommentDto dto){
		
		// 서비스에 위임
		CommentDto createDto = commentService.create(articleId,dto);
		// 결과 응답
		return ResponseEntity.status(HttpStatus.OK).body(createDto);
	}
	
    // 3.댓글 수정
	@PatchMapping("/api/comments/{id}") // 댓글 수정 요청 접수
	public ResponseEntity<CommentDto> update(@PathVariable Long id,@RequestBody CommentDto dto){
		//서비스에 위임
		CommentDto updateDto = commentService.update(id,dto);
		//결과 응답
		return ResponseEntity.status(HttpStatus.OK).body(updateDto);
	}
    // 4.댓글 삭제
	@DeleteMapping("/api/comments/{id}") // 댓글 삭제 요청 접수
	public ResponseEntity<CommentDto> delete (@PathVariable Long id){
		//서비스에 위임
		CommentDto deleteDto = commentService.delete(id);
		//결과 응답
		return ResponseEntity.status(HttpStatus.OK).body(deleteDto);
	}
}
