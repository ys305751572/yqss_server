<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en">
	<head>
		<meta charset="utf-8" />
		<title>爱奥乐健康云平台</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />

		<%@ include file="/WEB-INF/views/scripts.jsp"%>
		<link rel="stylesheet" type="text/css" href="${contextPath}/jquery-easyui-1.3.3/themes/bootstrap/easyui.css" />
		<link rel="stylesheet" type="text/css" href="${contextPath}/jquery-easyui-1.3.3/themes/icon.css" />
		<script type="text/javascript" src="${contextPath}/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
		<!-- The styles -->
		<style type="text/css">
			body {
				padding-bottom: 10px;
				padding-top: 0px;
			}
			
			.sidebar-nav {
				padding: 9px 0;
			}
			
			#DataTables_Table_0_paginate a {
				margin-left: 5px;
			}
			.dataTables_scroll{
				float: left;
			}
			#DataTables_Table_0_wrapper .row-fluid {
				float: left;
			}
		</style>
		
<script type="text/javascript">		
Date.prototype.format = function(format){
    var o = {
        "M+" :this.getMonth() + 1, // month
        "d+" :this.getDate(), // day
        "h+" :this.getHours(), // hour
        "m+" :this.getMinutes(), // minute
        "s+" :this.getSeconds(), // second
        "q+" :Math.floor((this.getMonth() + 3) / 3), // quarter
        "S" :this.getMilliseconds()
    };
    if (/(y+)/.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    };
    for ( var k in o) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
        }
    };
    return format;
};
</script>
		<script type="text/javascript">
				$(document).ready(function(){
					var msg = '${msg}';
					if(msg!=""){
						jAlert(msg,'提示');
					}
				});
				var delId = "";
				
				var columns = [ {'text':'商品名称','dataIndex':'name','width':'70px'},
							    {'text':'商品价格','dataIndex':'price','width':'60px'},
							    {'text':'商品类型','dataIndex':'productType','render': typeRender,'width':'60px'},
							    {'text':'商品链接','dataIndex':'url','render': urlRender,'width':'70'},
							    {'text':'是否上架','dataIndex':'isList','width':'50px','render': isListRender},
							    {'text':'是否置顶','dataIndex':'isTop','width':'50px','render': isTopRender}
							    ];
				var arrayObj = [];
				var dataTableObj ;
				$(function() {
					dataTableObj  = new czTools.dataTable({"columns":columns,"render":"doctorListDataTable",
												"url":"${contextPath}/management/product/findAll",
												"para":arrayObj,
												"autoIframeHeight":false,
												"showIndex":true,
												"fnComplete":function(){
													 window.parent.resetIframeHeight(dataTableObj.oTable[0].clientHeight+400);
												}});
					searchBtnClick();
					
					$('.form_datetime').datetimepicker({
				        language:  'zh-CN',
				        weekStart: 1,
				        todayBtn:  1,
						autoclose: 1,
						todayHighlight: 1,
						startView: 2,
						forceParse: 0,
				        showMeridian: 1,
				        format:'yyyy-mm-dd',
				        minView:2
				    });
					
				});
				
				function typeRender(row) {
					return row.productType.name;
				}
				
				function urlRender(row) {
					if(row.url.length > 30){
						return '<a  href="' + row.url + '" target="_blank">' + row.url.substr(0, 30) + "......" + '</a>';
					} else {
						return '<a  href="' + row.url + '" target="_blank">' + row.url + '</a>';
					}
				}

				function isListRender(row) {
					if(row.isList == 1) {
						return '已发布';
					}
					else if(row.isList == 2) {
						return '已下架';
					}
					else if(row.isList == 0) {
						return '未发布';
					}
				}
				
				function isTopRender(row) {
					if(row.isTop == 1) {
						return '未置顶';
					}
					else if(row.isTop == 2) {
						return '已置顶';
					}
				}
				
				function searchBtnClick(){
					var arrayObj = [
						{"name":"name","value":$("#name").val()}
					];
					dataTableObj.search(arrayObj);
				}
				
				// 新增医师
				function addProduct() {
					window.location.href = "${contextPath}/management/product/editPage";
				}
				
				// 编辑商品
				function editProduct() {
					if(!dataTableObj.getSelectedRow()){
						jAlert('请选择要查看的商品','提示');
						return;
					} else{
						window.location.href = "${contextPath}/management/product/editPage?id="+dataTableObj.getSelectedRow().id;
					}
				}
				
				function delProduct(){
					if(!dataTableObj.getSelectedRow()){
						jAlert('请选择要删除的记录','提示');
						return;
					} else {
						var id = dataTableObj.getSelectedRow().id;
						jConfirm('是否确认删除商品？',"提示",function(r){
							if(r) { 
								$.post("${contextPath}/management/product/delete",{"id":id},function(result){
									if(result.success){
										window.location.href = "${contextPath}/management/product/listPage";
									}
									else {
										jAlert(result.msg,'提示');
									}
								});
						 	}
						});
					}
				}
				
			    //查看用户信息
			    function detailProduct(){
			    	window.location.href = "${contextPath}/management/product/detail?id="+dataTableObj.getSelectedRow().id;
				}
			    
			    // 商品发布
			    function publish() {
			    	if(!dataTableObj.getSelectedRow()){
						jAlert('请选择要操作的记录','提示');
						return;
					} else {
						var id = dataTableObj.getSelectedRow().id;
						if(dataTableObj.getSelectedRow().isList == 1) {
							jAlert('已发布的商品无法再次发布','提示');
							return;
						}
						
						jConfirm('是否确认发布该商品？',"提示",function(r){
							if(r) { 
								$.post("${contextPath}/management/product/publish",{"id":id,"isList":'1'},function(result){
									if(result.success){
										window.location.href = "${contextPath}/management/product/listPage";
									}
									else {
										jAlert(result.msg,'提示');
									}
								});
						 	}
						});
					}
			    }
			    
			    // 下架
			    function unPublish() {
			    	if(!dataTableObj.getSelectedRow()){
						jAlert('请选择要操作的记录','提示');
						return;
					} else {
						var id = dataTableObj.getSelectedRow().id;
						if(dataTableObj.getSelectedRow().isList == 2) {
							jAlert('已下架的商品无法再次下架','提示');
							return;
						}
						
						jConfirm('是否确认下架该商品？',"提示",function(r){
							if(r) { 
								$.post("${contextPath}/management/product/publish",{"id":id,"isList":'2'},function(result){
									if(result.success){
										window.location.href = "${contextPath}/management/product/listPage";
									}
									else {
										jAlert(result.msg,'提示');
									}
								});
						 	}
						});
					}
			    }

			    // 置顶
			    function top1() {
			    	if(!dataTableObj.getSelectedRow()){
						jAlert('请选择要操作的记录','提示');
						return;
					} else {
						var id = dataTableObj.getSelectedRow().id;
						if(dataTableObj.getSelectedRow().isTop == 2) {
							jAlert('已置顶的商品无法再次置顶','提示');
							return;
						}
						
						jConfirm('是否确认置顶该商品？',"提示",function(r){
							if(r) { 
								$.post("${contextPath}/management/product/top",{"id":id,"isTop":'2'},function(result){
									if(result.success){
										window.location.href = "${contextPath}/management/product/listPage";
									}
									else {
										jAlert(result.msg,'提示');
									}
								});
						 	}
						});
					}
			    }
			    
			    // 取消置顶
			    function untop() {
			    	if(!dataTableObj.getSelectedRow()){
						jAlert('请选择要操作的记录','提示');
						return;
					} else {
						var id = dataTableObj.getSelectedRow().id;
						if(dataTableObj.getSelectedRow().isTop == 1) {
							jAlert('已取消置顶的商品无法再次取消','提示');
							return;
						}
						
						jConfirm('是否确认取消置顶该商品？',"提示",function(r){
							if(r) { 
								$.post("${contextPath}/management/product/top",{"id":id,"isList":'1'},function(result){
									if(result.success){
										window.location.href = "${contextPath}/management/product/listPage";
									}
									else {
										jAlert(result.msg,'提示');
									}
								});
						 	}
						});
					}
			    }
		</script>
	</head>
	<body>
		<!--detail start-->
		<div class="row-fluid z-ulnone" id="proList">
			<div class="box span12">			
				<!-- 操作按钮start -->
				<div class="breadcrumb">
					<li><a href="javascript:addProduct();" class="button button-rounded button-flat button-tiny" style="width: 120px;"><i class="icon-6" style="width: 20px; height: 20px; line-height: 20px;"></i>&nbsp;新增商品</a></li>
					<li style="color: #c5c5c5">|</li>
					<li><a href="javascript:editProduct();" class="button button-rounded button-flat button-tiny" style="width: 100px;"><i class="icon-2" style="width: 20px; height: 20px; line-height: 20px;"></i>&nbsp;编辑商品</a></li>
					<li style="color: #c5c5c5">|</li>
					<li><a href="javascript:delProduct();" class="button button-rounded button-flat button-tiny" style="width: 100px;"><i class="icon-2" style="width: 20px; height: 20px; line-height: 20px;"></i>&nbsp;删除商品</a></li>
					<li style="color: #c5c5c5">|</li>
					<li><a href="javascript:publish();" class="button button-rounded button-flat button-tiny" style="width: 100px;"><i class="icon-13" style="width: 20px; height: 20px; line-height: 20px;"></i>&nbsp;发布</a></li>
					<li style="color: #c5c5c5">|</li>
					<li><a href="javascript:unPublish();" class="button button-rounded button-flat button-tiny" style="width: 100px;"><i class="icon-12" style="width: 20px; height: 20px; line-height: 20px;"></i>&nbsp;下架</a></li>
					<li style="color: #c5c5c5">|</li>
					<li><a href="javascript:top1();" class="button button-rounded button-flat button-tiny" style="width: 100px;"><i class="icon-13" style="width: 20px; height: 20px; line-height: 20px;"></i>&nbsp;置顶</a></li>
					<li style="color: #c5c5c5">|</li>
					<li><a href="javascript:untop();" class="button button-rounded button-flat button-tiny" style="width: 100px;"><i class="icon-12" style="width: 20px; height: 20px; line-height: 20px;"></i>&nbsp;取消置顶</a></li>
				</div>
				<!-- 操作按钮end -->
			
				<div class="box-content"   style="padding: 0px;border: 0px">
					<!-- 搜索条件start -->
					<div class="modal-header" style="float: left;width: 100%; ">
						<form id="form1" name="form1" class="form-horizontal" action="" method="post" enctype="multipart/form-data">
							<table border="0px" style="height: 40px;word-break: keep-all;white-space:nowrap;float: left;">
								<tr>
									<td>产品名称：</td>
									<td><input id="name" name="name" type="text" value="" style="width: 120px; height: 15px;" /></td>
									<td width="10px">&nbsp;</td>
									<td height="40px" align="right">
										<button id="btnSendTop" name="btnSendTop"  style="width:50px;cursor:pointer;"type="button" class="btn btn-primary" onclick="searchBtnClick()"></i>搜索</button>
									</td>
								</tr>
							</table>
						</form>
						
					</div>
					<!-- 搜索条件end -->

					<!-- 列表start -->
					<div id="doctorListDataTable" class="z-informa2"></div> 
					<!-- 列表end -->
				</div> 
			</div> 
		</div> 
	</body>
</html>

