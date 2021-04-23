package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
//Assertions.assertThat으로 사용하기에는 중복되기 때문에 static method로 사용하는 것이 좋음 

class RateDiscountPolicyTest {

    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10% 할인 적용")
    void vip_o() {
        //given
        //VIP등급의 회원이 존재할 때
        Member member = new Member(1L, "memberVIP", Grade.VIP); 
        //when
        //해당 회원이 만원어치를 샀다고 하면 정률로 계산된 할인 금액이
        int discount = discountPolicy.discount(member, 10000); 
        //then
        //1000과 같은지
        assertThat(discount).isEqualTo(1000);
    }

    //위의 테스트는 성공하는 경우의 테스트이므로 하단과 같이 실패 테스트도 같이 만들어 주자
    @Test
    @DisplayName("VIP가 아닐 경우 할인 미적용")
    void vip_x() {
        //given
        //BASIC등급의 회원이 존재할 때
        Member member = new Member(1L, "memberBASIC", Grade.BASIC);
        //when
        //해당 회원이 만원어치를 샀다고 하면 정률로 계산된 할인 금액이
        int discount = discountPolicy.discount(member, 10000);
        //then
        //1000과 같은지? (돌려보자)
        //Assertions.assertThat(discount).isEqualTo(1000); -> 이대로 돌리면 0원으로 나오지 않아 오류 발생
        assertThat(discount).isEqualTo(0); //0으로 돌리면 문제 없이 테스트 끝
    }

}