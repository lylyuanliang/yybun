/**
 扩展一个自定义模块
 **/
layui.define(['layer'], function(exports){ //提示：模块也可以依赖其它模块，如：layui.define('layer', callback);
    var obj = {
        constants : {
            CONTEXT : "/yybun",
            CODE : {
                SUCCESS_CODE : "1"
            }
        },
        //Ajax提交
        ajaxPost : function (Url,jsonData,LodingFun,ReturnFun) {
            $.ajax({
                type: "POST",
                url: Url,
                data: jsonData,
                dataType: 'json',
                // contentType: 'text/json',
                async: true,
                beforeSend: LodingFun,
                error: function (e) {
                    var index = layer.msg(e, { time: 2000, anim:1 });
                    layer.style(index, {
                        color: '#777'
                    });
                },
                success: ReturnFun
            });
        }
    };

    //输出自定义接口
    exports('mylayui', obj);
});