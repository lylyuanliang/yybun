layui.use(['layer', 'form', 'mylayui'], function () {
    var layer = layui.layer;
    var form = layui.form;
    var $ = layui.$;
    var common = layui.mylayui;

    var context = common.constants.CONTEXT;
    var sussCode = common.constants.CODE.SUCCESS_CODE;

    //非空验证
    $('input[type="button"]').click(function () {
        var login = $('input[name="login"]').val();
        var pwd = $('input[name="pwd"]').val();
        var code = $('input[name="code"]').val();
        if (!login) {
            layer.msg('请输入您的账号');
            return ;
        } else if (!pwd) {
            layer.msg('请输入密码');
            return ;
        } /*else if (!code || code.length != 4) {
            layer.msg('输入正确的验证码');
            return ;
        }*/ else {
            //认证中..
            // fullscreen();  --- 页面全屏
            $('.login').addClass('test'); //倾斜特效
            setTimeout(function () {
                $('.login').addClass('testtwo'); //平移特效
            }, 300);
            setTimeout(function () {
                $('.authent').show().animate({ right: -80 }, {
                    easing: 'easeOutQuint',
                    duration: 600,
                    queue: false
                });
                $('.authent').animate({ opacity: 1 }, {
                    duration: 200,
                    queue: false
                }).addClass('visible');
            }, 500);

            //登录
            var jsonData = {
                username: login,
                password: pwd,
                code: code };
            //登陆请求
            var url = context + "/service/login/doLogin";

            common.ajaxPost(url, jsonData,function () {
                    //ajax加载中
                },
                function (data) {
                    //ajax返回
                    //认证完成
                    if (data["rtnCode"] == sussCode) {
                        //登录成功
                        $('.login div').fadeOut(100);
                        $('.success').fadeIn(1000);
                        $('.success').html(data["data"]);
                        //跳转操作
                        $(location).attr('href', context + '/views/customer/showcustomer.html');
                    } else {
                        layer.alert('<span style=\'color:black\'>' + data["message"] + '</span>', {
                            closeBtn: 0
                        }, function(index) {

                            $('.authent').show().animate({ right: 90 }, {
                                easing: 'easeOutQuint',
                                duration: 600,
                                queue: false
                            });
                            $('.authent').animate({ opacity: 0 }, {
                                duration: 200,
                                queue: false
                            }).addClass('visible');
                            $('.login').removeClass('testtwo'); //平移特效
                            $('.authent').hide();
                            $('.login').removeClass('test');
                            layer.close(index);
                        });
                    }
                })
        }
    })
});