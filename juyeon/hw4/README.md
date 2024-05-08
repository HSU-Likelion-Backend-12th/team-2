## 1. 프로젝트 생성 
- 📁 프로젝트 구조
  - **LikelionApplication** : 이 application의 시작점 , 스프링부트 프로젝트 생성 시 자동으로 만들어짐
 
  - **application.properties** : 스프링부트 프로젝트에서 사용되는 중요한 설정 파일, application 설정, 메세지 리소스, 데이터베이스 연결 정보 등을 저장
 
  - **.gitignore** : 깃이 버전관리에서 제외할 파일 또는 디렉토리를 지정하는 설정, 여기에 작성된 파일들을 깃허브에 올라가지 않음 , 구글에 gitignore.io 검색 -> 가장 위에 나오는 사이트 클릭 -> 이 사이트를 통해 쉽게 gitignore를 만들 수 있음 (윈도우 운영체제 : Java, Intellij, Windows)
 
  - **build.gradle** : 전체적인 프로젝트 설정 파일, 프로젝트를 생성할 때 추가한 의존성이 들어가 있음, Gradle 빌드 도구를 사용하여 프로젝트를 설정하고 빌드하는데 사용되는 파일
 
- 📄MySQL 연결
  - **application.properties에서 설정 작성**
    ```
    spring.application.name=likelion
    # DB (local)
    # 스키마 명
    spring.datasource.url = jdbc:mysql://localhost:3306/likelion  
    #MySql 계정 정보
    spring.datasource.username=root
    spring.datasource.password=1234
    
    
    #show SQL -> 쿼리문을 예쁘게
    spring.jpa.properties.hibernate.farmat_sql = true
    
    #기존 스키마가 있으면 삭제하고 다시 생성
    spring.jpa.hibernate.ddl-auto=create
    ```
  - **스키마와 테이블**
    - 스키마 : 데이터베이스 구조를 정의하는 논리적인 컨테이너, 하나의 데이터베이스에는 여러 개의        스키마가 존재, 각 스키마는 그 자체의 이름 공간을 가지며, 해당 스키마 내의 테이블, 뷰, 인텍        스 같은 객체를 포함할 수 있음
   
    - 테이블 : 데이터베이스에서 실제로 데이터가 저장되는 곳

   
## 2. Controller 와 CustomApiResponse
- ✨**TestController**
  - 요청이 들어오면 Controller에서 경로를 찾고 Service 레이어로 넘어가서 Repository 계층과 DB계층     을 이용해서 적절히 데이터를 구성한 뒤 클라이언트로 응답을 내려줌.
    
  - **HttpStatus** : 요청 응답 상태
    
  - **JSON** : key와 value로 이루어진 데이터
    
  - **RestController** : 해당 클래스가 RESTful 웹 서비스의 Controller임을 나타냄
 
  - **ResquestMapping("/경로")** : 해당 메소드가 지정된 경로에 요청을 처리하는데 사용됨을 명시
 
  - **GetMapping("/경로")** : GET 메소드로 들어온 요청을 받음 (@PostMapping, @PutMapping, @DeleteMapping 등 존재)
- ✨**ResponseEntity 이해하기**
    - HttpStatus를 바꾸기 위해 ResponseEntity 객체를 사용
        - HTTP 응답을 나타내는 객체
        - 응답의 상태 코드, 헤더 및 본문 데이터를 포함할 수 있음
        - 클라이언트에게 보낼 응답을 생성하고 정의하는데 유용
        - 사용 예시 : ``` ResponseEntity.status(HttpStatus.BAD_REQUEST).body("실패"); ```
        - status(HttpStatus.BAD_REQUEST) : 응답 상태 코드 지정
        - body("실패") : 응답 body에 들어갈 데이터
          
    - 리턴 타입을 ResponseEntity로 작성할 땐, 어떤 타입의 데이터가 들어가는지 명시해줄것!
      - ``` ResponseEntity<String> ```
        
    - 제네릭(Generic) 이란?
       - 제네릭 타입은 클래스 또는 메소드를 작성할 때 일반적인 데이터 타입을 지정하는 방법을 제공
       - 이를 통해서 코드의 유연성과 재사용성을 높일 수 있음
       - 제네릭을 사용하면 클래스나 메소드를 선언할 때 데이터 타입을 파라미터로 받아들이고, 실제           사용될 때 그 데이터 타입을 결정
       - 제네릭 예시
         ```
         List<String> stringList = new ArrayList<>();
         stringList.add("Hello");
         stringList.add("World");
         ```
         - List는 제네릭 클래스
         - List를 선언할 때 원하는 데이터 타입을 명시할 수 있음
         - 예를 들어, List<String>은 문자열만을 저장하는 리스트를 나타냄
         - 이렇게 하면, stringList에는 문자열만 추가할 수 있음
         - 컴파일러가 타입 일치를 확인하기 때문에 타입 안정성이 보장

