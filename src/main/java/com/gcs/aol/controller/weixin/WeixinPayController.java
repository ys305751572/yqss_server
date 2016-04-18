package com.gcs.aol.controller.weixin;

import com.gcs.utils.Result;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/4/15.
 */
@Controller
@RequestMapping(value = "/weixin/pay")
public class WeixinPayController {

    @Autowired
    private WxMpService wxMpService;

    @RequestMapping(value = "/getPayInfo")
    public Result getPayInfo() {
        String url = wxMpService.oauth2buildAuthorizationUrl(WxConsts.OAUTH2_SCOPE_BASE,null);

        Map<String,String> params = new HashMap<String, String>();
        Map<String, String> payinfo = wxMpService.getJSSDKPayInfo(params);
        return Result.success(payinfo);
    }
}
