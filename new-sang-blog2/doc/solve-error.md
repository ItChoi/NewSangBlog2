### 프로젝트 진행 중 에러 발생
#### 표기법
1. 발생 상황
2. 발생 에러
3. 발생 원인 추측
4. 시도한 방법
5. 발생 원인
6. 해결

#### 1. source tree / git 연동 에러
1. 발생 상황
    - 해당 프로젝트 폴더 위치에서 폴더를 깃의 관리 대상으로 설정 (git init)
    - source tree에 해당 프로젝트 add
    - 해당 프로젝트의 git repository를 만든 후  source tree에 git repository 추가
    - git ignore 파일 리스트 등록 후 commit & push
    <br/>
     
2. 발생 에러
    - commit은 되었으나 push에서 에러가 발생했다.
      - source tree
        - git -c diff.mnemonicprefix=false -c core.quotepath=false --no-optional-locks commit -q -F C:\Users\itcho\AppData\Local\Temp\psuw33m2.zgg
        - git -c diff.mnemonicprefix=false -c core.quotepath=false --no-optional-locks push -v --tags --set-upstream origin master:master
        - Pushing to https://github.com/ItChoi/NewSangBlog2.git
        - To https://github.com/ItChoi/NewSangBlog2.git
        - ! [rejected]        master -> master (non-fast-forward)
        - error: failed to push some refs to 'https://github.com/ItChoi/NewSangBlog2.git'

        - hint: Updates were rejected because the tip of your current branch is behind
        - hint: its remote counterpart. Integrate the remote changes (e.g.
        - hint: 'git pull ...') before pushing again.
        - hint: See the 'Note about fast-forwards' in 'git push --help' for details.
        - 오류가 나면서 완료됨.
    <br/>
      - git 
        - $ git push -u origin master
        - To https://github.com/ItChoi/NewSangBlog2.git
        - ! [rejected]        master -> master (non-fast-forward)
        - error: failed to push some refs to 'https://github.com/ItChoi/NewSangBlog2.git'
        - hint: Updates were rejected because the tip of your current branch is behind
        - hint: its remote counterpart. Integrate the remote changes (e.g.
        - hint: 'git pull ...') before pushing again.
        - hint: See the 'Note about fast-forwards' in 'git push --help' for details.
    <br/>

3. 시도한 방법
    - git pull origin master --allow-unrelated-histories

4. 발생 원인
    - 로컬에서 git init하여 master 브랜치가 있었고, 깃 저장소를 만들어 master 브랜치가 있었는데, 이 두개의 병합이 일어나지 않아 push가 거부 되었던 것 같음.

5. 해결
    - git pull origin master --allow-unrelated-histories
    

#### 2. 부트스트랩 적용이 안된다. 부트 스트랩 파일 404
1. 발생 상황
    - 부트스트랩 파일을 static 폴더에 옮겨놓고, index.html에서 필요한 부트스트랩 파일들을 가져와 사용하고 있는데, 경로 Ctrl + 좌클릭 하면 해당 파일까지 들어가지는데, 로컬 서버를 실행하고 해당 url에 들어가면 파일들이 적용되지 않는다. 404
2. 발생 에러
    - 404, 서버는 요청받은 리소스를 찾을 수 없다. 
