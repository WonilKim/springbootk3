package edu.pnu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.service.TestService;

@RestController
public class TestController {
	
	@Autowired
	private TestService testService;
	@Autowired
	private TestService testService2;
	
	// DI시 필드에 Autowired 하는 방법과 생성자에 Autowired 하는 방법의 객체 생성 순서가 다르다. 
	
	public TestController() {
		System.out.println("TestController");
	}
	
	@GetMapping({"/", "/home"})
	public String home() {
		return "Home";
	}

	@GetMapping("/test")
	public String test() {
		return testService.test();
	}


}
