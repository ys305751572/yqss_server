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
    <title>输入交易密码</title>
</head>
<body>
<div class="back" style="background: #dd403b;">
    <a href="javascript:history.go(-1)"><img src="image/Fill 6.png"></a>
    <span>输入交易密码</span>
</div>
<div class="container">
    <form class="form_inline">
        <div class="password_box">
            <label for="password">交易密码</label>
            <input type="password" class="form_password" id="password" placeholder="请输入6-10位字母数字密码">
        </div>
        <div>
        <span class="tips " style="width: 40%;position: relative;top: 0px;float: right;"><a href="忘记交易密码.html">忘记密码？</a></span>
        </div>
    </form>

    <div class="btn_box">
        <button type="button" class="btn-default1"  onclick="yqss.fn.doSubmit();">确定</button>
    </div>
</div>

<script type="text/javascript">
    var yqss = {
        v: {
            user_id:"yqss",
        },
        fn: {
            init: function () {
                $("#subInfoBtn").click(function () {
                    yqss.fn.doSubmit();
                });
            },

            doSubmit: function () {
                var flag = true;
                var password = $("#password").val();
                if (null == password || password == "") {
//                    $sixmac.notify("请输入交易密码", "error");
                    alert("请输入交易密码");
                    flag = false;
                    return;
                }
                if (password.length < 5 || password.length > 11) {
//                    $sixmac.notify("请输入5~10位交易密码", "error");
                    alert("请输入5~10位交易密码");
                    flag = false;
                    return;
                }
                if(flag) {
                    $.post("${contextPath}/moneymag/hq/confirmPwd?password=" + $("#password").val(),function(result) {
                        if(result == "SUCCESS") {

                            window.location.href = "${contextPath}/moneymag/hq/toConfirmPage";


                        }
                        else {
                            alert("密码错误");
                        }
                    });
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
