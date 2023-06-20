package edu.pnu;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BoardRepositoryTest {
	@Autowired
	private BoardRepository boardRepo;
	
	private long initCount = 100;
	
	@Test
	public void testQueryAnnotationTest5() {

		Pageable paging = PageRequest.of(2, 3, Sort.Direction.DESC, "seq");

		// select b from Board b order by b.seq asc
		List<Board> list = boardRepo.queryAnnotationTest5(paging);
		
		// Query에 order by 가 있으면 Pageable의 sort는 무시됨.
		// Query에 order by 가 없으면 Pageable의 sort가 정상 동작됨.
		
		for (Board b : list) {
			System.out.println("--> " + b);
		}
	}
	
//	@Test
	public void testQueryAnnotationTest4() {

		// Native query
		// Select seq, title, writer, create_date from Board where title like '%title4%' order by seq desc
		List<Object[]> list = boardRepo.queryAnnotationTest4("title4");
		
		for (Object[] row : list) {
			System.out.println(Arrays.toString(row));
		}
	}
	
//	@Test
	public void testQueryAnnotationTest3() {
		// 아래 JPQL 에서 Board 는 테이블이 아닌 클래스명, b는 alias.
		// Select b.seq, b.title, b.writer, b.createDate from Board b where b.title like '%title1%' order by b.seq desc
		// DB필드 이름은 create_date인데 언더바를 생략하고 D를 대문자로 바꿔서 createDate 라고 해도 정상적으로 실행된다.
		List<Object[]> list = boardRepo.queryAnnotationTest3("title3");
		
		for (Object[] row : list) {
			System.out.println(Arrays.toString(row));
		}
	}
	
//	@Test
	public void testQueryAnnotationTest2() {
		// 아래 JPQL 에서 Board 는 테이블이 아닌 클래스명, b는 alias.
		// Select b from Board b where b.title like '%title1%' order by b.seq desc
		List<Board> list = boardRepo.queryAnnotationTest2("title2");
		
		for (Board b : list) {
			System.out.println(b);
		}
	}
	
//	@Test
	public void testQueryAnnotationTest1() {
		// 아래 JPQL 에서 Board 는 테이블이 아닌 클래스명, b는 alias.
		// Select b from Board b where b.title like '%title1%' order by b.seq desc
		List<Board> list = boardRepo.queryAnnotationTest1("title1");
		
		for (Board b : list) {
			System.out.println(b);
		}
	}

//	@Test
	@Order(1)
	public void testInsertBoard() {
		Random random = new Random(System.currentTimeMillis());
		
		for (int i = 0; i < initCount + 1; i++) {
			Board board = new Board();
			board.setTitle(i + " 번째 게시글");
			board.setWriter("테스터" + i);
			board.setContent("잘 등록되나요?" + i);
			board.setCreateDate(new Date());
			board.setCnt(random.nextLong(100));

			boardRepo.save(board);
			
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
//	@Test	
	@Order(2)
	public void testUpdateBoard() {
		System.out.println("=== 8번 게시글 조회 ===");
		Board board = boardRepo.findById(8L).get();
		
		System.out.println("=== 8번 게시글 제목 수정 ===");
		board.setTitle("제목을 수정했습니다.");
		boardRepo.save(board);
	} 

//	@Test
	@Order(3)
	public void testGetBoard() {
		Board board = boardRepo.findById(8L).get();
		System.out.println(board.toString());
	} 

//	@Test
	@Order(4)
	public void testDeleteBoard() {
		boardRepo.deleteById(9L);
	}


}
