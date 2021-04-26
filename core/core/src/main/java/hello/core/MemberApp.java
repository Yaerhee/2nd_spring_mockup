package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class MemberApp {

    public static void main(String[] args) {
        
        //스프링 컨테이너를 활용하기 위한 선언
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        //AppConfig에 있는 설정 ex. Bean 설정 등을 컨테이너에 일괄 적용해 줌
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        //불러올 Bean 객체의 이름(스프링 컨테이너에는 보통 기본 이름으로 들어가 있음) 및 타입

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        //데이터가 입력이 되었는지 회원정보 찾기 기능을 구현
        Member findMember = memberService.findMember(1L);
        System.out.println("new Member = " + member.getName());
        System.out.println("findMember = " + findMember.getName());

        /*
        순수 JAVA로 개발하여 테스트 한 이력
        //(main method로 테스트를 개발하는 것은 좋은 방법은 아님 -> test 폴더 내에 파일을 다시 생성할 것)

        //AppConfig를 설정하고 나서 코드를 변경해줌
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        //appConfig를 통해 memberService 인터페이스를 받음 -> Impl이 들어가 있을 것

        //회원 서비스 구현체를 불러옴 (이전 코드라 더이상 활용하지 않음)
        //MemberService memberService = new MemberServiceImpl();
        
        //새로운 member 정보를 입력하여 회원가입
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);
        
        //데이터가 입력이 되었는지 회원정보 찾기 기능을 구현
        Member findMember = memberService.findMember(1L);
        System.out.println("new Member = " + member.getName());
        System.out.println("findMember = " + findMember.getName());
        */


    }
}
