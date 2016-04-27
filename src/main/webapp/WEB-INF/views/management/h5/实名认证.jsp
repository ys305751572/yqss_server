<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" pageEncoding="UTF-8" %>
<%@ page import="java.util.Date" %>
<%@ include file="/WEB-INF/views/include.inc.jsp" %>
<!doctype html>
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport"
          content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <link href="${contextPath}/resources/h5/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/h5/css/common.css">
    <script src="${contextPath}/resources/js/jquery-1.7.2.min.js"></script>
    <title>实名认证</title>
    <style type="text/css">
        .dialog_bg {
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.3);
            z-index: 10000;
            position: absolute;
            top: 0%;
            left: 0%;
            display: none;
        }

        .dialog_box {
            position: absolute;
            top: 50%;
            left: 50%;
            z-index: 10001;
            text-align: center;
            transform: translate(-50%, -50%);
            width: 70%;
            border-radius: 4px;
            background-color: #fff;
        }

        .dialog_sucess {
            height: 100px;
            line-height: 100px;
            border-bottom: 1px solid #e8e8e8;
            font-size: 1.4rem;
            color: #a7a7a7;
        }

        .btn_confirm {
            height: 30px;
            background-color: #fff;
            width: 100%;
            outline: none;
            border: none;
            color: rgb(3, 187, 244);
            border-radius: 4px;
        }

        .btn_confirm:active {
            background-color: rgba(0, 0, 0, 0.1);
        }
    </style>
</head>
<body>
<div class="back" style="background: #dd403b;">
    <a href="javascript:history.go(-1)"><img src="${contextPath}/resources/h5/image/Fill 6.png"></a>
    <span>实名认证</span>
</div>
<div class="container">
    <input id="name" type="text" class="check_box" maxlength="10" placeholder="请输入姓名">
    <input id="idCard" type="text" class="check_box" maxlength="18" placeholder="请输入身份证号">
    <input id="bankCard" type="text" class="check_box" maxlength="16" placeholder="请输入银行卡号">
    <div class="danger" id="dialog" style="color: red;">
        <span style="color: red;"></span>
    </div>
    <div class="btn_box">
        <button type="submit" id="subInfoBtn" class="btn-default1">立即加入</button>
    </div>
</div>
<div class="dialog_bg" id="dialog_tips">
    <div class="dialog_box">
        <div class="dialog_sucess">恭喜认证成功</div>
        <button class="btn_confirm" onclick="yqss.fn.do()">确定</button>
    </div>
</div>

<script type="application/javascript">
    var yqss = {
        v: {

        },
        fn: {
            init: function () {
                $("#subInfoBtn").click(function () {
                    yqss.fn.doSubmit();
                });
            },

            doSubmit: function () {
                var flag = true;
                var name = $("#name").val();
                var idCard = $("#idCard").val();
                var bankCard = $("#bankCard").val();
                var tips = $("#dialog_tips").val();
                var dialog = $("#dialog").val();
                var myname = /^[\u4e00-\u9fa5]+$/;
                var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
                if (null == name || name == "") {
                    dialog.style.display="block";
                    dialog.innerHTML="请输入姓名！";
                    flag = false;
                    return;
                }

                if (!myname.test(name)) {
                    dialog.style.display="block";
                    dialog.innerHTML="请输入中文姓名！";
                    flag = false;
                    return;
                }

                if (idCard == "") {
                    dialog.style.display="block";
                    dialog.innerHTML="请输入身份证号！";
                    flag = false;
                    return;
                }
                if (!reg.test(idCard)) {
                    dialog.style.display="block";
                    dialog.innerHTML="身份证号不合法！";
                    flag = false;
                    return;
                }

                if (bankCard == "") {
                    dialog.style.display="block";
                    dialog.innerHTML="请输入银行卡号！";
                    flag = false;
                    return;
                }
                if (isNaN(bankCard)) {
                    dialog.style.display="block";
                    dialog.innerHTML="请输入数字银行卡号！";
                    flag = false;
                    return;
                }
                if(flag) {
                    $("#dialog_tips").show();
                    }

                return flag;
            },

            do: function () {
                window.location.href = "${contextPath}/moneymag/hq/certification?name="  + $("#name").val() + '&idCard=' + $("#idCard").val() + '&bankCard=' + $("#bankCard").val();
            }

        }
    }

    $(function () {
        yqss.fn.init();
    });
</script>
</body>
</html>
