package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data","hello!!");
                return "hello";
    }
    @GetMapping("hello-mvc")
    public String hellomvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name",name);
        return "hello-template";
    }//html내용을 바꿔서 보내줌
    @GetMapping("hello-string")
    @ResponseBody//요청자체에 담겠다 라는 의미
    public String hellostring(@RequestParam("name") String name){
        return "hello "+name;
    }//그냥 무식하게 문자 그대로 들어가게 됨 html이 아니라

    @GetMapping("hello-api")
    @ResponseBody//요청자체에 담겠다 라는 의미
    public Hello helloapi(@RequestParam("name") String name){
        Hello hello=new Hello();
        hello.setName(name);
        return hello;
    }
    static class Hello{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


}
