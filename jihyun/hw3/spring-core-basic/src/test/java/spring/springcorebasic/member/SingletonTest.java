package spring.springcorebasic.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import spring.springcorebasic.AppConfig;

public class SingletonTest {
    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();

        // 첫번째 조회 : 호출할 때마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();

        // 두번째 조회 : 호출할 때마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();

        // 참조값 다름
        System.out.println("memberService1 = " + memberService1);
        System.out.println("membeService2 = " + memberService2);

        // memberService1 != memberService2
        Assertions.assertThat(memberService1).isNotSameAs(memberService2);



    }
}
