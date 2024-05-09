package hello.hellospring.service;
//Service : 핵심 비즈니스 로직 구현

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

@Transactional
public class MemberService{
	private final MemberRepository memberRepository; //멤버의 데이터를 다루기 위해 MemberRepository 객체 생성

	@Autowired // 생성자에 @Autowired를 사용하면 객체 생성 시점에 스프링 컨테이너에서 해당 스프링 빈을 찾아서 주입함.
	public MemberService(MemberRepository memberRepository){
		this.memberRepository = memberRepository;
	}
	public Long join(Member member) { //회원가입
		validateDuplicateMember(member); //회원의 중복 여부 확인
		memberRepository.save(member); //멤버 Repository에 새로운 멤버 저장
		return member.getId(); //멤버 id 리턴
	}
	
	//같은 이름이 있는 중복 회원x
	private void validateDuplicateMember(Member member) {
		memberRepository.findByName(member.getName()) //name으로 해당 이름을 가진 member가 있는지 확인
		.ifPresent(m -> { //같은 name이 존재하는 경우(중복된 회원이 존재)에만 실행

			throw new IllegalStateException("이미 존재하는 회원입니다"); //예외 발생
		});
		}
	
	public List<Member> findMembers() { //모든 멤버 찾기
		//repository에 저장된 멤버 찾기 (Array리스트가 오나?)
		return memberRepository.findAll();
	}
	
	public Optional<Member> findOne(Long memberId){ //멤버 한명만 찾기
		//멤버 id를 이용하여 Repository에 저장되어 있는 member 찾기
		return memberRepository.findById(memberId);
	}
}