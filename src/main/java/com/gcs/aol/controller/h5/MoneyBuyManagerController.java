package com.gcs.aol.controller.h5;

import com.gcs.aol.constant.Constant;
import com.gcs.aol.entity.*;
import com.gcs.aol.service.*;
import com.gcs.aol.service.h5.DQBaoService;
import com.gcs.aol.service.h5.HQBaoService;
import com.gcs.aol.service.impl.MoneyMagManagerImpl;
import com.gcs.aol.service.impl.MoneyMagTRManagerImpl;
import com.gcs.sysmgr.controller.GenericEntityController;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.RequestWrapper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by caiqi on 2016/4/18 0018.
 */
@Controller
@RequestMapping(value = "/moneymag")
public class MoneyBuyManagerController extends GenericEntityController<MoneyMag, MoneyMag, MoneyMagManagerImpl> {

    public static final Integer user_id = 9;
    public static final Integer dodId = 12;

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
    public String addDQIndex(HttpServletRequest request, Model model) {


        Users user = new Users();
        user.setUserId(user_id);
        request.getSession().setAttribute(Constant.CURRENT_LOGIN_USER, user);

//        List<MoneyMagDod> dod1 = iMoneyMagDodManager.findFixPeriodList();
        MoneyMagDod dod = iMoneyMagDodManager.findFixPeriodDetail(dodId);
        model.addAttribute("dod", dod);

        request.getSession().setAttribute(Constant.DQ, dod);

        return "management/h5/加入定期宝";
    }

    @RequestMapping(value = "/hq/addHQBao")
    public String addHQBao(HttpServletRequest request, Double money, Double earnings, Model model) {

        Users user = (Users) request.getSession().getAttribute(Constant.CURRENT_LOGIN_USER);
        if (user == null) {
            return null;
        }

        MoneyMagDod dod = iMoneyMagDodManager.findDueOnDemandDetail();
        if (dod == null) {
            return "management/h5/加入活期宝";
        }
        request.getSession().setAttribute(Constant.HQ, dod);

        earnings = (money * dod.getYearYield()) / 365;

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
    public String addDQBao(HttpServletRequest request, Double money) {

        Users user = (Users) request.getSession().getAttribute(Constant.CURRENT_LOGIN_USER);
        if (user == null) {
            return null;
        }

        MoneyMagDod dod = iMoneyMagDodManager.findFixPeriodDetail(dodId);
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
    public String certification(HttpServletRequest request, String name, String idCard, String bankCard) {

       /* Users user = (Users) request.getSession().getAttribute(Constant.CURRENT_LOGIN_USER);
        if(user == null) {
            return null;
        }*/
        MoneyMagUser mUser = new MoneyMagUser();
        mUser.setBankCard(bankCard);
        mUser.setIdCard(idCard);
        mUser.setName(name);

        Users user = new Users();
        user.setUserId(user_id);
        mUser.setUser(user);

        request.getSession().setAttribute(Constant.CERTIFICATION_INFO, mUser);

        // 跳转到输入密码页面
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
    public String findPassword(HttpServletRequest request, String mobile, String code, String password) {

        /*Users user = (Users) request.getSession().getAttribute(Constant.CURRENT_LOGIN_USER);
        if(user == null) {
            return null;
        }*/
        MoneyMagUser mUser = (MoneyMagUser) request.getSession().getAttribute(Constant.CERTIFICATION_INFO);
//        MoneyMagUser _user = iMoneyMagUserManager.findByUserId(user_id);
        mUser.setTradingPassword(password);

        Users user = (Users) (iUsersManager.queryByProperty("userId", mUser.getUser().getUserId()).get(0));
        mUser.setUser(user);

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
}
