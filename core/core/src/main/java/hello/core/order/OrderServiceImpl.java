package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    //회원 정보 조회를 위해 객체 호출
    private final MemberRepository memberRepository = new MemoryMemberRepository();
    //할인 정책 관련 객체도 호출
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        //단일책임원칙이 잘 지켜지고 있음(order에 있어 할인 정책은 DiscountPolicy가 담당)
        //-> 할인 관련하여 수정이 필요할 때는 DiscountPolicy만 수정하면 됨

        //할인 금액까지 계산 후 주문 return
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
