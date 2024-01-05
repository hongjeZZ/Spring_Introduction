package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "홍제!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    /*
     name = 홍제 일 때, "hello 홍제"
     위 방식과 차이점은 문자가 그대로 내려감. 즉 html 코드를 통하지 않음
     이걸 쓸 일은 많이 없음.
     */

    @GetMapping("hello-string")
    @ResponseBody // html에 나오는 바디 태그가 아니라, html에서 body 부의 이 데이터를 직접 넣는다.
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }

    /*
    json 방식
    getter/setter 자바 빈 표준 방식 -> private 데이터를 간접적으로 꺼냄
    1. 웹브라우저에서 "http://localhost:8080/hello-api" 를 찾아오면 내장 톰켓 서버에 전달
    2. 스프링 컨트롤러 getMapping에 hello-api를 찾음
    3. @ResponseBody가 붙어있기 때문에 문자를 그대로 내려보내야하지만, 객체를 반환해야함
    4. 스프링은 객체를 반환할 때, json 방식으로 데이터를 만들어서 반환한다.

    #객체 반환 동작 방식
    1. HttpMessageConverter 가 단순 문자인지 판별
    2. 단순 문자면 StringConverter 가 동작, 객체면 JsonConverter 가 동작

    #기존 동작 방식
    1. ViewResolver 가 resources/templates 에서 이름과 동일한 템플릿을 찾아 반환.
     */

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}