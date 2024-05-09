package spring.springcorebasic.discount;

import spring.springcorebasic.member.Grade;
import spring.springcorebasic.member.Member;

public class RateDiscountPolicy implements DiscountPolicy{

    private int discountParcent = 10; //10% discount rate
    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP){
            return price * discountParcent / 100;
        }
        else{
            return 0;
        }
    }
}
