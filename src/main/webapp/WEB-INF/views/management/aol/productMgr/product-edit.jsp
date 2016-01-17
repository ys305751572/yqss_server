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

<script type="text/javascript" src="${contextPath}/resources/upload/js/uploadPreview.min.js"></script>
<script type="text/javascript" src="${contextPath}/resources/upload/js/ajaxfileupload.js"></script>
<script type="text/javascript">
	var basePath = '${contextPath}';
</script>
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
	addPic();
	
	var content = "${product}";
	
	if(content){
		CKEDITOR.instances.detail.setData("${product.content}");
	}
});
var index = 1;

function delPic(index) {
	$("#div" + index).remove();
	if($("#pics").children().size() == 0) {
		addPic();
	}
}

function addPic() {
	
	var picHtml = "<div style=\"float:left;margin-left: 60px;\" id=\"div"+ index +"\">";
	picHtml += "<img id=\"picture"+ index +"\" src=\"\" style=\"height: 200px; width: 300px;display: inherit;\" border=\"0\"/>";
	picHtml += "<input type=\"file\" style=\"height: 20px; width: 220px\" name=\"add_img"+ index +"\" id=\"add_img"+ index +"\"/>";
	picHtml += "<input type=\"button\" class=\"easyui-linkbutton\" style=\"padding: 3px 5px 3px 5px;\" onclick=\"addPic();\" value=\"添加\"/>";
	picHtml += "<input type=\"button\" class=\"easyui-linkbutton\" style=\"padding: 3px 5px 3px 5px;\" onclick=\"delPic("+ index +");\" value=\"删 除\"/>";
	
	picHtml += "<script type=\"text/javascript\">";
	picHtml += "$(\"#add_img"+ index + "\").uploadPreview({";
	picHtml += "Img : \"picture"+ index + "\",";
	picHtml += "Width : 120,";
	picHtml += "Height : 120";
	picHtml += "});<\/script\>";
	picHtml += "</div>";
	$(picHtml).appendTo("#pics");
	++index;
}

function delPicFile(id) {
	$.ajax({
		url : "${contextPath}/management/product/image/delete?id=" + id,
		type : "POST",
		dataType : "json",
		success : function() {
			$("#pic_list" + id).remove();
		},
		
		error: function() {
		}
	});
} 

function doSubmit() {
	var content = CKEDITOR.instances.detail.getData();
	if($("#productForm").valid(this) == false) {
		return false;
	}		
	
	var array = new Array();
	$("input[type='file']").each(function(){
		array.push($(this).attr("id"));
	});
	
	$.ajaxFileUpload({
		url : "${contextPath}/management/product/update",
		type : 'post',
		secureuri : false,
		frameId : 'temp_upload_frame',
		fileElementId : array, //文件选择框的id属性
		dataType : 'json', //服务器返回的格式，可以是json
		param : {
			"id" : $("#id").val(),
			"name" : $("#name").val(),
			"price" : $("#price").val(),
			"productType" : $("#productType").val(),
			"url" : $("#url").val(),
			"stage":$("#stage").val(),
			"content":content
		},
		success : function(data) {
			window.location.href = "${contextPath}/management/product/listPage";
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
								<jsp:param name="url" value="${contextPath}/management/product/listPage"/>
							</jsp:include>
						</div>
						
						<!--box-content start-->
						<div class="box-content">
							
							<!--z-informa2 start-->
							<div class="z-informa2" style="margin-bottom: 10px;">
								<table>
									<input type="hidden" id="id" name="id" value="${product.id}">
									<tr>
										<td>
											<div class="control-group">
											  <label class="control-label" style="width:60px;" for="name">商品名称</label>
											  <div class="controls" style="margin-left: 80px;">
											  	  <input type="text" id="name" name="name" value="${product.name}" style="width:600px;" placeholder="请填写商品名称" maxlength="1000" check-type="required" required-message="请输入商品名称"/>
											  </div>
											</div>
										</td>
									</tr>
									<tr>
										<td>
											<div class="control-group">
											  <label class="control-label" style="width:60px;" for="price">价格</label>
											  <div class="controls" style="margin-left: 80px;">
											  	  <input type="text" id="price" name="price" value="${product.price}" style="width:600px;" placeholder="请填写价格" maxlength="1000" check-type="required price" required-message="请输入价格" />
											  </div>
											</div>
										</td>
									</tr>
									<tr >
										<td>
											<div class="control-group">
											<label class="control-label"  style="width:60px;" for="productType">商品类型</label>
											<div class="controls" style="margin-left: 80px;">
												<select id="productType" name="productType"  style="width: 120px;">
													<c:forEach items="${map.list}" var="productType">
														<option <c:if test="${(productType.id eq product.productType.id) or empty product.productType.id}">selected="selected" </c:if> value="${productType.id}">${productType.name}</option>
													</c:forEach>
												</select>
											</div>
										</td>
									</tr>
									<tr>
										<td>
											<div class="control-group">
											  <label class="control-label" style="width:60px;" for="url">商品链接</label>
											  <div class="controls" style="margin-left: 80px;">
											  	  <input type="text" id="url" name="url" value="${product.url}" style="width:600px;"  placeholder="请填写商品链接地址" maxlength="1000" check-type="required url" required-message="请输入链接地址" />
											  </div>
											</div>
										</td>
									</tr>
									<tr>
										<td>
											<div class="control-group">
											  <label class="control-label" style="width:60px;" for="url">商品分期</label>
											  <div class="controls" style="margin-left: 80px;">
											  	  <input type="text" id="stage" name="stage" value="${stage.stage}" style="width:600px;"  placeholder="请填写商品分期数，分期数用逗号分割" maxlength="1000" check-type="installment"/>
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
											  <label class="control-label" style="width:60px;" for="url">商品图片</label>
										</td>
									</tr>
									<tr>
										<td>
										  <div class="controls" style="margin-left: 0px;" id="pics">
										  	    <c:forEach items="${product.picList}" var="productImage">
											  	    <div id="pic_list${productImage.id}"  style="float:left;margin-left: 60px;">
														<img id="" src="${productImage.imageUrl}" style="height: 200px; width: 300px;display: inherit;" border="0" />
														<input type="file" style="height: 20px; width: 220px" name="" id=""/>
														<input type="button" class="easyui-linkbutton" style="padding: 3px 5px 3px 5px;" onclick="addPic();" value="添加" />
														<input type="button" class="easyui-linkbutton" style="padding: 3px 5px 3px 5px;" onclick="delPicFile(${productImage.id});" value="删 除" />
													</div>
										  	    </c:forEach>
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

