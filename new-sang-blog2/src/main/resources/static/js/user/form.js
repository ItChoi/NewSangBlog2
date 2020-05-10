let Form = {
    init : function() {

    },

    submitClick : function() {
        let isSucceed = validator();

        if (isSucceed) {
            let phoneNumber = document.getElementById('phoneNumber');
            phoneNumber.value = Utils.getPhoneNumber();
        }

        return isSucceed;
    },


};

    let loginId = document.getElementById('loginId');
    loginId.onkeyup = function() {
        axios({
            method: 'post',
            url: '/manager/user/duplicate-loginid-check',
            headers: {
                'X-Requested-With': 'XMLHttpRequest',
                'X-CSRF-TOKEN' : document.querySelector('input[name="_csrf"]').value
            },
            data: {
                'loginId' : loginId.value
            }

        }).then(function (response) {
            let duplicateText = document.getElementById('duplicate-text');

            if (response.data) {
                duplicateText.innerHTML = '이미 존재 하는 아이디 입니다..';
                return false;
            } else {
                duplicateText.innerHTML = '사용 가능한 아이디 입니다.';
                return true;
            }
        });
    };

function validator() {

    // 필수 입력 값
    if (!Validator.requiredValue()) {
        return false;
    }

    if (!Validator.passwordRegExp()) {
        return false;
    }

    if (!Validator.emailRegExp()) {
        return false;
    }

    if (!Validator.phoneNumberRegExp()) {
        return false;
    }

    return true;
}

Form.init();