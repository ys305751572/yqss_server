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
	
	var array = new Array();
	$("input[type='file']").each(function(){
		array.push($(this).attr("id"));
	});
	console.log(array);
	
	$.ajaxFileUpload({
		url : "${contextPath}/management/ads/edit",
		type : 'post',
		secureuri : false,
		frameId : 'temp_upload_frame',
		fileElementId : 'add_img', //文件选择框的id属性
		dataType : 'json', //服务器返回的格式，可以是json
		param : {
			"id" : $("#id").val(),
			"title" : $("#title").val(),
			"type" : $("#type").val(),
			"productId" : $("#product").val(),
		},
		success : function(data) {
			window.location.href = "${contextPath}/management/ads/listPage";
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
							<h2><i class="icon-film"></i> 新增/编辑商品</h2>
							<jsp:include page="/WEB-INF/views/backDiv.jsp" flush="true">
								<jsp:param name="url" value="${contextPath}/management/ads/listPage"/>
							</jsp:include>
						</div>
						
						<!--box-content start-->
						<div class="box-content">
							
							<!--z-informa2 start-->
							<div class="z-informa2" style="margin-bottom: 10px;">
								<table>
									<input type="hidden" id="id" name="id" value="${ads.id}">
									<tr>
										<td>
											<div class="control-group">
											  <label class="control-label" style="width:60px;" for="title">广告标题</label>
											  <div class="controls" style="margin-left: 80px;">
											  	  <input type="text" id="title" name="title" value="${ads.title}" style="width:600px;" placeholder="请填写广告标题" maxlength="1000" check-type="required" required-message="请输入广告标题"/>
											  </div>
											</div>
										</td>
									</tr>
									<tr >
										<td>
											<div class="control-group">
											<label class="control-label"  style="width:60px;" for="type">广告位置</label>
											<div class="controls" style="margin-left: 80px;">
												<select id="type" name="type"  style="width: 120px;">
													<option <c:if test="${ads.type eq 1 or empty ads.type}">selected="selected" </c:if> value="1">首页</option>
													<option <c:if test="${ads.type eq 2}">selected="selected" </c:if> value="2">商城</option>
													<option <c:if test="${ads.type eq 3}">selected="selected" </c:if> value="3">二手商品</option>
												</select>
											</div>
										</td>
									</tr>
									<tr>
										<td>
											<div class="control-group">
											  <label class="control-label" style="width:60px;" for="url">指向商品</label>
											  <div class="controls" style="margin-left: 80px;">
											  	  <select id="type" name="type"  style="width: 120px;">
											  	  	<c:forEach items="${map.productList}" var="product">
											  	  		<option <c:if test="${product.id eq ads.productId}">selected="selected" </c:if> value="${product.id }">${product.name }</option>
											  	  	</c:forEach>
												</select>
											  </div>
											</div>
										</td>
									</tr>
									<tr>
										<td>
											<div class="control-group">
											  <label class="control-label" style="width:60px;" for="url">商品图片</label>
										</td>
									</tr>
									<tr>
										<td>
										<div>
											<input type="file" style="height: 20px; width: 220px"
												name="add_img" id="add_img" /> <input
												type="hidden" id="hpicture" name="picture" value="" />
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

