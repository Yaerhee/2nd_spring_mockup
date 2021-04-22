package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class OrderApp {

    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        OrderService orderService = new OrderServiceImpl();

        //ID를 담을 그릇 생성
        Long memberId = 1L;
        //VIP 등급의 memberA -> 회원정보 입력
        Member member = new Member(memberId, "memberA", Grade.VIP);
        //memberA 회원가입 처리
        memberService.join(member);

        //주문 생성
        Order order = orderService.createOrder(memberId, "itemA", 10000);

        //위에 생성된 주문을 기반, Order.java에서 설계한 toString을 통해 내용이 출력됨
        System.out.println("order = " + order);
        System.out.println("order.calculatePrice = " + order.calculatePrice());
    }

}
