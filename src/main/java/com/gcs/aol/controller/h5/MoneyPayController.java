package com.gcs.aol.controller.h5;

import com.gcs.aol.constant.Constant;
import com.gcs.aol.service.IMoneyMagDodManager;
import com.gcs.utils.Result;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by Administrator on 2016/4/20.
 */
@Controller
@RequestMapping(value = "/weixin")
public class MoneyPayController {

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private IMoneyMagDodManager moneyMagDodManager;

    @RequestMapping(value = "/payConfig")
    @ResponseBody
    public Result payConfig(HttpServletRequest request,HttpServletResponse response,Integer dodId,Integer type) {

        Map<String,Object> resultParam = moneyMagDodManager.payConfig(request,response,dodId,type);
        return Result.success(resultParam,"payConfig");
    }
    /*********************************************************************************************
     *  暂留
     *********************************************************************************************/


    @RequestMapping(value = "/payIndex")
    public String index() {
        return "h5/pay_test";
    }

    @RequestMapping(value = "/toOauth")
    @ResponseBody
    public String toOauth(HttpServletRequest request,HttpServletResponse response) {

        Map<String,String> payInfo = null;
        String code = request.getParameter("code");
        WxMpUser user = (WxMpUser) request.getSession().getAttribute(Constant.WEIXIN_USER);
        if(user == null && code == null) {
            String queryString =  request.getQueryString() != null ? "?" + request.getQueryString() : "";
            String fullUrl = "http://8ac4879b.ngrok.io" + request.getRequestURI() + queryString;
            String url = wxMpService.oauth2buildAuthorizationUrl(fullUrl,WxConsts.OAUTH2_SCOPE_USER_INFO,"weixin_state");
            try {
                response.sendRedirect(url);
                return null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(user != null){
            System.out.println("user != null:" + user.getOpenId());
            payInfo = getPayInfo(user);
            return new GsonBuilder().create().toJson(payInfo);
        }
        if(code != null) {
            try {
                WxMpOAuth2AccessToken wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
                WxMpUser wxMpUser = wxMpService.oauth2getUserInfo(wxMpOAuth2AccessToken, null);
                System.out.println("code != null" + wxMpUser.getOpenId());

                if(wxMpUser != null) {
                    request.getSession().setAttribute(Constant.WEIXIN_USER,wxMpUser);
                    payInfo = getPayInfo(wxMpUser);
                    return  new GsonBuilder().create().toJson(payInfo);
                }
            } catch (WxErrorException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public Map<String, String> getPayInfo(WxMpUser wxMpUser) {
        String openId = wxMpUser.getOpenId();
        String outTradeNo = String.valueOf(System.currentTimeMillis());
        double amt = 0.01;
        String body = "支付测试";
        String tradeType = "JSAPI";
        String ip = "61.183.142.106";
        String notifyUrl = "http:/6mai.cc/yqss/login";
        Map<String, String> payInfo = wxMpService.getJSSDKPayInfo(openId,outTradeNo,amt,body,tradeType,ip,notifyUrl);
        return payInfo;
    }
}
