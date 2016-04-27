<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" pageEncoding="UTF-8" %>
<%@ page import="java.util.Date" %>
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
    <title>设置交易密码</title>
</head>
<body>
<div class="back" style="background: #dd403b;">
    <a href="javascript:history.go(-1)"><img src="${contextPath}/resources/h5/image/Fill 6.png"></a>
    <span>设置交易密码</span>
</div>
<div class="container">
    <form class="form_inline">
        <div class="password_box">
            <label for="password1">交易密码</label>
            <input type="password" class="form_password" id="password" placeholder="请输入6-10位字母数字密码">
        </div>
        <div class="password_box">
            <label for="password">确认密码</label>
            <input type="password" class="form_password" id="password1" placeholder="再次输入密码">
        </div>
    </form>

    <div class="danger" id="dialog" style="color: red;">
        <span style="color: red;"></span>
    </div>

    <div class="btn_box">
        <button type="submit" class="btn-default1"  onclick="yqss.fn.doSubmit();">确定</button>
    </div>
</div>

<script type="text/javascript">
    var yqss = {
        v: {},
        fn: {
            init: function () {
                $("#subInfoBtn").click(function () {
                    yqss.fn.doSubmit();
                });
            },

            doSubmit: function () {
                var flag = true;
                var password = $("#password").val();
                var password1 = $("#password1").val();
                if (null == password || password == "") {
//                    $sixmac.notify("请输入交易密码", "error");
//                    alert("请输入交易密码");
                    dialog.style.display="block";
                    dialog.innerHTML="请输入交易密码！";
                    flag = false;
                    return;
                }
                if (password.length < 5 || password.length > 11) {
//                    $sixmac.notify("请输入5~10位交易密码", "error");
//                    alert("请输入5~10位交易密码");
                    dialog.style.display="block";
                    dialog.innerHTML="请输入5~10位交易密码！";
                    flag = false;
                    return;
                }
                if (null == password1 || password1 == "") {
//                    $sixmac.notify("请输入确认密码", "error");
//                    alert("请输入确认密码");
                    dialog.style.display="block";
                    dialog.innerHTML="请输入确认密码！";
                    flag = false;
                    return;
                }
                if (password1 != password) {
//                    $sixmac.notify("两次密码输入不一致，请重新输入！", "error");
//                    alert("两次密码输入不一致，请重新输入！");
                    dialog.style.display="block";
                    dialog.innerHTML="两次密码输入不一致，请重新输入！";
                    flag = false;
                    return;
                }
                if(flag) {
                    window.location.href = "${contextPath}/moneymag/hq/findPassword?password=" + $("#password").val();
                }

                return flag;
            }

        }
    }
    $(function () {
        yqss.fn.init();
    });
</script>

</body>
</html>
