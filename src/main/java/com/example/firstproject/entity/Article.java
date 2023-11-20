package com.example.firstproject.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@ToString
public class Article {
	@Id
	@GeneratedValue
	private Long id;
	@Column
	private String title;
	@Column
	private String content;
	
	
	
}
