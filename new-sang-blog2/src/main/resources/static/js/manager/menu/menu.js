let Menu = {
    init : function() {
        // 메뉴 클릭 전에 right-menu는 display
        // document.getElementById('edit-menu-right').style.display = 'none';
        // 메뉴 순서 변경 모드 전용 버튼 (처음엔 display)
        // document.getElementById('menu-sort-mode-on').style.display = 'none';
        
        // 슈퍼 관리자만 메뉴의 특정 속성들 변경 가능
        let userAuth = document.getElementById('sec-roles').innerText.indexOf("ROLE_SUPERVISOR");
        if (userAuth !== 1) {
            document.getElementById('add-menu').disabled = true;
            let readonlyMenuByAuth = document.getElementsByClassName('supervisor-menu');
            for (let menuTag of readonlyMenuByAuth) {
                menuTag.readOnly = true;
                menuTag.disabled = true;
            }
        }

        // 등록 후 등록 메뉴의 아이디가 model로 넘어오는데, 있을 경우 edit 페이지 + 디테일 페이지 까지 보여주기.
        let existsMenuId = document.getElementById('existsMenuId').value;
        if (isNotEmpty(existsMenuId)) {
            Menu.menuDetail(existsMenuId);
        }

        // 메뉴 드래그 순서 변경 (jQuery)
        /*$("#sortable1").sortable();
        $("#sortable1").disableSelection();
        $("#sortable2").sortable();
        $("#sortable2").disableSelection();
        $("#sortable3").sortable();
        $("#sortable3").disableSelection();
        $("#sortable4").sortable();
        $("#sortable4").disableSelection();*/
        $(".sortable").sortable();
        $(".sortable").disableSelection();

    },

    menuDetail : function(menuId) {
        axios({
            method: 'get',
            url: '/manager/menu/' + menuId,
        }).then(function(response) {
            let data = response.data;
            document.getElementById('text-status').innerText = '수정';
            document.getElementById('edit-menu-right').style.display = 'block';
            setRightMenu(data);
        }).catch(function(error) {
            alert(error);
        });
    },

    controlRightMenu : function(type) {
        let rightMenu = document.getElementById('edit-menu-right');

        if (type === 'cancel') {
            clearRightMenu();
            rightMenu.style.display = 'none';
        } else {
            clearRightMenu();
            document.getElementById('text-status').innerText = '메뉴 추가';
            rightMenu.style.display = 'block';
        }
    },

    menuOrderingMode : function() {
        document.getElementById('menu-sort-mode-off').style.display = 'none';
        document.getElementById('menu-sort-mode-on').style.display = 'block';

        document.getElementsByClassName('menu-change-target').class
    }

    /*menuSave : function() {
        axios({
            method: 'post',
            url: '/manager/menu',
            params: {
                id : document.getElementById('menu-id').value,
                parentId : document.getElementById('parent-id').value,
                menuLevel : document.getElementById('menu-level').value,
                menuCode : document.getElementById('menu-code').value,
                menuName : document.getElementById('menu-name').value,
                ordering : document.getElementById('ordering').value,
                url : document.getElementById('menu-url').value,
                uri : document.getElementById('menu-uri').value,
                menuDisplay : document.getElementById('menu-display').value,
                menuType : document.getElementById('menu-type').value
            },
            headers: {
                'X-Requested-With': 'XMLHttpRequest',
                'X-CSRF-TOKEN' : document.querySelector('input[name="_csrf"]').value
            },
        }).then(function(response) {
            console.log(response);
            let html = '';

            html += '<li class="nav-item">';
            html += '   <a><i class="fas fa-fw"></i><span>' + response.data.menuName + '</span></a>';
            html += '</li>';

            let navItem = document.getElementsByClassName('nav-item');
            navItem.appendChild(html);


            alert("메뉴가 등록 완료.");
            Menu.menuDetail(response.data);
        }).catch(function(error) {
            alert(error)
        });

    }*/

};

function clearRightMenu() {
    document.getElementById('menu-id').value = '';
    document.getElementById('parent-id').value = '';
    document.getElementById('menu-level').value = '';
    document.getElementById('ordering').value = '';
    document.getElementById('menu-code').value = '';
    document.getElementById('menu-name').value = '';
    document.getElementById('menu-type').value = '';
    document.getElementById('menu-url').value = '';
    document.getElementById('menu-uri').value = '';
    document.getElementById('menu-display').value = '';
}

function setRightMenu(data) {
    document.getElementById('menu-id').value = data.id;
    document.getElementById('parent-id').value = data.parentId;
    document.getElementById('menu-level').value = data.menuLevel;
    document.getElementById('ordering').value = data.ordering;

    document.getElementById('menu-code').value = isNotEmpty(data.menuCode) ? data.menuCode : '';
    document.getElementById('menu-name').value = isNotEmpty(data.menuName) ? data.menuName : '';
    document.getElementById('menu-type').value = isNotEmpty(data.menuType) ? data.menuType : '';
    document.getElementById('menu-url').value = isNotEmpty(data.url) ? data.url : '';
    document.getElementById('menu-uri').value = isNotEmpty(data.uri) ? data.uri : '';
    document.getElementById('menu-display').value = isNotEmpty(data.menuDisplay) ? data.menuDisplay : '';
}

function isNotEmpty(data) {
    let notEmptyCheck = true;
    if (data === "" || data === null || data === undefined) {
        notEmptyCheck = false;
    }

    return notEmptyCheck;
}

Menu.init();