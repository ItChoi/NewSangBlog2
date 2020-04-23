let Validator = {
    // 필수 입력 값 체크
    requiredValue : function() {
        let requiredDatas = document.getElementsByClassName('required');

        // 필수 입력 값 체크
        for (let i = 0; i < requiredDatas.length; i++) {
            if (requiredDatas[i].value.length <= 0) {
                alert("필수 입력 값을 입력하지 않았습니다. [" + requiredDatas[i].title + "]");
                return false;
            }
        }
    },

    // 비밀번호 영문 + 숫자 + 특수문자 정규식
    passwordRegExp : function() {
        let passwordDatas = document.getElementsByClassName('password');

        let regex = /^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/;

        for (let i = 0; i < passwordDatas.length; i++) {
            if (!regex.test(passwordDatas[i].value)) {
                alert("패스워드는 문자 + 숫자 + 특수 문자를 포함한 8 ~ 15자리여야 합니다.");
                return false;
            }
        }
    },

    // 이메일 정규식
    emailRegExp : function() {
        let emailDatas = document.getElementsByClassName('email');
        let regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
        // 검증에 사용할 정규식 변수 regExp에 저장

        for (let i = 0; i < emailDatas.length; i++) {
            if (!regExp.test(emailDatas[i].value)) {
                alert("패스워드는 문자 + 숫자 + 특수 문자를 포함한 8 ~ 15자리여야 합니다.");
                return false;
            }
        }
    },

    // 핸드폰 번호 체크
    phonNumberRegExp : function() {
        let regExp1 = /^\d{3}$/;
        let regExp2 = /^\d{3, 4}$/;
        let regExp3 = /^\d{4}$/;

        let phoneNumber1 = document.getElementById('phoneNumber1');
        let phoneNumber2 = document.getElementById('phoneNumber1');
        let phoneNumber3 = document.getElementById('phoneNumber1');

        if (!regExp1.test(phoneNumber1) && phoneNumber1.value.length > 0) {
            alert("휴대폰 앞 자리를 정확히 입력해주세요.");
            return false;
        }
        if (!regExp2.test(phoneNumber2) && phoneNumber2.value.length > 0) {
            alert("휴대폰 가운데 자리를 정확히 입력해주세요.");
            return false;
        }
        if (!regExp3.test(phoneNumber3) && phoneNumber3.value.length > 0) {
            alert("휴대폰 끝 자리를 정확히 입력해주세요.");
            return false;
        }

    },



};