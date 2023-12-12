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

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.service.ArticleService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController // REST 컨트롤러 선언
public class ArticleApiController {

	@Autowired
	private ArticleService articleService; // 서비스 객체 주입

	@GetMapping("/api/articles") 
	public List<Article> index() {
		return articleService.index();
	}

	@GetMapping("/api/articles/{id}")
	public Article show(@PathVariable Long id) { 
		return articleService.show(id);
	}
}

//서비스 하기전에 있던 코드들
/*
 * @Autowired // 게시글 리파지터리 주입 private ArticleRepository articleRepository;
 * 
 * @GetMapping("/api/articles") //URL 요청 접수 public List<Article> index(){
 * //index() 메서드 정의
 * 
 * return articleRepository.findAll(); }
 * 
 * 
 * @GetMapping("/api/articles/{id}") public Article show(@PathVariable Long id)
 * { //show() 메서드 정의 //주소에 있는 id를 가져올수 있도록 @PathVariable을 작성 return
 * articleRepository.findById(id).orElse(null); }
 * 
 * @PostMapping("/api/articles") public Article create(@RequestBody ArticleForm
 * dto) { //create() 메서드 정의 Article article = dto.toEntity(); return
 * articleRepository.save(article); }
 * 
 * @PatchMapping("/api/articles/{id}") public ResponseEntity<Article>
 * update(@PathVariable Long id , @RequestBody ArticleForm dto ) { // 1. DTO ->
 * 엔티티 변환하기 Article article = dto.toEntity(); // dto를 엔티티로 변환
 * log.info("id: {}, article: {}", id, article.toString()); // 2. 타깃 조회하기
 * Article target = articleRepository.findById(id).orElse(null); // 3. 잘못된 요청
 * 처리하기 if(target == null || id != article.getId()) { //잘못된 요청인지 판별 // 400, 잘못된
 * 요청 응답 log.info("잘못된 요청! id: {}, article: {}", id, article.toString()); return
 * ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); } // 4. 업데이트 및 정상
 * 응답(200)하기 target.patch(article); Article updated =
 * articleRepository.save(target); //article 엔티티 DB에 저장 return
 * ResponseEntity.status(HttpStatus.OK).body(updated); }
 * 
 * @DeleteMapping("/api/articles/{id}") public ResponseEntity<Article>
 * delete(@PathVariable Long id){ // 1. 대상 찾기 Article target =
 * articleRepository.findById(id).orElse(null); // 2. 잘못된 요청 처리하기 if (target ==
 * null) { return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); } //
 * 3. 대상 삭제하기 articleRepository.delete(target); return
 * ResponseEntity.status(HttpStatus.OK).body(null); //return
 * ResponseEntity.status(HttpStatus.OK).build(); 위에랑 같은 결과 }
 */