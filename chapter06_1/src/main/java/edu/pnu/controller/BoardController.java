package edu.pnu.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import edu.pnu.domain.Board;
import edu.pnu.domain.Member;
import edu.pnu.service.BoardServiceImpl;

@SessionAttributes("member")
@Controller
public class BoardController {

	@GetMapping("/test")
	public String test() {
		// /WEB-INF/board/test.jsp
		return "test";
	}
	
	@ModelAttribute("member")
	public Member setMember() {
		return new Member();
	}


//	@RequestMapping("/getBoardList")
//	public String getBoardList(Model model) {
//		System.out.println("-- getBoardList()");
//
//		List<Board> boardList = new ArrayList<Board>();
//
//		for (long i = 1; i <= 10; i++) {
//			boardList.add(
//					Board.builder()
//					.seq(i)
//					.title("title" + i)
//					.writer("writer" + i)
//					.content("content" + i)
//					.createDate(new Date())
//					.cnt(i)
//					.build()
//					);
//		}
//
//		model.addAttribute("boardList", boardList);
//		return "getBoardList";
//	}
	
	@Autowired
	private BoardServiceImpl boardService;

//	@GetMapping("/getBoardList")
//	public String getBoardList(Model model) {
//		System.out.println("-- getBoardList()");
//
//		model.addAttribute("boardList", boardService.getBoardList());
//		return "getBoardList";
//	}
	
	@RequestMapping("/getBoardList")
	public String getBoardList(@ModelAttribute("member") Member member, Model model, Board board) {
		System.out.println("-- getBoardList()");
		System.out.println(member);
		if (member.getId() == null) {
			System.out.println("  member.getId() == null");
			return "redirect:login";
		}

		List<Board> boardList = boardService.getBoardList();

		model.addAttribute("boardList", boardList);
		return "getBoardList";
	}
	
	@GetMapping("/insertBoard")
	public String insertBoard() {
		// 뷰를 매핑하는 기능만 있다.
		return "insertBoard";
	}
	
	@PostMapping("/insertBoard")
	public String insertBoardPost(@ModelAttribute("member") Member member, Board board) {
		if (member.getId() == null) {
			return "redirect:login";
		}

		board.setCnt(0l);
		board.setCreateDate(new Date());
		System.out.println(board);
		boardService.insertBoard(board);
		return "redirect:getBoardList";
	}
	
	@GetMapping("/getBoard")
	public String getBoard(@ModelAttribute("member") Member member, Long seq, Model model) {
		if (member.getId() == null) {
			return "redirect:login";
		}

		Board board = boardService.getBoard(
				Board.builder().seq(seq).build()
				);
		model.addAttribute("board", board);
		return "getBoard";
	}
	
	@PostMapping("/updateBoard")
	public String updateBoard(@ModelAttribute("member") Member member, Board board) {
		if (member.getId() == null) {
			return "redirect:login";
		}

		boardService.updateBoard(board);
		return "redirect:getBoardList";
	}

	@GetMapping("/deleteBoard")
	public String deleteBoard(@ModelAttribute("member") Member member, Board board) {
		if (member.getId() == null) {
			return "redirect:login";
		}

		boardService.deleteBoard(board);
		return "redirect:getBoardList";
	}

//	@Autowired
//	private BoardService boardService;
//
//	@ModelAttribute("member")
//	public Member setMember() {
//		return new Member();
//	}
//
//	@RequestMapping("/getBoardList")
//	public String getBoardList(@ModelAttribute("member") Member member, Model model, Board board) {
//		if (member.getId() == null) {
//			return "redirect:login";
//		}
//
//		List<Board> boardList = boardService.getBoardList(board);
//
//		model.addAttribute("boardList", boardList);
//		return "getBoardList";
//	}
//
//	@GetMapping("/insertBoard")
//	public String insertBoardView(@ModelAttribute("member") Member member) {
//		if (member.getId() == null) {
//			return "redirect:login";
//		}
//
//		return "insertBoard";
//	}
//
//	@PostMapping("/insertBoard")
//	public String insertBoard(@ModelAttribute("member") Member member, Board board) {
//		if (member.getId() == null) {
//			return "redirect:login";
//		}
//
//		boardService.insertBoard(board);
//		return "redirect:getBoardList";
//	}
//
//	@GetMapping("/getBoard")
//	public String getBoard(@ModelAttribute("member") Member member, Board board, Model model) {
//		if (member.getId() == null) {
//			return "redirect:login";
//		}
//
//		model.addAttribute("board", boardService.getBoard(board));
//		return "getBoard";
//	}
//
//	@PostMapping("/updateBoard")
//	public String updateBoard(@ModelAttribute("member") Member member, Board board) {
//		if (member.getId() == null) {
//			return "redirect:login";
//		}
//
//		boardService.updateBoard(board);
//		return "forward:getBoardList";
//	}
//
//	@GetMapping("/deleteBoard")
//	public String deleteBoard(@ModelAttribute("member") Member member, Board board) {
//		if (member.getId() == null) {
//			return "redirect:login";
//		}
//
//		boardService.deleteBoard(board);
//		return "forward:getBoardList";
//	}
}
