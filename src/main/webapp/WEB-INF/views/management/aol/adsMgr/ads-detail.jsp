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

function doSubmit() {
	
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
							<h2><i class="icon-film"></i> 新增/编辑广告</h2>
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
											  	  ${ads.title}
											  </div>
											</div>
										</td>
									</tr>
									<tr >
										<td>
											<div class="control-group">
											<label class="control-label"  style="width:60px;" for="type">广告位置</label>
											<div class="controls" style="margin-left: 80px;">
												<c:if test="${ads.type eq 1}">
													首页
												</c:if>
												<c:if test="${ads.type eq 2}">
													商城
												</c:if>
												<c:if test="${ads.type eq 3}">
													二手商品
												</c:if>
											</div>
										</td>
									</tr>
									<tr>
										<td>
											<div class="control-group">
											  <label class="control-label" style="width:60px;" for="url">指向商品</label>
											  <div class="controls" style="margin-left: 80px;">
											  	  <c:forEach items="${pro.product}" var="product">
											  	  	  <c:if test="${product.id eq ads.productId}">${product.name}</c:if>
											  	  	</c:forEach>
											  	  	<c:forEach items="${pro.second}" var="second">
											  	  		<c:if test="${second.id eq ads.productId}">${second.title}</c:if>
											  	  	</c:forEach>
											  </div>
											</div>
										</td>
									</tr>
									<tr>
										<td>
											<div class="control-group">
											  <label class="control-label" style="width:60px;" for="url">商品图片</label>
											  <div class="controls" style="margin-left: 80px;">	
												<img id="picture" src="${ads.imageUrl}" style="height: 200px; width: 300px;display: inherit;" border="0"/>
											</div>
										</td>
									</tr>
									<tr>
										<td>
										
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

