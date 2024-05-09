package spring.springcorebasic;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.springcorebasic.member.Grade;
import spring.springcorebasic.member.Member;
import spring.springcorebasic.member.MemberService;
import spring.springcorebasic.member.MemberServiceImpl;
import spring.springcorebasic.order.Order;
import spring.springcorebasic.order.OrderService;
import spring.springcorebasic.order.OrderServiceImpl;

public class OrderApp {
    public static void main(String[] args) {
        ApplicationContext applicationContext =new
                AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService =
                applicationContext.getBean("memberService", MemberService.class);
        OrderService orderService =
                applicationContext.getBean("orderService",OrderService.class);
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 1000);

        System.out.println("order = " + order);
    }
}
