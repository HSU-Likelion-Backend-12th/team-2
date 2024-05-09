package spring.springcorebasic.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.springcorebasic.AppConfig;

public class MemberServiceTest {

    ApplicationContext applicationContext =new
            AnnotationConfigApplicationContext(AppConfig.class);
    MemberService memberService =
            applicationContext.getBean("memberService", MemberService.class);
    // Test Case를 쉽게 작성하기 위한 로직 given-when-then
    @Test
    void join() {
        // given 이런 환경이 주어졌을때
        // 새로운 유저가 있을때
        Member member = new Member(1L, "choi", Grade.VIP);

        // when 이렇게 했을 때
        // 그 유저가 가입을 했을때
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        // then 이렇게 된다.
        // 가입한 유저와 가입된 유저가 같은 객체인지 확인
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}
