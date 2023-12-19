package com.example.firstproject.service;

import org.junit.jupiter.api.Test; //Test 패키지 임포트
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;

import static org.junit.jupiter.api.Assertions.*; // 앞으로 사용할 수 있는 패키지 임포트

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest // 해당 클래스를 스프링 부트와 연동해 테스트
public class ArticleServiceTest {
	
	@Autowired
	ArticleService articleService; // 객체 주입
	
	@Test //해당 메서드가 테스트 코드임을 선언
	void index() {
		// 1. 예상 데이터
		Article a = new Article(1L , "가가가가" , "1111"); //예상 데이터 객체로 저장
		Article b = new Article(2L , "나나나나" , "2222"); //예상 데이터 객체로 저장
		Article c = new Article(3L , "다다다다" , "3333"); //예상 데이터 객체로 저장
		List<Article> expected = new ArrayList<Article>(Arrays.asList(a,b,c)); // a,b,c 합치기
		
		// 2. 실제 데이터
		List<Article> articles = articleService.index();
		// 3. 비교 및 검증
		
		assertEquals(expected.toString(), articles.toString());
	}
	
	@Test 
	void show_성공_존재하는_id_입력() {
		// 1. 예상 데이터
		Long id = 1L; //예상 데이터 저장
		Article expected = new Article(id,"가가가가","1111"); //예상 데이터 저장
		// 2. 실제 데이터
		Article article = articleService.show(id); //실제 데이터 저장
		// 3. 비교 및 검증
		assertEquals(expected.toString(),article.toString() ); //비교 	
	}
	
	@Test 
	void show_실패_존재하는_id_입력() {
		// 1. 예상 데이터
		Long id = -1L; //예상 데이터 (null)저장
		Article expected = null; //예상 데이터(null) 저장
		// 2. 실제 데이터
		Article article = articleService.show(id); //실제 데이터 저장
		// 3. 비교 및 검증
		assertEquals(expected,article); //비교 
	}
	
	@Test 
	@Transactional
	void create_성공_title과_content만_있는_dto_입력() {
		// 1. 예상 데이터
		String title = "라라라라"; //title 값 임의 배정
		String content = "4444"; // content 값 임의 배정
		ArticleForm dto = new ArticleForm(null , title , content); //dto 생성
		Article expected = new Article(4L , title , content); // 예상 데이터 저장
		// 2. 실제 데이터
		Article article = articleService.create(dto); //실제 데이터 저장
		// 3. 비교 및 검증
		assertEquals(expected.toString(), article.toString()); //비교
	}
	
	@Test 
	@Transactional
	void create_실패_id가_포함된_dto_입력() {
		// 1. 예상 데이터
		Long id = 4L; //id 값 임의 배정
		String title = "라라라라"; //title 값 임의 배정
		String content = "4444"; // content 값 임의 배정
		ArticleForm dto = new ArticleForm(id , title , content); //dto 생성
		Article expected = null; // 예상 데이터 저장
		// 2. 실제 데이터
		Article article = articleService.create(dto); //실제 데이터 저장
		// 3. 비교 및 검증
		assertEquals(expected, article); //비교
	}
	
	@Test 
	@Transactional
	void update_성공_존재하는_id와_title_content가_있는_dto_입력() {
		// 1. 예상 데이터
		Long id = 1L; //id 값 임의 배정
		String title = "가나다라"; //title 값 임의 배정
		String content = "1234"; // content 값 임의 배정
		ArticleForm dto = new ArticleForm(id , title , content); //dto 생성
		Article expected = new Article(id , title , content); // 예상 데이터 저장
		// 2. 실제 데이터
		Article article = articleService.update(id, dto); //실제 데이터 저장
		// 3. 비교 및 검증
		assertEquals(expected.toString(), article.toString()); //비교
	}
	
	@Test 
	@Transactional
	void update_성공_존재하는_id와_title만_있는_dto_입력() {
		// 1. 예상 데이터
		Long id = 1L; //id 값 임의 배정
		String title = "AAAA"; //title 값 임의 배정
		String content = null; // content 값 임의 배정
		ArticleForm dto = new ArticleForm(id , title , content); //dto 생성
		Article expected = new Article(1L , "AAAA" , "1111"); // 예상 데이터 저장
		// 2. 실제 데이터
		Article article = articleService.update(id, dto); //실제 데이터 저장
		// 3. 비교 및 검증
		assertEquals(expected.toString(), article.toString()); //비교
	}
	
	@Test 
	@Transactional
	void update_실패_존재하는_않는_id의_dto_입력() {
		// 1. 예상 데이터
		Long id = -1L; //id 값 임의 배정
		String title = "가나다라"; //title 값 임의 배정
		String content = "1234"; // content 값 임의 배정
		ArticleForm dto = new ArticleForm(id , title , content); //dto 생성
		Article expected = null; // 예상 데이터 저장
		// 2. 실제 데이터
		Article article = articleService.update(id, dto); //실제 데이터 저장
		// 3. 비교 및 검증
		assertEquals(expected, article); //비교
	}
	
	@Test 
	@Transactional
	void delete_성공_존재하는_id_입력() {
		// 1. 예상 데이터
		Long id = 1L; //id 값 임의 배정
		Article expected = new Article(id , "가가가가" , "1111"); //예상 데이터 저장
		// 2. 실제 데이터
		Article article = articleService.delete(id); //실제 데이터 저장
		// 3. 비교 및 검증
		assertEquals(expected.toString(), article.toString()); //비교
	}
	
	@Test 
	@Transactional
	void delete_실패_존재하지_않는_id_입력() {
		// 1. 예상 데이터
		Long id = -1L; //id 값 임의 배정
		Article expected = null; //예상 데이터 저장
		// 2. 실제 데이터
		Article article = articleService.delete(id); //실제 데이터 저장
		// 3. 비교 및 검증
		assertEquals(expected, article); //비교
	}
	
	
}
