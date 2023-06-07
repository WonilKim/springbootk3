package com.rubypaper.domain;

import java.util.Date;

import org.springframework.web.bind.annotation.GetMapping;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter		// 자동으로 Getter 생성
@Setter		// 자동으로 Setter 생성
@ToString	// 자동으로 ToString 생성
@Builder	// Build 패턴을 제공함
@NoArgsConstructor	// 자동으로 Argument가 없는 생성자 생성
@AllArgsConstructor	// 자동으로 모든 Argument를 받는 생성자 생성
public class BoardVO {
	
	private int seq;
	private String title;
	private String writer;
	private String content;
	private Date createData = new Date();
	private int cnt = 0;
	
}
