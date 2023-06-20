package edu.pnu;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.BooleanBuilder;

import edu.pnu.domain.Board;
import edu.pnu.domain.QBoard;
import edu.pnu.persistence.DynamicBoardRepository;

@SpringBootTest
public class DynamicQueryTest {

	@Autowired
	private DynamicBoardRepository boardRepo;
	
	@Test
	public void dynamicQueryTest1() {
		System.out.println("-- dynamicQueryTest1");
		String searchCondition = "TITLE";
		String searchKeyword = "title1";

		test(searchCondition, searchKeyword);

	}
	
	@Test
	public void dynamicQueryTest2() {
		System.out.println("-- dynamicQueryTest2");
		String searchCondition = "CNT";
		long searchKeyword = 50;

		test(searchCondition, searchKeyword);

	}
	
	@Test
	public void dynamicQueryTest3() {
		System.out.println("-- dynamicQueryTest3");
		String searchCondition = "TITLE";
		String searchKeyword = "title1";

		testPage(searchCondition, searchKeyword);

	}
	
	@Test
	public void dynamicQueryTest4() {
		System.out.println("-- dynamicQueryTest4");
		String searchCondition = "CNT";
		long searchKeyword = 50;

		testPage(searchCondition, searchKeyword);

	}
	
	private void test(String searchCondition, String searchKeyword) {
		
		BooleanBuilder builder = new BooleanBuilder();
		
		QBoard qboard = QBoard.board;
		
		if(searchCondition.equals("TITLE")) {
			builder.and(qboard.title.like("%" + searchKeyword + "%"));
		} else if(searchCondition.equals("CONTENT")) {
			builder.and(qboard.content.like("%" + searchKeyword + "%"));
		}		
		
		Iterable<Board> boardList = boardRepo.findAll(builder);
		
		System.out.println("검색 결과");
		for (Board board : boardList) {
			System.out.println("---> " + board.toString());
		}

	}
	
	private void test(String searchCondition, long searchKeyword) {
		
		BooleanBuilder builder = new BooleanBuilder();
		
		QBoard qboard = QBoard.board;
		
		if(searchCondition.equals("CNT")) {
			builder.and(qboard.cnt.gt(searchKeyword));
		} 	
		
		Iterable<Board> boardList = boardRepo.findAll(builder);
				
		System.out.println("검색 결과");
		for (Board board : boardList) {
			System.out.println("---> " + board.toString());
		}

	}
	
	private void testPage(String searchCondition, long searchKeyword) {
		
		BooleanBuilder builder = new BooleanBuilder();
		
		QBoard qboard = QBoard.board;
		
		if(searchCondition.equals("CNT")) {
			builder.and(qboard.cnt.gt(searchKeyword));
		} 	
		
		Pageable paging = PageRequest.of(0, 5);
		
		Page<Board> boardList = boardRepo.findAll(builder, paging);
				
		System.out.println("검색 결과");
		for (Board board : boardList) {
			System.out.println("---> " + board.toString());
		}

	}
	
	private void testPage(String searchCondition, String searchKeyword) {
		
		BooleanBuilder builder = new BooleanBuilder();
		
		QBoard qboard = QBoard.board;
		
		if(searchCondition.equals("TITLE")) {
			builder.and(qboard.title.like("%" + searchKeyword + "%"));
		} else if(searchCondition.equals("CONTENT")) {
			builder.and(qboard.content.like("%" + searchKeyword + "%"));
		}		
		
		Pageable paging = PageRequest.of(0, 5);
		
		Page<Board> boardList = boardRepo.findAll(builder, paging);
				
		System.out.println("검색 결과");
		for (Board board : boardList) {
			System.out.println("---> " + board.toString());
		}

	}
	
	private void testPage(Map<String, String> map) {
		
		BooleanBuilder builder = new BooleanBuilder();
		
		QBoard qboard = QBoard.board;
		
		Set<String> keys = map.keySet();
		for (String k : keys) {
			
			String searchCondition = k;
			String searchKeyword = map.get(k);

			if(searchCondition.equals("TITLE")) {
				builder.and(qboard.title.like("%" + searchKeyword + "%"));
			} else if(searchCondition.equals("CONTENT")) {
				builder.and(qboard.content.like("%" + searchKeyword + "%"));
			}		
			
			Pageable paging = PageRequest.of(0, 5);
			
			Page<Board> boardList = boardRepo.findAll(builder, paging);
					
			System.out.println("검색 결과");
			for (Board board : boardList) {
				System.out.println("---> " + board.toString());
			}

		}
		
	}
	
//	@Test
	public void dynamicQueryTest() {
//		String searchCondition = "CONTENT";
//		String searchKeyword = "content1";

		String searchCondition = "CONTENT";
		String searchKeyword = "content2";

		testPage(searchCondition, searchKeyword);
		
		Map<String, String> map = new HashMap<>();
		map.put("CONTENT", "2");	// 다음 실행됨. title4가 포함된 것 들 중 (and) content에 2가 포함된 것 가져옴.
		map.put("TITLE", "title4");	// 먼저 실행됨. title4가 포함된 모든 것 가져옴.
		
		testPage(map);
		
		
	}
}
