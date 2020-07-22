### 꿀 팁~
#### 1. 스프링 부트에서 yml
- .properties에 비해 상대적으로 유연한 구조를 가졌다.
- yml은 상위 계층에 대한 표현, List등 완전하게 표현 가능

#### 2. IDE를 인텔리제이로 사용 시 .http로 Postman 대체 가능
- [https://jojoldu.tistory.com/266](https://jojoldu.tistory.com/266)

#### 3. 생성시간 / 수정시간 자동화 - JPA Auditing

#### 4. SpringDataJpa 버전에서 LocalDate와 LocalDateTime이 DB 저장 시 제대로 전환이 안되는 경우가 있다.
- 이 문제는 SpringDataJpa의 코어 모듈인 Hibernate core 5.2.10부터는 해결 되었다.

#### 5. application.yml에 DB 정보 등 민감한 정보가 노출되면 보안에 문제가 발생한다. 
- 스프링 부트 사용 시 이런 문제를 해결하기 위한 방법 중 하나로, Jasypt 라이브러리(오픈 소스)를 사용한다.
- 사용 방법
    1. jasypt 의존성 추가
    2. jasypt 관련 설정 클래스 추가 (애노테이션 기준)
    3. .yml 또는 .properties 파일에 jasypt 설정 파일 추가
    4. Application.java 메인 클래스에 @EnableEncryptableProperties 추가

#### 6. 암호화 방식
- 암호화 방식이 모두 안전하다고 말하는 데는 무리가 있다. md5 방식만 봐도 복호화할 수 없다고 했지만, 이젠 md5도 복호화가 가능하다.
- BCryptPasswordEncoder
    - bcrypt 해시 알고리즘 이용하여 데이터 암호화
- StandardPasswordEncoder
    - sha 해시 알고리즘 이용하여 데이터 암호화
    - default는 sha-256
- 두 알고리즘 모두 내부적으로 salt를 사용
- Spring Security 신규 개발 시 BCrypt 사용 권장
- 기존 sha 해시 적용 시 Standard 사용 권장