### 스프링 시큐리티

#### 스프링 시큐리티란?
- 스프링 기반의 보안(인증, 권한)을 담당하는 프레임워크
- 스프링 시큐리티를 사용하지 않을 시 자체적으로 세션 체크, 리다이렉트 등이 필요
- filter 기반으로 동작
- Spring MVC와 분리되어 관리 및 동작
- security 3.2 부터는 간단하게 Bean으로 설정 가능
- 세션-쿠키방식으로 인증

#### 보안 용어 정리
- 접근 주체 (Principal)
    - 보호된 대상에 접근하는 유저
- 인증 (Authenticate)
    - 현재 유저가 누군인지 확인 (로그인)
    - 애플리케이션 작업 수행 주체임을 증명
- 인가 (Authorize)
    - 현재 유저가 어떤 서비스, 페이지에 접근할 수 있는 권한인 지 검사
- 권한 
    - 인증된 주체가 애플리케이션 동작을 수행할 수 있도록 허락되었는 지 결정
    - 권한 승인이 필요한 부분 접근 시 인증 과정을 통해 주체가 증명되어야만 한다.
    - 권한 부여에도 두 가지 영역이 존재, 웹 요청 권한과 메소드 호출 및 도메인 인스턴스에 대한 접근 권한 부여
    
#### Spring Security의 구조
- 인증  관련 아키텍처
    1. 유저 로그인 시도
    2. AuthenticationFilter는 user DB 조회
    3. DB 조회되는 유저는 UserDetails로 꺼내 유저 Session 생성
    4. spring security 인 메모리 세션 저장소인 SecurityContextHolder에 저장
    5. 유저에게 Session Id와 함께 응답
    6. 이후 요청은 요청 쿠키에서 JSessionId를 꺼내 검증 후 유효하면 Authentication을 부여
    <br/>
    
- Security Filter
    1. SecurityContextPersistenceFilter
      - SecurityContextRepository에서 SecurityContext를 가져오거나 저장하는 역할
    2. LogoutFilter 
      - 설정된 로그아웃 URL로 오는 요청을 감시하며, 해당 유저를 로그아웃 처리
    3. (UsernamePassword)AuthenticationFilter
      - 아이디와 비밀번호를 사용하는 form 기반 인증
      - 설정된 로그인 URL로 오는 요청을 감시하며, 유저 인증 처리
      - AuthenticationManager를 통한 인증 실행
      - 인증 성공 시 얻은 Authentication 객체를 SecurityContext 저장 후 AuthenticationSuccessHandler 실행
      - 인증 실패 시 AuthenticationFailurehandler 실행
    4. DefaultLoginPageGeneratingFilter
      - 인증을 위한 로그인 폼 URL을 감시
    5. BasicAuthenticationFilter
      - HTTP 기본 인증 헤더를 감시하여 처리
    6. RequestCacheAwareFilter
      - 로그인 성공 후 원래 요청 정보를 재구성
    7. SecurityContextHolderAwareRequestFilter
      - HttpServletRequestWrapper를 상속한 SecurityContextHolderAwareRequestWrapper 클래스로 HttpServletRequest 정보를 감싼다.
      - SecurityContextHolderAwareRequestWrapper 클래스는 필터 체인 상의 다음  필터들에게 부가 정보 제공
    8. AnonymousAuthenticationFilter
      - 이 필터 호출 시 까지 사용자 정보가 인증되지 않을 시 인증 토큰에 사용자가 익명 사용자로 나타난다.
    9. SessionManagerFilter
      - 인증된 사용자와 관련된 모든 세션을 추적한다.
    10. ExceptionTranslationFilter
      - 보호된 요청을 처리하는 중 발생할 수 있는 예외를 위임하거나 전달하는 역할
    11. FilterSecurityInterceptor
      - AccessDecisionManager로 권한 부여 처리를 위임함으로써 접근 제어 결정을 쉽게 해준다.
      
#### Authentication
- 모든 접근 주체(유저)는 Authentication(인터페이스)을 생성, 그리고 SecurityContext에 보관되고 사용된다.
- Security의 세션들은 내부 메모리 (SecurityContextHolder)에 쌓고 꺼내 쓴다!
 
#### AuthenticationManager
- 유저의 요청 내에 담긴 Authentication을 AuthenticationManager로 넘겨주고, 이를 구현한 ProviderManager가 처리한다.
- ProviderManager는 여러 AuthenticationProvider를 가질 수 있는데, 처리를 다 하면 Authentication을 반환, 실패 시 예외
    - AuthenticationManager: 인증 요청을 받고 Authentication를 채운다.
    - AuthenticationProvider: 실제 인증이 일어나며, 성공 시 Authentication.isAuthenticated = true

