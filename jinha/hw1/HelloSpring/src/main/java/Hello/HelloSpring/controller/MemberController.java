package Hello.HelloSpring.controller;

import Hello.HelloSpring.domain.Member;
import Hello.HelloSpring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    private final MemberService memberService;

    @Autowired //Dependency Injection의 3가지 방법 중 1. 생성자 주입
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    //@Autowired private MemberService memberService; //2. 필드 주입_ 별로 추천하지 않는다.

    //@Autowired //3. Setter 주입_ 늘 public으로 열려있어야 하므로 위험.
    //public MemberController(MemberService memberService){
    //    this.memberService = memberService;
    //}

    //생성자 주입 추천!
    //@Autowired는 Spring container에 들어가는 것들만 적용된다.

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);
        System.out.println("member = "+member.getName());

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);

        return "members/memberList";
    }
}
