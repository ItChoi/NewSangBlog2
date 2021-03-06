## SangBlog (포트폴리오 사이트)
### 프로젝트 적용 기술
1. Spring Boot
2. JPA (QueryDSL)
3. Gradle
4. Aws
  - Ec2
  - RDS
  - S3
5. Git
   - Travis CI (깃 허브 제공 무료 CI 서비스)
6. Thymeleaf

### SNS 로그인 적용 (Oauth2)
### Ec2 적용
### RDS 적용
### Travis CI 적용
1. http://travis-ci.org/
   - 깃 계정 로그인 후 (계정명 -> Settings 클릭)
   - 해당 Repository 상태바 활성화 
   - 저장소 클릭 후 저장소 빌드 히스토리 페이지 이동
   - 웹 사이트 설정은 위에가 끝, 상세 설정은 플젝 .yml 파일로 진행
     - build.gradle과 같은 위치에서 .travis.yml 생성 후 코드 추가
      
### S3 적용     
- 일종의 파일 서버  
  - 이미지 파일을 비롯한 정적인 파일 관리
  - 배포 파일들을 관리 
  - 등등 보통 이미지 업로드 구현 시 S3 이용 해서 구현
  
- Aws 서비스와 Travis 연동
  - 실제 배포는 AWS CodeDeploy라는 서비스 이용
    - S3 연동이 먼저 필요한 이유는 Jar 파일을 전달하기 위함
    - CodeDeploy는 저장 기능이 없다. 그래서 Travis CI가 빌드한 결과물을 받아 CodeDeploy가 가져갈 수 있는 공안을 S3로 사용
      - CodeDeploy로 빌드와 배포를 할 수 있다. CodeDeploy는 깃에 있는 코드를 가져 올 수 있기 떄문, 그러나 빌드와 배포는 분리 하는게 좋다. 빌드가 아닌 배포만 가져 올 때 곤란쓰
      
  - Travis CI와 S3 연동
    1. AWS Key 발급
       - AWS 서비스에 외부 서비스가 접근 불가, 따라서 접근 권한을 가진 Key 생성 후 사용
         - AWS에서는 인증과 관련 기능을 제공하는 서비스 IAM(Identity and Access Management)
       - IAM은 AWS에서 제공하는 서비스의 접근 방식과 권한 관리
       - IAM을 통해 Travis CI가 S3와 CodeDeploy에 접근 하도록 설정
         - AWS -> IAM 검색
         - 액세스 관리 -> 사용자 -> 사용자 추가 클릭
         - 사용자 이름 입력 후 프로그래밍 방식 액세스 체크 후 다음 클릭
         - 기존 정책 직접 연결 체크 후 정책 필터에 s3full과 codedeployf 체크 (실제 서비스 회사에서는 권한도 S3와 CodeDeploy 분리하여 관리하기도 한다.)
         - 태그는 Name 값 지정, 본인이 인지 가능한 이름 지정, 항목 확인 후 키 생성!
           - 키: 액세스 키 ID / 비밀 액세스 키
       - Travis CI에 키 등록
         - 해당 리파지토리에 설정 화면 이동 후 Environment Variables 항목에 환경 변수 추가
           - AWS_ACCESS_KEY : 액세스 키 ID
           - AWS_SECRET_KEY : 비밀 액세스 키
         - 액세스 키와 비밀 액세스 키 등록 후 .travis.yml에서 $AWS_ACCESS_KEY, $AWS_SECRET_KEY란 이름으로 사용 가능
       - 키를 사용하여 Jar를 관리할 S3 버킷 생성
         - S3
           - 일종의 파일 서버, 순수하게 파일들을 저장하고 접근 권한을 관리, 검색 등을 지원하는 파일 서버의 역할
           - 게시글 작성 시 나오는 첨부 파일 등록 구현 시 많이 이용
         - Travis CI에서 생성된 Build 파일 저장 하도록 구성!
           - S3에 저장된 Build 파일은 이후 CodeDeploy에서 배포할 파일로 가져가도록 구성할 예정
         - S3 검색 후 이동 후 버킷 만들기 클릭
           - 버킷명 작성 (배포할 Zip 파일이 모여있는 장소)
           - 다음 다음 다음, 모든 퍼블릭 액세스 차든 체크 (실제 서비스으이 jar 파일을 누구든 받을 수 있게 되는 것을 막는다.)
       - .travis.yml 추가
         - 코드 추가
         - 푸시 후 Travis 확인, S3에 Zip 파일이 올라 갔는 지 확인
       - CodeDeploy로 배포 완료하기 (Travis CI와 AWS S3, CodeDeploy 연동하기)
         - CodeDeploy 이용 하기 전 배포 대상인 Ec2가 CodeDeploy를 연동 받을 수 있게 IAM 역할 하나 생성!
           - IAM 검색 -> 액세스 관리 -> 역할 -> 역할 만들기
             - 사용자와 역할의 차이
               - 사용자: AWS 서비스 외에 사용할 수 있는 권한 (로컬 PC, IDC 서버 등)
               - 역할: AWS 서비스에서만 할당할 수 있는 권한 (EC2, CodeDeploy, SQS 등)
           - AWS 서비스 체크, EC2 체크 후 다음
           - 정책 필터로 Ec2RoleForA를 검색 후 CodeDeploy 체크
           - 태그명 만들기~ Name
           - 역할 이름 짓기 후 만들기
       - EC2 서비스에 등록!
         - EC2 인스턴스 목록 이동 후 인스턴스 우클릭 후 인스턴스 설정 -> IAM 역할 연결/바꾸기 선택 후 만들 역할 연동
         - 인스턴스 재부팅 -> 재부팅 해야 역할 정상 적용
         - 재부팅 완료 후 CodeDeploy 요청을 받을 수 있게 에이전트 하나 설치!
       - CodeDeploy 에이전트 설치
         - Ec2 접속 후 다음 명령어 입력
           - aws s3 cp s3://aws-codedeploy-ap-northeast-2/latest/install . --region ap-northeast-2
           - install 파일 생성이 되고, 실행 권한 추가하기 
             - chmod +x ./install
           - install 파일로 설치 진행
             - sudo ./install auto
               - ruby가 설치 되지 않았으면 설치
             - sudo service codedeploy-agent status로 running 메시지 출력되면 정상!
       - CodeDeploy를 위한 권한 생성
         - CodeDeploy에서 EC2에 접근하려면 권한이 필요
         - AWS 서비스이니 IAM 역할 생성
           - codedeploy 체크 후 정책 필터 awscodedeployrole 체크 후 생성
       - CodeDeploy 생성
         - CodeDeploy는 AWS의 배포 삼형제 중 하나
         - 배포 삼형제
           - Code Commit
             - 깃 허브와 같은 코드 저장소 역할 
           - Code Build
             - Travis CI와 마찬가지로 빌드용 서비스
             - 멀티 모듈을 배포해야 하는 경우 사용해볼만하다. 그런데 대부분 젠킨스/팀시티 등을 이용
           - CodeDeploy
             - 앞에 것들은 대체제가 있다. Aws 배포 서비스!
             - 오토 스케일링 그룹 배포, 블루 그린 배포, 롤링 배포, EC2 단독 배포 등 많은 기능 지원
         - CodeDeploy
           - AWS - CodeDeploy 검색 후 애플리케이션 생성
             - 애플리케이션 이름과 컴퓨팅 플랫폼 (EC2/온프레미스) 선택
           - 배포 -> 애플리케이션 -> 해당 애플리케이션 선택 후 배포 그룹 -> 배포 그룹 생성
             - 배포 그룹명, 서비스 역할 선택, 현재 위치 선택
             - 환경 구성에서 아마존 EC2 인스턴스 선택 후 키와 밸류 값 넣어준다.
             - 배포 설정에서 배포 구성은 CodeDeployDefault.AllAtOnce 선택 로드 밸런스에서 로드 밸런싱 활성화는 미선택
           - Travis CI, S3, CodeDeploy 연동
             - S3에서 넘겨줄 zip 파일을 저장할 디렉토리 생성
               - ec2 서버 접속 후 zip 폴더 생성 (깃 권한 영역 밖)
             - AWS CodeDeploy의 설정 appspec.yml로 진행 (숨김 파일 아님)
               - Travis 설정은 .travis.yml
               - appspec.yml 코드 추가, .travis.yml 코드 추가
       - 배포 자동화 구성
         - Travis CI, S3, CodeDeploy 연동 후 이를 기반으로 실제 Jar를 배포하여 실행해보기.
           - deploy.sh 파일 추가
             - script 디렉토리 생성 후 스크립트 생성!
       - 실제 배포 과정 체험
         - build.gradle에서 프로젝트 버전 변경!
       - CodeDeploy 로그 확인
         - CodeDeploy와 같이 AWS가 지원하는 서비스에서는 오류가 발생했을 때 로그 찾는 방법을 모르면 오류 해결하기 어렵다.
         - 따라서 배포 실패 시  어느 로그를 봐야 할 지 알아야 한다.
           - CodeDeploy 관한 대부분 내용은 -> /opt/codedeploy-agent/deployment-root 에 있다.
           - tail -F /var/log/aws/codedeploy-agent/codedeploy-agent.log
           
