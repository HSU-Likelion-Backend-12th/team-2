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
    // to fulfill DIP

    @Autowired
    /*@Autowired를 사용하는 이유는 이전에 AppConfig 파일로 직접 의존관계를 등록해주었는데,
    * 이제는 AppConfig 파일을 이용한 의존성 주입을 하지 않기 때문에 직접 명시해주어야 하기 때문이다.
    * @Autowired는 의존성을 자동으로 주입해주는 역할을 하는 어노테이션이다.
    *  */
    public MemberServiceImpl(MemberRepository memberRepository) {
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
