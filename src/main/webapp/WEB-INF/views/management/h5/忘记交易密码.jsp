<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/include.inc.jsp" %>
<!doctype html>
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <link href="${contextPath}/resources/h5/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/h5/css/common.css">
    <link href="${contextPath}/resources/h5/css/bootstrap.min.css" rel="stylesheet">
    <script src="${contextPath}/resources/js/jquery-1.7.2.min.js"></script>
    <title>忘记交易密码</title>
    <script type="text/javascript">
        function changeStyle() {
            var flag = true;
            var mobile = $("#mobile").val();
            var code = $("#code").val();
            var dialog = document.getElementById("dialog");
            if(mobile == ""){
                alert("请输入手机号码");
                return;
            }

            if(code == ""){
                alert("请输入验证码");
                return;
            }if(flag) {
                window.location.href = "${contextPath}/moneymag/hq/confirmCode?mobile=" + $("#mobile").val() + '&requestCode=' + $("#code").val();
            }
        }
    </script>
</head>
<body>
<div class="back" style="background: #dd403b;">
    <a href="javascript:history.go(-1)"><img src="${contextPath}/resources/h5/image/Fill 6.png"></a>
    <span>忘记交易密码</span>
</div>
<div class="container">
    <input type="text" class="check_box" id="mobile" placeholder="请输入手机号码">
    <div class="input_box">
        <input type="text" placeholder="请输入验证码" id="code"></input>
        <span><button id="codeBtn">获取验证码</button></span>
    </div>
    <div class="danger" id="dialog" style="color: red;">
        <span></span>
    </div>
    <div class="btn_box">
        <button type="submit" class="btn-default1" onclick="changeStyle();">下一步</button>
    </div>
</div>
<script type="application/javascript">

    $(function() {
        $("#codeBtn").click(function() {
            $.ajax({
                "url":"${contextPath}/moneymag/sendCode",
                "type":"post",
                "data": {"mobile":  $("#mobile").val()},
                "dataType" : "json",

            })
        });
    })


</script>
</body>
</html>
