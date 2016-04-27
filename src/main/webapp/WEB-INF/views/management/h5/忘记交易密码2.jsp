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
            var code = $("#code").val();
            var requestCode = ${map.requestCode}
            var dialog = document.getElementById("dialog");

            if(code != requestCode){
                dialog.style.display="block";
                dialog.innerHTML="验证码错误，请重新输入！";
                flag = false;
                return flag;
            }
            if(flag) {
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
    <input type="text" class="check_box" id="mobile" value="${map.mobile}">
    <div class="input_box">
        <input type="text" placeholder="请输入验证码" id="code" />
        <span><input type="button" id="codeBtn" onclick="settime(this)" value="获取验证码"/></span>
    </div>
    <div class="danger" id="dialog" style="color: red;">
        <span></span>
    </div>
    <div class="btn_box">
        <button type="submit" class="btn-default1" onclick="changeStyle();">下一步</button>
    </div>
</div>
<%--<script type="application/javascript">--%>
    <%--var yqss = {--%>
        <%--v : {--%>

        <%--},--%>
        <%--fn : {--%>
            <%--init : function() {--%>
                <%--$(".btn-default1").click(function() {--%>
                    <%--yqss.fn.doSubmit();--%>
                <%--});--%>
            <%--},--%>
            <%--doSubmit : function(btn) {--%>

                <%--$("#codeBtn").click(function(btn) {--%>
                    <%--$.ajax({--%>
                        <%--"url":"${contextPath}/moneymag/sendCode",--%>
                        <%--"type":"post",--%>
                        <%--"data": {"mobile":  $("#mobile").val()},--%>
                        <%--"dataType" : "json",--%>
                        <%--"success": function(result) {--%>
                            <%--if(result == Result.success()) {--%>
                                <%--var countdown = 60;--%>
                                <%--countdown--;--%>
                                <%--btn.setAttribute("disabled", true);--%>
                                <%--btn.value = countdown + "秒后重新发送";--%>
                                <%--setTimeout(function () {--%>
                                    <%--settime(btn)--%>
                                <%--}, 1000)--%>
                            <%--}--%>
                        <%--}--%>
                    <%--})--%>
        <%--});--%>
    <%--})--%>


<%--</script>--%>

<script type="text/javascript">
    var countdown = 60;
    function settime(btn) {
        var mobile = $('#mobile').val();
        if (null == mobile || mobile == '') {
            alert('手机号不能为空');
        } else {
            // 发送验证码
            $.ajax({
                type: "post",
                url: "${contextPath}/moneymag/sendCode",
                data: {
                    mobile: mobile
                },
                success: function (result) {
                    if (result == "success") {
                        alert(result);
                    }
                }
            });
            jian(btn);
        }
    }
    function jian(btn) {
        var tg = document.getElementById("codeBtn");
        if (countdown == 0) {
            btn.removeAttribute("disabled");
            btn.value = "点击发送验证码";
            countdown = 60;
            btn.className = "btn_sendcode1";
            tg.className = "register1";
        } else {
            countdown--;
            btn.setAttribute("disabled", true);
            $("#codeBtn").attr("value",countdown + "秒后重新发送");
            btn.className = "btn_sendcode2";
            tg.className = "register2";
            setTimeout(function () {
                jian(btn)
            }, 1000)
        }
    }
</script>

</body>
</html>
