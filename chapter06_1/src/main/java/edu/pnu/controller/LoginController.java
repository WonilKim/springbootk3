package edu.pnu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import edu.pnu.domain.Member;
import edu.pnu.exception.UserNameNotFoundException;
import edu.pnu.service.MemberService;


@SessionAttributes("member")
@Controller
public class LoginController {
	@GetMapping("/login")
	public void loginView() {
		System.out.println("-- loginView()");
	}

	@Autowired
	private MemberService memberService;

	@PostMapping("/login")
	public String login(Member member, Model model) {
		System.out.println("-- login()");
		Member findMember = memberService.getMember(member);

		if (findMember != null && findMember.getPassword().equals(member.getPassword())) {
			model.addAttribute("member", findMember);
			return "redirect:getBoardList";
		} else {
			// return "redirect:loginError";
			throw new UserNameNotFoundException("사용자를 찾을 수 없습니다."); 
		}
	}
	
	@GetMapping("/logout")
	public String logout(SessionStatus status) {
		System.out.println("-- logout()");
		status.setComplete();
		return "redirect:index.html";		
	}

}
