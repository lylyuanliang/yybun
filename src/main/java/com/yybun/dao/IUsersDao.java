package com.yybun.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface IUsersDao {

    /**
     * 查询用户信息，key account
     * @param account
     * @return
     */
    @Select("select account,pwd from users where account=#{account}")
    public Map<String, Object> getUserInfo(@Param("account") String account) ;
}
