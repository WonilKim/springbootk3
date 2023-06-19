package edu.pnu;

import java.util.Date;
import java.util.Random;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BoardRepositoryTest {
	@Autowired
	private BoardRepository boardRepo;
	
	private long initCount = 100;

	@Test
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
	@Test	
	@Order(2)
	public void testUpdateBoard() {
		System.out.println("=== 8번 게시글 조회 ===");
		Board board = boardRepo.findById(8L).get();
		
		System.out.println("=== 8번 게시글 제목 수정 ===");
		board.setTitle("제목을 수정했습니다.");
		boardRepo.save(board);
	} 

	@Test
	@Order(3)
	public void testGetBoard() {
		Board board = boardRepo.findById(8L).get();
		System.out.println(board.toString());
	} 

	@Test
	@Order(4)
	public void testDeleteBoard() {
		boardRepo.deleteById(9L);
	}


}
