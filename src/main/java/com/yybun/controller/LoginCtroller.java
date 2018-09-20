package com.yybun.controller;

import com.yybun.common.utils.UUIDUtils;
import com.yybun.common.vo.Constant;
import com.yybun.common.vo.ResponseVo;
import com.yybun.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/login")
public class LoginCtroller {

    // log4j
    private static Logger LOG = LoggerFactory.getLogger(LoginCtroller.class);

    @Autowired
    private IUserService userService;

    /**
     * do login
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/doLogin")
    @ResponseBody
    public String dologin(HttpServletRequest request, HttpServletResponse response) {

        String traduuid = UUIDUtils.createUUID();//产生一个交易码，方便日志追踪

        try {
            String username = (String) request.getParameter("username");//获取用户名
            String password = (String) request.getParameter("password");//获取登陆密码

            return userService.login(traduuid, username, password);
        }catch (Exception e) {
            LOG.error("【" + traduuid + "】登陆发生系统异常", e);
            return ResponseVo.createResponseStr(Constant.SYSTEM_EXCEPTION_CODE, "【" + traduuid + "】登陆失败！", null);
        }
    }
}
