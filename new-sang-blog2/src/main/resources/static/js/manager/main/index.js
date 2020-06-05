let Index = {
    init : function() {

    },

    menuToggle : function(that, menuType, url) {
        if (menuType === 'FILE') {
            location.href = url;
        } else if (menuType === 'DIRECTORY') {
            let id = that.id;
            let menuClass = document.getElementsByClassName(id);
            let dispalyStatus = that.classList.contains('display-none') ? 'none' : 'block';
            that.classList.toggle('display-none');
            menuClass[0].style.display = dispalyStatus;
        }

    }
    /*menuToggle : function(that) {
        let menuClass = document.getElementsByClassName(that.id);
        let dispalyStatus = that.classList.contains('display-none') ? 'none' : 'block';
        that.classList.toggle('display-none');

        for (let i = 0; i < menuClass.length; i++) {
            menuClass[i].style.display = dispalyStatus;
        }
    }*/


};

Index.init();