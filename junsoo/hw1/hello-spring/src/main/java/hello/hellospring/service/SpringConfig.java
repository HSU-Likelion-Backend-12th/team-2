package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.*;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    private  final MemberRepository memberRepository;
    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /*private final EntityManager em;//jpa
    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    private DataSource dataSource;//jdbc템플릿
    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    */
    @Bean
    public MemberService memberService(){
        return  new MemberService(memberRepository);
    }
//    @Bean
//    public MemberRepository memberRepository(){
//        //return new JdbcMemberRepository(dataSource);
//
//        //return new JdbcTemplateMemberRepository(dataSource);
//
//        //  return  new JpaMemberRepository(em);
//
//    }
}
