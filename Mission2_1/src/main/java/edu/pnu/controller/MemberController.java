package edu.pnu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.Member;
import edu.pnu.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor	// 4번 방법
public class MemberController {
	
//	// 1번 방법 : 필드에 설정하는 방법
//	@Autowired
//	private MemberService memberService;

//	// 2번 방법 : 생성자에서 설정하는 방법
//	private MemberService memberService;
//	@Autowired
//	public MemberController(MemberService memberService) {
//		this.memberService = memberService;
//	}
	
//	// 3번 방법 : Setter를 이용하는 방법
//	private MemberService memberService;
//	@Autowired
//	private void setMemberService(MemberService memberService) {
//		this.memberService = memberService;
//	}
	
	// 4번 방법 : 클래스에 @RequiredArgsConstructor 어노테이션을 두고 필드를 final 로 설정. 2번 방법을 자동으로 만들어 줌.
	private final MemberService memberService;
	
	@GetMapping("/member/{id}")
	public Member getMember(@PathVariable Long id) {
		return memberService.getMember(id);
	}
	
	
	@GetMapping("/member")
	public List<Member> getMembers() {
		return memberService.getMembers();
	}

	@PostMapping("/member")
	public Member insertMember(@RequestBody Member member) {
		return memberService.insertMember(member);
	}

	@PutMapping("/member")
	public Member updateMember(@RequestBody Member member) {
		return memberService.updateMember(member);
	}

	@DeleteMapping("/member/{id}")
	public Member deleteMember(@PathVariable Long id) {
		return memberService.deleteMember(id);
	}

	
}
