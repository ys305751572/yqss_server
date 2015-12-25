<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date"%>
<%@page import="com.ckeditor.CKEditorConfig"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<%@ taglib uri="http://ckeditor.com" prefix = "ckeditor" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en">
<head>
<meta charset="utf-8" />
<title></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<%@ include file="/WEB-INF/views/scripts.jsp"%>
<link rel="stylesheet" type="text/css" href="${contextPath}/jquery-easyui-1.3.3/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css" href="${contextPath}/jquery-easyui-1.3.3/themes/icon.css" />
<script type="text/javascript" src="${contextPath}/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>

<script type="text/javascript">
	var basePath = '${contextPath}';
</script>
<script type="text/javascript" src="${contextPath}/ckeditor/ckeditor.js"></script>

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
$(document).ready(function(){
	//提示
	if(result){
		var bb = result.substring(0, 1);
		var tt = result.substring(2, result.length);
		if(bb == "0"){
			jAlert(tt,'提示', function(){
				window.location.href = "${contextPath}/management/messages/draftboxlist";
			});
		} else if(bb == "1"){
			jAlert(tt,'提示', function(){
				window.location.href = "${contextPath}/management/messages/sendedlist";
			});
		} else {
			jAlert(tt,'提示');
		}
	};
});


</script>
</head>
<body>
<div class="container-fluid">
	<div class="row-fluid">
		<div id="content" class="span12">
			<div class="box-header well z-h2">
				<h2><i class="icon-film"></i> 用户信息</h2>
				
				<jsp:include page="/WEB-INF/views/backDiv.jsp" flush="true">
					<jsp:param name="url" value="${contextPath}/management/imageads/adslist"/>
				</jsp:include>
			</div>
			<div class="row-fluid z-ulnone">
				<form class="form-horizontal" method="post" id="messageForm" name="messageForm" action="${contextPath}/management/messages/saveAppMessage" onsubmit="return check_task()" enctype="multipart/form-data">
					<!--box span12 start-->
					<div class="box span12">
						<!--box-content start-->
						<div class="box-content">
							
							<!--z-informa2 start-->
							<div class="z-informa2" style="margin-bottom: 10px;">
								<table>
									<tr>
										<td>
											<div class="control-group" style="margin-top: 10px;">
											  <label class="control-label" style="width:60px;" for="messagesSendee">用户名</label>
											  <div class="controls" style="margin-left: 80px;">
												  	 ${users.name }
											  </div>
											</div>
										</td>
									</tr>
									<tr>
										<td>
											<div class="control-group">
											  <label class="control-label" style="width:60px;" for="messagesTitle">手机</label>
											  <div class="controls" style="margin-left: 80px;">
											  	${users.mobile }
											  </div>
											</div>
										</td>
									</tr>
									<tr>
										<td>
											<div class="control-group">
											  <label class="control-label" style="width:60px;" for="messagesContent">性别</label>
											  <div class="controls" style="margin-left: 80px;">
											  	<c:if test="${users.gender eq 1}">
											  		男
											  	</c:if>
											  	<c:if test="${users.gender eq 2 }">
											  		女
											  	</c:if>
											  </div>
											</div>
										</td>
									</tr>
									<tr>
										<td>
											<div class="control-group">
											  <label class="control-label" style="width:60px;" for="messagesContent">身份证</label>
											  <div class="controls" style="margin-left: 80px;">
											  	${users.idCard }
											  </div>
											</div>
										</td>
									</tr>
									<tr>
										<td>
											<div class="control-group">
											  <label class="control-label" style="width:60px;" for="messagesContent">学校名称</label>
											  <div class="controls" style="margin-left: 80px;">
											  	${users.schoolName}
											  </div>
											</div>
										</td>
									</tr>
									<tr>
										<td>
											<div class="control-group">
											  <label class="control-label" style="width:60px;" for="messagesContent">地址</label>
											  <div class="controls" style="margin-left: 80px;">
											  	${users.address}
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

