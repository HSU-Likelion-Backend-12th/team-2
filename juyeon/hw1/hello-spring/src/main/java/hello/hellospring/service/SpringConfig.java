package hello.hellospring.service;
import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import jakarta.persistence.EntityManager;

import javax.sql.DataSource;
//스프링 프레임워크에서 사용되는 설정 클래스, 의존성 주입을 설정하고 Bean객체를 생성하는 역할


//이 클래스가 스프링의 설정 클래스임을 나타냄
@Configuration
public class SpringConfig {
	
	private final MemberRepository memberRepository;
	private EntityManager em;
	
	@Autowired
	//생성자 -> 외부에서 주입된 MemberRepository 의존성을 받아 사용
	public SpringConfig(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	//@Bean : 스프링 컨테이너에 빈 객체를 등록
	@Bean
	public MemberService memberService() {
		return new MemberService(memberRepository());
	}
	@Bean
	public MemberRepository memberRepository() {
		// return new MemoryMemberRepository();
		// return new JdbcMemberRepository(dataSource);
		// return new JdbcTemplateMemberRepository(dataSource);
		return new JpaMemberRepository(em);
	}
}