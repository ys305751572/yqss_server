package com.gcs.aol.controller.h5;

import com.gcs.aol.constant.Constant;
import com.gcs.aol.entity.*;
import com.gcs.aol.service.IMoneyMagDodManager;
import com.gcs.aol.service.IMoneyMagManager;
import com.gcs.aol.service.IMoneyMagUserManager;
import com.gcs.aol.service.IUsersManager;
import com.gcs.aol.service.h5.HQBaoService;
import com.gcs.aol.service.impl.MoneyMagManagerImpl;
import com.gcs.sysmgr.controller.GenericEntityController;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by caiqi on 2016/4/18 0018.
 */
@Controller
@RequestMapping(value = "/moneymag")
public class MoneyBuyHQManagerController extends GenericEntityController<MoneyMag, MoneyMag, MoneyMagManagerImpl> {

    public static final Integer user_id = 7;

    @Autowired
    private IMoneyMagUserManager iMoneyMagUserManager;

    @Autowired
    private IMoneyMagManager iMoneyMagManager;

    @Autowired
    private HQBaoService hqBaoService;

    @Autowired
    private IMoneyMagDodManager iMoneyMagDodManager;

    @Autowired
    private IUsersManager iUsersManager;

    @RequestMapping(value = "/hq/addIndex")
    public String addIndex() {
        return "management/h5/加入活期宝";
    }

    @RequestMapping(value = "/hq/addHQBao")
    public String addHQBao(HttpServletRequest request,Integer hqId, Double money, Double earnings) {

//        Users user = (Users) request.getSession().getAttribute(Constant.CURRENT_LOGIN_USER);
//        if(user == null) {
//            return null;
//        }
//        MoneyMag moneyMag = iMoneyMagManager.findByUserId(user_id);
        MoneyMagDod dod = iMoneyMagDodManager.findDueOnDemandDetail();

        earnings = money * dod.getYearYield() / 365;

        HQBao hqBao = null;
        if(hqId != null) {
            hqBao = hqBaoService.getbyid(hqId);
        }else {
            hqBao = new HQBao();
        }

        if(money <= dod.getResidue())
        {
            hqBao.setMoney(money);
            hqBao.setEarning(earnings);

            if(!iMoneyMagUserManager.isCertification(user_id)) {
                // 跳转到认证页
                return "management/h5/实名认证";
            }else {
                // 跳转到输入密码页面
                return "management/h5/交易密码";
            }
        }else {
            return "输入投资金额不对，请重新输入！";
        }
    }

    @RequestMapping(value = "/hq/certification")
    public String certification(HttpServletRequest request,String name,String idCard,String bankCard) {

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
    public String confirmPwd(HttpServletRequest request,String password) {
        /*Users user = (Users) request.getSession().getAttribute(Constant.CURRENT_LOGIN_USER);
        if(user == null) {
            return null;
        }*/


        if(!iMoneyMagUserManager.isCorrectPwd(user_id, password)) {
            // 返回密码错误信息
            return "密码错误，请重新输入！";
        }
        // 返回加入确认页面
        return "management/h5/确认加入活期宝";
    }

    @RequestMapping(value = "/hq/findPassword")
    public String findPassword(HttpServletRequest request,String mobile,String code,String password) {

        /*Users user = (Users) request.getSession().getAttribute(Constant.CURRENT_LOGIN_USER);
        if(user == null) {
            return null;
        }*/
        MoneyMagUser mUser = (MoneyMagUser) request.getSession().getAttribute(Constant.CERTIFICATION_INFO);
//        MoneyMagUser _user = iMoneyMagUserManager.findByUserId(user_id);
        mUser.setTradingPassword(password);

        Users user = (Users) (iUsersManager.queryByProperty("userId",mUser.getUser().getUserId()).get(0));
        mUser.setUser(user);

        iMoneyMagUserManager.save(mUser);
        // 跳转到输入密码页面
        return "management/h5/输入交易密码";
    }

}
