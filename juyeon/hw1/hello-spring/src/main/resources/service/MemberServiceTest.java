package hello.hellospring.service;
//테스트 코드
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.*;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;

class MemberServiceTest{
	
	MemberService memberService; //서비스를 동작하기 위항 memberService 객체 생성
	MemoryMemberRepository memberRepository; //멤버 데이터에 접근하기 위해 memberRepository 객체 생성

	// BeforeEach : 각테스트 실행 전 호출 -> 테스트가 서로 영향이 없도록 항상 새로운 객체를 생성
	@BeforeEach
	public void beforeEach() {
		memberRepository = new MemoryMemberRepository();
		memberService = new MemberService(memberRepository);
	}
	@AfterEach
	public void afterEach() {
		memberRepository.clearStore();
	}
	//MemberService의 join() 함수 테스트, 회원 가입 검증
	@Test
	void 회원가입() {
		//given, 테스트를 위한 초기 설정, 새로운 회원 객체 생성
		Member member = new Member();
		member.setName("hello");
		//when, join 함수를 호출하여 회원 가입 시도
		//새로 가입된 회원 ID를 반환
		Long saveId = memberService.join(member);
		//then
		// findOne(saveId): 주어진 ID에 해당하는 회원 찾고
		// get()을 호출하여 해당 회원 객체를 가져옴
		Member findMember = memberService.findOne(saveId).get();
		//assertThat : 실제로 저장된 회원의 이름이 예상한 이름과 일치하는지 검증
		assertThat(member.getName()).isEqualTo(findMember.getName());
	}

	//validateDuplicateMember() 함수 테스트
	@Test
	void 중복_회원_예외() {
		//given
		// 테스트를 위한 member1 생성
		Member member1 = new Member();
		//member1 이름 설정
		member1.setName("spring");

		//테스트를 위한 member2 생성
		Member member2 = new Member();
		//member2 이름 설정 : member1과 같은 이름으로
		member2.setName("spring");

		//when
		//member1 회원가입
		memberService.join(member1);
		//member2를 가입 시킬 때 예외가 발생하는지 테스트함, 예외 발생 x -> 테스트 실패
		IllegalStateException e = assertThrows(IllegalStateException.class,() -> memberService.join(member2));//예외가 발생해야 한다.
		assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

//		try {
//			memberService.join(member2);
//			fail();
//		}catch(IllegalStateException e){
//			assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//		}
//		memberService.join(member2);
		
		//then
		
	}
	@Test
	void findOne() {
		
	}
}