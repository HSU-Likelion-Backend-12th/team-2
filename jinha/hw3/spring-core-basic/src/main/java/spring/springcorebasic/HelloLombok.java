package spring.springcorebasic;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HelloLombok {

    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("jinha");
        helloLombok.setAge(23);

        String name = helloLombok.getName();
        int age = helloLombok.getAge();

        System.out.println("Hello " + name + " " + age + "!");
    }
}
