package edu.pnu;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.test.web.servlet.MockMvc;
// 
import org.springframework.web.bind.annotation.RestController;

/*
 * 클래스의 객체를 만들어서 자동으로 컨테이너(IoC Container)에 올려주는 어노테이션들. 
 * Component 의 자식들
@RestController
@Controller
@Service
@Repository
@Configuration
@Bean

 * AutoWired 가 있으면 그 타입의 객체가 컨테이너에 있는지 확인하고 그 주소를 연결한다?
 * 부트가 실행되면 MockMvc 의 객체를 자동으로 만들어서 메모리에 올려 놓는다.
 * 그 객체 메모리(힙)에 참조변수(스택)를 연결해주는 것.  
*/

// @SpringBootTest
// @AutoConfigureWebMvc
@WebMvcTest
class Chapter03ApplicationTests {

	/*
	 * AutoWired 가 있으면 그 타입의 객체가 컨테이너에 있는지 확인하고 그 주소를 연결한다. 
	 * 부트가 실행되면 MockMvc 의 객체를 자동으로 만들어서 메모리에 올려 놓는다. 
	 * 그 객체 메모리(힙)에 참조변수(스택)를 연결해주는 것.
	 * new 키워드를 사용해 생성자를 호출하기에는 사용자가 어떤데이터를 넣어야 하는지 모른다.
	 * 부트가 알아서 해주도록 아래처럼 해줌.
	 */
	@Autowired
	MockMvc mockMvc;
	
	@Test
	void contextLoads() {
		try {
			/*
			 * // /hello?name=둘리 mockMvc.perform(get("/hello").param("name", "둘리"))
			 * .andExpect(status().isOk()) // .andExpect(content().string("hello : 들리"))
			 * .andDo(print());
			 */			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
