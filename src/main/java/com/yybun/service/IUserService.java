package com.yybun.service;

public interface IUserService {

    /**
     * 登陆业务逻辑处理
     * @param traduuid  交易uuid
     * @param username
     * @param password
     * @return
     */
    public String login(String traduuid, String username, String password) ;
}
