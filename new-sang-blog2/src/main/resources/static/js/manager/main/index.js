let Index = {
    init : function() {

    },

    test : function(that, index) {
        console.log("test: ", index);
        that.parentNode.closest("ul").style.display = "block";
        console.log("test: ", that.parentNode.childNodes);
    }


};

Index.init();