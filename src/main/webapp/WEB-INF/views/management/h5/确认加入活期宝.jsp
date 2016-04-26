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
    <style type="text/css">
        .checkbox1 {
            position: relative;
        }
        .checkbox1 label {
            display: block;
            width: 28px;
            height: 28px;
            border-radius: 15px;
            -webkit-transition: all .5s ease;
            -moz-transition: all .5s ease;
            -o-transition: all .5s ease;
            -ms-transition: all .5s ease;
            transition: all .5s ease;
            cursor: pointer;
            position: absolute;
            top: -4px;
            left: 0px;
            z-index: 1;
            background: #fff;
            border: 2px solid #e8e8e8;
            -webkit-box-shadow:inset 0px 1px 3px rgba(0,0,0,0.5);
            -moz-box-shadow:inset 0px 1px 3px rgba(0,0,0,0.5);

        }
        .checkbox1 input[type=checkbox]:checked + label {
            background: url("../image/Group3.png");
        }
        .checkbox1 input{
            transform: translate(20%,-25%);
        }
    </style>
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

        <div class="checkbox1">
            <input type="checkbox" value="1" id="checkboxInput" name="radio2"/>
            <label for="checkboxInput"></label>
            <span style="position: relative;left: 40px;">同意<a href="${contextPath}/moneymag/detail" style="color: #e9827f;text-decoration: none;">《xxx协议》</a>
            </span>
        </div>

        <div class="btn_box">
            <button type="submit" class="btn-default1" >立即加入</button>
        </div>

        <div class="hide1" id="hide1">
            <div class="background">
                <div class="pay">
                    <span>请选择支付方式</span>
                    <div class="pay_way">
                        <a id="pay" href="${contextPath}/weixin/payConfig?type=2"><img src="${contextPath}/resources/h5/image/p6-7-1pay.png"></a>
                        <img src="${contextPath}/resources/h5/image/Oval 60 Copy.png">
                        <a id="weixin" href="${contextPath}/weixin/payConfig?type=1"><img src="${contextPath}/resources/h5/image/p6-7-1weixin.png"></a>
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
                    alert("还未同意该协议");
                    flag = false;
                    return;
                }
                if(flag) {
                    $.post("${contextPath}/moneymag/hq/confirmJoinHQ",function(result) {
                        if(result == "SUCCESS") {

                            //yqss.fn.show();
                            $("#hide1").show();

                        }
                        else {
                            alert("密码错误");
                        }
                    });
                }
            },
            <%--show : function() {--%>
                <%--//展示第三方支付标志--%>
                <%--$("#hide1").show();--%>
            <%--},--%>
            <%--pay : function() {--%>
                <%--//window.location.href = "${contextPath}/weixin/payConfig";--%>
                <%--window.location.href = "${contextPath}/moneymag/dq/addDQBao?type=" + ${tr.dod.type};--%>
            <%--},--%>
            <%--weixin : function() {--%>
                <%--//window.location.href = "${contextPath}/weixin/payConfig";--%>
                <%--window.location.href = "${contextPath}/moneymag/dq/addDQBao?type=" + ${tr.dod.type};--%>
            <%--}--%>
        }
    }

    $(function() {
        yqss.fn.init();
    });
</script>
</body>
</html>
