#server:
#  address: localhost
#  port: 8080

---

spring:
#  h2:
#    console:
#      enabled: true
  datasource:
    #driver-class-name: com.mysql.cj.jdbc.Driver
    #url: jdbc:mysql://localhost:3306/sangblog?serverTimezone=UTC
    #username: root
    #username: enffl18
    #password: ENC(6qflBiVkNBfNw4n2SKvl9Qjxxq7bjb3M)
    #password: Tkatjd1818
    driver-class-name: oracle.jdbc.driver.OracleDriver
    jdbc: jdbc:oracle:thin:@localhost:1521/orcl
    username: enffl18
    password: tkatjd18
 

    
#    driver-class-name: oracle.jdbc.driver.OracleDriver
#    url: jdbc:log4jdbc:oracle:thin:@localhost:1521:orcl
#    username: enffl18
#    password: tkatjd18
    
    type: com.zaxxer.hikari.HikariDataSource
    hikari:
      maximum-pool-size: 10
      max-lifetime: 30

#  jpa:
#    database: mysql
#    database-platform: org.hibernate.dialect.MySQL5InnoDBDialect
    # DDL 정의시 DB 고유 기능 사용, ex) 테이블 생성 삭제 등
#    generate-ddl: true
#    open-in-view: false
    # API 호출 시 , SQL문을 콘솔에 출력
#    show-sqsl: true    
#    hibernate:
      # 콘솔에 출력되는 JPA 실행 쿼리를 가독성있게 표현
#      format_sql: true
#      ddl-auto: create
  
---

logging:
  level:
    org:
      hibernate:
        SQL: DEBUG
        type:
          descriptor:
            sql:
              BasicBinder: TRACE
---
