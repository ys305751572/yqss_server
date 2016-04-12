package com.gcs.utils;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.gcs.aol.constant.ErrorCode;
import com.gcs.sysmgr.page.Page;

/**
 * 
 * 
 * @author yesong
 *
 */
public final class Result {

	private Integer status;                                           // 状态 0:成功 1:失败（异常）
	private String msg;                                               // 错误信息
	private Map<String, Object> data = new HashMap<String,Object>();  // 数据内容

	private static final int SUCCESS = 0;                             // 成功
	private static final int ERROR = 1;                               // 失败(异常)
	
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	public static Result success() {
		Result result = new Result();
		result.status = SUCCESS;  
		result.msg = "";
		return result;
	}

	@SuppressWarnings({ "rawtypes" })
	public static Result success(Object data,String... args) {
		if(data == null) {
			Map<String,Object> map = new HashMap<String,Object>();
			data = map;
		}
		Result result = new Result();
		result.status = SUCCESS;
		result.msg = "";
		if(data instanceof List) {
			result.data.put("list", BeanUtils.listToMap((List)data));
		}
		else if(data instanceof Page) {
			Page page = (Page) data;
			List list = (List) page.getRows();
			Map<String, Object> pagemap = new HashMap<String, Object>();
            pagemap.put("totalNum", page.getTotal());
            pagemap.put("totalPage", page.getPageCount());
            pagemap.put("currentPage", page.getCurrent());
            result.data.put("page", pagemap);
            result.data.put("list", BeanUtils.listToMap(list));
		}
		else if(data instanceof Map) {
			String name = "";
			if(args == null || args.length == 0) {
				name = "object";
			}
			else {
				name = args[0];
			}
			result.data.put(name, data);
		}
		else {
			String objName = data.getClass().getSimpleName().toLowerCase();
			result.data.put(objName, BeanUtils.beanToMap(data));
		}
		return result;
	}
	
	public static Result failure(String... errorMessage) {
		Result result = new Result();
		result.status = ERROR;
		if(errorMessage == null || errorMessage.length == 0) {
			result.msg = ErrorCode.ERROR_01;
		} 
		else {
			result.msg = errorMessage[0];
		}
		return result;
	}
}
