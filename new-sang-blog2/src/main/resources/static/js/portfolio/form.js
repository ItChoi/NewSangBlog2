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

window.addEventListener('DOMContentLoaded', (event) => {
    let editor = new FroalaEditor('#editor', {
        imageUploadURL: '/uploader/s3', // 업로드 처리 end point
        imageUploadParam: 'file', // 파일 파라메터명
        imageUploadMethod: 'POST',
        imageAllowedTypes: ['jpeg', 'jpg', 'png'],
        imageMaxSize: 2 * 1024 * 1024, // 최대 이미지 사이즈 : 2메가
        requestHeaders: {
            'X-CSRF-TOKEN': document.getElementsByName("_csrf")[0].value
        }
    });
});