- ✨**CustomApiResponse 작성하기**
    - CustomApiResponse 클래스
        - 공통적으로 status, data, message 필드를 갖고 있어야함
        - JSON 형식의 데이터를 리턴해야함
        - CustomApiResponse는 ResponseEntity의 ResonposeBody에 들어가게 됨
        ```
        //1. 응답 body 구성
          CustomApiResponse<Object> 
          responseBody = CustomApiResponse.createSuccess(HttpStatus.OK.value(), null, "회원 가입에 성공했습니다.");
        //2. 응답 Body를 ResponseEntity에 넣기
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
        ```
    - Dto (Data Transfer Object)
      - 프론트에서 필요로 하는 데이터
      - 데이터를 전송하기 위한 객체
      - 순수한 데이터만 존재
      - Lombok 어노테이션 사용
          - @Getter, @Setter  : Getter 및 Setter를 자동으로 만들어주는 어노테이션
          - @AllArgsConstrustor : 모든 필드를 매개 변수로 받는 생성자를 만들어주는 어노테이션
          - @NoArgsConstructor : 아무 필드도 갖지 않는 생성자를 만들어주는 어노테이션
          - @Builder
      - dto 클래스 방법
        1. new 로 생성하기
          ```
            SimpleDto  dto = new SimpleDto("example","example@naver.com");
          ```
        3. builder로 생성하기
          ```
          SimpleDto dto = SimpleDto.builder()
                .userId("example")
                .email("example@naver.com")
                .build();
           ```
         


## 3. CustomErrorController와 GlobalExceptionHandler 
- ✨**CustomErrorController**
  - 존재하지 않는 api 경로로 요청을 보냈을 경우에는 기본적으로
    ```
     {
        "timestamp" : "2024-05-04~~~~",
        "status" : 404,
        "error" : "Not Found",
        "path" : "/hello"
      }
    ```
    이런 형식으로 스프링부트의 기본 응답 형식으로 응답한다.
    - 하지만 CustomApiResponse 클래스를 활용하여 어떤 오류인지에 따라 알맞은 에러 메세지와 상태        가 갖춰진 형식으로 응답이 오도록 할 수 있다.
  - ErrorContorller 인터페이스를 구현
   - @RestController : RESTful 웹 서비스의 컨트롤러임을 나타냄
   - @RequestMapping("/error") : /error 경로로 들어오는 요청을 이 클래스의 메소드로 매핑
  - handleError 메소드 : HttpServletRequest : Spring MVC에서 요청에 대한 정보를 전달하는 데 사용되는 클래스
  - Object status = requset.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
      :현재 요청에서 발생한 오류 코드 받아올 수 있음
  - 실습으로 구현해본 error 응답 형식
    - 400 BAD_REQUEST (잘못된 요청)
    - 403 FORBIDDEN (접근 금지)
    - 404 NOT_FOUND (요청 경로를 찾을 수 없음)
    - 405 METHOD_NOT_ALLOWED (허용되지 않은 메소드)
    - 500 INTERNAL_SERVER_ERROR (내부 서버 오류)

- ✨**GlobalExceptionHandler**
  - 특정 유형의 예외를 처리하는 클래스
    - @ControllerAdvice : 이 클래스가 전역 예외 처리를 담당함을 나타냄
    - @ExceptionHandler : 처리할 예외 클래스 작성

