package com.gcs.aol.controller.h5;

import co.emay.channel.SmsSend;
import com.gcs.aol.constant.Constant;
import com.gcs.aol.constant.ErrorCode;
import com.gcs.aol.entity.*;
import com.gcs.aol.service.IMoneyMagDodManager;
import com.gcs.aol.service.IMoneyMagTRManager;
import com.gcs.aol.service.IMoneyMagUserManager;
import com.gcs.aol.service.IUsersManager;
import com.gcs.aol.service.impl.MoneyMagManagerImpl;
import com.gcs.cache.CacheService;
import com.gcs.sysmgr.controller.GenericEntityController;
import com.gcs.utils.CommonUtils;
import com.gcs.utils.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by caiqi on 2016/4/18 0018.
 */
@Controller
@RequestMapping(value = "/moneymag")
public class MoneyBuyManagerController extends GenericEntityController<MoneyMag, MoneyMag, MoneyMagManagerImpl> {

    private static Map<String,String> codeMap = new HashMap<String,String>();


    static {
        if(codeMap == null || codeMap.isEmpty()) {
            codeMap =  new HashMap<String,String>();
        }
    }

    public static final Integer user_id = 6;

//    @Resource(name = "cacheTempCodeServiceImpl")
//    private CacheService<String> cacheService;

    @Autowired
    private IMoneyMagUserManager iMoneyMagUserManager;

    @Autowired
    private IMoneyMagDodManager iMoneyMagDodManager;

    @Autowired
    private IUsersManager iUsersManager;

    @Autowired
    private IMoneyMagTRManager iMoneyMagTRManager;


    @RequestMapping(value = "/hq/addHQIndex")
    public String addHQIndex(HttpServletRequest request) {

        Users user = new Users();
        user.setUserId(user_id);
        request.getSession().setAttribute(Constant.CURRENT_LOGIN_USER, user);

        return "management/h5/加入活期宝";
    }

    @RequestMapping(value = "/hq/addDQIndex")
    public String addDQIndex(HttpServletRequest request,Integer id, Model model) {


        Users user = new Users();
        user.setUserId(user_id);
        request.getSession().setAttribute(Constant.CURRENT_LOGIN_USER, user);

//        List<MoneyMagDod> dod1 = iMoneyMagDodManager.findFixPeriodList();
        MoneyMagDod dod = iMoneyMagDodManager.findFixPeriodDetail(id);
        model.addAttribute("dod", dod);

        request.getSession().setAttribute(Constant.DQ, dod);

        return "management/h5/加入定期宝";
    }

    @RequestMapping(value = "/hq/index")
    public String index(){return "management/h5/忘记交易密码";}

    @RequestMapping(value = "/hq/onBluer")
    @ResponseBody
    public Double onBluer(HttpServletRequest request, Double money, Double earnings,Double e){

        MoneyMagDod dod = iMoneyMagDodManager.findDueOnDemandDetail();
        request.getSession().setAttribute(Constant.HQ, dod);
        earnings =  (money * dod.getYearYield()) / 365;


        MoneyMagTR tr = new MoneyMagTR();
        tr.setEarnings(earnings);
        request.getSession().setAttribute(Constant.TR, tr);
        request.getSession().setAttribute(Constant.HQ, dod);

        BigDecimal b = new BigDecimal(earnings);
        e = b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();

        //df.format(earnings);
        return e;
    }

