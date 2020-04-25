let Utils = {
    init : function() {

    },

    concatValue : function() {
        let result = "";
        for (let value of arguments) {
            result += value;
        }

        return result;
    },

    getPhoneNumber : function() {
        let phoneNumber1 = document.getElementById('phoneNumber1');
        let phoneNumber2 = document.getElementById('phoneNumber2');
        let phoneNumber3 = document.getElementById('phoneNumber3');

        return Utils.concatValue(phoneNumber1, phoneNumber2, phoneNumber3);
    }

};

Utils.init();