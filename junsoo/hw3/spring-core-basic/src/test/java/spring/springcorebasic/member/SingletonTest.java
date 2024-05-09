package spring.springcorebasic.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import spring.springcorebasic.AppConfig;

public class SingletonTest {
    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer(){
        // 고객 접근이 초당 100명이 될 경우, 객체는 총 100개가 동시에 생성되어 메모리 낭비 및 충돌을 야기할 수 있다.
        AppConfig appConfig = new AppConfig();
        // 첫 번째 조회 : 호출할 때 마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();
        // 두 번째 조회 : 호출할 때 마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();
        // 참조 값이 다른다.
        System.out.println("memberService1 = "+ memberService1);
        System.out.println("memberService2 = "+ memberService2);

        // memberService != memberService2
        Assertions.assertThat(memberService1).isNotSameAs(memberService2);
    }
}