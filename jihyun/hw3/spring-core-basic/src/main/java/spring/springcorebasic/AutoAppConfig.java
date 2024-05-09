package spring.springcorebasic;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

// @ComponentScan을 사용하면 @Configuration의 설정 정보도 자동으로 등록된다. excludeFilters를 사용하면 설정 정보를 스캔 대상에서 제외할 수 있다.
// basePackages로 탐색을 시작할 패키지를 지정한다.
@Configuration
@ComponentScan(
        basePackages = "spring.springcorebasic.member",
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class))
public class AutoAppConfig {
}
