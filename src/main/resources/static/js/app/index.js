var main= { //왜 이거 썼냐면 index로 scope를 한정시키기 위해 사용한다.중복되는거 있으면 브라우저의 scope는 공용이므로 난리가 난다.
    init : function (){
        var _this = this;
        $('#btn-save').on('click',function(){
            _this.save();
        })
    },
    save : function(){
        var data ={
            title : $('#title').val(),
            author: $('#author').val(),
            content: $('#content').val()
        };
        $.ajax({
            type: 'POST',
            url: '/api/v1/posts',
            dataType:'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function(){
            alert('글이 등록되었습니다');
            window.location.href='/';
        }).fail(function(error){
            alert(JSON.stringify(error));
        })
    }
};

main.init();