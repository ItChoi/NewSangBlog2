let Menu = {
    init : function() {

    },

    menuDetail : function(menuId) {
        axios({
            method: 'get',
            url: '/manager/menu/' + menuId,
        }).then(function(response) {

            document.getElementsByClassName('menu-code')[0].value = isNotEmpty(response.data.menuCode) ? response.data.menuCode : '';
            document.getElementsByClassName('menu-name')[0].value = isNotEmpty(response.data.menuName) ? response.data.menuName : '';
            document.getElementsByClassName('menu-type')[0].value = isNotEmpty(response.data.menuType) ? response.data.menuType : '';
            document.getElementsByClassName('menu-url')[0].value = isNotEmpty(response.data.menuUrl) ? response.data.menuUrl : '';
            document.getElementsByClassName('menu-uri')[0].value = isNotEmpty(response.data.menuUri) ? response.data.menuUri : '';
            document.getElementsByClassName('menu-display')[0].value = isNotEmpty(response.data.menuDisplay) ? response.data.menuDisplay : '';
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