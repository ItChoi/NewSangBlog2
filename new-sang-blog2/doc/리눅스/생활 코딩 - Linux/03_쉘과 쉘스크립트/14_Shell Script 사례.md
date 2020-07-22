## Shell script 사용
1. .log의 파일들을 bak 폴더에 저장
  - cp *.log bak
2. bak 폴더가 있으면 다시 .log 파일들을 bak에 저장, 없을 시 폴더를 만들어주는 shell script를 만들자.

- bash 프로그램은 어디 있을까?
  - ls /bin
    - 루트 디렉토리 밑에 bin에 기본적인 명령들, 유닉스 계열에 탑재되어 있는 기본 프로그램들이 위치하는 디렉토리
- nano를 통해 backup 파일을 만든다.
  - 첫 시작에 #!/bin/bash
    - 작성하고 있는 backup 프로그램을 실행했을 때, 운영 체제는 첫 번 째 줄에 있는 #!를 보고, 그뒤에 /bin/bash를 보고서 밑에 작성된 코드들이 /bin/bash를 통해서 해석되어야 한다는 것을 알 수 있다. (약속)
  - if [ -d bak ]; 
    - [] 사이에 띄어쓰기 필수!
    - 현재 디렉토리에 bak이라는 디렉토리가 존재하는가?
  - if ! [ -d bak]; then
    - 현재 디렉토리에 bak라는 디렉토리가 존재하지 않는다면! then 밑 줄에 실행할 코드를 작성
  - if ! [ -d bak]; then
  - mkdir bak
  - fi
  - cp *.log bak
  - backup이라는 쉘 스크립트를 실행하기 위해서는 ./backup
    - 허가 거부
    - backup 프로그램이, 누군가가 실행할 권한이 없기 떄문에 권한 에러가 뜬다.
    - backup이 실행 가능한 프로그램이라고 리눅스에게 알려줘야 한다.
      - chmod +x backup
- Shell script는 Shell에서 실행되는 스크립트! 쉘 명령어들이 실행되어야 할 순서, 방법을 각본으로 짜서 저장해둔 파일이라고 할 수 있다.

## TODO::: Shell Script는 따로 더 공부하기        
      