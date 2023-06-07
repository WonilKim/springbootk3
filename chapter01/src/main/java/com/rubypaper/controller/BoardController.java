package com.rubypaper.controller;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rubypaper.domain.BoardVO;

/*public class BoardController extends HttpServlet {
	// boot 에서는 extends 로 사용하지 않게 한다.
}
*/

// @RestController 어노테이션이 있으면 boot가 객체를 자동으로 생성해서 컨테이너에 넣는다.
// 컨트롤러는 진입점. (url)
@RestController
public class BoardController {
	public BoardController() {
		System.out.println("=".repeat(50));
		System.out.println("BoardController가 생성되었습니다.");
		System.out.println("=".repeat(50));
	}
	
	// http://localhost:8080/hello?name=홍길동
	// 위 주소로 접속하면 함수 내용이 화면에 출력됨.
	@GetMapping("/hello")
	public String hello(String name) {
		return "Get Hello : " + name;
		// @RestController는 여기서 return 하는 것을 그대로 웹브라우저에 보내준다.
		// 객체타입을 리턴하면 json으로 변환해서 보냄
		// @Controller를 사용하면 View 로 인식해서 문제가 생김.
	}
	
	@GetMapping("/helloController")
	public @ResponseBody String helloController(String name) {
		return "Get Hello : " + name;
		// @Controller를 사용하려면 return에 @ResponseBody를 넣어야 body에 내용을 출력한다.
		// @ResponseBody 가 없으면 리턴하는 텍스트를 파일명으로 인식함
	}
	
	@PostMapping("/hello")
	public String helloPost(String name) {
		return "Post Hello : " + name;
	}
	
	@PutMapping("/hello")
	public String helloPut(String name) {
		return "Put Hello : " + name;
	}
	
	@DeleteMapping("/hello")
	public String helloDelete(String name) {
		return "Delete Hello : " + name;
	}
	
	//
	@GetMapping("/getBoard")
	public BoardVO getBoard() {
		BoardVO board = new BoardVO(); // 클래스에 @NoArgsConstructor 어노테이션 필요
		board.setSeq(1);
		board.setTitle("텍스트 제목");
		board.setWriter("테스터");
		board.setContent("테스트 내용입니다..");
		board.setCreateData(new Date());
		board.setCnt(0);
		
		return board;
	}

	@GetMapping("/getBoardAllArg")
	public BoardVO getBoardAllArg() {
		BoardVO board = new BoardVO( // 클래스에 @AllArgsConstructor 어노테이션 필요
				1, "텍스트 제목", "테스터", "테스트 내용입니다..", new Date(), 0);
		
		return board;
	}

	@GetMapping("/getBoardBuilder")
	public BoardVO getBoardBuilder() {
				
		return BoardVO.builder()
				.seq(1)
				.title("title")
				.writer("writer")
				.content("content")
				.createData(new Date())
				.cnt(0)
				.build();
	}

	@GetMapping("/getBoardList")
	public ArrayList<BoardVO> getBoardList() {

		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		
		for(int i=0; i<10; i++) {
			list.add(
					BoardVO.builder()
						.seq(i)
						.title("title" + i)
						.writer("writer" + i)
						.content("content" + i)
						.createData(new Date())
						.cnt(0)
						.build()
					);
		} // for(int i=0; i<10; i++)
		
		return list;
	}
	
	@GetMapping("/board")
	public BoardVO board(@RequestBody BoardVO b) {
		// 객체를 받을때 @RequestBody 어노테이션이 있어야 데이터를 제대로 받을 수 있다.
		b.setCreateData(new Date());
		b.setCnt(300);
		System.out.println(b);
		return b;
	}

	
}
