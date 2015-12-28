package com.gcs.aol.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gcs.aol.entity.Video;
import com.gcs.aol.service.IVideoManager;
import com.gcs.aol.service.impl.VideoManagerImpl;
import com.gcs.sysmgr.controller.GenericEntityController;
import com.gcs.sysmgr.entity.MsgJsonReturn;
import com.gcs.sysmgr.vo.PageParameters;
import com.gcs.utils.DataTableReturnObject;
import com.gcs.utils.JSONParam;
import com.gcs.utils.JSONResponse;
import com.gcs.utils.PageUtil;

@RequestMapping("/management/video/")
@Controller
public class VideoController extends GenericEntityController<Video, Video, VideoManagerImpl>{

	public static final String VIDEO_LIST_PAGE = "management/aol/videoMgr/video-list";
	public static final String VIDEO_EDIT_PAGE = "management/aol/videoMgr/video-edit";
	public static final String VIDEO_DETAIL_PAGE = "management/aol/videoMgr/video-detail";
	
	@Autowired
	private IVideoManager manager;
	
	@RequestMapping(value="listPage", method = RequestMethod.GET)
	public String listPage() {
		return VIDEO_LIST_PAGE;
	}
	
	@RequestMapping(value="editPage", method = RequestMethod.GET)
	public String editPage(@RequestParam(value="id", required=false) Integer id,Model model) {
		
		if(id != null) {
			Video video = manager.queryByPK(id);
			model.addAttribute("video", video);
		}
		return VIDEO_EDIT_PAGE;
	}
	
	@RequestMapping(value="detailPage", method = RequestMethod.GET)
	public String detailPage(Integer id,Model model) {
		
		Video video = manager.queryByPK(id);
		model.addAttribute("video", video);
		return VIDEO_DETAIL_PAGE;
	}
	
	@RequestMapping(value="findAll", method = RequestMethod.POST)
	@ResponseBody
	public JSONResponse findAll(@RequestParam JSONParam[] params) {
		
		HashMap<String, String> paramMap = (HashMap<String, String>) convertToMap(params);
		String sortStr = paramMap.get("bbSortName");
		PageParameters pp = PageUtil.getParameter(paramMap, sortStr);
		
		Page<Video> page = manager.query(null, pp);
		return successed(new DataTableReturnObject(page.getTotalElements(), page.getTotalElements(), pp.getSEcho(), page.getContent()));
	}
	
	@RequestMapping(value="edit", method = RequestMethod.POST)
	@ResponseBody
	public MsgJsonReturn edit(Video video) {
		
		manager.save(video);
		return new MsgJsonReturn(true, video.getId() == null ? "新增成功": "编辑成功");
	}
	
	@RequestMapping(value="delete", method = RequestMethod.POST)
	@ResponseBody
	public MsgJsonReturn delete(Integer id) {
		manager.deleteByPK(id);
		return new MsgJsonReturn(true, "删除成功");
	}
}