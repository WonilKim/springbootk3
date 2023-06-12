package edu.pnu.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j	// Log 제공
@RestController
public class HomeController {
	
	public HomeController() {
		System.out.println("HomeController가 생성되었습니다.");
		
		log.error("(error)HomeController가 생성되었습니다.");
		log.warn("(warn)HomeController가 생성되었습니다.");
		log.info("(info)HomeController가 생성되었습니다.");
		log.debug("(debug)HomeController가 생성되었습니다.");
		log.trace("(trace)HomeController가 생성되었습니다.");
		
		//application.properties 파일에서 로그 출력 레벌을 설정할 수 있음
		
	}
	
	@GetMapping("/hello")
	public String hello(String name) {
		return "hello : " + name;
		
	}

}
