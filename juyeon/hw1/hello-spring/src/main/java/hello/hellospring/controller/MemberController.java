package hello.hellospring.controller;
//Controller : 웹 MVC의 컨트롤러 역할
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;


@Controller
public class MemberController{
	private final MemberService memberService; 

	//@Autowired : 스프링이 연관된 객체를 스프링 컨테이너에서 찾아서 넣어줌 (의존성 주입)
	@Autowired
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	//HTTP GET 요청 메소드 지정
	// "/members/new" 경로로 들어오는 GET 요청 처리
	@GetMapping("/members/new")
	public String createForm() {
		//요청에 대한 응답으로 "members/createMemberForm"(view의 이름) 문자열 반환
		return "members/createMemberForm";
	}

	//HTTP POST 요청 메소드 지정
	// "/member/new" 경로로 들어오는 POST 요청 처리
	@PostMapping("/members/new")
	public String create(MemberForm form) {
		//요청이 들어오면 새로운 member 객체 생성
		Member member = new Member();
		//사용자가 form에 입력한 이름을 member 객체에 저장
		member.setName(form.getName());
		//member를 회원가입 시킴
		memberService.join(member);

		//회원가입을 완료하면 "/"로 redirect 시킴
		return "redirect:/";
	}

	//HTTP GET 요청 메소드 지정
	// "/members 경로로 들어오는 GET 요청 처리
	@GetMapping(value = "/members")
	public String list(Model model) {
		//모든 회원의 목록을 가져옴
		 List<Member> members = memberService.findMembers();
		 //가져온 회원 목록을 모델에 추가
		 model.addAttribute("members", members);

		 //요청에 대한 응답으로 "members/memberList"(view의 이름) 반환
		 return "members/memberList";
	}
}