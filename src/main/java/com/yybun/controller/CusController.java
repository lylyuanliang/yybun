package com.yybun.controller;

import com.yybun.common.utils.UUIDUtils;
import com.yybun.common.vo.Constant;
import com.yybun.common.vo.ResponseVo;
import com.yybun.service.ICusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 顾客相关
 */
@Controller
@RequestMapping("/cus")
public class CusController {

    // log4j
    private static Logger LOG = LoggerFactory.getLogger(CusController.class);

    @Autowired
    private ICusService cusService;

    /**
     * 获取顾客列表接口
     * @param request
     * @return
     */
    @RequestMapping("/getCusList")
    @ResponseBody
    public String getCusList(HttpServletRequest request) {
        String traduuid = UUIDUtils.createUUID();//产生一个交易码，方便日志追踪

        try {
            //获取请求参数
            String condition =  request.getParameter("condition");
            return cusService.getCustomerList(traduuid, condition);
        }catch (Exception e) {
            LOG.error("【" + traduuid + "】查询顾客列表信息系统异常", e);
            return ResponseVo.createResponseStr(Constant.SYSTEM_EXCEPTION_CODE, "【" + traduuid + "】查询顾客列表信息系统异常", null);
        }
    }
}
