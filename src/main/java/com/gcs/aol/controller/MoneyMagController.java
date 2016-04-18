//package com.gcs.aol.controller;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.gcs.aol.constant.Constant;
//import com.gcs.aol.entity.MoneyMag;
//import com.gcs.aol.entity.MoneyMagCommon;
//import com.gcs.aol.entity.MoneyMagDod;
//import com.gcs.aol.entity.MoneyMagUser;
//import com.gcs.aol.entity.Users;
//import com.gcs.aol.service.IMoneyMagCommonManager;
//import com.gcs.aol.service.IMoneyMagDodManager;
//import com.gcs.aol.service.IMoneyMagManager;
//import com.gcs.aol.service.IMoneyMagTRManager;
//import com.gcs.aol.service.IMoneyMagUserManager;
//import com.gcs.aol.service.impl.MoneyMagManagerImpl;
//import com.gcs.sysmgr.controller.GenericEntityController;
//import com.gcs.utils.Result;
//
//@RequestMapping(value = "/moneymag")
//public class MoneyMagController extends GenericEntityController<MoneyMag, MoneyMag, MoneyMagManagerImpl>{
//
//	@Autowired
//	private IMoneyMagManager moneyMagManager;
//
//	@Autowired
//	private IMoneyMagTRManager moneyMagTrManager;
//
//	@Autowired
//	private IMoneyMagUserManager moneyMagUserManager;
//
//	@Autowired
//	private IMoneyMagDodManager moneyMagDodManager;
//
//	@Autowired
//	private IMoneyMagCommonManager moneyMagCommonManager;
//
//	/**
//	 * 关于理财
//	 * @param type
//	 * @return
//	 */
//	@RequestMapping(value = "/about")
//	@ResponseBody
//	public Result about(Integer type) {
//
//		MoneyMagCommon common = moneyMagCommonManager.findByType(type);
//		return Result.success(common);
//	}
//
//	/**
//	 * 理财首页
//	 * @return
//	 */
//	@RequestMapping(value = "/index")
//	@ResponseBody
//	public Result moneymagIndex(Model model) {
//
//		Map<String,MoneyMag> map = new HashMap<String,MoneyMag>();
//		List<MoneyMag> list = moneyMagManager.queryAll();
//		for (MoneyMag moneyMag : list) {
//			if(moneyMag.getType() == 1) {
//				map.put("hq", moneyMag);
//			}
//			else {
//				map.put("dq", moneyMag);
//			}
//		}
//		model.addAttribute("map",map);
//		return Result.success(map);
//	}
//
//	/**
//	 * 活期宝详情
//	 * @return
//	 */
//	@RequestMapping(value = "/hq/detail")
//	@ResponseBody
//	public Result hQDetail() {
//
//		MoneyMagDod dod = moneyMagDodManager.findDueOnDemandDetail();
//		return Result.success(dod);
//	}
//
//	/**
//	 * 定期宝列表
//	 * @return
//	 */
//	@RequestMapping(value = "/dq/list")
//	@ResponseBody
//	public Result dQList() {
//
//		List<MoneyMagDod> list = moneyMagDodManager.findFixPeriodList();
//		return Result.success(list);
//	}
//
//	/**
//	 * 定期详情
//	 * @param id
//	 * @return
//	 */
//	@RequestMapping(value = "/dq/detail")
//	@ResponseBody
//	public Result dQDetail(Integer id) {
//		MoneyMagDod dod = moneyMagDodManager.findFixPeriodDetail(id);
//		return Result.success(dod, "dod");
//	}
//
//	/**
//	 * 加入活期宝
//	 * @param money
//	 * @param earnings
//	 * @return
//	 */
//	@RequestMapping(value = "/hq/join")
//	public String join(HttpServletRequest request,Double money,Double earnings) {
//
//		Users user = (Users) request.getSession().getAttribute(Constant.CURRENT_LOGIN_USER);
//		if(user == null) {
//			return null;
//		}
//		if(!moneyMagUserManager.isCertification(user.getId())) {
//			// 跳转到认证页
//			return null;
//		}
//		// 跳转到输入密码页面
//		return null;
//	}
//
//	@RequestMapping(value = "/hq/certification")
//	public String certification(HttpServletRequest request,String name,String idCard,String bankCard) {
//
//		Users user = (Users) request.getSession().getAttribute(Constant.CURRENT_LOGIN_USER);
//		if(user == null) {
//			return null;
//		}
//		MoneyMagUser mUser = new MoneyMagUser();
//		mUser.setBankCard(bankCard);
//		mUser.setIdCard(idCard);
//		mUser.setName(name);
//
//		request.getSession().setAttribute(Constant.CERTIFICATION_INFO, mUser);
//		// 跳转到输入密码页面
//		return null;
//	}
//
//	/**
//	 * 确认密码
//	 * @param request
//	 * @param password
//	 * @return
//	 */
//	@RequestMapping(value = "/hq/confirmPwd")
//	public String confirmPwd(HttpServletRequest request,String password) {
//		Users user = (Users) request.getSession().getAttribute(Constant.CURRENT_LOGIN_USER);
//		if(user == null) {
//			return null;
//		}
//
//		if(!moneyMagUserManager.isCorrectPwd(user.getId(), password)) {
//			// 返回密码错误信息
//			return null;
//		}
//		// 返回加入确认页面
//		return null;
//	}
//
//	@RequestMapping(value = "/hq/findPassword")
//	public String findPassword(HttpServletRequest request,String mobile,String code,String newPwd) {
//
//		Users user = (Users) request.getSession().getAttribute(Constant.CURRENT_LOGIN_USER);
//		if(user == null) {
//			return null;
//		}
//		MoneyMagUser _user = moneyMagUserManager.findByUserId(user.getId());
//		_user.setTradingPassword(newPwd);
//
//		moneyMagUserManager.save(_user);
//		// 跳转到输入密码页面
//		return null;
//	}
//}
