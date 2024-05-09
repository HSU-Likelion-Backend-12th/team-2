package Hello.HelloSpring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    //Spring Container는 우선 "home"이라는 view page를 찾아보고, 없으면 index.html을 반환하게 된다.
    public String home(){
        return "home";
    }
}
