package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


// Controller 어노테이션을 사용하면, 자동으로 컨트롤러 객체를 생성, 스프링이 스프링 컨테이너에서 bean 을 관리한다.
@Controller
public class MemberController {

//    @Autowired private final MemberService memberService;  -> 필드 주입 (단점이 많음)

//    @Autowired                   -> setter 주입 (public 으로 노출이 됨 -> 문제가 발생할 수 있음)
//    public void setMemberService(MemberService memberService) {
//        this.memberService = memberService;
//    }

    private final MemberService memberService;

    /**
     * 스프링 빈을 등록하는 2가지 방법
     * 1. 컴포넌트 스캔과 자동 의존관계 설정 -> @Autowired (생성자를 통한 의존성 주입)
     * 이때, @Component 어노테이션이 붙은 컴포넌트를 스캔한다. 일반적으로 싱글톤 객체로 생성하고, 실행하는 Application이 속한 패키지의 Component 만 스캔함.
     * 2. 자바 코드로 직접 스프링 빈 등록하기 -> SpringConfig 객체 참조
     * 클래스에 @Configuration 어노테이션 붙이고, 등록할 객체를 @Bean 어노테이션 붙여서 메소드를 만듬
     * 3. XML 사용 -> 현재는 사용하지 않음
     */

     /* 서버 실행중에 동적으로 변하는 경우가 거의 없으므로 생성자 주입을 권장함.
     @Autowired 는 스프링 컨테이너에서 멤버 서비스를 가지고 온다. = 연결을 시키는 과정 -> 의존성 주입
     이때 서비스도 @Service 어노테이션이 붙어야함. 또한 스프링 빈에 등록되지 않은 클래스는 @Autowired 가 실행되지 않는다.
     */
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/"; // 홈 화면으로 보내기
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
