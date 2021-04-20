package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;

//순수 JAVA로 개발하여 테스트 해보기
//(main method로 테스트를 개발하는 것은 좋은 방법은 아님 -> test 폴더 내에 파일을 다시 생성할 것)
public class MemberApp {

    public static void main(String[] args) {
        //회원 서비스 구현체를 불러온 후
        MemberService memberService = new MemberServiceImpl();
        
        //새로운 member 정보를 입력하여 회원가입
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);
        
        //데이터가 입력이 되었는지 회원정보 찾기 기능을 구현
        Member findMember = memberService.findMember(1L);
        System.out.println("new Member = " + member.getName());
        System.out.println("findMember = " + findMember.getName());

    }
}
