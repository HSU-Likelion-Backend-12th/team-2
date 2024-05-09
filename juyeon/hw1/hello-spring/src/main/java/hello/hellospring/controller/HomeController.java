package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//이 클래스가 스프링 MVC의 컨트롤러임을 나타냄
@Controller
public class HomeController {
	//HTTP GET 요청 처리 메소드 지정
	// "/" 경로로 들어오는 GET 요청 처리
	@GetMapping("/")
	//요청에 대한 응답으로 home 문자열 반환
	//home은 view(뷰)의 이름을 나타냄
	public String home() {
		return "home";
	}
}
