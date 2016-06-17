package com.gcs.aol.controller;

import com.gcs.aol.entity.AolUser;
import com.gcs.aol.service.IAolUserManager;
import com.gcs.aol.service.IUsersManager;
import com.gcs.sysmgr.SecurityConstants;
import com.gcs.sysmgr.vo.LoginUserVO;
import com.gcs.utils.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gcs.aol.entity.Commons;
import com.gcs.aol.service.ICommonsManager;
import com.gcs.aol.service.impl.CommonsManagerImpl;
import com.gcs.sysmgr.controller.GenericEntityController;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/management/commons")
@Controller
public class CommonsController extends GenericEntityController<Commons, Commons, CommonsManagerImpl> {

    public static final String COMMONS_EDIT_PAGE = "management/aol/commonMgr/common-edit";
    public static final String COMMONS_DETAIL_PAGE = "management/aol/commonMgr/common-detail";

    @Autowired
    private ICommonsManager manager;

    @Autowired
    private IAolUserManager aolUserManager;

    @RequestMapping(value = "editPage", method = RequestMethod.GET)
    public String editPage(Model model) {

        Commons common = manager.queryAll().get(0);
        model.addAttribute("common", common);
        return COMMONS_EDIT_PAGE;
    }

    @RequestMapping(value = "detailPage", method = RequestMethod.GET)
    public String detailPage(Model model) {

        Commons common = manager.queryAll().get(0);
        model.addAttribute("common", common);
        return COMMONS_DETAIL_PAGE;
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String edit(Commons commons, Model model) {

        manager.save(commons);
        model.addAttribute("common", commons);
        return COMMONS_DETAIL_PAGE;
    }

    @RequestMapping(value = "password", method = RequestMethod.GET)
    public String pwdPage() {
        return "management/aol/commonMgr/pwd-edit";
    }

    @RequestMapping(value = "savePwd", method = RequestMethod.POST)
    @ResponseBody
    public Result savePwd(String oldPwd, String newPwd, HttpServletRequest request) {

        LoginUserVO user = (LoginUserVO) request.getSession().getAttribute(SecurityConstants.LOGIN_USER);
        AolUser aolUser = aolUserManager.queryByPK(user.getUserId());
        if(StringUtils.isNotBlank(oldPwd) && StringUtils.isNotBlank(aolUser.getPassword()) && aolUser.getPassword().equals(oldPwd)) {
            aolUser.setPassword(newPwd);
            aolUserManager.save(aolUser);
        }
        else {
            return Result.failure("原密码错误");
        }
        return Result.success();
    }
}
