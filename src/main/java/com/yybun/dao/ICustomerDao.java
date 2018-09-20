package com.yybun.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ICustomerDao {

    /**
     * 获取顾客列表
     * @param condition 排序条件
     * @return
     */
    @Select("<script>" +
            "select c_name cusName,c_sex cusSex,c_tel cusTel, c_address cusAddr,c_note cusNote,c_purchase purchase,date_format(create_time, '%Y-%m-%d %H:%i:%s') date" +
            " from customer" +
            "<if test='condition==\"date\"'>" +
            " order by create_time desc" +
            "</if>" +
            "<if test='condition==\"purchase\"'>" +
            " order by c_purchase desc" +
            "</if>" +
            "</script>")
    public List<Map<String, Object>> getCusList(@Param("condition")String condition);
}
