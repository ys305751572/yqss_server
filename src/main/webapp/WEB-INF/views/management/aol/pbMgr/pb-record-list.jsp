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
				var columns = [ 
							    {'text':'还款金额','dataIndex':'money','render': moneyRender,'width':'60px'},
							    {'text':'申请时间','dataIndex':'createDate','render': timeRender,'width':'70'}
							    ];
				var arrayObj = [];
				var dataTableObj ;
				$(function() {
					arrayObj = [{name : "borrowInfoId", value : $("#borrowInfoId").val()}];
					console.log("arrayObj:" + arrayObj);
					
					dataTableObj  = new czTools.dataTable({"columns":columns,"render":"doctorListDataTable",
												"url":"${contextPath}/management/pb/record/list",
												"searchArr":arrayObj,
												"autoIframeHeight":false,
												"showIndex":true,
												"fnComplete":function(){
													 window.parent.resetIframeHeight(dataTableObj.oTable[0].clientHeight+400);
												}});
					//searchBtnClick();
					
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
				
				function moneyRender(row) {
					return row.money;
				}
				
				function isListRender(row) {
					if(row.isList == 0){
						return "审核中";
					}
					else if(row.isList == 1) {
						return "同意";
					}
					else if(row.isList == 2) {
						return "拒绝";
					}
				}
				
				function dayRender(row) {
					return row.maxDay;
				}
				
				function userRender(row) {
					return row.user.name;
				}

				function timeRender(row){
					var regtime = "";
					if(row.createDate){
						regtime = new Date(row.createDate).format("yyyy-MM-dd hh:mm:ss");
					}
					return regtime;
				}
				
				function searchBtnClick(){
					var arrayObj = [
						{"name":"regTimeQ","value":$("#regTimeQ").val()},
						{"name":"regTimeZ","value":$("#regTimeZ").val()},
						{"name":"isList","value":$("#isList").val()}
					];
					dataTableObj.search(arrayObj);
				}
				// 同意借贷
				function agree(){
					if(!dataTableObj.getSelectedRow()){
						jAlert('请选择要操作的记录','提示');
						return;
					} else {
						var id = dataTableObj.getSelectedRow().id;
						if(dataTableObj.getSelectedRow().isList == 1) {
							jAlert('已同意的借贷无法再次同意','提示');
							return;
						}
						jConfirm('是否确认同意此次借贷？',"提示",function(r){
							if(r) { 
								$.post("${contextPath}/management/borrow/agree",{"id":id},function(result){
									if(result.success){
										jAlert(result.msg,'提示');
										window.location.href = "${contextPath}/management/borrow/listPage";
									}
									else {
										jAlert(result.msg,'提示');
									}
								});
						 	}
						});
					}
				}
				
				// 拒绝借贷
				function refuse(){
					if(!dataTableObj.getSelectedRow()){
						jAlert('请选择要操作的记录','提示');
						return;
					} else {
						var id = dataTableObj.getSelectedRow().id;
						if(dataTableObj.getSelectedRow().isList == 2) {
							jAlert('已拒绝的借贷无法再次拒绝','提示');
							return;
						}
						jConfirm('是否确认拒绝此次借贷？',"提示",function(r){
							if(r) { 
								$.post("${contextPath}/management/borrow/refuse",{"id":id},function(result){
									if(result.success){
										jAlert(result.msg,'提示');
										window.location.href = "${contextPath}/management/borrow/listPage";
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
						jAlert('请选择要操作的记录','提示');
						return;
					}
			    	window.location.href = "${contextPath}/management/borrow/detailPage?id="+dataTableObj.getSelectedRow().id;
				}
		</script>
	</head>
	<body>
		<!--detail start-->
		<div class="row-fluid z-ulnone" id="proList">
			<div class="box span12">			
				<!-- 操作按钮start -->
				<!-- 操作按钮end -->
				<input type="hidden" id="borrowInfoId" name="borrowInfoId" value="${map.borrowInfoId}">
				<div class="box-content"   style="padding: 0px;border: 0px">
					<!-- 搜索条件start -->
					<!-- 搜索条件end -->

					<!-- 列表start -->
					<div id="doctorListDataTable" class="z-informa2"></div> 
					<!-- 列表end -->
				</div> 
			</div> 
		</div> 
	</body>
</html>

