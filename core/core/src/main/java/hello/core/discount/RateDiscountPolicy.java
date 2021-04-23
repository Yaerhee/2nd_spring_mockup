package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

//정률% 할인으로 유동적으로 변경하기 위한 코드 추가
public class RateDiscountPolicy implements DiscountPolicy {

    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100;
        } else {
            return 0;
        }
    }
}
