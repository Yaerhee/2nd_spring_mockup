package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName() {
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        //System.out.println("memberService = " + memberService); 출력하여 테스트
        //System.out.println("memberService.getClass() = " + memberService.getClass());
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
        //memberService가 MemberServiceImpl 클래스의 인스턴스가 맞는지 assertion -> pass하면 성공
    }

    @Test
    @DisplayName("이름 없이 타입으로만 조회") //같은 타입이 여러개일 때는 사용하기 곤란함
    void findBeanByType() {
        MemberService memberService = ac.getBean(MemberService.class); //인터페이스
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("구체 타입으로 조회") //직접 클래스를 입력하여 조회. 유연성이 떨어짐(추천 방법은 X)
    void findBeanByName2() {
        MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
        //스프링 빈으로 등록 된 인스턴스 타입을 보고 출력을 결정(꼭 인터페이스를 지정하지 않아도 됨)
    }

    //실패 테스트
    @Test
    @DisplayName("빈 이름으로 조회 X")
    void findBeanByNameX() {
        //ac.getBean("xxxxx", MemberService.class);
        //MemberService xxxxx = ac.getBean("xxxxx", MemberService.class);
        //xxxxx라는 이름의 빈이 없기 때문에 예외가 발생함
        
        //하단과 같이 람다식 우측에 있는 조건을 실행했을 때, 좌측에 서술한 예외가 발생해야 한다는 조건으로
        //assertThrows 구문을 작성 (예외가 발생해야 성공)
        assertThrows(NoSuchBeanDefinitionException.class, 
                () -> ac.getBean("xxxxx", MemberService.class));
    }
    
}
