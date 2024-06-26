package spring.springcorebasic.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// 관례상 구현체는 인터페이스 이름 + Impl
// 새로운 회원을 가입(join)하고, 조회(findMember)하는 기능 구현 필요
// 위 기능을 위해 저장소(MemberRepository)에 대한 참조가 필요
@Component
public class MemberServiceImpl implements MemberService{

    // new 키워드를 사용하여 인터페이스에 대한 구현체 설정
    // => 구현체가 변경될 경우 코드 수정이 필요하므로 SOLID 원칙중 DIP(의존관계 역전 원칙)을 위배함
    // DIP : 구현에 의존하지 않고, 역할에 의존해야한다. => New 키워드를 사용한 구현체에 직접 의존하면 안된다.
    private final MemberRepository memberRepository;

    // AppConfig에서는 @Bean으로 직접 설정 정보를 작성하고 의존 관계를 명시했지만,
    // 현재(AutoAppConfig)는 설정 정보 자체가 없기 때문에 의존 관계 주입도 이 클래스 안에서 해결해야 한다.
    @Autowired // 의존 관계를 자동으로 주입해준다.
    public MemberServiceImpl (MemberRepository memberRepository) {

        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {

        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {

        return memberRepository.findById(memberId);
    }
}
