package Hello.HelloSpring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class helloController {

    //Model/View Controller
    @GetMapping("hello")
    public String hello(Model model){
        //controller가 자동으로 값을 매핑해서 넣어주고 thymleaf에 전달
        model.addAttribute("data", "hello!");
        return "hello";
        //view page인 hello.html을 찾아서 반환해줌.
        //viewResolver에 의해 템플릿을 찾아가게 된다.
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name") String name, Model model){
        //RequestParam nullable 여부의 Default는 true이다.
        //http method get으로 localhost:8080/hello-mvc?name={name}의 url이 넘어온다.
        model.addAttribute("name", name);
        //외부에서 data를 전달받아 Model 객체에 저장 후 View page를 반환
        return "hello-template";
    }

    //그대로 data를 넘겨줌
    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name){
        return "hello " + name;
    }

    //json 형식으로 값을 전달해주는 방식_ key, value로 넘어간다.
    //localhost:8080/hello-api?name=jinha 라는 url이 넘어오면 key값은 name, value값은 jinha
    //가장 많이 사용하는 방식_ 객체를 반환하여 json으로 변환
    @GetMapping("hello-api")
    @ResponseBody //HTTP Body에 문자 내용을 직접 반환해줌
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello{
        private String name;

        public String getName(){
            return name;
        }

        public void setName(String name){
            this.name = name;
        }
    }


}