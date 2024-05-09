package spring.springcorebasic.singleton;

public class SingletonService {


       // - 생성자를 private로 접근을 막아 외부에서 new 키워드로 객체 생성을 막음
    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance(){
        return instance;
    }

    private SingletonService() { }

    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }
}