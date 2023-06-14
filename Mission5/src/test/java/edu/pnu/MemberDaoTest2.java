package edu.pnu;

import java.util.ArrayList;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.pnu.dao.LogDao;
import edu.pnu.dao.MemberDaoList;
import edu.pnu.dao.MemberDaoMysql;
import edu.pnu.dao.MemberInterface;
import edu.pnu.domain.MemberVO;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MemberDaoTest2 {
	
	@Autowired
	MemberInterface memberDao;
	@Autowired
	LogDao logDao;
	
	public MemberDaoTest2() {
		System.out.println("-- MemberDaoTest()");

	}

	@Test
	@Order(1)
	public void insertMemberTest() {
		System.out.println("-- insertMemberTest()");
		
		int startIndex = 30;
		int count = 10;
		
		for(int i = startIndex; i < startIndex + count; i++) {
			// 방법 1
			memberDao.insertMember(logDao, 
					MemberVO.builder()
					.pass("pass" + i)
					.name("nick" + i)
					.build()
					
					);
			
		}
		
		getMembersTest();
	}
	
	@Test		// @Test 는 테스트 순서를 보장하지 않는다.
	@Order(2)	// 테스트 순서를 정하기 위해 @TestMethodOrder 와 @Order 어노테이션을 사용한다. 
	public void getMembersTest() {
		System.out.println("-- getMembersTest()");
		ArrayList<MemberVO> list = memberDao.getMembers(logDao);
		for(MemberVO m : list) {
			System.out.println(m);
		}
	}
	
	@Test		
	@Order(3)	
	public void getMemberTest() {
		System.out.println("-- getMemberTest()");
		
		int id = 10;
		MemberVO m = memberDao.getMember(logDao, id);
		System.out.println(m);
	}

	@Test		
	@Order(4)	
	public void updateMemberTest() {
		System.out.println("-- updateMemberTest()");
		
		int id = 11;
		MemberVO m = MemberVO.builder()
				.pass("pass" + 77)
				.name("nick" + 77)
				.build();
		
		MemberVO mm = memberDao.updateMember(logDao, id, m);
		
		System.out.println(mm);
	}

	@Test		
	@Order(5)	
	public void deleteMemberTest() {
		System.out.println("-- deleteMemberTest()");
		
		int id = 13;
		MemberVO m = memberDao.deleteMember(logDao, id);
		if (m != null)
			System.out.println(m);
		else
			System.out.println("삭제한 데이터가 없습니다.");
	}


}
