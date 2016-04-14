package com.gcs.aol.controller.weixin;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.gcs.utils.WebUtil;

import me.chanjar.weixin.mp.api.WxMpService;

@RequestMapping(value = "/weixin")
@Controller
public class WxInitController {

	@Autowired
	private WxMpService wxMpService;

	@RequestMapping(method = {RequestMethod.GET, RequestMethod.HEAD})
	public void handleGet(HttpServletResponse response,@RequestParam(value = "signature") String signature,
			@RequestParam(value = "timestamp") String timestamp, @RequestParam(value = "nonce") String nonce,
			@RequestParam(value = "echostr") String echostr) {
		System.out.println("timestamp:" + timestamp + "=signature:" + signature + "==nonce:" + nonce + "==echostr:" + echostr);
		if (wxMpService.checkSignature(timestamp, nonce, signature)) {
			 WebUtil.print(response,echostr);
		}
		 WebUtil.print(response,"非法请求");
	}
}
