## IO Redirection
- Input
- Output


### UNIX Programs
- Unix Process
  - Standard Output (stdout) (output)
  - Standard Error (stderr) (output)
  - Exit status (return, exit)
  - Standard Input (stdin) (input - 실용적이지 않다.)
  - Command-line Arguments (argc, argv)
  - Environment Variables (getenv)

### Output
- ls -al > result.txt
  - 출력되는 방향을 다른 곳으로 돌려서 파일에 저장! 이를 Redirection이라고 한다.

- ls -al
  - ls라는 프로그램의 입력으로 들어오는 입력 값 중 하나가 -al
    - cla(command line arguments)
  - 정보 출력 되는 것들 standard output
    - standard output: 프로그램 출력 결과가 모니터에 출력 된다.
      - 모니터에 출력되는 방향을 바꿔서 다른 곳에 출력되게 할 수 있다.
        - 대표적인 것이 파일! 
        - Redirection을 의미하는 > 
          - ls -al > result.txt
            - 화면에 출력되는 대신에 result.txt 파일로 출력이 되어 파일 안에 저장되어 있게 된다.
- (>)
  - 꺽세는 standard output을 기준으로 ! 원래는 1>
  - standard error를 파일로 출력하려면 2> 
  - 하이브리드 방식으로 하려면
    - rm test.txt 1> success.txt 2> fail.txt
      - 성공 시 standard output의 내용이 success.txt에 저장
      - 실패 시 standard error의 내용이 fail.txt에 저장 
  
  
### Input
- 하나의 프로그램은 여러 프로세스를 가질 수 있다.
- cat을 하고 파일 명을 주면 파일의 내용을 보여준다.
  - cat만 치면 키보드의 입력을 받지만, cat < hello.txt 라고 하면,
    - hello.txt 파일의 내용을 입력으로 받는다.
- cat hello.txt와 cat < hello.txt의 차이
  - cat hello.txt는 cat 프로그램의 인자로 전달 (Command-line Arguments)
  - cat < hello.txt는 인자가 아닌, Standard Input로 입력을 받는다.
- head
  - 텍스트의 앞 쪽 일부만을 출력하는 명령어


### append
- 악세사리 같은 기능, 수업 안들어도 괜찮~  
- 실행될 때 마다 내용을 덮어 쓰는 게 아니라, 추가!
  - 꺽세를 2개 쓰면 된다 >>
  - ls -al >> result.txt
  - /dev/null
    - 유닉스 계열에선 휴지통 같은 것! 화면, 파일에도 출력되지 않는다.
    - ls -al > /dev/null