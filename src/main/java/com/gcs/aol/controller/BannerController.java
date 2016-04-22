package com.gcs.aol.controller;

import com.gcs.aol.entity.Ads;
import com.gcs.aol.entity.Bank;
import com.gcs.aol.entity.Banner;
import com.gcs.aol.service.IBannerManager;
import com.gcs.aol.service.impl.BannerManagerImpl;
import com.gcs.aol.utils.CommonUtils;
import com.gcs.sysmgr.controller.GenericEntityController;
import com.gcs.sysmgr.entity.Attach;
import com.gcs.sysmgr.entity.MsgJsonReturn;
import com.gcs.sysmgr.vo.PageParameters;
import com.gcs.utils.DataTableReturnObject;
import com.gcs.utils.JSONParam;
import com.gcs.utils.JSONResponse;
import com.gcs.utils.PageUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by Administrator on 2016/4/22.
 */
@RequestMapping(value = "/management/banner")
@Controller
public class BannerController extends GenericEntityController<Banner,Banner,BannerManagerImpl>{

    @Autowired
    public IBannerManager manager;

    @RequestMapping(value = "/index")
    public String index() {
        return "management/aol/bannerMgr/list";
    }

    @RequestMapping(value="list", method = RequestMethod.POST)
    @ResponseBody
    public JSONResponse list(@RequestBody JSONParam[] params) {

        HashMap<String, String> paramMap = (HashMap<String, String>) convertToMap(params);
        String sortStr = paramMap.get("bbSortName");
        PageParameters pp = PageUtil.getParameter(paramMap, sortStr);

        Banner banner = new Banner();
        if(StringUtils.isNotBlank(paramMap.get("title"))) {
            banner.setTitle(paramMap.get("title"));
        }
        if(StringUtils.isNotBlank(paramMap.get("isList"))) {
            banner.setIsList(Integer.parseInt(paramMap.get("isList")));
        }
        Page<Banner> page = manager.findPage(banner,pp.getStart(),pp.getLength());
        return successed(new DataTableReturnObject(page.getTotalElements(), page.getTotalElements(), pp.getSEcho(), page.getContent()));
    }

    @RequestMapping(value = "/add")
    public String add(Integer id, Model model) {

        if(id != null) {
            model.addAttribute("banner",manager.queryByPK(id));
        }
        return "management/aol/bannerMgr/add";
    }

    @RequestMapping(value = "/save")
    public String save(Banner banner, MultipartHttpServletRequest fileRequest, HttpServletRequest request) {
        if(banner.getId() != null) {
            banner = manager.queryByPK(banner.getId());
        }
        saveImages(banner,fileRequest,request);
        banner.setIsList(0);
        manager.save(banner);
        return "management/aol/bannerMgr/list";
    }

    private void saveImages(Banner banner, MultipartHttpServletRequest fileRequest, HttpServletRequest request) {
        Iterator<String> fileNames = fileRequest.getFileNames();
        String webRoot = request.getServletContext().getRealPath("");

        while (fileNames.hasNext()) {
            String fileName = fileNames.next();
            MultipartFile file = fileRequest.getFile(fileName);
            Attach attach = CommonUtils.uploadAttach(file, webRoot,"/upload/pi", null);
            if(attach != null) {
                banner.setPath(attach.getBak1());
            }
        }
    }

    /**
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/detail")
    public String detail(Integer id,Model model) {
        model.addAttribute("banner",manager.queryByPK(id));
        return "management/aol/bannerMgr/detail";
    }

    @RequestMapping(value = "/publish")
    @ResponseBody
    public MsgJsonReturn publish(Integer id,Integer isList) {
        try {
            Banner banner = manager.queryByPK(id);
            banner.setIsList(isList);

            manager.save(banner);
            return  new MsgJsonReturn(true, banner.getIsList() == 1 ? "发布成功":"下架成功");
        } catch (Exception e) {
            e.printStackTrace();
            return  new MsgJsonReturn(false, "操作失败");
        }
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public MsgJsonReturn delete(Integer id) {
        try {
            Banner banner = manager.queryByPK(id);
            manager.delete(banner);
            return  new MsgJsonReturn(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return  new MsgJsonReturn(false, "删除失败");
        }
    }
}
