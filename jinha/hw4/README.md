# 📚 [hw4] Session5 과제를 통해 배운점

<br>

>  Session5 강의를 듣고 배운 점에 대해 정리해보고자 한다.

***

## 1️⃣ Api Response의 형식을 재정의하는 방법

  -> RestController는 기본적으로 Json 형태로 ResponseEntity 객체 데이터를 반환한다.
 
  -> 이때 응답에 응답 본문(Data, Message)과 함께 Http Status Code를 지정하여 요청에 대한 응답을 한 눈에 볼 수 있도록 재정의할 수 있다.

  -> 제네릭 클래스의 형태로 재정의할 클래스를 생성한 후 응답 본문에 담을 Http Status Code, Data, Message 필드를 가진다.

  -> 각각 Suceess, Failure 메서드도 정의할 수 있다.

  -> 재정의한 Responseentity를 활용하여 응답을 보내려면 재정의한 ResponseEntity를 사용하여 응답 Body를 구성한 다음, 이를 Body에 담아 반환하면 된다.

<br>

 > 예시코드
```java
@PostMapping("/simple")
public ResponseEntity<CustomApiResponse> simple(){

  // 1. 응답 Body 구성
  CustomApiResponse<Object> responseBody = CustomApiResponse.createSuccess(200/*HttpStatus.OK*/, null, "회원가입에 성공하였습니다.");

  // 2. 응답 Body를 ResponseEntity에 넣기
  return ResponseEntity.status(HttpStatus.OK).body(responseBody);

 }
```
<br>

***

## 2️⃣ Http Status Code에 따른 응답 메세지를 정의할 수 있는 ErrorController를 구현하는 방법

  -> 모든 요청에 대한 응답에 사용할 응답 메세지를 ErrorContoller를 구현하는 것을 통해 재정의할 수 있다.
  
  -> 해당 컨트롤러에서는 HttpServletRequest를 받아와서 getAttribute를 통해 Status를 확인하여 어떤 Error가 발생했음을 알 수 있다.

  -> Bad Request (400), Forbidden (403) 등에 대한 오휴 발생에 대한 응답 메세지를 1️⃣ 에서 만든 CustomResponseEntity를 통해 재정의할 수 있다.

  <br>

 > 예시코드(응답 메서드 재정의의 예)
```java
// Bad Request (400)
if(statusCode == HttpStatus.BAD_REQUEST.value()) { // statusCode == 400

  return ResponseEntity
    .status(HttpStatus.BAD_REQUEST)
    .body(new CustomApiResponse<>(HttpStatus.BAD_REQUEST.value(), null, "잘못된 요청입니다."));

}
```
<br>

***

## 3️⃣ 각각의 Error에 대한 예외처리를 지정하는 방법

  -> 전역적으로 사용할 GlobalExceptionHandler를 구현하는 것을 통해 예외처리를 할 수 있다.

  -> 이때, 클래스에 @ControllerAdvice 어노테이션을 붙이면 전역적으로 모든 컨트롤러의 예외처리를 담당하게 된다.

  -> 각각의 예외에 대한 처리는 @ExceptionHandler 어노테이션을 통해 예외를 지정할 수 있다.

  <br>

 > 예시코드(@ControllerAdvice가 붙은 GlobalExceptionHandler의 메서드)
```java
// javax.validation.ConstraintViolationException package 내에서 발생하는 Error Handler
// ConstraintViolationException 은 RuntimeException을 상속하는 unhandled exception이다.
// 데이터베이스에서 발생하는 예외 중 하나로, 제약 조건이 위배되었을 때 발생하는 예외이다.
// ex)_ DB의 Unique field 에 중복된 값을 insert 하려고 할 때
// ex)_ FK 제약조건을 위반하여 참조할 수 없는 값을 참조하려고 할 때
@ExceptionHandler(ConstraintViolationException.class)
public ResponseEntity<CustomApiResponse<?>> handleConstraintViolationException(ConstraintViolationException e){
  String errorMassage = e.getConstraintViolations().stream()
    .map(ConstraintViolation::getMessage)
    .collect(Collectors.joining("; "));

  return ResponseEntity
    .status(HttpStatus.BAD_REQUEST)
    .body(CustomApiResponse.createFailWithout(HttpStatus.BAD_REQUEST.value(), errorMassage));
}
```
<br>

***

## 4️⃣ @EntityListeners(AuditingEntityListener.class) 어노테이션

  -> jpa 에서 엔티티 생명주기 이벤트를 처리하기 위한 어노테이션이다.

  -> 이 클래스를 상속받은 클래스에 대해 AuditingEntityListener 클래스가 엔티티의 생성 및 변경을 감지하고 관리한다.

  -> 이 기능을 사용하기 위해서는 Main Application 파일에서 JpaAuditing을 활성화해주어야 한다.
  
  > 다음은 Main Application에서 JpaAuditing을 활성화한 모습이다.
```java
@EnableJpaAuditing
@SpringBootApplication
public class LikelionApplication {

	public static void main(String[] args) {
		SpringApplication.run(LikelionApplication.class, args);
	}

}
```

  -> 해당 어노테이션은 모든 Entity가 공통적으로 가지는 필드를 모아 공통 BaseEntity를 정의할 때 쓰인다.