### 무중단 배포
- 배포 시 애플리케이션이 종료된다. 새로운 Jar가 실행되기 전 까진 기존 Jar를 종료시키기 때문. 종료가 되면 안되는 서비스는 무중단 배포를 한다.
- 배포를 수동으로 할 시 문제가 많다.
  - 개발자가 모두 남아 사용자가 없는 시간대에 배포
  - 배포 시 에러가 생기면 새벽에 처리... 
  - 배포가 서비스를 정지해야만 가능 할 때는 롤백의 문제도 생김
- 무중단 배포 방법
  - AWS에서 블루 그린(Blue-Green) 무중단 배포
  - 도커를 이용한 웹 서비스 무중단 배포
  - L4 스위치를 이용한 무중단 배포 (L4는 비싸다.)
  - Nginx
- 무중단 배포 Nginx 방법 사용
  - 엔진엑스는 웹 서버, 리버스 프록시, 캐싱, 로드 밸런싱, 미디어 스트리밍 등을 위한 소프트웨어
  - 이전에 Apache 자리를 뺏은 가장 유명한 웹 서버이자 오픈소스!
  - 엔진 엑스의 리버스 프록시란 기능을 통해 무중단 배포 환경 구축 (가장 저렴, 쉽다)
    - 리버스 프록시: 외부의 요청을 받아 백엔드 서버로 요청을 전달하는 행위
    - 실제 요청에 대한 처리는 웹앱 서버들이 처리
  - 구조는 간단쓰 하나의 Ec2 혹은 리눅스 서버에 엔진엑스 1대와 스프링 부트 Jar를 2대 사용
    - 엔진 엑스는 80(http), 443(https) 포트 할당
    - 스프링 부트1은 8081 포트로 실행
    - 스프링 부트2은 8082 포트로 실행
    - 사용자의 요청에 엔진엑스가 81 포트로 요청 전달, 신규 배포 필요시 82 포트로 요청 전달
- 엔진엑스 설치와 스프링 부트 연동
  1. Ec2에 엔진엑스 설치
     - sudo yum install nginx
     - sudo service nginx status
- 무중단 배포 스크립트 만들기
  - profile rest api 만들기.
  - real1, real2 yml 파일 만들기.
  - nginx 설정 (프록시 설정이 교체될 수 있도록 설정)
    - /etc/nginx/conf.d/ service-url.inc 파일 생성
  - 배포 스크립트들 작성
    - stop.sh : 기존 엔진엑스에 연결되어 있진 않지만, 실행 중이던 스프링 부트 종료
    - start.sh : 배포할 신규 버전 스프링 부트 프로젝트를 stop.sh로 종료한 'profile'로 실행
    - health.sh : 'start.sh'로 실행시킨 프로젝트가 정상적으로 실행됐는지 체크
    - switch.sh : 엔진엑스가 바라보는 스프링 부트를 최신 버전으로 변경
    - profile.sh : 앞선 4개 스크립트 파일에서 공용으로 사용할 'profile'과 포트 체크 로직
    - appspec.yml에 설정 
    
    
  
  
            
         
       
         
      
         
         
### Travis CI 적용 (향후 젠킨스 대체)
- test
