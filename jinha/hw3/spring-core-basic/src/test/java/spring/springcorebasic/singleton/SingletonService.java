package spring.springcorebasic.singleton;

public class SingletonService {

/*  - static 영역에 객체 인스턴스를 미리 하나 생성해서 올려둠
    - 이 객체 인스턴스가 필요하면 오직 getInstance() 를 이용해서 조회 가능
    - 해당 메서드를 호출하면 항상 같은 인스턴스를 반환
    - 생성자를 private로 접근을 막아 외부에서 new 키워드로 객체 생성을 막음*/
    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance(){
        return instance;
    }

    private SingletonService() { }

    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }
}
