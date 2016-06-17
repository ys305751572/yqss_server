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
	
	$("#add_img").uploadPreview({
	 Img : "picture",
	 Width : "120",
	 Height : "120"
	})
});


function doSubmit() {
	
	if($("#productForm").valid(this) == false) {
		return false;
	}		
	$.ajax({
		url : "${contextPath}/management/commons/savePwd",
		type : "POST",
		data : {
			"oldPwd" : $("#oldPwd").val(),
			"newPwd" : $("#newPwd").val()
		},
		success : function(result) {
			if(result.status == 0) {
				alert("修改成功");
				window.location.reload();
			}
			else {
				alert(result.msg);
			}
		},
		error : function() {
			alert("系统繁忙,请稍后再试");
		}
	});
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
							<h2><i class="icon-film"></i> 基础设置</h2>
							<jsp:include page="/WEB-INF/views/backDiv.jsp" flush="true">
								<jsp:param name="url" value="${contextPath}/management/video/listPage"/>
							</jsp:include>
						</div>
						
						<!--box-content start-->
						<div class="box-content">
							
							<!--z-informa2 start-->
							<div class="z-informa2" style="margin-bottom: 10px;">
								<table>
									<tr>
										<td>
											<div class="control-group">
											  <label class="control-label" style="width:60px;" for="oldPwd">原密码</label>
											  <div class="controls" style="margin-left: 80px;">
											  	  <input type="password" id="oldPwd" name="oldPwd" value="" style="width:600px;" placeholder="请填写原密码" maxlength="1000" check-type="required" required-message="请填写原密码"/>
											  </div>
											</div>
										</td>
									</tr>
									<tr>
										<td>
											<div class="control-group">
											  <label class="control-label" style="width:60px;" for="newPwd">新密码</label>
											  <div class="controls" style="margin-left: 80px;">
											  	  <input type="password" id="newPwd" name="newPwd" value="" style="width:600px;" placeholder="请填写新密码" maxlength="1000" check-type="required" required-message="请输入新密码"/>
											  </div>
											</div>
										</td>
									</tr>
									<tr>
										<td>
											<div class="control-group">
											  <label class="control-label" style="width:60px;" for="newPwd2">确认密码</label>
											  <div class="controls" style="margin-left: 80px;">
											  	  <input type="password" id="newPwd2" name="newPwd2" value="" style="width:600px;" placeholder="请填写确认密码" maxlength="1000" check-type="required matchTo" required-message="请输入确认密码"/>
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

