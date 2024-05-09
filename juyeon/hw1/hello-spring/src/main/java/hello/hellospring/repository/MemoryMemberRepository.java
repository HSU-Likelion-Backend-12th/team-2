package hello.hellospring.repository;

import java.util.*;

import org.springframework.stereotype.Repository;

import hello.hellospring.domain.Member;

@Repository
public class MemoryMemberRepository implements MemberRepository {
    private static Map<Long, Member> store = new HashMap<>(); //Long 타입 key와 Member 객체를 값으로 가지는 HashMap 객체 store 생성
    private static long sequence = 0L; //멤버의 ID , 멤버가 저장될 때 마다 1씩 증가, 고유한 ID -> 저장된 멤버 식별 및 검색 가능
    
    @Override
    public Member save(Member member)  { //멤버를  저장하는 메소드
        member.setId(++sequence); //ID 저장
        store.put(member.getId(), member); //store맵에 Id와 member 저장
        return member;
    }
    
    @Override
    public Optional<Member> findById(Long id) { //Id로 찾기
        //Optional : 반환할 값이 없을 수도 있을 수도 있음을 나타냄
        //store 맵에 해당하는 id의 멤버가 없을 수 있기 때문에 그런 경우를 대비하여
        //Optional로 감싸서 반환함
        return Optional.ofNullable(store.get(id));
    }
    
    @Override
    public Optional<Member> findByName(String name){ //이름으로 찾기
        //values() : store맵에 있는 모든 member 객체
        // stream() : 해당 값들의 스트림을 생성
        return store.values().stream()
                //stream()에서의 멤버의 이름이 주어진 이름과 일치하는지
                // member 객체의 getName()메소드를 이용하여검사
                .filter(member -> member.getName().equals(name))
                //findAny() : 필터링 된 결과 중 임의의 요소 반환
                // Optional<Member>로 감싸서 반환
                //일치하는 멤버가 존재 하지 않으면 Optional 반환
                .findAny();
    }
    
    @Override
    public List<Member> findAll(){ //모두 찾기?
        //ArrayList에 member 객체를 넣는다
        //새로운 ArrayList 반환
        return new ArrayList<>(store.values());
    }
    
    public void clearStore()  { //지우기
    	 store.clear();
    	 }
    
}