## 4. 회원가입 API
- ✨**User 엔티티 작성하기**
   - @Entity : 클래스를 JPA 엔티티로 표시한다. 즉, 이 클래스의 인스턴스는 데이터베이스 테이블의 해당 레코드에 매핑된다.
   - @Table(name = "USERS") : 이 엔티티가 매핑된 테이블의 세부 정보를 지정. 이 경우에는 백그라운드 데이터베이스의 "USERS" 테이블에 엔티티가 매핑되었음을 지정
   - @Getter : getter 메소드를 자동으로 생성
   - @NoArgsConstrustor : 매개변수가 없는 생성자 생성
   - @AllArgsConstructor : 모든 필드에 대한 매개변수가 있는 생성자 생성
   - @Builder : 빌더 패턴을 생성하여 많은 필드가 있는 객체의 인스턴스화를 더 쉽게 함.
   - @Id : 해당 필드를 엔티티의 기본 키로 표시
   - @GeneratedValue(strategy = Generation.IDENTITY) : 기본 키 값을 어떻게 생성할지를 지정함. 이 경우에는 데이터베이스가 식별 열을 사용하여 기본 키 값을 자동으로 생성함.
   - Column (name ="id") : 필드가 데이터베이스 테이블 열에 매핑되는 세부 정보를 지정함. 이 경우에은 자바 클래스의 "id" 필드가 "USERS" 테이블의 "Id"열에 해당함을 지정

- ✨**Repository**
  - JPARepository 인터페이스
    - Spring Data JPA 프레임워크에서 제공하는 인터페이스
    - 기본적인 CRUD(create, Read, Update, Delete) 작업을 수행하는 메서드를 제공
    - 사용 방법 : extends JPARepository<T,ID>
  - UserRepository 인터페이스는 JPARepository 인터페이스를 상속
    -@Repository : Spring 데이터 엑세스 계층에서 데이터베이스 작업을 처리하는데 사용되는 클래스에 적용됨. 

- ✨**Service 계층**
  - 비즈니스 로직이 포함된 계층
  - @Service : 해당 클래스가 Spring의 서비스 컴포넌트임을 나타냄. Spring은 이 어노테이션이 지정된 클래스를 스캔하여 해당 클래스의 인스턴스를 생성하고 애플리케이션 컨텍스트 빈으로 등록함.
  - @RequiredArgsConstructor : lombok 라이브러리에서 제공하는 어노테이션으로 final로 선언된 필드를 가진 생성자가 자동으로 생성됨.
  -  
 
- ✨**Controller 계층**
  - @RestController : 해당 클래스가 RESTful 웹 서비스의 컨트롤러임을 나타냄. 이 클래스는 HTTP 요청을 처리하고 응답을 생성하는 역할을 함. @Controller와 @ResponseBody 어노테이션을 합친 것과 같은 역할을 함.
  - @RequestMapping : 컨트롤러의 메소드가 처리할 요청 URL의 기본 경로를 지정함. 
  - @RequierdArgsConstructor : lombok 라이브러리에서 제공하는 어노테이션으로, 필드에 대한 생성자가 자동으로 생성된다.

- ✨**BaseEntity 작성하기**
  - 데이터 생성 일시, 수정 일시와 같이 모든 테이블에 공통적으로 있는 필드들을 정의
  - @MappedSuperclass : 해당 클래스가 JPA 엔티티가 아니라고 표시. 그러나 이 클래스의 필드는 하위 엔티티 클래스에서 공유되어 매핑됨. 즉 이 클래스의 필드는 다른 엔티티 클래스에서 상속될 수 있음.
  - @EntityListener : 엔티티의 변경 사항을 모니터링하고 자동으로 생성 및 수정 시간을 관리하기 위해 사용.
  - @CreateDate : 이 어노테이션이 지정된 필드는 엔티티가 생성될 때의 시간을 자동으로 저장함.
  - @LastModifiedData : 이 어노테이션이 지정된 필드는 엔티티가 마지막으로 수정된 시간을 자동으로 저장함.
  - @EnableJpaAuditing(Main 클래스에)  : 자동 감사를 활성화하는 어노테이션, 이 어노테이션을 구성 클래스에 적용하면 엔티티의 생성일자 및 수정일자를 자동으로 관리할 수 있음. 
