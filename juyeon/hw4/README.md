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
