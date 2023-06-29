package edu.pnu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@GetMapping("/login")
	public void login() {
		System.out.println("-- login()");
	}
	
	@GetMapping("/loginSuccess")
	public void loginSuccess() {
		System.out.println("-- loginSuccess()");
	}
	
	//
	@GetMapping("/accessDenied")
	public void accessDeniend() {
		System.out.println("-- accessDenied()");
	}
	

}
