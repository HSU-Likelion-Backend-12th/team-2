package Hello.HelloSpring.service;

import Hello.HelloSpring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    //@Autowired private DataSource dataSource;혹은
    //private DataSource dataSource; //이렇게도 가능하다. this is for jdbcTemplate

    /*public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }*/

    //for JPA
    //@PersistenceContext
    //private EntityManager em;

    //public SpringConfig(EntityManager em) {
    //    this.em = em;
    //}

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository);
    }

    /*
    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
        return new JdbcMemberRepository(dataSource);
        return new JpaMemberRepository(em);
    }
    */
}
