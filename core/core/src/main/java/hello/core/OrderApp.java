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
        //appConfig 설정하기
        AppConfig appConfig = new AppConfig();
        //각 Impl 클래스에 접근하여 생성자 주입을 처리하도록 함(구조를 구축)
        MemberService memberService = appConfig.memberService();
        OrderService orderService = appConfig.orderService();
        //-> 더이상 구체 클래스에 의존하지 않음

        //MemberService memberService = new MemberServiceImpl();
        //OrderService orderService = new OrderServiceImpl();

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
        //System.out.println("order.calculatePrice = " + order.calculatePrice());
    }

}
