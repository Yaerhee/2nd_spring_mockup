package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class AppConfig {

    //memberService는 이 클래스에서 직접 만들게 됨
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
        //Impl 내의 인터페이스를 MemoryMemberRepository()에서 변경
        //기존의 Impl에 있던 = MemoryMemberRepository 부분을 삭제하고
        //이 클래스를 통해 생성자로 주입되도록 변경
    }

    private MemberRepository memberRepository() {
        //다시 주입되는 부분을 상위에서 호출함으로써 역할이 더 뚜렷하게 구분이 됨
       return new MemoryMemberRepository(); //역할에 해당하는 구현 클래스
    }

    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    public DiscountPolicy discountPolicy() { //상위의 생성자를 다시 끌어옴으로써 역할이 잘 구분되고 있음
        return new FixDiscountPolicy(); //역할에 해당하는 구현 클래스
    }


}
