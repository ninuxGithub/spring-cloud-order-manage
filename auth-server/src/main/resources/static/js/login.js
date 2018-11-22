/**
 * 登录模块
 */
$(function () {

    function isNullOrEmpty(s) {
        if (s == null || s == undefined || s.trim() == '') {
            return true;
        }
        return false;
    }

    $('#login').on('click', function () {
        var $form = $('#loginForm');
        var formData = {
            userName: $form.find('input[name="userName"]').val(),
            password: $form.find('input[name="password"]').val()
        };

        if (isNullOrEmpty(formData.userName)) {
            easyDialog.open({
                container: {
                    header: '登录验证消息',
                    content: '用户名称不能为空',
                    yesFn: function () {
                        return true;
                    },
                    noFn: true
                }
            });
            return;
        }
        if (isNullOrEmpty(formData.password)) {
            easyDialog.open({
                container: {
                    header: '登录验证消息',
                    content: '密码不能为空',
                    yesFn: function () {
                        return true;
                    },
                    noFn: true
                }
            });
            return;
        }
        //console.dir('登录名称,密码验证不为空开始登录..')

        $.ajax({
            url: "/auth",
            type: "POST",
            data: JSON.stringify(formData),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (data, textStatus, jqXHR) {
                if(data !=null){
                    localStorage.setItem('cacheToken',JSON.stringify(data));
                    window.location.href='index.html';
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                if (jqXHR.status === 401 || jqXHR.status === 403) {
                    easyDialog.open({
                        container: {
                            header: '登录验证消息',
                            content: '消息来自服务器' + jqXHR.responseText,
                            yesFn: function () {
                                return true;
                            },
                            noFn: true
                        }
                    });
                } else {
                    throw new Error("an unexpected error occured: " + errorThrown);
                }
            }
        });

    });

});