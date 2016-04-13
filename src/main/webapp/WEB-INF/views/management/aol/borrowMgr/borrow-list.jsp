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
				
				var columns = [ {'text':'借款用户','dataIndex':'user','render':userRender,'width':'70px'},
							    {'text':'借款额度','dataIndex':'limitMoney','render':moneyRender,'width':'60px'},
							    {'text':'最大期限','dataIndex':'maxDay','render':dayRender,'width':'60px'},
							    {'text':'用户名称','dataIndex':'username','width':'60px'},
							    {'text':'身份证','dataIndex':'idCard','width':'60px'},
							    {'text':'审核状态','dataIndex':'isList','render':isListRender,'width':'60px'},
							    {'text':'申请时间','dataIndex':'createDate','render': timeRender,'width':'70'}
							    ];
				var arrayObj = [];
				var dataTableObj ;
				$(function() {
					dataTableObj  = new czTools.dataTable({"columns":columns,"render":"doctorListDataTable",
												"url":"${contextPath}/management/borrow/findAll",
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
				
				function moneyRender(row) {
					return row.limitMoney;
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
			    
			    //查看还款记录
			    function record(){
			    	if(!dataTableObj.getSelectedRow()){
						jAlert('请选择要操作的记录','提示');
						return;
					}
			    	window.location.href = "${contextPath}/management/borrow/record/index?borrowInfoId="+dataTableObj.getSelectedRow().id;
				}
		</script>
	</head>
	<body>
		<!--detail start-->
		<div class="row-fluid z-ulnone" id="proList">
			<div class="box span12">			
				<!-- 操作按钮start -->
				<div class="breadcrumb">
					<li><a href="javascript:detail();" class="button button-rounded button-flat button-tiny" style="width: 100px;"><i class="icon-2" style="width: 20px; height: 20px; line-height: 20px;"></i>&nbsp;查看详情</a></li>
					<li style="color: #c5c5c5">|</li>
					<li><a href="javascript:agree();" class="button button-rounded button-flat button-tiny" style="width: 100px;"><i class="icon-2" style="width: 20px; height: 20px; line-height: 20px;"></i>&nbsp;同意</a></li>
					<li style="color: #c5c5c5">|</li>
					<li><a href="javascript:refuse();" class="button button-rounded button-flat button-tiny" style="width: 100px;"><i class="icon-2" style="width: 20px; height: 20px; line-height: 20px;"></i>&nbsp;拒绝</a></li>
					<li style="color: #c5c5c5">|</li>
					<li><a href="javascript:record();" class="button button-rounded button-flat button-tiny" style="width: 100px;"><i class="icon-2" style="width: 20px; height: 20px; line-height: 20px;"></i>&nbsp;查看还款记录</a></li>
					
				</div>
				<!-- 操作按钮end -->
			
				<div class="box-content"   style="padding: 0px;border: 0px">
					<!-- 搜索条件start -->
					<div class="modal-header" style="float: left;width: 100%; ">
						<form id="form1" name="form1" class="form-horizontal" action="" method="post" enctype="multipart/form-data">
							<table border="0px" style="height: 40px;word-break: keep-all;white-space:nowrap;float: left;">
								<tr>
									<td>申请时间范围：</td>
									<td>
										<input type="text" name="regTimeQ"  id="regTimeQ"  readonly   class="form_datetime"  required="required"  style="width: 90px"/>
										~
										<input type="text" name="regTimeZ"  id="regTimeZ"   readonly  class="form_datetime"  required="required"  style="width: 90px"/>
									</td>
									<td width="20px">&nbsp;</td>
									
								</tr>
							</table>
							<table border="0px" style="height: 40px;word-break: keep-all;white-space:nowrap;float: left;">
								<tr>
									<td>审核状态：</td>
									<td>
									<select id="isList" name="isList"  style="width: 60px;">
										<option value="" <c:if test="${isList ==''}">selected="selected" </c:if>>全部</option>
										<option <c:if test="${isList=='0'}">selected="selected" </c:if> value="0">未处理</option>
										<option <c:if test="${isList=='1'}">selected="selected" </c:if> value="1">同意</option>
										<option <c:if test="${isList=='2'}">selected="selected" </c:if> value="2">拒绝</option>
									</select>
									</td>
									<td width="20px">&nbsp;</td>
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

