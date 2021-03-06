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
    <script src="${contextPath}/resources/pay/js.js"></script>

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
            <label for="hqId" class="form-group-lable" style="color: #948b8b; font-size: 1.4rem;font-weight: 100;margin-bottom: 0px;">投资金额</label>
            <span style="font-size: 1.4rem;color: #948b8b;">元</span>
            <input type="text" id="hqId" readonly="readonly" value="${tr.money}" style="text-align: right;font-size: 1.4rem;" />
            <input type="hidden" id="sn" value="" style="text-align: right;" />
        </div>
        <div  class="form-group form-group2">
            <label for="input_check1" style="color: #948b8b;font-size: 1.4rem;font-weight: 100;margin-bottom: 0px;">预计每日收益</label>
            <span style="color: #de433f;font-size: 1.4rem">元</span>
            <input type="text" id="input_check1" style="text-align: right;font-size: 1.4rem;color: #de433f" readonly="readonly" placeholder="" value="${tr.earnings}"/>
        </div>

        <div class="checkbox">
            <input type="checkbox" value="1" id="checkboxInput" name="radio2">
            <label for="checkboxInput"></label><span class="agree" style="font-size: 1.4rem">同意</span><a href="${contextPath}/moneymag/detail" class="agree" style="font-size: 1.4rem">《xxx协议》</a>
        </div>

        <div class="danger" id="dialog" style="color: red;">
            <span style="color: red;"></span>
        </div>

        <div class="btn_box" style="font-size: 1.4rem">
            <button type="submit" class="btn-default1" >立即加入</button>
        </div>

        <div class="hide1" id="hide1">
            <div class="background">
                <div class="pay">
                    <span>请选择支付方式</span>
                    <div class="pay_way">
                        <button id="pay" onclick="yqss.fn.pay()" style="border: none;background-color: rgba(0,0,0,0);outline: none"><img src="${contextPath}/resources/h5/image/p6-7-1pay.png"></button>
                        <img src="${contextPath}/resources/h5/image/Oval 60 Copy.png">
                        <button id="weixin" onclick="yqss.fn.weixin()" style="border: none;background-color: rgba(0,0,0,0);outline: none"><img src="${contextPath}/resources/h5/image/p6-7-1weixin.png"></button>
                    </div>
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
                    dialog.style.display="block";
                    dialog.innerHTML="还未同意该协议！";
                    flag = false;
                    return;
                }
                if(flag) {
                    $.post("${contextPath}/moneymag/hq/confirmJoinHQ",function(result) {
                        if(result.status == 0) {
                            $("#sn").val(result.tr.sn);
                            dialog.style.display="none";
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
                $("#hide1").show();
            },
            pay : function() {

                $.post("${contextPath}/weixin/payConfig?type=2&sn=" + $("#sn").val(),function(result) {
                    android.alipay(result);
                });
            },
            weixin : function() {
                $.post("${contextPath}/weixin/payConfig?type=1&sn=" + $("#sn").val(),function(result) {
                    android.weixin(result);
                });
            }
        }
    }

    $(function() {
        yqss.fn.init();
    });
</script>
</body>
</html>
