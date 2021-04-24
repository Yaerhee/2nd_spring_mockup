package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    //회원 정보 조회를 위해 객체 호출
    private final MemberRepository memberRepository = new MemoryMemberRepository();
    //하단의 주석 코드와는 다르게, 인터페이스에만 의존하도록 설계 및 코드를 변경하였음
    private DiscountPolicy discountPolicy;
    //실행해 보면 NullPointerException임 -> discountPolicy에 아무 값도 할당이 안 되어 있음
    //=> ** 해당 인터페이스의 구현 객체를 대신 생성하고 주입해 주는 존재가 필요함 **

    //아래의 코드는 이전에 시도했던 소스 코드임(참고용)

    /*
    //할인 정책 관련 객체도 호출
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    //정률 할인으로 바뀌었으므로 다시 적용해주면 되..지만 사실 소스 코드를 직접 고친 상황
    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    //OCP를 위반하고 있음(맞물리는 소스 코드도 함께 변경해야 함)
    //+DIP도 위반: Impl 클래스가 FixDiscountPolicy 구현체 클래스에 함께 의존하고 있음
    */

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
