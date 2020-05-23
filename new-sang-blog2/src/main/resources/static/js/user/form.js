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

    duplicateInfoCheck : function(that) {
        axios({
            method: 'get',
            url: '/manager/user/duplicate-info-check?' + that.name + "=" + that.value,
            headers: {
                'X-Requested-With': 'XMLHttpRequest',
                'X-CSRF-TOKEN' : document.querySelector('input[name="_csrf"]').value
            },
        }).then(function (response) {
            if (response.status === 200) {
                that.nextElementSibling.innerHTML = '사용 가능한 ' + that.name + ' 입니다.';
                return true;
            }
        }).catch(function (error) {
            that.nextElementSibling.innerHTML = '이미 존재 하는 ' + that.name + ' 입니다.';
            return false;
        });
    }

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