package edu.pnu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import edu.pnu.domain.Member;
import edu.pnu.exception.IDExistException;
import edu.pnu.service.MemberService;



@Controller
public class JoinController {
	@GetMapping("/join")
	public void joinView() {
		System.out.println("-- joinView()");
	}

	@GetMapping("/joinComplete")
	public void joinCompleteView() {
		System.out.println("-- joinCompleteView()");
	}

	@Autowired
	private MemberService memberService;

	@PostMapping("/join")
	public String join(Member member, Model model) {
		System.out.println("-- join()");
		
		Member findMember = memberService.getMember(member);
		if(findMember != null) {
			// 사용자가 이미 있음
			// return "redirect:join";
			throw new IDExistException("이미 존재하는 ID 입니다.");
		} else {
			member.setRole("ROLE_USER");
			memberService.insertMember(member);
			
			model.addAttribute("join", member);
			return "redirect:joinComplete";
		}

	}

}
