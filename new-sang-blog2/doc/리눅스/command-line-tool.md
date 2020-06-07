### 리눅스 커맨드 라인 툴
#### 강좌 소개
- 클라우드 환경에서도 리눅스 비율이 9:1로 높다.


#### 강좌 로드맵
- 텍스트 처리
  - head, tail, wc, nl, sort, uniq, cut, tr, sed, awk
- 검색
  - find, grep, apropos, locate, which
- 시스템 정보
  - ps, top, lsof, netstat, sysctl
- 텍스트 편집
  - vim
- 개발 도구
  - diff, patch, cscope, ctags, strace, gdb, valgrind

  
#### head 명령어
- 문서 내용의 앞 부분을 출력
- cat은 문서의 처음부터 끝까지 다 보여준다. head는 문서의 시작부터 기본적으로는 10줄
- 자주 사용되는 옵션
  - -c, --bytes[-]NUM : NUM byte만 출력
  - -n, --liines=[-]NUM : NUM line만 출력
    - 마이너스 옵션은 끝에서부터 숫자만큼 뺀 문서를 출력

    
#### tail
- 문서 내용의 뒷 부분 출력 (끝에서부터 10줄 (옵션 없는 경우))
- 자주 사용되는 옵션
  - -c, --bytes[+]NUM : NUM byte만 출력
  - -n, --lines[+]NUM : NUM line만 출력 (뒤에 있는 몇 줄)
  - -f, --follow[={name|descr}] : 추가되는 내용 대기. 추가되는 내용은 append하여 출력
    - 파일을 삭제 후 다시 똑같은 이름으로 만들면, follow 하지 않는다. 
    - tail aa -f
  - -F : 파일이 truncate 되는 경우 re-open하여 follow함 (logrotate 되는 파일에 유용)
    - 파일을 삭제하면 파일이 삭제되었다고 알람이 뜬다.
    - 다시 똑같은 이름으로 파일을 만들 경우 다시 팔로우 한다고 알림이 뜨고 작동 (감지를 한다: truncate)
    - 로그 파일의 경우 모니터링으로 많이 사용한다.
  - NUM : byte 입력 시 K,M,G,T 입력 가능(예: 10M), '+' 입력 시 문서 시작의 NUM byte/line 지점에서 출력 시작
- 사용 예제
  - tail /etc/passwd -n 1
  - tail /etc/passwd -n +5
  - cat /etc/passwd | tail -n 15
  - cat /etc/passwd | tail -n +5
  
  
#### wc
- 하나의 파일에 설정을 할 때 한 줄이 하나의 설정일 수 있다. 이 때 라인 수로 설정을 할 수 있어서 유용하게 사용 가능하다.
- word count : 문서에 워드가 몇 개인지, 라인이 몇 개인지, 캐릭터가 몇개인지 세어서 화면에 출력 
- line/word/byte count 출력
- 자주 사용되는 옵션
  - -l : 라인수만 출력
- 사용 예제
  - wc FILENAME
  - wc -l FILENAME
  - cat FILENAME | wc - l : stdin으로부터 라인수만 획득
  - wc -l FILENAME | cut -d ' ' -f 1 : 라인수만 획득
  - wc -l FILENAME | awk '{ print $1 }' : 라인수만 획득
  - wc *.c : 여러 파일 입력 시 합계 출력
  
  
#### nl
- 파일 내용을 라인 넘버와 함께 출력 (cat 조회 + 라인 넘버)
- 공백을 제외한 라인! 
  - -ba: 모든 라인에 대해 넘버링
- 자주 사용되는 옵션
  - -ba : 모든 라인에 대해 라인 넘버링
  - -v N : 시작 라인 넘버를 N으로 지정
  - -s : 라인 넘버 출력 후 출력할 separator 지정
- 사용 예제
  - cat FILENAME
  - nl FILENAME
  - nl -ba FILENAME 
  - nl -ba -s ": " FILENAME
  - nl -ba -s ": " FILENAME | tail
    
  
#### sort
- 정렬하여 출력
- 자주 사용되는 옵션
  - 위치 지정
    - -k, --key=KEYDEF : key에 의한 정렬 수행 (몇 번째 컬럼을 기준으로 1부터 시작)
    - -t, --field-seperator : 필드 구분자 (기본 값은 공백 문자) (delimeter)
- 정렬 기준
  - -f, --ignore-case
  - -g, --general-numeric-sort
  - -n, --numeric-sort
  - -r, --reverse
  - -u, --unique
  - --debug : 어떤 기준으로 정렬 했는지 보여준다.


#### uniq
- 중복된 내용 제거 후 출력
- 자주 사용되는 옵션
  - -d, --repeated : 중복된 내용만 출력
  - -u, --unique : 중복되지 않은 내용만 출력
  - -i, --ignore-case : 대소문자 무시
- 사용 예제
  - nl uniq_sample
  - uniq uniq_sample | nl : 연속적으로 중복된 부분만 체크 (1, 3 라인이 같을 때 중복 제거가 안된다. 1,2라인이 같을때만 제거)
  - sort uniq_sample | uniq | nl
  - sort uniq_sample | uniq -i | nl
  - sort uniq_sample | uniq -d | nl
  - sort uniq_sample | uniq -u | nl
  - grep "shm_open" *.c | awk -F: '{ print $1 }' | uniq