    @RequestMapping(value = "/hq/addHQBao")
    public String addHQBao(HttpServletRequest request, Double money, Double e, Double earnings,Model model) {

        request.getSession().removeAttribute(Constant.HQ);
        request.getSession().removeAttribute(Constant.DQ);

        Users user = (Users) request.getSession().getAttribute(Constant.CURRENT_LOGIN_USER);
        if (user == null) {
            return null;
        }

        MoneyMagDod dod = iMoneyMagDodManager.findDueOnDemandDetail();
        request.getSession().setAttribute(Constant.HQ, dod);

        e = (money * dod.getYearYield()) / 365;

        BigDecimal b = new BigDecimal(e);
        earnings = b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();

        MoneyMagTR tr = new MoneyMagTR();
        tr.setMoney(money);
        tr.setEarnings(earnings);
        request.getSession().setAttribute(Constant.TR, tr);
        request.getSession().setAttribute(Constant.HQ, dod);

        if (money <= dod.getResidue()) {

            if (!iMoneyMagUserManager.isCertification(user.getUserId())) {
                // 跳转到认证页
                return "management/h5/实名认证";
            } else {
                // 跳转到输入密码页面
                return "management/h5/输入交易密码";
            }
        } else {
            return "输入投资金额不对，请重新输入！";
        }
    }

    @RequestMapping(value = "/dq/addDQBao")
    public String addDQBao(HttpServletRequest request, Integer id,Double money) {

        request.getSession().removeAttribute(Constant.HQ);
        request.getSession().removeAttribute(Constant.DQ);

        Users user = (Users) request.getSession().getAttribute(Constant.CURRENT_LOGIN_USER);
        if (user == null) {
            return null;
        }

        MoneyMagDod dod = iMoneyMagDodManager.findFixPeriodDetail(id);
        request.getSession().setAttribute(Constant.DQ, dod);

        MoneyMagTR tr = new MoneyMagTR();
        tr.setMoney(money);
        request.getSession().setAttribute(Constant.TR, tr);
        request.getSession().setAttribute(Constant.DQ, dod);

        if (money <= dod.getResidue()) {

            if (!iMoneyMagUserManager.isCertification(user.getUserId())) {
                // 跳转到认证页
                return "management/h5/实名认证";
            } else {
                // 跳转到输入密码页面
                return "management/h5/输入交易密码";
            }
        } else {
            return "输入转入金额不对，请重新输入！";
        }
    }

    @RequestMapping(value = "/hq/certification")
    public String certification(HttpServletRequest request,String name, String idCard, String bankCard) {

        Users users = (Users) request.getSession().getAttribute(Constant.CURRENT_LOGIN_USER);
        if(users == null) {
            return null;
        }

        MoneyMagUser mUser = new MoneyMagUser();
        mUser.setBankCard(bankCard);
        mUser.setIdCard(idCard);
        mUser.setName(name);

//        Users user = new Users();
//        user.setUserId(user_id);

        Users user = iUsersManager.queryByPK(users.getUserId());
        mUser.setUser(user);

        iMoneyMagUserManager.save(mUser);

        request.getSession().setAttribute(Constant.CERTIFICATION_INFO, mUser);

        return "management/h5/设置交易密码";
    }

    @RequestMapping(value = "/hq/confirmPwd")
    @ResponseBody
    public String confirmPwd(HttpServletRequest request, String password, Model model) {
        Users user = (Users) request.getSession().getAttribute(Constant.CURRENT_LOGIN_USER);
        if (iMoneyMagUserManager.isCorrectPwd(user.getUserId(), password)) {
            return "SUCCESS";
        }
        return "ERROR";
    }

    @RequestMapping(value = "/hq/toConfirmPage")
    public String toConfirmPage(HttpServletRequest request, Model model) {
        Users user = (Users) request.getSession().getAttribute(Constant.CURRENT_LOGIN_USER);

        MoneyMagDod dod = (MoneyMagDod) request.getSession().getAttribute(Constant.HQ);
        MoneyMagDod dod1 = (MoneyMagDod) request.getSession().getAttribute(Constant.DQ);
        if (user == null) {
            return null;
        }

        if (null != dod && dod.getType() == 1) {
            MoneyMagTR tr = (MoneyMagTR) request.getSession().getAttribute(Constant.TR);
            model.addAttribute("tr", tr);
            return "management/h5/确认加入活期宝";
        }
        if (dod1.getType() == 2) {
            MoneyMagTR tr = (MoneyMagTR) request.getSession().getAttribute(Constant.TR);
            model.addAttribute("tr", tr);
            MoneyMagDod dod2 = (MoneyMagDod) request.getSession().getAttribute(Constant.DQ);
            model.addAttribute("dod2", dod2);
            return "management/h5/确认加入定期宝";
        }

        return null;
    }



