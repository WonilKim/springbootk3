package edu.pnu.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.MemberVO;
import edu.pnu.service.MemberService;

//@Slf4j	log 자동 생성
@RestController
public class MemberController {

	private MemberService memberService;
	
	private static final Logger log = LoggerFactory.getLogger(MemberController.class);
	
	public MemberController() {
		memberService = new MemberService();
	}
	
	@GetMapping("/member")
	public List<MemberVO> getMembers() {
		log.info("getMembers()");
		return memberService.getMembers();
	}

	// 아래 내용과 같은 기능
	// http://localhost:8080/member/2 과 같이 호출
	@GetMapping("/member/{id}")
	public MemberVO getMember(@PathVariable Integer id) { // @PathVariable 필요
		log.info("getMember()");
		return memberService.getMember(id);
	}
	
	// 위 내용과 같은 기능
	// http://localhost:8080/member2?id=3 과 같이 호출
	@GetMapping("/member2")
	public MemberVO getMember2(Integer id) {
		log.info("getMember2()");
		return memberService.getMember(id);
	}
	// [CRUD]	:	[Web]	:	[DB]
	// Create	:	Post	:	Insert
	// Read		:	Get		:	Select
	// Update	:	Update	:	Update
	// Delete	:	Delete	:	Delete
	
	
	@PostMapping("/member")
	public MemberVO addMember(@RequestBody MemberVO member) {
		log.info("addMember()");
		return memberService.addMember(member);
	}

	@PutMapping("/member")
	public MemberVO updateMember(@RequestBody MemberVO member) {
		log.info("updateMember()");
		return memberService.updateMember(member);
	}
	
	@DeleteMapping("/member/{id}")
	public MemberVO deleteMember(@PathVariable Integer id) {
		log.info("deleteMember()");
		return memberService.deleteMember(id);
	}
}
