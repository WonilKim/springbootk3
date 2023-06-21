package edu.pnu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;

@Service
public class BoardServiceImpl {
	
	@Autowired
	private BoardRepository boardRepo;
	
	public List<Board> getBoardList() {
		return boardRepo.findAll();
	}
	
	public void insertBoard(Board board) {
		boardRepo.save(board);
	}
	
	public Board getBoard(Board board) {
		return boardRepo.findById(board.getSeq()).get();
	}
	
	

}
