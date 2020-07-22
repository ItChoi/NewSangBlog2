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

        return true;
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

        let isSamePwd = Validator.isSamePwd();
        if (!isSamePwd) {
            return false;
        }

        return true;
    },

    // 패스워드 일치 체크 (비밀번호1, 비밀번호2, ... 일치 체크)
    isSamePwd : function() {
        let passwordDatas = document.getElementsByClassName('password');

        if (passwordDatas.length >= 2) {
            let checkPassword = passwordDatas[0].value;

            for (let i = 1; i < passwordDatas.length; i++) {
                if (checkPassword != passwordDatas[i].value) {
                    alert("입력하신 비밀번호를 일치시켜주세요.");
                    return false;
                }
            }
        }

        return true;
    },

    // 이메일 정규식
    emailRegExp : function() {
        let regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
        let emailDatas = document.getElementsByClassName('email');
        // 검증에 사용할 정규식 변수 regExp에 저장

        for (let i = 0; i < emailDatas.length; i++) {
            if (!regExp.test(emailDatas[i].value)) {
                alert("이메일을 정확히 입력해주세요.");
                return false;
            }
        }

        return true;
    },

    // 핸드폰 번호 체크
    phoneNumberRegExp : function() {
        let phoneNumber1 = document.getElementById('phoneNumber1').value;
        let phoneNumber2 = document.getElementById('phoneNumber2').value;
        let phoneNumber3 = document.getElementById('phoneNumber3').value;

        let regExp1 = /^\d{3}$/;
        let regExp2 = /^\d{3,4}$/;
        let regExp3 = /^\d{4}$/;

        if (phoneNumber1.length != 3) {
            if (!regExp1.test(phoneNumber1)) {
                alert("휴대폰 앞 자리를 정확히 입력해주세요.");
                return false;
            }
        }

        if (phoneNumber2.length != 4) {
            if (!regExp2.test(phoneNumber2)) {
                alert("휴대폰 가운데 자리를 정확히 입력해주세요.");
                return false;
            }
        }

        if (phoneNumber3.length != 4) {
            if (!regExp3.test(phoneNumber3)) {
                alert("휴대폰 끝 자리를 정확히 입력해주세요.");
                return false;
            }
        }

        if ((phoneNumber2.length > 0 && phoneNumber3.length == 0) || (phoneNumber3.length > 0 && phoneNumber2.length == 0)) {
            alert("휴대폰 번호를 모두 입력해주세요.");
            return false;
        }

        return true;

    },



};