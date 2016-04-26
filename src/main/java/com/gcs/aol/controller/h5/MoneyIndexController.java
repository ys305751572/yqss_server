package com.gcs.aol.controller.h5;

import com.gcs.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by yesong on 2016/4/26.
 */
@Controller
@RequestMapping(value = "/moneymag")
public class MoneyIndexController {

    public Result h5Index(Integer userId) {


        return Result.success();
    }
}
