let Form = {
    init : function() {

    },

    submitClick : function() {
        return validator();
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

    if (!Validator.phonNumberRegExp()) {
        return false;
    }

}

Form.init();