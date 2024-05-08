## 스프링부트 프로젝트 likelion 생성
### Dependencies
- Spring Web : 웹 애플리케이션을 개발하기 위한 기본적인 웹 관련 라이브러리와 도구들을 포함
- Spring Data JPA : 자바 객체와 관계형 데이터베이스 간의 매핑을 간소화하고 객체 지향적인 데이터 액세스를 지원
- Validation : 입력 데이터나 동작의 유효성을 검사하고 보증하기 위함
- Lombok : 자바 소스 코드의 반복적이고 장황한 부분을 자동으로 생성
- MySQL Driver : 관계형 데이터베이스 관리 시스템(RDBMS) 중 하나로, 데이터베이스 관련 작업을 수행

### 프로젝트 구조
- main -> LikelionApplication : 이 어플리케이션의 시작점! 스프링부트 프로젝트 생성시 자동으로 만들어진다.
- resources -> application.properties : 스프링부트 프로젝트에서 사용되는 중요한 설정 파일이다. 애플리케이션 설정, 메세지 리소스, 데이터 베이스 연결 정보 등을 저장하고 있다.
- .gitignore : 파일은 깃이 버전관리에서 제외할 파일이나 디렉토리를 지정할 수 있다. 이 파일에 작성된 파일이나 디렉토리는 깃허브에 올라가지 않는다.
- build.gradle : 전체적인 프로젝트 설정파일. 스프링부트 파일 생성시 추가한 의존성들이 들어가있다.

### 스키마와 테이블
- 스키마 
  - 데이터베이스 구조를 정의하는 논리적인 컨테이너
  - 하나의 데이터 베이스에는 여러 개의 스키마가 존재할 수 있다.
  - 스키마는 그 자체의 이름공간을 가지며 해당 스키마 내에 테이블, 뷰, 인덱스 같은 객체를 포함할 수 있다.
- 테이블은 데이터베이스에서 실제로 데이터가 저장되는 곳. 
##### 즉, 스키마는 데이터베이스 구조를 정의하는 논리적인 단위이고, 테이블은 해당 스키마 내에서 실제 데이터를 저장하는 데 사용되는 구조.

<br><br>

## Controller와 CustomApiResponse
### TestController
#### Server와 Client
클라이언트가 요청을 보내면 서버는 그에 맞는 응답을 제공한다. <br>
요청이 들어오면 Controller에서 그 경로를 찾고 Service 레이어로 넘어가서 Repository 계층과 DB 계층을 이용해 적절히 데이터를 구성한 다음, 클라이언트로 응답을 내려준다.

##### 1. 응답 Body 구성한다 <br> 2. 응답 Body를 ResponseEnity에 넣는다

#### 사용 어노테이션
@RestController : 이 어노테이션이 부착된 클래스는 restful 웹서비스의 컨트롤러이다. <br>
@RequsestMapping("/경로") : 해당 메소드가 지정된 경로에 요청을 처리하는데 사용됨을 명시한다. <br>
@GetMapping("/경로") : GET 메소드로 들어온 요청을 받는다. @PostMapping, @PutMapping, @DeleteMapping 등이 존재한다. <br>

### Response Entity
ResponseEntity : HTTP 응답을 나타내는 객체. 응답의 상태 코드, 헤더 및 본문 데이터를 포함할 수 있다. 클라이언트에게 보낼 응답을 생성하고 정의하는데 유용하다. <br>
###### 사용 예시 `ResponseEntity.status(HttpStatus.BAD_REQUEST).body("실패");`

## CustomApiResponse
### DTO
- 데이터를 전송하기 위한 객체
- 순수한 데이터만 존재
- Lombok 어노테이션 사용
  - @Getter
  - @Setter
  - @Builder
  - @NoArgsConstructor
  - @AllArgsConstructor
    
#### 사용 방법
1. new로 생성 <br>
  `SimpleDto dto = new SimpleDto("example", "example@naver.com");`
2. builder로 생성<br>
  `SimpleDto dto = SimpleDto.builder()
                .userId("example")
                .email("example@naver.com")
                .build();`
   
<br><br>

## CustomErrorController와 GlobalExceptionHandler
### CustomErrorController
#### 프로젝트 실행 후 존재하지 않는 API 경로로 요청을 보내면? 
스프링부트 기본 응답 형식이 온다. 

#### 응답 형식 커스텀
ErrorController 인터페이스를 구현함으로써 응답 형식을 커스텀할 수 있다. <br>

#### 사용 인터페이스, 클래스, 어노테이션
- ErrorController : 스프링에서 제공하는 표준 에러 컨트롤러 인터페이스로, 애플리케이션에서 발생한 에러를 처리하는 방법이 정의되어 있다.
- HttpServletRequest : 요청에 대한 정보를 처리하는데 사용되는 클래스이다.
- @RequestBody 어노테이션으로 RequestBody에 어떤 데이터가 들어올 것인지 명시해줄 수 있다.
- @ControllerAdvice : 부착된 클래스가 전역적으로 예외처리를 담당할 것이라는 것을 명시해주는 어노테이션

### GlobalExceptionHandler
- 특정 유형의 예외를 처리하는 클래스
#### 사용 어노테이션
- @ControllerAdvice : 이 클래스가 전역 예외 처리를 담당함을 나타낸다.
- @ExceptionHandler : 처리할 예외 클래스 작성

<br><br>

## 회원가입 API 작성
### JPA Repository
- Spring Data JPA 프레임워크에서 제공하는 인터페이스
- 기본적인 CRUD 작업을 수행하는 메서드 제공
###### 사용 예시 `extends JPARepository<T, ID>`

### Service
- 비즈니스 로직이 포함된 계층
- @Service, @RequiredConstructor

### Controller
- @RestController
- @RequestMapping
- @RequiredArgsConstructor

### BaseEntity
- 모든 테이블의 정보. 사용자가 언제 값을 바꿨는지 등을 추적할 수 있다.
#### 사용 어노테이션
- @EntityListeners : JPA에서 엔티티 생명주기 이벤트를 처리하기 위한 어노테이션. <br>
특정 엔티티 클래스에 적용되면서 AuditingEntityListener를 사용하여 해당 엔티티의 생성, 수정, 삭제 등의 생명주기 이벤트를 감지하고 처리한다. 이 기능을 사용하기 위해 JPAAuditing을 활성화해야한다. @EnableJpaAuditing 어노테이션을 달아주면 된다.



