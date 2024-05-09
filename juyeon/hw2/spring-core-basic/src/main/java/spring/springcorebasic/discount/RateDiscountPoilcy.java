package spring.springcorebasic.discount;

import spring.springcorebasic.member.Grade;
import spring.springcorebasic.member.Member;

public class RateDiscountPoilcy implements DiscountPolicy {
    private int discountPercent = 10; //10% 할인

    @Override
    public int discount(Member member, int price){
        if(member.getGrade() == Grade.VIP){
            return price * discountPercent / 100;
        }
        else {
            return 0;
        }
    }

}
