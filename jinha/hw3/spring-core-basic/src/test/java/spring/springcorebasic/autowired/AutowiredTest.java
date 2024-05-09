package spring.springcorebasic.autowired;

import jakarta.annotation.Nullable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.springcorebasic.member.Member;

import java.util.Optional;

public class AutowiredTest {
    @Test
    void AutowiredOption(){
        ApplicationContext context = new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean{
        // @Autowired(required = false) : 자동 주입할 대상이 없으면 Setter 메서드 자체가 호출되지 않는다.
        // 따라서 위 코드는 호출되지 않는다.
        @Autowired(required = false)
        // Member가 Spring bean으로 등록되어있지 않기 때문에 required가 true로 된다면 오류가 난다.
        public void setNoBean1(Member noBean1){
            System.out.println("setNoBean1 = " + noBean1);
        }

        //org.springframework.land.@Nullable : 자동 주입할 대상이 없으면 null이 입력된다.
        // 따라서 위 코드에서는 null이 호출된다.
        @Autowired
        public void setNoBean2(@Nullable Member noBean2){
            System.out.println("setNoBean2 = " + noBean2);
        }

        // Optional<> : 자동 주입할 대상이 없으면 Optional.empty 가 입력된다.
        // 따라서 위 코드에서는 Optional.empty가 호출된다.
        @Autowired(required = false)
        public void setNoBean3(Optional<Member> noBean3){
            System.out.println("setNoBean3 = " + noBean3);
        }
    }
}
