package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memberService;

    @BeforeEach //Test 실행 전 AppConfig 설정이 선행되도록 함
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    @Test //JUnit 활용
    void join() {
        //given
        Member member = new Member(1L, "memberA", Grade.VIP);

        //when
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        //then
        Assertions.assertThat(member).isEqualTo(findMember);

        //-> join한 멤버 = 찾은 멤버일 경우 Test 성공
        //Test가 실패하더라도 어떤 부분에서 무엇이 문제인지를 자세히 알려주기 때문에 테스트 코드는 필수임!
    }
}
