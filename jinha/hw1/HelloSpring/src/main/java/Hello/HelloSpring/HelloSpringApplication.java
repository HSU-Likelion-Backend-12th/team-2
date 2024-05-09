package Hello.HelloSpring;
//spring container는 위의 package와 동일 및 하위 패키지만 컴포넌트 스캔을 통해 빈으로 등록한다.

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelloSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloSpringApplication.class, args);
	}

}