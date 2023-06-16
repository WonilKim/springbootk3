package edu.pnu.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
public class TestService {
	
	public TestService() {
		System.out.println("TestService");
	}

	public String test() {
		
		return "TestService.test";
	}
	
}
