package com.yybun.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.yybun.common.vo.Constant;
import com.yybun.common.vo.ResponseVo;
import com.yybun.dao.IUsersDao;
import com.yybun.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements IUserService {

    private static Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private IUsersDao usersDao;
    /**
     * 登陆业务逻辑 实现
     * @param traduuid  交易uuid
     * @param username
     * @param password
     * @return
     */
    public String login(String traduuid, String username, String password) {
        if(StringUtils.isBlank(username)) {
            LOG.info("【"+ traduuid +"】用户名为空！");
            return ResponseVo.createResponseStr(Constant.BUSINESS_EXCEPTION_CODE, "【"+ traduuid +"】用户名为空！", null);
        }
        if(StringUtils.isBlank(password)) {
            LOG.info("【"+ traduuid +"】登陆密码为空！");
            return ResponseVo.createResponseStr(Constant.BUSINESS_EXCEPTION_CODE, "【"+ traduuid +"】登陆密码为空！", null);
        }

        try {
            LOG.info("【"+ traduuid +"】登陆请求报文：username=" + username);
            Map<String, Object> resultMap = usersDao.getUserInfo(username);
            LOG.info("【"+ traduuid +"】登陆返回报文：" + JSONObject.toJSONString(resultMap));
            if(resultMap !=null && !resultMap.isEmpty()) {
                String pwd = (String) resultMap.get("pwd");
                if(password.equals(pwd)) {
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("username", username);
                    return ResponseVo.createResponseStr(Constant.SUCCESS_CODE, "登陆成功！", map);
                }
            }
            return ResponseVo.createResponseStr(Constant.BUSINESS_EXCEPTION_CODE, "用户名或密码不正确！", null);
        }catch (Exception e) {
            LOG.error("【"+ traduuid +"】登陆系统异常！", e);
            return ResponseVo.createResponseStr(Constant.BUSINESS_EXCEPTION_CODE, "【"+ traduuid +"】登陆失败！", null);
        }
    }
}
