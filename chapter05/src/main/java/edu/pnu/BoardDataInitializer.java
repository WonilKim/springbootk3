package edu.pnu;

import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;

@Component
public class BoardDataInitializer implements ApplicationRunner {
	
	@Autowired
	private BoardRepository boardRepo;

	private long initCount = 100l;
	
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		Random random = new Random(System.currentTimeMillis());
		
		for (int i = 0; i < initCount + 1; i++) {
			Board board = new Board();
			board.setTitle("title" + i);
			board.setWriter("writer" + i);
			board.setContent("content" + i);
			board.setCreateDate(new Date());
			board.setCnt(random.nextLong(initCount));

			boardRepo.save(board);
			
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
