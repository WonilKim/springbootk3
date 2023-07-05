package edu.pnu.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

	// 로그인 세션 정보 확인용 URL
	@GetMapping("/auth")
	public @ResponseBody String auth(@AuthenticationPrincipal User user) {
		return user.toString();
	}

}
