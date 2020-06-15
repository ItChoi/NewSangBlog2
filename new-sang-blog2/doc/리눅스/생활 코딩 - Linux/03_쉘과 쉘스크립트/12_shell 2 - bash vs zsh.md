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
  - bash가 가지고 있지 않은 것들을 zsh는 가지고 있어서 더 편리할 수 있다.
  
  