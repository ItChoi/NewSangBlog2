let Form = {
    init : function() {

    },

    submitClick : function() {
        let isSuccessed = validator();


        if (isSuccessed) {
            let phoneNumber = document.getElementById('phoneNumber');
            phoneNumber.value = Utils.getPhoneNumber();
        }

        return isSuccessed;
    },

    axiosFunction : function() {
        axios({
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
            if (response.data) {
                return true;
            } else {
                return false;
            }

        }).catch(function (error) {
            alert(error.response.data.message);
            return false;
        });
    }


};

function validator() {

    if (!Form.axiosFunction()) {
        return false;
    }

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