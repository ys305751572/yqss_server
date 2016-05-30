package com.gcs.aol.controller.h5;

import com.gcs.aol.entity.ActEntity;
import com.gcs.aol.entity.Ads;
import com.gcs.aol.service.ActService;
import com.gcs.aol.service.impl.ActServiceImpl;
import com.gcs.sysmgr.controller.GenericEntityController;
import com.gcs.sysmgr.vo.PageParameters;
import com.gcs.utils.DataTableReturnObject;
import com.gcs.utils.JSONParam;
import com.gcs.utils.JSONResponse;
import com.gcs.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

/**
 * Created by Administrator on 2016/5/27.
 */
@Controller
@RequestMapping(value = "/management/act")
public class ActController extends GenericEntityController<ActEntity,ActEntity,ActServiceImpl>{

    @Autowired
    public ActService manager;

    @RequestMapping(value = "/index")
    public String index() {
        return "management/act/list";
    }

    @RequestMapping(value="/list", method = RequestMethod.POST)
    @ResponseBody
    public JSONResponse findAll(@RequestBody JSONParam[] params) {
        HashMap<String, String> paramMap = (HashMap<String, String>) convertToMap(params);
        String sortStr = paramMap.get("bbSortName");
        PageParameters pp = PageUtil.getParameter(paramMap, sortStr);
        Page<ActEntity> page = manager.findPage(paramMap.get("mobile"), pp.getStart(), pp.getLength());
        return successed(new DataTableReturnObject(page.getTotalElements(), page.getTotalElements(), pp.getSEcho(), page.getContent()));
    }
}
