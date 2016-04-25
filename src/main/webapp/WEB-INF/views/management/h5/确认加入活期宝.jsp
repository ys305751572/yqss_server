<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<!doctype html>
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/h5/css/common.css">
    <link href="${contextPath}/resources/h5/css/bootstrap.min.css" rel="stylesheet">

    <script src="${contextPath}/resources/js/jquery-1.7.2.min.js"></script>

    <title>确认加入活期宝</title>
</head>
<body>
<div class="back" style="background: #dd403b;">
    <a href="javascript:history.go(-1)"><img src="${contextPath}/resources/h5/image/Fill 6.png"></a>
    <span>确认加入活期宝</span>
</div>
<div class="container">
    <div class="form-inline">
        <div  class="form-group">
            <label for="hqId" class="form-group-lable">投资金额</label>
            <span>元</span>
            <input type="text" id="hqId" readonly="readonly" value="${tr.money}" style="text-align: right;" />
        </div>
        <div  class="form-group form-group2">
            <label for="input_check1">预计每日收益</label>
            <span style="color: #de433f">元</span>
            <input type="text" id="input_check1" readonly="readonly" placeholder="" value="${tr.earnings}"/>
        </div>
        <div class="radio">
            <input type="checkbox" name="radio2" id="radio3" value="radio3">
            <label for="radio3">
                同意<a href="${contextPath}/moneymag/detail" style="color: #e9827f;text-decoration: none;">《xxx协议》</a>
            </label>
        </div>
        <div class="btn_box">
            <button type="submit" class="btn-default1" >立即加入</button>
        </div>
        <div class="aui-dialog aui-hidden" id="dialog">
            <span class="aui-dialog-tip">请选择支付方式</span>
            <div class="aui-dialog-body">
                <div class="aui-dialog-pay1">
                    <a href="#"><img src="image/p6-7-1支付宝.png" style="width: 30%;"></a>
                </div>
                <div class="aui-dialog-pay2">
                    <a href="#"><img src="image/p6-7-1微信.png" style="width: 30%;"></a>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="application/javascript">
    var yqss = {
        v : {

        },
        fn : {
            init : function() {
                $(".btn-default1").click(function() {
                    yqss.fn.doSubmit();
                });
            },
            doSubmit : function() {
                var flag=true;
                var isCheck =$('input:checkbox[name="radio2"]:checked').val();
                console.log(isCheck);
                if(isCheck == null || isCheck ==""){
                    alert("还未同意该协议");
                    flag = false;
                    return;
                }
                if(flag) {
                    $.post("${contextPath}/moneymag/hq/confirmJoinHQ",function(result) {
                        if(result == "SUCCESS") {

                            yqss.fn.show();

                        }
                        else {
                            alert("密码错误");
                        }
                    });
                }
            },
            show : function() {
                //展示第三方支付标志

            }
        }
    }

    $(function() {
        yqss.fn.init();
    });
</script>
</body>
</html>
