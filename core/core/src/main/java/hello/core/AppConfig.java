package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//Spring 방식으로 코드 변경
@Configuration //설정 정보, 구성 정보
public class AppConfig {

    @Bean //스프링 컨테이너에 등록하기 위한 애노테이션
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }


    /*

    -- 순수 Java로만 구성된 이전 코드(참고용으로 남겨놓음) --

    //memberService는 이 클래스에서 직접 만들게 됨
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
        //Impl 내의 인터페이스를 MemoryMemberRepository()에서 변경
        //기존의 Impl에 있던 = MemoryMemberRepository 부분을 삭제하고
        //이 클래스를 통해 생성자로 주입되도록 변경
    }

    public MemberRepository memberRepository() {
        //다시 주입되는 부분을 상위에서 호출함으로써 역할이 더 뚜렷하게 구분이 됨
       return new MemoryMemberRepository(); //역할에 해당하는 구현 클래스
    }

    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    public DiscountPolicy discountPolicy() { //상위의 생성자를 다시 끌어옴으로써 역할이 잘 구분되고 있음
        //return new FixDiscountPolicy();
        //역할에 해당하는 구현 클래스이며, 변경이 필요할경우 하단과 같이 연결되는 클래스만 바꿔주면 됨
        return new RateDiscountPolicy();
        //Rate로 변경해 준 후 Test들과 OrderApp을 실행보면, 잘 적용되어 있는 것을 확인할 수 있음
    }
    */

}
