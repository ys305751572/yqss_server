package com.gcs.aol.controller.h5;

import com.gcs.aol.entity.MoneyMag;
import com.gcs.aol.entity.MoneyMagCommon;
import com.gcs.aol.entity.MoneyMagDod;
import com.gcs.aol.service.*;
import com.gcs.aol.service.impl.MoneyMagManagerImpl;
import com.gcs.sysmgr.controller.GenericEntityController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lili on 2016/4/18 0018.
 *
 */
@RequestMapping(value = "moneymag")
@Controller
public class MoneyInfoManagerController extends GenericEntityController<MoneyMag, MoneyMag, MoneyMagManagerImpl> {

    @Autowired
    private IMoneyMagManager moneyMagManager;

    @Autowired
    private IMoneyMagDodManager moneyMagDodManager;

    @Autowired
    private IMoneyMagCommonManager moneyMagCommonManager;

    public static final Integer user_id = 6;

    /**
     * 关于理财
     * @param type
     * @return
     */
    @RequestMapping(value = "/about")
    public String about(Integer type,Model model) {

        MoneyMagCommon common = moneyMagCommonManager.findByType(1);
        model.addAttribute("common",common);
        return "management/h5/关于财蜜理财";
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
    public String hQDetail(Model model) {

        MoneyMagDod dod = moneyMagDodManager.findDueOnDemandDetail();
//        List<MoneyMagDod> list = moneyMagDodManager.queryAll();
//        for (MoneyMagDod moneyMagDod : list) {
//            if(moneyMagDod.getType() == 1) {
//                dod.put("hq", moneyMagDod);
//            }
//            else {
//                dod.put("dq", moneyMagDod);
//            }
//        }
        model.addAttribute("dod",dod);
        return "management/h5/活期宝";
    }

    /**
     * 定期宝列表
     * @return
     */
    @RequestMapping(value = "/dq/list")
    public String dQList(Model model) {

        List<MoneyMagDod> list = moneyMagDodManager.findFixPeriodList();
        model.addAttribute("list",list);
        return "management/h5/定期宝list";
    }

    /**
     * 定期详情
     * @param id
     * @return
     */
    @RequestMapping(value = "/dq/detail")
    public String dQDetail(Integer id,Model model) {
        MoneyMagDod dod = moneyMagDodManager.findFixPeriodDetail(id);
        model.addAttribute("dod",dod);
        return "management/h5/定期宝";
    }


}

