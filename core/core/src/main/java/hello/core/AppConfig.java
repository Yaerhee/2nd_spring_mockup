package hello.core;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class AppConfig {

    //memberService는 이 클래스에서 직접 만들게 됨
    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository());
        //기존의 Impl에 있던 = MemoryMemberRepository 부분을 삭제하고
        //이 클래스를 통해 생성자로 주입되도록 변경
    }

    public OrderService orderService() {
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }


}
