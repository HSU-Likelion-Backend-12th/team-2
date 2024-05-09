package spring.springcorebasic.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.springcorebasic.AppConfig;
import spring.springcorebasic.member.MemberService;

public class SingletonTest {

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    public void singletonServiceTest(){
        // singleton이므로 new SingletonService();는 안된다.

        // 2번 접근. 같은 주소값을 가져야 한다.
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);

        Assertions.assertThat(singletonService1).isSameAs(singletonService2);

        singletonService1.logic();
    }

/*
    스프링 컨테이너는 객체 인스턴스를 싱글톤으로 관리
        - 컨테이너는 객체를 하나만 생성해서 관리
    스프링 컨테이너는 싱글톤 컨테이너 역할을 함
    스프링 컨테이너의 기능 덕분에 싱글톤 패턴의 단점을 해결하면서 객체를 싱글톤으로 유지 가능

    스프링 컨테이너는 싱글톤 패턴의 문제점을 보완하기 위해 스프링 컨테이너에 싱글톤 패턴을 적용한 것이다.
*/
    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        // 참조 값이 같아야 한다.
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        Assertions.assertThat(memberService1).isSameAs(memberService2);
    }
}
