package com.gcs.aol.controller.h5;

import com.gcs.aol.entity.MoneyMag;
import com.gcs.aol.entity.MoneyMagCommon;
import com.gcs.aol.entity.MoneyMagDod;
import com.gcs.aol.service.*;
import com.gcs.aol.service.impl.MoneyMagManagerImpl;
import com.gcs.sysmgr.controller.GenericEntityController;
import com.gcs.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lili on 2016/4/18 0018.
 */
@RequestMapping(value = "/moneymag")
@Controller
public class MoneyInfoManagerController extends GenericEntityController<MoneyMag, MoneyMag, MoneyMagManagerImpl> {

    @Autowired
    private IMoneyMagManager moneyMagManager;

    @Autowired
    private IMoneyMagDodManager moneyMagDodManager;

    @Autowired
    private IMoneyMagCommonManager moneyMagCommonManager;

    /**
     * 关于理财
     * @param type
     * @return
     */
    @RequestMapping(value = "/about")
    @ResponseBody
    public Result about(Integer type) {

        MoneyMagCommon common = moneyMagCommonManager.findByType(type);
        return Result.success(common);
    }

    /**
     * 理财首页
     * @return
     */
    @RequestMapping(value = "/index")
    public String moneymagIndex(Model model) {

        Map<String,MoneyMag> map = new HashMap<String,MoneyMag>();
        List<MoneyMag> list = moneyMagManager.queryAll();
        for (MoneyMag moneyMag : list) {
            if(moneyMag.getType() == 1) {
                map.put("hq", moneyMag);
            }
            else {
                map.put("dq", moneyMag);
            }
        }
        model.addAttribute("map",map);
        return "management/h5/理财";
    }

    /**
     * 活期宝详情
     * @return
     */
    @RequestMapping(value = "/hq/detail")
    @ResponseBody
    public Result hQDetail(Model model) {

        MoneyMagDod dod = moneyMagDodManager.findDueOnDemandDetail();
        model.addAttribute("dod",dod);
        return Result.success(dod);
    }

    /**
     * 定期宝列表
     * @return
     */
    @RequestMapping(value = "/dq/list")
    @ResponseBody
    public Result dQList() {

        List<MoneyMagDod> list = moneyMagDodManager.findFixPeriodList();
        return Result.success(list);
    }

    /**
     * 定期详情
     * @param id
     * @return
     */
    @RequestMapping(value = "/dq/detail")
    @ResponseBody
    public Result dQDetail(Integer id) {
        MoneyMagDod dod = moneyMagDodManager.findFixPeriodDetail(id);
        return Result.success(dod, "dod");
    }
}
