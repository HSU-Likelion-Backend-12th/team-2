package lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLIfeCycleTest {
    @Test
    public void lifeCycleTest(){
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = context.getBean(NetworkClient.class); // 조회
        context.close(); // 스프링 컨테이너를 종료한다. 이때, ConfigurableApplicationContext 파일이 필요하다.
    }

    @Configuration
    static class LifeCycleConfig {

        @Bean
        // 여기서 return된 결과물이 Spring bean으로 등록된다.
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }
}
