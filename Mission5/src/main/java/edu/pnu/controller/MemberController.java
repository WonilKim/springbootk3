package edu.pnu.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.MemberVO;
import edu.pnu.service.MemberService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class MemberController {
	
	private MemberService memberService;

	@Autowired
	public MemberController(MemberService memberService) {

		log.info("MemberController 가 생성되었습니다.");
		
		this.memberService = memberService;
	}
	
	@GetMapping("/member/{id}")
	public MemberVO getMember(@PathVariable int id) {
		return memberService.getMember(id);
	}
	
	
	@GetMapping("/member")
	public ArrayList<MemberVO> getMembers() {
		return memberService.getMembers();
	}

	@PostMapping("/member")
	public MemberVO insertMember(@RequestBody MemberVO member) {
		return memberService.insertMember(member);
	}

	@PutMapping("/member")
	public MemberVO updateMember(@PathVariable int id, @RequestBody MemberVO member) {
		return memberService.updateMember(id, member);
	}

	@DeleteMapping("/member/{id}")
	public MemberVO deleteMember(@PathVariable int id) {
		return memberService.deleteMember(id);
	}

	
}
