package com.gcs.aol.controller.h5;

import com.gcs.aol.constant.Constant;
import com.gcs.aol.entity.*;
import com.gcs.aol.service.IMoneyMagDodManager;
import com.gcs.aol.service.IMoneyMagManager;
import com.gcs.aol.service.IMoneyMagUserManager;
import com.gcs.aol.service.h5.DQBaoService;
import com.gcs.aol.service.h5.HQBaoService;
import com.gcs.aol.service.impl.MoneyMagManagerImpl;
import com.gcs.sysmgr.controller.GenericEntityController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by caiqi on 2016/4/18 0018.
 */
@Controller
@RequestMapping(value = "/moneymag")
public class MoneyBuyDQManagerController extends GenericEntityController<MoneyMag, MoneyMag, MoneyMagManagerImpl> {

    @Autowired
    private IMoneyMagUserManager iMoneyMagUserManager;

    @Autowired
    private IMoneyMagManager iMoneyMagManager;

    @Autowired
    private DQBaoService dqBaoService;

    @Autowired
    private IMoneyMagDodManager iMoneyMagDodManager;

    @RequestMapping(value = "/dq/addIndex")
    public String addIndex() {
        return "management/h5/加入定期宝";
    }

    @RequestMapping(value = "/dq/addDQBao")
    public String addDQBao(HttpServletRequest request, Integer id,Integer dodId,Double money, Double earnings) {

        Users user = (Users) request.getSession().getAttribute(Constant.CURRENT_LOGIN_USER);
        if(user == null) {
            return null;
        }

        MoneyMagDod dod = iMoneyMagDodManager.findFixPeriodDetail(dodId);

        DQBao dqBao = null;
        if(id != null) {
            dqBao = dqBaoService.getbyid(id);
        }else {
            dqBao = new DQBao();
        }

        if(money <= dod.getPercent() && money <= dod.getResidue()) {
            dqBao.setMoney(money);
            dqBao.setMonth(dod.getMonth());
            dqBao.setRate(dod.getYearYield());

            if (!iMoneyMagUserManager.isCertification(user.getId())) {
                // 跳转到认证页
                return "views/management/h5/实名认证";
            } else {
                // 跳转到输入密码页面
                return "views/management/h5/交易密码";
            }
        }else {
            return "输入转入金额不对，请重新输入！";
        }
    }

    @RequestMapping(value = "/dq/certification")
    public String certification(HttpServletRequest request,String name,String idCard,String bankCard) {

        Users user = (Users) request.getSession().getAttribute(Constant.CURRENT_LOGIN_USER);
        if(user == null) {
            return null;
        }
        MoneyMagUser mUser = new MoneyMagUser();
        mUser.setBankCard(bankCard);
        mUser.setIdCard(idCard);
        mUser.setName(name);

        request.getSession().setAttribute(Constant.CERTIFICATION_INFO, mUser);
        // 跳转到输入密码页面
        return "views/management/h5/设置交易密码";
    }

    @RequestMapping(value = "/dq/confirmPwd")
    public String confirmPwd(HttpServletRequest request,String password) {
        Users user = (Users) request.getSession().getAttribute(Constant.CURRENT_LOGIN_USER);
        if(user == null) {
            return null;
        }

        if(!iMoneyMagUserManager.isCorrectPwd(user.getId(), password)) {
            // 返回密码错误信息
            return "密码错误，请重新输入！";
        }
        // 返回加入确认页面
        return "views/management/h5/确认加入活期宝";
    }

    @RequestMapping(value = "/dq/findPassword")
    public String findPassword(HttpServletRequest request,String mobile,String code,String newPwd) {

        Users user = (Users) request.getSession().getAttribute(Constant.CURRENT_LOGIN_USER);
        if(user == null) {
            return null;
        }
        MoneyMagUser maguser = iMoneyMagUserManager.findByUserId(user.getId());
        maguser.setTradingPassword(newPwd);

        iMoneyMagUserManager.save(maguser);
        // 跳转到输入密码页面
        return "views/management/h5/重置密码";
    }

}
