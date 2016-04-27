package com.gcs.pay.excute;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gcs.pay.AccessTokenRequestHandler;
import com.gcs.pay.ClientRequestHandler;
import com.gcs.pay.PackageRequestHandler;
import com.gcs.pay.PrepayIdRequestHandler;
import com.gcs.pay.util.ConstantUtil;
import com.gcs.pay.util.TenpayUtil;
import com.gcs.pay.util.WXUtil;
import com.gcs.utils.CommonUtils;
import org.json.JSONException;


public class PayRequest {

    public static Map<String, Object> pay(HttpServletRequest request, HttpServletResponse response) {

        response.resetBuffer();
        response.setHeader("ContentType", "text/xml");
//		out.println("<?xml version=\"1.0\" encoding=\"GBK\"?>");
//		out.println("<root>");
        //---------------------------------------------------------
        //---------------------------------------------------------

        // 回调地址(测试)
//		String notify_url_borrow = "http://121.40.63.108:8081/caimi/pay/notify/weixinborrow";
//		String notify_url_pb = "http://121.40.63.108:8081/caimi/pay/notify/weixinpb";
//		String notify_url_product = "http://121.40.63.108:8081/caimi/pay/notifyWeixin";

        // 回调地址(正式)
        String notify_url = "http://121.41.17.108:8080/yqss_server/weixin/notify/weixinmoney";


        String currTime = TenpayUtil.getCurrTime();
        String strTime = currTime.substring(8, currTime.length());
        String strRandom = TenpayUtil.buildRandom(4) + "";
//		String strReq = strTime + strRandom;
        String out_trade_no = request.getAttribute("sn").toString();
        PackageRequestHandler packageReqHandler = new PackageRequestHandler(request, response);
        PrepayIdRequestHandler prepayReqHandler = new PrepayIdRequestHandler(request, response);
        ClientRequestHandler clientHandler = new ClientRequestHandler(request, response);

        Map<String, Object> resultMap = new HashMap<String, Object>();

        int retcode;
        String retmsg = "";
        String xml_body = "";

        // 预支付ID
        String prepayid = "";

        //token
        String token = AccessTokenRequestHandler.getAccessToken();
        if (!"".equals(token)) {
//			packageReqHandler.setParameter("bank_type", "WX");
            prepayReqHandler.setParameter("appid", ConstantUtil.APP_ID);

            prepayReqHandler.setParameter("body", "还款支付");
            prepayReqHandler.setParameter("device_info", ConstantUtil.DEVICE_INFO);
            prepayReqHandler.setParameter("fee_type", "CNY");
            prepayReqHandler.setParameter("mch_id", ConstantUtil.PARTNER);
            String noncestr = WXUtil.getNonceStr();
            prepayReqHandler.setParameter("nonce_str", noncestr);

            prepayReqHandler.setParameter("notify_url", notify_url);
            prepayReqHandler.setParameter("out_trade_no", out_trade_no);

//			packageReqHandler.setParameter("input_charset", "GBK");

            String timestamp = WXUtil.getTimeStamp();
            String traceid = "";
//			prepayReqHandler.setParameter("appkey", ConstantUtil.APP_KEY);

//			prepayReqHandler.setParameter("package", packageValue);
//			prepayReqHandler.setParameter("timestamp", timestamp);
//			prepayReqHandler.setParameter("traceid", traceid);

            prepayReqHandler.setParameter("spbill_create_ip", CommonUtils.getRealAddress(request));
            prepayReqHandler.setParameter("total_fee", "1");
//			prepayReqHandler.setParameter("total_fee", request.getAttribute("fee").toString());
            prepayReqHandler.setParameter("trade_type", ConstantUtil.TRADE_TYPE);


            String sign = prepayReqHandler.createMD5Sign();
            prepayReqHandler.setParameter("sign", sign);

//			prepayReqHandler.setParameter("sign_method",
//					ConstantUtil.SIGN_METHOD);
//			String gateUrl = ConstantUtil.GATEURL + token;
//			prepayReqHandler.setGateUrl(gateUrl);
            try {
                prepayid = prepayReqHandler.sendPrepay();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (null != prepayid && !"".equals(prepayid)) {

                clientHandler.setParameter("appid", ConstantUtil.APP_ID);
//				clientHandler.setParameter("appkey", ConstantUtil.APP_KEY);
                clientHandler.setParameter("noncestr", noncestr);
                clientHandler.setParameter("package", "Sign=WXPay");
                clientHandler.setParameter("partnerid", ConstantUtil.PARTNER);
                clientHandler.setParameter("prepayid", prepayid);
                clientHandler.setParameter("timestamp", WXUtil.getTimeStamp());

                sign = clientHandler.createMD5Sign();
                clientHandler.setParameter("sign", sign);

                resultMap = clientHandler.getMap();
                retcode = 0;
                retmsg = "OK";
            } else {
                retcode = -2;
                retmsg = "";
            }
        } else {
            retcode = -1;
            retmsg = "";
        }
        return resultMap;
    }
}
