package edu.pnu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
	
	@GetMapping("/hello")
	public void hello(Model model) {
		model.addAttribute("greeting", "Hello 타임리프");
	}
	

	

	@GetMapping("/home")
	public String home() {
		// /WEB-INF/board/home.jsp
		return "home";
	}
	
	@GetMapping("/home1")
	public String home1() {
		// /WEB-INF/board/home1.jsp
		return "home1";
	}
	
	// webapp 이 웹 페이지의 루트이다.
	// 외부에서 경로로 직접 접근은 되지 않는다.
	// http://localhost:8080//WEB-INF/board/home1.jsp	// 404 에러 발생
	// http://localhost:8080/home1	// 정상 접속
	
	@GetMapping("/model")
	public String model(Model model) {
		model.addAttribute("data", "홍길동");
		
		return "model";
	}
	
}
