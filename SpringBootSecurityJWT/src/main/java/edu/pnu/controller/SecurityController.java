package edu.pnu.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

	@GetMapping("/")
	public String index() {
		System.out.println("index 요청.");
		return "index";
	}
	
	@GetMapping("/member")
	public String member() {
		System.out.println("member 요청.");
		return "member";
	}
	
	@GetMapping("/manager")
	public String manager() {
		System.out.println("manager 요청.");
		return "manager";
	}
	
	@GetMapping("/admin")
	public String admin() {
		System.out.println("admin 요청.");
		return "admin";
	}


}
