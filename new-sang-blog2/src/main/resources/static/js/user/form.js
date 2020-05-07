let Form = {
    init : function() {

    },

    submitClick : function() {
        let isSucceed = validator();

        /*axios({
            method: 'post',
            url: '/manager/user/check-duplication',
            headers: {
                'X-Requested-With': 'XMLHttpRequest',
                'X-CSRF-TOKEN' : document.querySelector('input[name="_csrf"]').value
            },
            data: {
                'email' : document.getElementById('email').value,
                'loginId' : document.getElementById('loginId').value
            }
        }).then(function (response) {
            alert("test: " + response.data);
            if (response.data) {
                alert("1111");
                return true;
            } else {
                alert("2222");
                return false;
            }

        }).catch(function (error) {
            alert(error.response.data.message);
            return false;
        });*/

        if (isSucceed) {
            let phoneNumber = document.getElementById('phoneNumber');
            phoneNumber.value = Utils.getPhoneNumber();
        }

        return isSucceed;
    },



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