3. 발생 원인 추측
    - 정적 파일(css, js, ...)들이 브라우저에 캐싱 되어 적절한 경로 설정 전 경로를 가져오나?
    - 일단은 경로 문제인 거 같다.
    - 경로를 상대 경로(현재 경로를 통해 적용이 필요한 폴더로 이동 -> Ctrl + 좌클릭 시 해당 파일로 이동은 되는데  404다... 상대 경로의 문제??
    
4. 시도한 방법
    - ../../static/bootstrap ....
    - /static/bootstrap ....
    - /resources/static/bootstrap ...
    - /bootstrap/...
5. 발생 원인
    - 스프링 부트는 기본적으로 src/main/resources/static은 URL에서 /로 지정된다. 따라서 /bootstrap 으로 경로를 시작하니 적용이 됐다 ㅠㅠ
6. 해결
    - 경로 제대로 지정!
    
#### 3. 스프링 시큐리티 적용하면서 발생한 에러
1. 발생 상황
    - 스프링 설정 클래스 추가 
    - @Configuration 코드 추가
    - @EnableWebSecurity 코드 추가
    - configure(WebSecurity web) 오버라이드
    - configure(HttpSecurity http) 오버라라이드
    - 서버 실행 시 에러 발생
2. 발생 에러
    - org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'springSecurityFilterChain' defined in class path resource [org/springframework/security/config/annotation/web/configuration/WebSecurityConfiguration.class]: Bean instantiation via factory method failed; nested exception is org.springframework.beans.BeanInstantiationException: Failed to instantiate [javax.servlet.Filter]: Factory method 'springSecurityFilterChain' threw exception; nested exception is java.lang.NullPointerException
3. 발생 원인 추측
    - 접근 경로에 필요한 접근 권한을 제대로 주지 못한 거 같음. HttpSecurty http를 파라미터로 받는 오버라이드에 경로를 재설정하는 것이 필요할 거 같다.
4. 시도한 방법
    - 내 경로에 적합하게 경로 설정
5. 발생 원인
    - WebSecurityConfigurerAdapter를 상속받아 스프링 시큐리티 설정을 할 때 setAuthenticationConfiguration(AuthenticationConfiguration
	 * authenticationConfiguration)도 오버라이드 해놓고 아무 코드도 안 써놓고 있었다 흙흙....
6. 해결
    - 주석처리 해놓으니 정상 작동.........................................
    
#### 4. application.yml 등 설정 파일에 민감한 정보를 암호화하기 위해 오픈 소스인 jasypt 사용하면서 막힘.
1. 발생 상황
    - build.gradle에 jasypt 의존성을 추가 했는데, @EnableEncryptableProperties 애노테이션이 안 뜬다.
2. 발생 에러
    - X
3. 발생 원인 추측
    - 의존성이 제대로 추가되지 않았나?
4. 시도한 방법
    - jasypt 의존성 제대로 추가 되었는 지 확인 필요 
    - 최신 버전을 의존성으로 추가
       - [https://javalibs.com/artifact/com.github.ulisesbocchio/jasypt-spring-boot-starter](https://javalibs.com/artifact/com.github.ulisesbocchio/jasypt-spring-boot-starter)
5. 발생 원인
    - 그래들에 추가한 jasypt 버전이 현재 사용하고 있는 스프링이나 그래들 버전에 호환이 되지 않았던 거 같다. 최신 버전으로 하니 추가됌
6. 해결
    - jasypt 의존성 최신 버전으로 추가
    
#### 5. 스프링 부트 mysql 연동 에러
1. 발생 상황
    - build.gradle에 mysql 의존성 추가
    - .yml에 mysql 관련 프로퍼티 추가
2. 발생 에러
    - java.sql.SQLException: Access denied for user 'root'@'localhost' (using password: YES)
    - org.springframework.jdbc.support.MetaDataAccessException: Could not get Connection for extracting meta-data; nested exception is org.springframework.jdbc.CannotGetJdbcConnectionException: Failed to obtain JDBC Connection; nested exception is java.sql.SQLException: Access denied for user 'root'@'localhost' (using password: YES)
    - Caused by: org.springframework.jdbc.CannotGetJdbcConnectionException: Failed to obtain JDBC Connection; nested exception is java.sql.SQLException: Access denied for user 'root'@'localhost' (using password: YES)
    - org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'entityManagerFactory' defined in class path resource [org/springframework/boot/autoconfigure/orm/jpa/HibernateJpaConfiguration.class]: Invocation of init method failed; nested exception is javax.persistence.PersistenceException: [PersistenceUnit: default] Unable to build Hibernate SessionFactory; nested exception is org.hibernate.exception.GenericJDBCException: Unable to open JDBC Connection for DDL execution
    - Caused by: javax.persistence.PersistenceException: [PersistenceUnit: default] Unable to build Hibernate SessionFactory; nested exception is org.hibernate.exception.GenericJDBCException: Unable to open JDBC Connection for DDL execution
    - Caused by: org.hibernate.exception.GenericJDBCException: Unable to open JDBC Connection for DDL execution
3. 발생 원인 추측
    - 패스워드 불일치 (해시태그 미적용, 비밀번호 손상)
    - 원격 접속 권한 허용?
    - .yml에 설정한 DB 정보가 연결이 되지 않는다? -> 아마 이건듯....
      - db 정보들로 db 툴 접속 가능
    - 3306 port 중복 사용되고 있는지 여부?
4. 시도한 방법
    - mysql 계정 비밀번호 재 설정 -> 해결 X
    - mysql root 계정 모든 권한을 줘도 안된다.
    - javassist 의존성 추가 -> 새로운 에러 생김 -> 없앰	
    - 다른 코드를 구글링하여 적용해봄
5. 발생 원인
    - 알고 보니 패스워드가 진짜 틀렸었네..............ㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠ
6. 해결
    - 정확한 패스워드 입력! 

#### 6. 스프링 시큐리티 적용 - 로그인 클릭 시 csrf 값을 못 찾는지 설정한 post로그인 페이지 not found
1. 발생 상황
    - csrf 값을 hidden으로 주고 있는데, 타임리프 속성을 적용하면 로그인 페이지 접속 에러가 뜨고, 속성을 없애면 로그인 버튼 클릭 시 스프링 시큐리티에서 제공하는 로그인 post를 못 찾는다. 그리고 csrf 값도 제대로 들어오는 듯 한데... 흠
2. 발생 에러
    - org.thymeleaf.exceptions.TemplateProcessingException: Exception evaluating SpringEL expression: "_csrf.parameterName" (template: "/manager/user/login" - line 50, col 41)
    - Caused by: org.springframework.expression.spel.SpelEvaluationException: EL1007E: Property or field 'parameterName' cannot be found on null
3. 발생 원인 추측
    - 타임리프 문제인가? 타임 리프로 데이터 뿌려주는 건 제대로 뿌려지고 있다. 그런데 왜 th: 속성으로 csrf를 주면 에러 페이지가 나올까, 속성 잘못 넣었나
    - csrf 값도 제대로 나오는 거 같은데, 스프링 시큐리티 테스트하기 위한 프론트 페이지 진입을 잘못하고 있나?
    - login.html의 form의 action의 경로 문제인가?
    - 스프링 시큐리티 설정 파일(SecurityConfig)에서 설정 정보를 잘못 입력했나?? ...흠
    - .and().csrf().disable() ... 이거 하든 안하든 결과는 비슷 흐음
    - 405 에러가 뜨는데, 스프링 시큐리티가 제대로 적용이 안된듯.... 
4. 시도한 방법
5. 발생 원인
6. 해결

### JPA 다대일 매핑 지연로딩 에러
1. 발생 상황
    - UserRole(N) : Manager(1) -> 다대일
    - 외래키는 @ManyToOne에서 Many에 있어야 한다. (다)를 기준으로 조회
    - USER_ROLE 기준으로 MANAGER 참조 시 지연로딩 된다.
    - MANAGER 기준으로 USER_ROLE 참조 시 지연로딩 안된다.
2. 발생 에러
    - org.springframework.security.authentication.InternalAuthenticationServiceException: failed to lazily initialize a collection of role: com.blog.newsangblog2.manager.user.domain.Manager.userRoles, could not initialize proxy - no Session
3. 발생 원인 추측
    - Manager 조회 후 getUserRole했을 시 지연로딩이 안된다. 
    - Manager(1) 기준으로 양방향 매핑 조회 시 UserRole을 가져올 수 있는 방법이 필요할듯....
    - 다대일 관계를 다른 관계로 매핑해야 되나? -> Manager 한명은 여러 UserRole을 갖고, UserRole도 여러 Manager를 가질 수 있으나, 현업에서 다대다는 사용하지 않는다고 한다. 
        - 현재는 UserRole(N) - (1)Manager 인데, Manager(N) - (1)UserAuthority(1) - (N)UserRole로 바꿔야 하나? 아닌듯... 암튼 다른 매핑 관계로 만들어야 하는 걸 수도...
4. 시도한 방법
5. 발생 원인
    - 아마 JPA 원리에서 영속성 컨텍스트에서 저 연관관계에서 지연로딩을 제대로 가져오지 못하는 거 같다. 정확히는 모르겠지만.... 아무튼 @OneToMany은 기본 값이 지연 로딩으로 되어 있어서, 이것을 즉시 로딩으로 가져오도록 설장하니까 가져오긴 하는데, 원리를 더 알아야 할 필요가 있다.
6. 해결
    - Manager.java
        - @OneToMany(mappedBy = "manager", fetch = FetchType.EAGER)
	private List<UserRole> userRoles = new ArrayList<>();
	- UserRole.java
	    - @ManyToOne
	@JoinColumn(name = "MANAGER_ID")
	private Manager manager;