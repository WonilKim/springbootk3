package edu.pnu.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.Member;
import edu.pnu.service.MemberService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class MemberController {
	
	private MemberService memberService;

	public MemberController() {
		System.out.println("MemberController 가 생성되었습니다.");
		log.info("MemberController 가 생성되었습니다.");
//		log.debug("MemberController 가 생성되었습니다.");
//		log.warn("MemberController 가 생성되었습니다.");
		
		memberService = new MemberService();
	}
	
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
