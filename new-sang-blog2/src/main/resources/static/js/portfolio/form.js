let Form = {
    init : function() {
    },

    addHashTag : function(type) {
        let title = '';
        let html = '';
        let hashTag = '';
        let name = '';
        if (type === "env") {
            name = 'envHashTag.title';
            hashTag = document.getElementById('env_hashtag');
            title = document.getElementById('envHashTag').value;
        } else {
            name = 'skillHashTag.title';
            hashTag = document.getElementById('skill_hashtag');
            title = document.getElementById('skillHashTag').value;
        }

        html += '<div class="hash-tag">';
        html += '   <input type="hidden" name="' + name + '" value="' + title + '">';
        html += '   <span>' + title + '</span>';
        html += '   <a href="">';
        html += '       <img src="/img/close-button.png" alt="X">';
        html += '   </a>';
        html += '</div>';
        html += '\n';

        hashTag.insertAdjacentHTML('beforeend', html);

        /*axios({
            method: 'POST',
            url: '/manager/portfolio/hashtag',
            data: {
                refId : portfolioId,
                title : hashtagTitle,
                hashTagType : hashTagType
            },
            headers: {
                'X-Requested-With': 'XMLHttpRequest',
                'X-CSRF-TOKEN' : document.querySelector('input[name="_csrf"]').value
            },
        }).then(function(response) {
            console.log(response);
        }).catch(function(error) {
            alert(error)
        });*/




    }
};

Form.init();