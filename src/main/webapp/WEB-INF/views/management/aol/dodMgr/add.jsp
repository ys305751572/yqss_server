<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<%@page import="com.ckeditor.CKEditorConfig"%>
<%@ taglib uri="http://ckeditor.com" prefix = "ckeditor" %>
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
	<script type="text/javascript">
		var basePath = '${contextPath}';
	</script>
<script type="text/javascript" src="${contextPath}/resources/upload/js/uploadPreview.min.js"></script>
<script type="text/javascript" src="${contextPath}/resources/upload/js/ajaxfileupload.js"></script>
	<script type="text/javascript" src="${contextPath}/ckeditor/ckeditor.js"></script>
	<%
		CKEditorConfig settings = new CKEditorConfig();
		settings.addConfigValue("image_previewText"," ");
		settings.addConfigValue("width","880px");
		settings.addConfigValue("height","260px");
	%>

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

	var content = "${dod}";

	if(content){
		CKEDITOR.instances.detail.setData("${dod.content}");
	}
});


function doSubmit() {

	var content = CKEDITOR.instances.detail.getData();

	if($("#productForm").valid(this) == false) {
		return false;
	}
	$.post("${contextPath}/management/dod/save",
		{"id":$("#id").val(),
		 "name" : $("#name").val(),
		 "month" : $("#month").val(),
		 "type" : $("#type").val(),
		 "introduce" : $("#introduce").val(),
		 "yearYield" : $("#yearYield").val(),
		 "moneyLimit" : $("#moneyLimit").val(),
		 "content" : content
		},function(result) {
			if(result.success) {
				window.location.href = "${contextPath}/management/dod/index";
			}
			else {
				jAlert(result.msg,'提示');
			}
		}
	);
}
</script>
</head>
<body>
<div class="container-fluid">
	<div class="row-fluid">
		<div id="content" class="span12">
			<div class="row-fluid z-ulnone">
				<form class="form-horizontal" method="post" id="productForm" name="doctorForm" action="" enctype="multipart/form-data">
					<!--box span12 start-->
					<div class="box span12" style="height: auto;">
						<div class="box-header well z-h2">
							<h2><i class="icon-film"></i> 新增/编辑</h2>
							<jsp:include page="/WEB-INF/views/backDiv.jsp" flush="true">
								<jsp:param name="url" value="${contextPath}/management/dod/index"/>
							</jsp:include>
						</div>
						
						<!--box-content start-->
						<div class="box-content">
							
							<!--z-informa2 start-->
							<div class="z-informa2" style="margin-bottom: 10px;">
								<table>
									<input type="hidden" id="id" name="id" value="${dod.id}">
									<tr>
										<td>
											<div class="control-group">
											  <label class="control-label" style="width:60px;" for="month">月份</label>
											  <div class="controls" style="margin-left: 80px;">
											  	  <input type="text" id="month" name="month" value="${dod.month}"  style="width:600px;" placeholder="请填写月份" maxlength="1000" check-type="required" required-message="请输入月份"/>
											  </div>
											</div>
										</td>
									</tr>
									<tr>
										<td>
											<div class="control-group">
											  <label class="control-label" style="width:60px;" for="type">类型</label>
											  <div class="controls" style="margin-left: 80px;">
												<select id="type" name="type"  style="width: 120px;">
													<option <c:if test="${dod.type eq 2}"> selected="selected" </c:if> value="2">定期宝</option>
													<option <c:if test="${dod.type eq 1}"> selected="selected" </c:if> value="1">活期宝</option>
												</select>
											</div>
											</div>
										</td>
									</tr>
									<tr>
										<td>
											<div class="control-group">
											  <label class="control-label" style="width:60px;" for="yearYield">年华收益率</label>
											  <div class="controls" style="margin-left: 80px;">
											  	  <input type="text" id="yearYield" name="yearYield" value="${dod.yearYield}" style="width:600px;" placeholder="请填写年华收益率" maxlength="1000" check-type="required" required-message="请填写年华收益率"/>
											  </div>
											</div>
										</td>
									</tr>
									<tr>
										<td>
											<div class="control-group">
											  <label class="control-label" style="width:60px;" for="moneyLimit">最大限额</label>
											  <div class="controls" style="margin-left: 80px;">
											  	  <input type="text" id="moneyLimit" name="moneyLimit" value="${dod.moneyLimit}" style="width:600px;" placeholder="请填写年期限" maxlength="1000" check-type="required" required-message="请填写年期限"/>
											  </div>
											</div>
										</td>
									</tr>
									<tr>
										<td>
											<div class="control-group">
												<label class="control-label" style="width:60px;" for="detail">商品介绍</label>
												<div class="controls" style="margin-left: 80px">
													<textarea id="detail" name="detail" rows="25"></textarea>
													<ckeditor:replace replace="detail" basePath="${contextPath}/ckeditor/"  config="<%=settings%>"></ckeditor:replace>
												</div>
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
			</div>
		</div>
	</div>
</div>
</body>
</html>

