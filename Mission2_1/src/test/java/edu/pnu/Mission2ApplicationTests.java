package edu.pnu;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.pnu.domain.Member;
import edu.pnu.service.MemberService;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class Mission2ApplicationTests {
	
	@Autowired
	MemberService memberService;

	
	@Test
	@Order(1)
	void contextLoads() {
		List<Member> list = memberService.getMembers();
		for(Member m : list) {
			System.out.println(m);
		}
	}
	

}
