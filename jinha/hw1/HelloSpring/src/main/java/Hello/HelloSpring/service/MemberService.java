package Hello.HelloSpring.service;

import Hello.HelloSpring.domain.Member;
import Hello.HelloSpring.repository.MemberRepository;
import Hello.HelloSpring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@Service //for component scan
//메서드 이름을 지정할 때 service는 보통 business에 더 의존적으로 설계를 하고, repository는 기계적인 용어들을 선택한다.
public class MemberService {
    private final MemberRepository memberRepository;

    //@Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    /*
    * 회원가입
    */
    public Long join(Member member){
        //같은 이름을 가진 중복 회원은 안된다.
        /*Optional<Member> result = memberRepository.findByName(member.getName());
        result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }); -> 어차피 findByName은 Optional 객체를 반환하므로 위의 코드는 아래와 같이 줄일 수 있다.*/

        validateDuplicateMember(member); //중복 회원 검증

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
            .ifPresent(m -> {
                throw new IllegalStateException("이미 존재하는 회원입니다.");
            });
    }

    /*
    * 전체 회원 조회
    */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
