package spring.springcorebasic;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

// @ComponentScan 어노테이션을 사용하면 하나하나 빈으로 등록해주는 수고를 덜 수 있다.

@Configuration
// @Configuration은 스프링 빈이 싱글톤을 유지하도록 추가 처리를 한다.
@ComponentScan(
        basePackages = "spring.springcorebasic.member",
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class))
public class AutoAppConfig {

}
