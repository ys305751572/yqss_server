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
				
				var columns = [ {'text':'招聘标题','dataIndex':'title','width':'70px'},
							    {'text':'薪资范围','dataIndex':'salaryMin','render': salaryRender,'width':'60px'},
							    {'text':'手机','dataIndex':'mobile','width':'60px'},
							    {'text':'职位','dataIndex':'position','width':'70'},
							    {'text':'人数','dataIndex':'counts','width':'50px'}
							    ];
				var arrayObj = [];
				var dataTableObj ;
				$(function() {
					dataTableObj  = new czTools.dataTable({"columns":columns,"render":"doctorListDataTable",
												"url":"${contextPath}/management/hr/findAll",
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
				
				function salaryRender(row) {
					var min = row.salaryMin;
					var max = row.salaryMax;
					if((min == null || min == '') && (max !=null && max != '')) {
						return max + "以下";
					}
					if((min != null || min != '') && (max ==null && max == '')) {
						return min + "以上";
					}
					else {
						return min + "~" + max;
					}
				}

				function timeRender(row) {
					var regtime = "";
					if(row.createDate){
						regtime = new Date(row.createDate).format("yyyy-MM-dd hh:mm:ss")
					}
					return regtime;
				}
				
				function searchBtnClick(){
					var arrayObj = [
						{"name":"title","value":$("#title").val()}
					];
					dataTableObj.search(arrayObj);
				}
				
				// 新增医师
				function add() {
					window.location.href = "${contextPath}/management/hr/editPage";
				}
				
				// 编辑商品
				function edit() {
					if(!dataTableObj.getSelectedRow()){
						jAlert('请选择要查看的记录','提示');
						return;
					} else{
						window.location.href = "${contextPath}/management/hr/editPage?id="+dataTableObj.getSelectedRow().id;
					}
				}
				
				function del(){
					if(!dataTableObj.getSelectedRow()){
						jAlert('请选择要删除的记录','提示');
						return;
					} else {
						var id = dataTableObj.getSelectedRow().id;
						jConfirm('是否确认删除记录？',"提示",function(r){
							if(r) { 
								$.post("${contextPath}/management/hr/delete?id=" + id,function(result){
									if(result.success){
										jAlert(result.msg,'提示');
										window.location.href = "${contextPath}/management/hr/listPage";
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
			    function detail(){
			    	if(!dataTableObj.getSelectedRow()){
						jAlert('请选择要删除的记录','提示');
						return;
					}
			    	window.location.href = "${contextPath}/management/hr/detailPage?id="+dataTableObj.getSelectedRow().id;
				}
			    
			    // 商品发布
			    function publish() {
			    	if(!dataTableObj.getSelectedRow()){
						jAlert('请选择要删除的记录','提示');
						return;
					} else {
						var id = dataTableObj.getSelectedRow().id;
						if(dataTableObj.getSelectedRow().isList == 1) {
							jAlert('已发布的广告无法再次发布','提示');
							return;
						}
						
						jConfirm('是否确认发布该广告？',"提示",function(r){
							if(r) { 
								$.post("${contextPath}/management/ads/publish",{"id":id,"isList":'1'},function(result){
									if(result.success){
										window.location.href = "${contextPath}/management/ads/listPage";
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
						jAlert('请选择要删除的记录','提示');
						return;
					} else {
						var id = dataTableObj.getSelectedRow().id;
						if(dataTableObj.getSelectedRow().isList == 2) {
							jAlert('已下架的广告无法再次下架','提示');
							return;
						}
						
						jConfirm('是否确认下架广告？',"提示",function(r){
							if(r) { 
								$.post("${contextPath}/management/ads/publish",{"id":id,"isList":'2'},function(result){
									if(result.success){
										window.location.href = "${contextPath}/management/ads/listPage";
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
					<li><a href="javascript:add();" class="button button-rounded button-flat button-tiny" style="width: 120px;"><i class="icon-6" style="width: 20px; height: 20px; line-height: 20px;"></i>&nbsp;新增</a></li>
					<li style="color: #c5c5c5">|</li>
					<li><a href="javascript:edit();" class="button button-rounded button-flat button-tiny" style="width: 100px;"><i class="icon-2" style="width: 20px; height: 20px; line-height: 20px;"></i>&nbsp;编辑</a></li>
					<li style="color: #c5c5c5">|</li>
					<li><a href="javascript:del();" class="button button-rounded button-flat button-tiny" style="width: 100px;"><i class="icon-2" style="width: 20px; height: 20px; line-height: 20px;"></i>&nbsp;删除</a></li>
				</div>
				<!-- 操作按钮end -->
				<div class="box-content"   style="padding: 0px;border: 0px">
					<!-- 搜索条件start -->
					<div class="modal-header" style="float: left;width: 100%; ">
						<form id="form1" name="form1" class="form-horizontal" action="" method="post" enctype="multipart/form-data">
							<table border="0px" style="height: 40px;word-break: keep-all;white-space:nowrap;float: left;">
								<tr>
									<td>招聘信息标题：</td>
									<td><input id="title" name="title" type="text" value="" style="width: 120px; height: 15px;" /></td>
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

