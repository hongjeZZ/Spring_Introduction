package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home";
    }

    /*
     * GetMapping("/") 은 홈 화면임. 아무것도 없음
     * 아무것도 없이 들어오면 스프링 컨테이너에서 관련 컨트롤러를 찾고, 없다면 static (정적) 페이지를 찾아서 보낸다. -> index.html
     * 우리는 home.html 을 만들었음으로, index.html은 무시된다.
     */
}
