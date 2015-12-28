<%@ page language="java" contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date"%>
<%@page import="com.ckeditor.CKEditorConfig"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en">
<head>
<meta charset="utf-8" />
<title></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<%@ include file="/WEB-INF/views/scripts.jsp"%>
<link rel="stylesheet" type="text/css"
	href="${contextPath}/jquery-easyui-1.3.3/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css"
	href="${contextPath}/jquery-easyui-1.3.3/themes/icon.css" />
<script type="text/javascript"
	src="${contextPath}/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>

<script type="text/javascript">
	var basePath = '${contextPath}';
</script>
<script type="text/javascript" src="${contextPath}/ckeditor/ckeditor.js"></script>
<%
	CKEditorConfig settings = new CKEditorConfig();
	settings.addConfigValue("image_previewText", " ");
	settings.addConfigValue("width", "880px");
	settings.addConfigValue("height", "260px");
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
	background-image:
		url("${contextPath}/resources/img/glyphicons-halflings.png");
}

.icon-user {
	background-position: -168px 2px;
	padding-right: 0px;
	background-image:
		url("${contextPath}/resources/img/glyphicons-halflings.png");
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
	var result = '${msg}';
	var message = '${messages}';
	$(document)
			.ready(
					function() {
						//提示
						if (result) {
							var bb = result.substring(0, 1);
							var tt = result.substring(2, result.length);
							if (bb == "0") {
								jAlert(
										tt,
										'提示',
										function() {
											window.location.href = "${contextPath}/management/messages/draftboxlist";
										});
							} else if (bb == "1") {
								jAlert(
										tt,
										'提示',
										function() {
											window.location.href = "${contextPath}/management/messages/sendedlist";
										});
							} else {
								jAlert(tt, '提示');
							}
						}
						;
						var infoTree = $('#messagesSendee').combotree('tree');
						infoTree.tree({
							onCheck : function(node, checked) {
								var infoChoose = infoTree.tree('getChecked');
								var names = "";
								var ids = "";
								for (var i = 0; i < infoChoose.length; i++) {
									var checknode = infoChoose[i];
									var nodeid = checknode.id;
									if (nodeid.substr(0, 2) == "U_") {
										names = names + checknode.text + ",";
										ids = ids + nodeid + ",";
									}
								}
								$("input.combo-text").val(names);
								$('#messagesSendee').combo('setValues',
										ids.split(","));
							}
						});

						if (message) {
							var sendees = '${messages.messagesSendee}';
							$('#messagesSendee').combo('setValues',
									sendees.split(","));
							CKEDITOR.instances.messagesContent
									.setData("${messages.messagesContent}");
						}
					});

	function submitMessageFrom(bb) {
		var sendssnames = $('#messagesSendee').combo('getText');
		$('#messagesSendeeName').val(sendssnames);

		if (bb == "send") {
			var messagesSendee = $('#messagesSendee').combo('getValues');
			var messagesTitle = $('#messagesTitle').val();
			var messagesContent = CKEDITOR.instances.messagesContent.getData();

			if (messagesSendee == "") {
				jAlert('请填写收件人！', '提示');
			} else if (messagesTitle == "") {
				jAlert('请填写主题！', '提示');
			} else if (messagesContent == "") {
				jAlert('请填写正文！', '提示');
			} else {
				$('#saveORsend').val(bb);
			}
		} else if (bb == "save") {
			$('#saveORsend').val(bb);
		} else {
		}
		$('#messageForm').submit();
	}

	function check_task() {
		var saveORsend = $('#saveORsend').val();
		if (saveORsend == "") {
			return false;
		}

		return true;
	}
</script>
</head>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<div id="content" class="span12">
				<div class="row-fluid z-ulnone">
					<form class="form-horizontal" method="post" id="messageForm"
						name="messageForm"
						action="${contextPath}/management/messages/saveAppMessage"
						onsubmit="return check_task()" enctype="multipart/form-data">
						<!--box span12 start-->
						<div class="box span12">
							<div class="box-header well z-h2">
								<div class="controls" style="margin-left: 10px;">
									<h2>医师信息详情</h2>
									<jsp:include page="/WEB-INF/views/backDiv.jsp" flush="true">
										<jsp:param name="url" value="${contextPath}/management/imageads/adslist"/>
									</jsp:include>
								</div>
								
							</div>

								<!--box-content start-->
						<div class="box-content">
							
							<!--z-informa2 start-->
							<div class="z-informa2" style="margin-bottom: 10px;">
								<table>
									<input type="hidden" id="id" name="id" value="${shp.id}">
									<tr>
										<td>
											<div class="control-group">
											  <label class="control-label" style="width:60px;" for="title">商品名称</label>
											  <div class="controls" style="margin-left: 80px;">
											  	  ${shp.title}
											  </div>
											</div>
										</td>
									</tr>
									<tr>
										<td>
											<div class="control-group">
											  <label class="control-label" style="width:60px;" for="price">价格</label>
											  <div class="controls" style="margin-left: 80px;">
											  	 ${shp.price }
											  </div>
											</div>
										</td>
									</tr>
									<tr >
										<td>
											<div class="control-group">
											<label class="control-label"  style="width:60px;" for="productType">发布人</label>
											<div class="controls" style="margin-left: 80px;">
												${shp.user.name }
											</div>
										</td>
									</tr>
									<tr>
										<td>
											<div class="control-group">
											  <label class="control-label" style="width:60px;" for="url">发布时间</label>
											  <div class="controls" style="margin-left: 80px;">
												  	 ${shp.createDate}
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
													</div>
										  	    </c:forEach>
										  </div>
										</td>
									</tr>
								</table>
							</div><!--z-informa2 end-->
						</div><!--box-content end-->
						</div>
						<!--box span12 end-->
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
