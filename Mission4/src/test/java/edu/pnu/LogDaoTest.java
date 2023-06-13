package edu.pnu;

import java.util.ArrayList;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import edu.pnu.dao.LogDao;
import edu.pnu.domain.DBLog;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LogDaoTest {
	LogDao logDao;
	
	public LogDaoTest() {
		System.out.println("-- LogDaoTest()");

		logDao = new LogDao();
	}

	@Test
	@Order(1)
	public void insertLogTest() {
		System.out.println("-- insertLogTest()");
		
		int startIndex = 30;
		int count = 10;
		
		for(int i = startIndex; i < startIndex + count; i++) {
			// 방법 1
			logDao.insertLog( 
					DBLog.builder()
					.method("method" + i)
					.sqlstring("sqlstring" + i)
					.success(false)
					.build()
					
					);
			
		}
		
//		getLogsTest();
	}
	
	@Test		// @Test 는 테스트 순서를 보장하지 않는다.
	@Order(2)	// 테스트 순서를 정하기 위해 @TestMethodOrder 와 @Order 어노테이션을 사용한다. 
	public void getLogsTest() {
		System.out.println("-- getLogsTest()");
		ArrayList<DBLog> list = logDao.getLogs();
		for(DBLog m : list) {
			System.out.println(m);
		}
	}
	
	@Test		
	@Order(3)	
	public void getLogTest() {
		System.out.println("-- getLogTest()");
		
		int id = 10;
		DBLog m = logDao.getLog(id);
		System.out.println(m);
	}

}