    @RequestMapping(value = "/hq/findPassword")
    public String findPassword(HttpServletRequest request,String password) {

        Users user = (Users) request.getSession().getAttribute(Constant.CURRENT_LOGIN_USER);
        if(user == null) {
            return null;
        }


        MoneyMagUser mUser = iMoneyMagUserManager.findByUserId(user.getUserId());
        mUser.setTradingPassword(password);

        Users _user = (Users) (iUsersManager.queryByProperty("userId", mUser.getUser().getUserId()).get(0));
        mUser.setUser(_user);

        iMoneyMagUserManager.save(mUser);
        // 跳转到输入密码页面
        return "management/h5/输入交易密码";
    }

    /**
     * @param request
     * @return
     */
    @RequestMapping(value = "/hq/confirmJoinHQ")
    public String confirmJoinHQ(HttpServletRequest request) {

        MoneyMagTR tr = (MoneyMagTR) request.getSession().getAttribute(Constant.TR);
        Users user = (Users) request.getSession().getAttribute(Constant.CURRENT_LOGIN_USER);

        MoneyMagDod dod = (MoneyMagDod) request.getSession().getAttribute(Constant.HQ);

        MoneyMagDod _dod = iMoneyMagDodManager.queryByPK(dod.getId());
        tr.setDod(_dod);

        Users _user = iUsersManager.queryByProperty("userId", user.getUserId()).get(0);
        tr.setUser(_user);
        iMoneyMagTRManager.save(tr);

        request.getSession().removeAttribute(Constant.TR);
        request.getSession().removeAttribute(Constant.HQ);

        return null;
    }

    @RequestMapping(value = "/hq/confirmJoinDQ")
    public String confirmJoinDQ(HttpServletRequest request) {

        MoneyMagTR tr = (MoneyMagTR) request.getSession().getAttribute(Constant.TR);
        Users user = (Users) request.getSession().getAttribute(Constant.CURRENT_LOGIN_USER);

        MoneyMagDod dod = (MoneyMagDod) request.getSession().getAttribute(Constant.DQ);

        MoneyMagDod _dod = iMoneyMagDodManager.queryByPK(dod.getId());
        tr.setDod(_dod);

        Users _user = iUsersManager.queryByProperty("userId", user.getUserId()).get(0);
        tr.setUser(_user);
        iMoneyMagTRManager.save(tr);

        request.getSession().removeAttribute(Constant.TR);
        request.getSession().removeAttribute(Constant.DQ);

        return null;
    }

    @RequestMapping(value = "/sendCode")
    @ResponseBody
    public Result sendCode(String mobile) {

        if(StringUtils.isBlank(mobile) ) {
            return Result.failure("参数错误");
        }

        try {
            // 生成验证码
            String code = CommonUtils.getCode(6);
            // 成功
            if (SmsSend.send(mobile, code)) {
                codeMap.put(mobile,code);
                return Result.success();
            }
            // 失败
            else {
                return Result.failure(ErrorCode.ERROR_09);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure();
        }
    }

    @RequestMapping(value = "/hq/confirmCode")
    public String confirmCode(HttpServletRequest request,String mobile,String requestCode) {

        Users user = (Users) request.getSession().getAttribute(Constant.CURRENT_LOGIN_USER);
        if(user == null) {
            return null;
        }

        String code = codeMap.get(mobile);

        if(StringUtils.isBlank(requestCode) || !requestCode.equals(code)) {
            return ErrorCode.ERROR_10;
        }

        return "management/h5/设置交易密码";
    }
}
