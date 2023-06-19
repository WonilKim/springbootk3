package edu.pnu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;

@RestController
public class BoardController {
	
	@Autowired
	private BoardRepository boardRepo;


	public BoardController() {
		System.out.println("-- BoardController");
	}
	
	@GetMapping("/test1")
	public String test1() {
		List<Board> list = boardRepo.findByTitleLike("%1%");
		
//		String result = "";
//		for(Board board : list) {
//			result += board.toString() + "\n";
//		}
		String result = BoardRepository.asJsonString(list);
		
		return result;
	}

	@GetMapping("/test2")
	public String test2() {
		List<Board> list = boardRepo.findByTitleLikeAndCntGreaterThan("%1%", 50);
		
//		String result = "";
//		for(Board board : list) {
//			result += board.toString() + "\n";
//		}
		String result = BoardRepository.asJsonString(list);
		
		return result;
		
	}
	
	@GetMapping("/test3")
	public String test3() {
		List<Board> list = boardRepo.findByCntBetweenOrderBySeqAsc(10, 50);
		
//		String result = "";
//		for(Board board : list) {
//			result += board.toString() + "\n";
//		}
		String result = BoardRepository.asJsonString(list);
		
		return result;
		
	}
	
	@GetMapping("/test4")
	public String test4() {
		List<Board> list = boardRepo.findByTitleLikeOrContentLikeOrderBySeqDesc("%10%", "%2%");
		
//		String result = "";
//		for(Board board : list) {
//			result += board.toString() + "\n";
//		}
		String result = BoardRepository.asJsonString(list);
		
		return result;
		
	}
	
	@GetMapping("/test5")
	public String test5(Integer pageNum, Integer size) {
		
		Pageable pageable = PageRequest.of(pageNum, size);
		Page<Board> page = boardRepo.findAll(pageable);
			
		String result = BoardRepository.asJsonString(page.getContent());
		
		return result;

	}


}
