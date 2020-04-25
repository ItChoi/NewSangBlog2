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

    if (!Validator.phonNumberRegExp()) {
        return false;
    }

    return true;
}

Form.init();