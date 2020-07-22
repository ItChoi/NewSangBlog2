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
  
#### cut
- 컬럼 잘라내기
- 자주 사용되는 옵션
  - -b, --bytes=LIST : byte 선택
  - -c, --characters=LIST : character 선택
  - -f, --fields=LIST : 필드(컬럼) 선택
  - -d, --delimiter=DELIM : tab 대신 사용할 구분자 지정
  - --complement : 선택 반전
  - --output-delimiter=STRING : 출력 시 사용하 구분자 지정
- 사용 예제
  - head /etc/passwd | cut -d ':' -f 1,7
  - head /etc/passwd | cut -d ':' -f 1,7 --output-delimiter=': '
  - ls -al | cut -b -10
  - ls -al | cut -b -10 --complement


#### tr
- 어떤 내용을 변환(translate)한다.
- 기본 사용법: tr [OPTION]... SET1 [SET2]
- 자주 사용되는 옵션
  - -c, -C, --complement
  - -d, --delete
  - SET
    - CHAR1-CHAR2 : CHAR1부터 CHAR2까지 (예: 'a-z')
    - [:alnum:] : 문자 + 숫자
    - [:alpha:] : 문자
    - [:blank:] : 공백
    - [:space:] : 공백 + newline
    - [:digit:] / [:xdigit:] : 10진수 숫자 / 16진수 숫자
    - [:lower:] / [:upper:] : 소문자 / 대문자


#### sed
- stream editor: 파일의 내용을 출력하는 대 있어서 그 내용을 에디팅 할 수 있다고 생각....
- 자주 사용되는 옵션
  - {RANGE}p : range 내의 라인을 출력
  - {RANGE}d : range 내의 라인을 삭제
  - /SEARCHPATTERN/p : SEARCHPATTERN과 매치되는 라인을 출력
  - /SEARCHPATTERN/d : SEARCHPATTERN과 매치되는 라인을 삭제
  - s/REGEX/REPLACE/ : REGEX에 매치되는 부분을 REPLACE로 교체(substitute)
- 사용 예제
  - head /etc/passwd | sed -n '1,3p'
  - head /etc/passwd | sed -1,3d'
  - head /etc/passwd | sed -n '/nologin/p'
  - head /etc/passwd | sed 's/daemon/DAEMON/'
  - head /etc/passwd | sed 's/daemon/DAEMON/g'
  - head /etc/passwd | sed '3,5 s/:/^/g'
  - head /etc/passwd | sed -n '/games/,+2p'
  

#### awk
- 텍스트 처리 script language
- syntax: awk options 'selection _criteria {action }' input-file
- 자주 사용되는 옵션
  - -F : field seperator 지정
- 주요 내장 변수
  - $1, $2, $3, ... : Nth field
  - NR : number of records
  - NF : number of fields
  - FS : field separator(default 'white space')
  - RS : record separator (default 'new line')
  - OFS : Output field separator
  - ORS : Output record separator



#### find
- 조건에 맞는 파일을 찾아 명령을 수행한다.
- 기본 사용 방법: find [OPTIONS] path EXPR
- 자주 사용되는 옵션
  - 조건
    - -name : 이름으로 검색
    - -regex : regex에 매치로 검색
    - -empty : 빈 디렉토리 혹은 빈 검색
    - -size : 사이즈로 검색 (M,G로 표기 가능)
      - -N : 이하
      - +N : 이상
    - -type: 파일 타입으로 검색
      - d : directory
      - p : named pipe
      - f : regular file
      - l : softlink
      - s : socket
    - -perm : 퍼미션으로 검색 (읽기 4, 쓰기 2, 실행 1)
      - mode : 정확히 일치하는 파일
      - +mode : 모든 flag가 포함된 파일
      - /mode : 어떤 flag라도 포함된 파일
      
  - 액션
    - -delete : 파일 삭제
    - -ls : ls -dils 명령 수행
    - -print : 파일 이름 출력
    - -printf : 파일 이름을 포맷에 맞게 출력
    - -exec : 주어진 명령 수행 (유용)
    - -execdir : 해당 디렉터리로 이동하여 명령 수행 (유용)
    - -ok : 사용자에게 확인 후 exec (유용)
    - -okdir : 사용자에게 확인 후 실행 execdir (유용)
    
    
#### grep
- 파일 내용 중 원하는 내용을 찾는다.
- grep [OPTIONS] PATTERN [FILE...]
- 자주 사용되는 옵션
  - -r : recursive : 전체 디렉토리를 다 뒤진다.
  - -i : ignore case 
  - -v : invert match : 패턴이 매치가 안 되는 것을 찾는다.
  - -q : quiet mode
- 사용 예제
  - grep fork *.c
  - grep fork *.c -q
  - grep "\<for\>" *.c
  - grep "^static.*(void)" *.c
  - ls -al | grep posix
  - find . | grep posix
  
  
#### apropos
- man page 이름과 설명을 검색한다.
- 자주 사용되는 옵션
  - -s, --sections=LIST, --section=LIST : 탐색할 섹션을 colon으로 구분하여 입력
- 사용 예제
  - apropos pthread
  - apropos pthread -s 7
  - apropos '^sem_'
  - apropos '.*'
  - apropos '.*' -s 5:6:7
  
#### locate
- 파일의 위치를 찾아 보여준다.
- 단, updatedb가 저장해놓은 DB 파일 내에서 검색하므로 누락 파일이 생길 수 있음
- locate [OPTION]... PATTERN...
- 자주 사용되는 옵션
  - -i, --ignore-case : 대소문자 구분없이 검색
  - -l, --limit, -n LIMIT : 출력 결과를 LIMIT 만큼만 출력
  - --regex : PATTERN을 regex로 해석
- 사용 예제
  - locate main.c
  - locate main.c -n 10
  - locate --regex "/usr/src/.*\<main.c$"
  
  
#### which
- 실행 파일의 위치를 보여준다.
- 자주 사용 되는 옵션
  - 없음
- 사용 예제
  - which ls
  - which chmod
  - which ls strace chmod
    