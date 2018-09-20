package com.yybun.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.yybun.common.vo.Constant;
import com.yybun.common.vo.ResponseVo;
import com.yybun.dao.ICustomerDao;
import com.yybun.service.ICusService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CusServiceImpl implements ICusService {

    private static Logger LOG = LoggerFactory.getLogger(CusServiceImpl.class);

    @Autowired
    private ICustomerDao customerDao;

    /** 默认查询条件  按date查***/
    private static String DEFAULT_CONDITION = "date";
    /**
     * 获取顾客列表
     * @param traduuid 交易uuid
     * @param condition 排序条件
     * @return
     */
    public String getCustomerList(String traduuid, String condition) {
        if(StringUtils.isBlank(condition)) {
            //查询条件为空，采用默认 查询条件
            condition = DEFAULT_CONDITION;
        }

        //开始调用dao层查询数据
        try {
            LOG.info("【" + traduuid + "】查询顾客列表信息，请求报文：condition=" + condition);
            List<Map<String, Object>> resultList = customerDao.getCusList(condition);
            LOG.info("【" + traduuid + "】查询顾客列表信息，返回报文：" + JSONObject.toJSONString(resultList));
            //组装返回报文
            String rtnCode = Constant.SUCCESS_CODE;
            String message = "查询成功";
            if(resultList.isEmpty()) {
                rtnCode = Constant.BUSINESS_NO_DATA_CODE;
                message = "暂时没有顾客信息";
            }

            return ResponseVo.createResponseStr(rtnCode, message, resultList);
        }catch (Exception e) {
            LOG.error("【" + traduuid + "】查询顾客列表信息系统异常", e);
            return ResponseVo.createResponseStr(Constant.SYSTEM_EXCEPTION_CODE, "【" + traduuid + "】查询顾客列表信息系统异常", null);
        }
    }
}
