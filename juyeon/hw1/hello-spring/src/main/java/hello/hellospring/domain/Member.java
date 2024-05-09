package hello.hellospring.domain;

import jakarta.persistence.*;

public class Member { //회원
    
    private Long id; //아이디
    private String name; //이름
    
    public Long getId() {
        return id;
    } //private로 선언된 id 변수에 접근 하기 위한 get 메소드
    
    public void setId(Long id) {
        this.id = id;
    } //private로 선언된 id 변수에 접근 하기 위한 set 메소드
    
    public String getName() {
        return name;
    } //private로 선언된 name 변수에 접근 하기 위한 get 메소드
    
    public void setName(String name) {
        this.name = name;
    } //private로 선언된 id 변수에 접근 하기 위한 set 메소드
}
