layui.use(['layer', 'form', 'mylayui','element'], function () {
    var layer = layui.layer;
    var form = layui.form;
    var $ = layui.$;
    var common = layui.mylayui;
    var element = layui.element;
    //上下文
    var context = common.constants.CONTEXT;
    var sussCode = common.constants.CODE.SUCCESS_CODE;

    var url = context + "/service/cus/getCusList";
    var condition = "";
    var param = {
        condition : condition
    };
    common.ajaxPost(url,param,function () {
    },function (data) {
        if(data["rtnCode"] == sussCode) {
            //开始渲染页面
            showData(data["data"]);
            element.render("ur");
        }else {
            layer.alert('<span style=\'color:black\'>' + data["message"] + '</span>', {
                closeBtn: 0
            }, function(index) {
                layer.close(index);
            });
        }
    })

    /**
     * 数据展示
     * @param data
     */
    function showData(data) {
        var html = "";
        if(data) {
            $.each(data, function (i, obj) {
                html += '<li class="layui-timeline-item">';
                html += '   <i class="layui-icon layui-timeline-axis">&#xe63f;</i>';
                html += '   <div class="layui-timeline-content layui-text">';
                html += '       <h3 class="layui-timeline-title">' + obj["date"] +'</h3>';
                html += '       <div class="layui-collapse expand">';
                html += '           <div class="layui-colla-item">';
                html += '               <h5 class="layui-colla-title">' + obj["cusName"] + '</h5>';
                html += '               <div class="layui-colla-content ">';
                html += '                   <div class="layui-card">';
                html += '                       <div class="layui-card-body">';
                html += '                           <span>金额：</span><span>' + obj["purchase"] + '</span>';
                html += '                       </div>';
                html += '                       <div class="layui-card-body">';
                html += '                           <span>性别：</span><span>' + (obj["cusSex"] == "1" ? "男" : "女") + '</span>';
                html += '                       </div>';
                html += '                       <div class="layui-card-body">';
                html += '                           <span>电话：</span><span>' + obj["cusTel"] + '</span>';
                html += '                       </div>';
                html += '                       <div class="layui-card-body">';
                html += '                           <span>地址：</span><span>' + obj["cusAddr"] + '</span>';
                html += '                       </div>';
                html += '                       <div class="layui-card-body">';
                html += '                           <span>备注：</span><span>' + obj["cusNote"] + '</span>';
                html += '                       </div>';
                html += '                   </div>';
                html += '               </div>';
                html += '           </div>';
                html += '       </div>';
                html += '   </div>';
                html += '</li>';
            });
        }
        $("ul").html(html);
    }
});