package spring.springcorebasic.order;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import spring.springcorebasic.discount.DiscountPolicy;
import spring.springcorebasic.discount.FixDiscountPolicy;
import spring.springcorebasic.discount.RateDiscountPoilcy;
import spring.springcorebasic.member.MemberRepository;
import spring.springcorebasic.member.MemberService;
import spring.springcorebasic.member.MemberServiceImpl;
import spring.springcorebasic.member.MemoryMemberRepository;

@Configuration
public class AppConfig {
    @Bean
    public MemberService  memberService(){
        return new MemberServiceImpl(memberRepository());
    }
    @Bean
    public OrderServiceImpl orderService(){
        return new OrderServiceImpl(discountPolicy(), memberRepository());
    }
    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
    @Bean
    public DiscountPolicy discountPolicy (){
        return new RateDiscountPoilcy();
    }
}
