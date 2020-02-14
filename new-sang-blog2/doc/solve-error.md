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
    
    