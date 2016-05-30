package com.gcs.aol.controller.h5;

import com.gcs.aol.entity.ActEntity;
import com.gcs.aol.service.ActService;
import com.gcs.utils.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2016/5/27.
 */
@RequestMapping(value = "/moneymag")
@Controller
public class ActWebController{

    @Autowired
    private ActService service;

    @RequestMapping(value = "/act/index")
    public String h5Index() {
        return "h5/cy";
    }

    @RequestMapping(value = "/act/insert")
    @ResponseBody
    public Result insert(String mobile,String code) {

        if(StringUtils.isNotBlank(mobile) && StringUtils.isNotBlank(code)) {

            List<ActEntity> list = service.queryByProperty("mobile",mobile);
            if(list != null && list.size() > 0) {
                return Result.failure("请不要重复加入");
            }
            String _code = MoneyBuyManagerController.codeMap.get(mobile);
            if(StringUtils.isBlank(_code) || !_code.equals(code)) {
                return Result.failure("验证码错误");
            }
            ActEntity act = new ActEntity();
            act.setMobile(mobile);
            act.setCreateDate(System.currentTimeMillis());
            service.insert(act);
            return Result.success();
        }
        else {
            return Result.failure("添加失败");
        }
    }
}
