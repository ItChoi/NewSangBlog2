let Login = {
	init : function() {
        document.getElementById('sign_up_button').onclick = function() {
            location.href = "/manager/user/create";
        }
	}

};

Login.init();
