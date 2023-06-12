package edu.pnu;

import java.util.ArrayList;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import edu.pnu.dao.MemberDao;
import edu.pnu.domain.Member;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MemberDaoTest {

	@Test
	@Order(1)
	public void insertMemberTest() {
		MemberDao memberDao = new MemberDao();
		
		int startIndex = 20;
		int count = 10;
		
		for(int i = startIndex; i < startIndex + count; i++) {
			// 방법 1
			memberDao.insertMember(
					Member.builder()
					.name("name" + i)
					.age(20 + i)
					.nickname("nick" + i)
					.build()
					
					);
			
			// 방법 2
			// memberDao.insertMember(new Member(-1, "name" + i, 20 + i, "nick" + i, null))
			
			// 방법 3
			// Member m = new Member();
			// m.setName("name" + i);
			// m.setAge(20 + i);
			// m.setNickname("nick" + i);
			// memberDao.insertMember(m);
			
		}
	}
	
	@Test		// @Test 는 테스트 순서를 보장하지 않는다.
	@Order(2)	// 테스트 순서를 정하기 위해 @TestMethodOrder 와 @Order 어노테이션을 사용한다. 
	public void getMembersTest() {
		MemberDao memberDao = new MemberDao();
		ArrayList<Member> list = memberDao.getMembers();
		for(Member m : list) {
			System.out.println(m);
		}
	}
}