#### 설정하기
- Maven 인듯
    1. 의존성 추가
      1. groupId: org.springframework.security
      2. artifactId
         1. spring-security-core
         2. spring-security-web
         3. spring-security-config
         4. spring-security-taglibs
         5. spring-security-test
         
    2. web.xml에 springSecurityFilterChain 등록, context 등록
       1. contextConfigLocation에 스프링 설정파일.xml 추가
       2. HttpSessionEventPublisher
           - 한 유저가 다른 브라우저로 동시 로그인 하는 것을 막는다.
       3. springSecurityFilterChain (DelegatingFilterProxy)
           - 모든 요청은 이 프록시 필터를 거친다.
           - 스프링 시큐리티는 이를 통해 인증, 인가 수행
    3. security 설정
       1. security:http (springSecurityFilterChain 설정)
           - auto-config
              - true 시 filter는 default 값으로 동작
              - false 시 anonymous, x509, http-basic, session-management, expression-handler, custom-filter, port-mappings, request-cache, remember-me를 정의해줘야 한다.
          - use-expressions
              - 스프링 표현실 (spEL) 사용 여부
          - csrf
              - csrf 보안 설정 여부
          - intercept-url
              - pattern에 정의된 경로에 대해 access 권한 지정 (Filter가 감시)
          - form-login
              - login-page: login form 페이지 URL
              - username-parameter: form id의 name 속성 값
              - password-parameter: form pw의 name 속성 값
              - login-processing-url: form action 값 (security를 이용해 인증처리)
              - default-target-url: 로그인 성공 시 이동 URL
              - authentication-failure-url: 로그인 실패 시 이동 rul
              - always-use-default-target
                - true 시 무조건 default-target-url로 이동
                - false 시 authentication-success-handler 대로 redirect
              - authentication-success-handler-ref
                - 로그인 성공 시 프로세스 정의, bean, id 입력
                - 만약 최종 로그인 일시를 DB 기록해야 할 시 handler 처리 하는게 좋다.
                - res.sendRedirect 등으로 처리  
          - logout
              - logout-url: 로그아웃 시 처리할 url
              - logout-success-url: 로그아웃 성공 시 이동 URL
              - success-handler-ref: 로그아웃 성공 시 프로세스 정의 bean, id 입력
              - invalidate-session: 로그아웃 시 세션 삭제 여부
          - session-managerment
              - invalid-session-url: invalid session(세션 타임아웃 등) 일 때 이동 URL
              - max-sessions: 최대 허용 가능 세션 수
              - expired-rl: 중복 로그인 시 이동 URL
              - error-if-maximum-exceeded: max-sessions을 넘었을 때 접속한 세션을 실패 처리할 지 여부
       2. AuthenticationSuccessHandler, AuthenticationFailureHanlder, LogoutSuccessHandler
           - 로그인 성공 시, 로그인 실패 시, 로그아웃 성공 시 동작할 수 있는 hanlder
           - 모두 인터페이스이며 내부 코드는 authentication에 있다. 직접 구현해도 되지만 상속받는 걸 추천
       3. PasswordEncoder
           - 암호화 알고리즘을 골라 bean으로 만들 수 있다.   
           - AuthenticationProvider의 구현체로 DaoAuthenticationProvider 사용 시 기본적으로 PlaintextPasswordEncoder를 사용
              - NoOpPasswordEncoder: decreate 되었다. 테스트할 때만 사용 
              - BCrptPasswordEncoder: bcrpt 해쉬 알고리즘을 이용 (추천)
              - StandardPasswordEncoder: sha 해쉬 알고리즘 이용
       4. Access
           - HasRole(Role): 해당 Role을 갖고 있는 사용자 허용
           - hasAnyRole(Role1, Role2, ...): 해당 Role중 1개 이상 갖고 있는 사용자 허용
           - anonymous: 익명 사용자 허용
           - authenticated: 인증된 사용자 허용
           - permitAll: 모두 허용
           - denyAll: 모두 거부  

### 스프링 부트 - 스프링 시큐리티 적용
- 의존성 추가
    - spring security 의존성 뿐만 아니라, Thymeleaf에서 Spring Security 통합 모듈을 사용하기 위한 의존성도 필요
    - 'org.springframework.boot:spring-boot-starter-security'
    - 'org.thymeleaf.extras:thymeleaf-extras-springsecurity5'
- Spring Security 설정
    - Spring Security는 FilterChainProxy를 통해 여러 내부 Filter들이 동작하고 있다.
      - Client -> Filter -> FilterChainProxy(Spring Security Filters) -> Filter -> Servlet
    - 따라서 구현 단계에서 별도의 로직을 작성하지 않아도 설정만으로 로그인 / 로그아웃 등의 처리가 가능
    - WebSecurityConfigurerAdapter라는 클래스를 상속받은 클래스에서 메서드를 오버라이딩하여 조정할 수 있다.


#### 참조
1. [https://sjh836.tistory.com/165](https://sjh836.tistory.com/165)
2. [https://victorydntmd.tistory.com/328](https://victorydntmd.tistory.com/328)
