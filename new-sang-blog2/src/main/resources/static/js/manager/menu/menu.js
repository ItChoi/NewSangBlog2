let Menu = {
    init : function() {
        // 메뉴 클릭 전에 right-menu는 display
        document.getElementById('edit-menu-right').style.display = 'none';
        
        // 슈퍼 관리자만 메뉴의 특정 속성들 변경 가능
        let userAuth = document.getElementById('sec-roles').innerText.indexOf("ROLE_SUPERVISOR");
        if (userAuth === 1) {
            let readonlyMenuByAuth = document.getElementsByClassName('supervisor-menu');
            for (let menuTag of readonlyMenuByAuth) {
                menuTag.readOnly = true;
                menuTag.disabled = true;
            }
        }
    },

    menuDetail : function(menuId) {
        axios({
            method: 'get',
            url: '/manager/menu/' + menuId,
        }).then(function(response) {
            document.getElementById('edit-menu-right').style.display = 'block';
            document.getElementById('menu-id').value = response.data.id;
            document.getElementById('menu-code').value = isNotEmpty(response.data.menuCode) ? response.data.menuCode : '';
            document.getElementById('menu-name').value = isNotEmpty(response.data.menuName) ? response.data.menuName : '';
            document.getElementById('menu-type').value = isNotEmpty(response.data.menuType) ? response.data.menuType : '';
            document.getElementById('menu-url').value = isNotEmpty(response.data.url) ? response.data.url : '';
            document.getElementById('menu-uri').value = isNotEmpty(response.data.uri) ? response.data.uri : '';
            document.getElementById('menu-display').value = isNotEmpty(response.data.menuDisplay) ? response.data.menuDisplay : '';
        }).catch(function(error) {
            alert(error);
        });
    }

};

function isNotEmpty(data) {
    let notEmptyCheck = true;
    if (data === "" || data === null || data === undefined) {
        notEmptyCheck = false;
    }

    return notEmptyCheck;
}

Menu.init();