//config的设置是全局的
layui.config({
    base: '/yybun/statics/js/common/' //假设这是你存放拓展模块的根目录
}).extend({ //设定模块别名
    mylayui: 'mylayui' //如果 mylayui.js 是在根目录，也可以不用设定别名 冒号后面是基于base目录下 文件的目录加文件名（文件名不要后缀），这里commonUtils是别名
});