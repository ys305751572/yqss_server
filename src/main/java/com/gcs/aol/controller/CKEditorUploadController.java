package com.gcs.aol.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.gcs.aol.utils.CommonUtils;
import com.gcs.sysmgr.entity.Attach;

@RequestMapping("/cKEditorUpload/")
@Controller
public class CKEditorUploadController {

	@RequestMapping(value="upload")
	public String upload(@RequestParam(value="upload", required=false) MultipartFile file,HttpServletRequest request,HttpServletResponse resp) {
		
		PrintWriter out = null;
		try {
			out = resp.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		 String callback = request.getParameter("CKEditorFuncNum");  
		
		String webRoot = request.getSession().getServletContext().getRealPath("");
		Attach attach = CommonUtils.uploadAttach(file, webRoot, "/upload/pro", null);
		
		 // 返回"图像"选项卡并显示图片  request.getContextPath()为web项目名   
		String scheme = request.getScheme();
		String host = request.getServerName();
		int port = request.getServerPort();
		String absPath = scheme + "://" + host + (port != 80 ? ":" + port : "") + "/upload/pro/" + attach.getAttachName();
		
		 // 返回"图像"选项卡并显示图片  request.getContextPath()为web项目名   
        out.println("<script type=\"text/javascript\">");  
        out.println("window.parent.CKEDITOR.tools.callFunction(" + callback  
                + ",'" + absPath + "','')");  
        out.println("</script>");  
		return null;
	}
}
