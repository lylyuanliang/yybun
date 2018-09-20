package com.yybun.service;

public interface ICusService {

    /**
     * 获取顾客列表
     * @param traduuid 交易uuid
     * @param condition 排序条件
     * @return
     */
    public String getCustomerList(String traduuid, String condition);
}
