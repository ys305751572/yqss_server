package com.gcs.aol.controller.h5;

import com.gcs.aol.service.IMoneyMagManager;
import com.gcs.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by yesong on 2016/4/26.
 */
@Controller
@RequestMapping(value = "/moneymag")
public class MoneyIndexController {

    @Autowired
    private IMoneyMagManager moneyMagManager;

    @RequestMapping(value = "/h5Index")
    @ResponseBody
    public Result h5Index(Integer userId) {
        return Result.success(moneyMagManager.h5Index(userId));
    }
}
