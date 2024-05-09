package hello.hellospring.repository;
import hello.hellospring.domain.Member;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
/*
	JPA
	- JPA는 기존의 반복 코드는 물론이고, 기본적인 sql도 jpa가 직접 만등어서 실행한다
	- JPA를 사용하면, JQL과 데이터 중심의 설계에서 객체 중심의 설계로 패러다임을 전환할 수 있다.
	- JPA를 사용하면, 개발 생산성을 크게 높일 수 있다.
*/
public class JpaMemberRepository implements MemberRepository {
	//EntityManager를 멤버 변수로 선언
	//EntityManager : jpa에서 엔티티 관리를 담당하는 인터페이스로, 영속성 컨텍스트를 관리하고 엔티티를 데이터베이스와 매핑
	private final EntityManager em;
	//JpaMemberRepository의 생성자
	//EntityManager를 매개변수로 받아와서 멤버변수 em에 할당.
	public JpaMemberRepository(EntityManager em) {
		this.em = em;
	}
	
	@Override
	//데이터데이스에 회원 저장
	public Member save(Member member) {
		//EntityManager를 사용하여 주어진 회원 객체를 데이터 베이스에 영속화(persist)
		//영속화 (persist) : 엔티티를 영구 저장소에 저장하고 그 상태를 관리하는 것
		em.persist(member);
		//저장된 회원 객체 리턴
		return member;
	}
	
	@Override
	public Optional<Member> findById(Long id) { //데이터베이스에서 id로 멤버 조회
		//EntityManager에서 id에 해당하는 member를 찾아서 member 객체에 저장
		Member member = em.find(Member.class, id);
		//id에 해당하는 member가 없다면 Optional에 감싸서 리턴
		return Optional.ofNullable(member);
	}
	
	@Override
	public List<Member> findAll() { //데이터베이스에서 모두 조회
		return em.createQuery("select m from Member m", Member.class)
				.getResultList();
	}

	
	@Override
	public Optional<Member> findByName(String name) { //데이터베이스에서 이름으로 멤버 조회
		List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
				.setParameter("name", name)
				.getResultList();
		return result.stream().findAny();
	}
}