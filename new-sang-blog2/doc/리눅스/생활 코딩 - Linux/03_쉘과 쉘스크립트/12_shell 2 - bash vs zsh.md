## shell - bash vs zsh
- echo
  - 뒤에 들어오는 문자를 화면에 출력
  - $0
    - 사용하고 있는 쉘 종류를 확인할 수 있다.
- zsh
  - 명령어로 zsh 쳤을 때 입력이 되지 않으면, 설치가 안되어 있는 것.
  - 설치 -> sudo apt-get install zsh
  - zsh 설치 후 zsh 명령어 입력 후 echo를 통해 $0 하는 경우 zsh가 뜬다.
  - zsh 후 입력하는 명령들은 zsh을 통해 커널로 전달, 커널에 만들어진 결과는 zsh를 통해 화면에 출력
- bash vs zsh
  - bash가 가지고 있지 않은 것들을 추가적으로 zsh는 가지고 있어서 더 편리할 수 있다.
  - bash의 경우 cd 후 tab 키 두 번을 누르면 숨겨진 디렉토리까지 표시 해준다.
  - zsh은 cd 후 tab 키를 한 번 누르면, 디렉토리가 나오는데, 숨김 디렉토리는 표현이 안된다.
  - bash -> cd /home/bean
  - zsh -> cd /h/b 후 탭 키 누르면 자동 완성
  - zsh -> /home/why -> why 디렉토리가 아닌 같은 경로의 what 디렉토리로 변경 하려면, cd why what 명령
  
  
  