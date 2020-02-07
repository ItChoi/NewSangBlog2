### 꿀 팁~
#### 1. 스프링 부트에서 yml
- .properties에 비해 상대적으로 유연한 구조를 가졌다.
- yml은 상위 계층에 대한 표현, List등 완전하게 표현 가능

#### 2. IDE를 인텔리제이로 사용 시 .http로 Postman 대체 가능
- [https://jojoldu.tistory.com/266](https://jojoldu.tistory.com/266)

#### 3. 생성시간 / 수정시간 자동화 - JPA Auditing

#### 4. SpringDataJpa 버전에서 LocalDate와 LocalDateTime이 DB 저장 시 제대로 전환이 안되는 경우가 있다.
- 이 문제는 SpringDataJpa의 코어 ㅁ듈인 Hibernate core 5.2.10부터는 해결 되었다.