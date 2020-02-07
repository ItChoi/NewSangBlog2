### 프로젝트 진행 중 에러 발생
#### 표기법
1. 발생 상황
2. 발생 에러
3. 시도한 방법
4. 발생 원인
5. 해결

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
    

#### build.gradle 에러 발생
1. 발생 상황
2. 발생 에러
3. 시도한 방법
4. 발생 원인
5. 해결
    
    