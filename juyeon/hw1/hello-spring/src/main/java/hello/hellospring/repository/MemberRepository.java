package hello.hellospring.repository;
import hello.hellospring.domain.Member;
import java.util.List;
import java.util.Optional;
//Repository = 데이터 베이스에 접근, 도메인 객체(Member)를 DB에 저장하고 관리하기 위함


public interface MemberRepository{
	Member save(Member member); //저장
	Optional<Member> findById(Long id); //아이디로 찾기
	Optional<Member> findByName(String name); //이름으로 찾기
	List<Member> findAll(); //모두 찾기
}