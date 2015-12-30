<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en">
<head>
<meta charset="utf-8" />
<title></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<%@ include file="/WEB-INF/views/scripts.jsp"%>
<script src="${contextPath}/resources/bootstrap/js/bootstrap3-validation.js"></script>
<link rel="stylesheet" type="text/css" href="${contextPath}/jquery-easyui-1.3.3/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css" href="${contextPath}/jquery-easyui-1.3.3/themes/icon.css" />
<script type="text/javascript" src="${contextPath}/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>

<script type="text/javascript" src="${contextPath}/resources/upload/js/uploadPreview.min.js"></script>
<script type="text/javascript" src="${contextPath}/resources/upload/js/ajaxfileupload.js"></script>

<!-- The styles -->
<style type="text/css">
	body {
		padding-bottom: 10px;
		padding-top: 0px;
	}
	.icon-briefcase {
		background-position: -432px -142px;
		padding-right: 0px;
		background-image: url("${contextPath}/resources/img/glyphicons-halflings.png");
	}
	.icon-user {
		background-position: -168px 2px;
		padding-right: 0px;
		background-image: url("${contextPath}/resources/img/glyphicons-halflings.png");
	}
	
	.tree-expanded, .tree-collapsed, .tree-checkbox, .tree-indent {
		display: inline-block;
		width: 14px;
		height: 18px;
		vertical-align: top;
		overflow: hidden;
	}
</style>
		
<script type="text/javascript">
$(function(){
	$("form").validation({icon:true});
});


function doSubmit() {
	
	if($("#productForm").valid(this) == false) {
		return false;
	}		
	$("form").submit();
}
</script>
</head>
<body>
<div class="container-fluid">
	<div class="row-fluid">
		<div id="content" class="span12">
			<div class="row-fluid z-ulnone">
				<form class="form-horizontal" method="post" id="productForm" name="doctorForm" action="${contextPath}/management/hr/edit" enctype="multipart/form-data">
					<!--box span12 start-->
					<div class="box span12" style="height: auto;">
						<div class="box-header well z-h2">
							<h2><i class="icon-film"></i> 新增/编辑招聘信息</h2>
							<jsp:include page="/WEB-INF/views/backDiv.jsp" flush="true">
								<jsp:param name="url" value="${contextPath}/management/hr/listPage"/>
							</jsp:include>
						</div>
						
						<!--box-content start-->
						<div class="box-content">
							
							<!--z-informa2 start-->
							<div class="z-informa2" style="margin-bottom: 10px;">
								<table>
									<input type="hidden" id="id" name="id" value="${hr.id}">
									<tr>
										<td>
											<div class="control-group">
											  <label class="control-label" style="width:60px;" for="title">招聘标题</label>
											  <div class="controls" style="margin-left: 80px;">
											  	  <input type="text" id="title" name="title" value="${hr.title}" style="width:600px;" placeholder="请填写招聘标题" maxlength="1000" check-type="required" required-message="请输入招聘标题"/>
											  </div>
											</div>
										</td>
									</tr>
									<tr>
										<td>
											<div class="control-group">
											  <label class="control-label" style="width:60px;" for="content">招聘内容</label>
											  <div class="controls" style="margin-left: 80px;">
											  	  <input type="text" id="content" name="content" value="${hr.content}" style="width:600px;" placeholder="请填写招聘内容" maxlength="1000" check-type="required" required-message="请输入招聘内容"/>
											  </div>
											</div>
										</td>
									</tr>
									<tr>
										<td>
											<div class="control-group">
											  <label class="control-label" style="width:60px;" for="salaryMin">薪资下限</label>
											  <div class="controls" style="margin-left: 80px;">
											  	  <input type="text" id="salaryMin" name="salaryMin" value="${hr.salaryMin}" style="width:600px;" placeholder="请填写招聘薪资(下限)" maxlength="1000" check-type="required number" required-message="请输入招聘薪资(下限)"/>
											  </div>
											</div>
										</td>
									</tr>
									<tr>
										<td>
											<div class="control-group">
											  <label class="control-label" style="width:60px;" for="salaryMax">薪资上限</label>
											  <div class="controls" style="margin-left: 80px;">
											  	  <input type="text" id="salaryMax" name="salaryMax" value="${hr.salaryMax}" style="width:600px;" placeholder="请填写招聘薪资(上限)" maxlength="1000" check-type="required number" required-message="请输入招聘薪资(上限)"/>
											  </div>
											</div>
										</td>
									</tr>
									<tr>
										<td>
											<div class="control-group">
											  <label class="control-label" style="width:60px;" for="mobile">手机</label>
											  <div class="controls" style="margin-left: 80px;">
											  	  <input type="text" id="mobile" name="mobile" value="${hr.mobile}" style="width:600px;" placeholder="请填写手机" maxlength="1000" check-type="required" required-message="请输入手机"/>
											  </div>
											</div>
										</td>
									</tr>
									<tr>
										<td>
											<div class="control-group">
											  <label class="control-label" style="width:60px;" for="address">地址</label>
											  <div class="controls" style="margin-left: 80px;">
											  	  <input type="text" id="address" name="address" value="${hr.address}" style="width:600px;" placeholder="请填写地址" maxlength="1000" check-type="required" required-message="请输入地址"/>
											  </div>
											</div>
										</td>
									</tr>
									<tr>
										<td>
											<div class="control-group">
											  <label class="control-label" style="width:60px;" for="company">公司名称</label>
											  <div class="controls" style="margin-left: 80px;">
											  	  <input type="text" id="company" name="company" value="${hr.company}" style="width:600px;" placeholder="请填写公司名称" maxlength="1000" check-type="required" required-message="请输入公司名称"/>
											  </div>
											</div>
										</td>
									</tr>
									<tr>
										<td>
											<div class="control-group">
											  <label class="control-label" style="width:60px;" for="position">职位</label>
											  <div class="controls" style="margin-left: 80px;">
											  	  <input type="text" id="position" name="position" value="${hr.position}" style="width:600px;" placeholder="请填写应聘职位" maxlength="1000" check-type="required" required-message="请输入应聘职位"/>
											  </div>
											</div>
										</td>
									</tr>
									<tr>
										<td>
											<div class="control-group">
											<label class="control-label"  style="width:60px;" for="counts">招聘人数</label>
											<div class="controls" style="margin-left: 80px;">
											  	 <input type="text" id="counts" name="counts" value="${hr.counts}" style="width:600px;" placeholder="请填写招聘人数" maxlength="1000" check-type="required" required-message="请输入招聘人数"/>
											</div>
										</td>
									</tr>
									<tr>
										<td>
											<div class="control-group">
												<div class="controls" style="margin-left: 20px;">
													<input type="hidden" id="saveORsend" name="saveORsend" value=""/>
													<button id="btnSendBottom" name="btnSendBottom" type="button" onclick="doSubmit();" class="btn btn-primary">保存</button>
												</div>
											</div>
										</td>
									</tr>
								</table>
							</div><!--z-informa2 end-->
						</div><!--box-content end-->
					</div><!--box span12 end-->
				</form>
				<iframe src="" name="temp_upload_frame" id="temp_upload_frame" style="display: none;"></iframe>
			</div>
		</div>
	</div>
</div>
</body>
</html>

