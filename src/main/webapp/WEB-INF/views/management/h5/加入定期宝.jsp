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

    <title>加入定期宝</title>
</head>
<body>
<div class="back" style="background: #dd403b;">
    <a href="javascript:history.go(-1)"><img src="${contextPath}/resources/h5/image/Fill 6.png"></a>
    <span>加入定期宝</span>
</div>
<div class="container">
    <div class="form-inline">
        <div  class="form-group" style="margin-bottom: 0px ;">
            <label for="input_check1" style="color: #948b8b;font-size: 1.4rem;font-weight: 200;position: relative;top: 5px;">转入金额</label>
            <span style="font-size: 1.4rem">元</span>
            <input type="text" id="input_check1"  style="text-align: right;font-size: 1.4rem"  />
        </div>
        <div  class="form-group"style="margin-bottom: 0px ">
            <label for="input_check2" style="color: #948b8b;font-size: 1.4rem;font-weight: 200;position: relative;top: 5px;" >期限</label>
            <span style="font-size: 1.4rem">个月</span>
            <input type="text"  id="input_check2" style="text-align: right;font-size: 1.4rem" value="${dod.month}"></input>
        </div>
        <div  class="form-group">
            <label for="input_check3" style="color: #948b8b;font-size: 1.4rem;font-weight: 200;position: relative;top: 5px;">年化收益率</label>
            <span style="font-size: 1.4rem">%</span>
            <input type="text" id="input_check3" style="text-align: right;font-size: 1.4rem" value="${dod.yearYield}">
            </input>
        </div>
        <div class="danger" id="dialog" style="color: red;font-size: 1.4rem">
            <span style="color: red;">请填写投资金额!!!</span>
        </div>
        <div class="danger" id="dialog_tips">
            <span style="color: red;">请输入数字！！！</span>
        </div>
        <div class="btn_box" style="font-size: 1.4rem">
            <button type="submit" class="btn-default1" >立即加入</button>
        </div>
    </div>
</div>
<script type="application/javascript">
    var yqss = {
        v : {
            user_id:"yqss",
        },
        fn : {
            init : function() {
                $(".btn-default1").click(function() {
                    yqss.fn.doSubmit();
                });
            },

            doSubmit : function() {
                var flag = true;
                var money = $("#input_check1").val();
                var residue = ${map.residue};
                if (null == money || money == "") {
                    dialog.style.display="block";
                    dialog.innerHTML="请输入投资金额!";
                    flag = false;
                    return;
                }

                if(!/^\d+(?:.\d{1,2})?$/.test($("#input_check1").val())){
                    dialog.style.display="block";
                    dialog.innerHTML="请输入数字!";
                    flag = false;
                    return;
                }

                if (money > residue) {
                    dialog.style.display="block";
                    dialog.innerHTML="投资金额超过上限，请重新输入!";
                    flag = false;
                    return;
                }

                if(flag){
                    window.location.href = "${contextPath}/moneymag/dq/addDQBao?money=" + $("#input_check1").val() + '&id=' +${dod.id};
                }

            },

            <%--doOnBluer : function() {--%>
                <%--if(!/^\d+(?:.\d{1,2})?$/.test($("#input_check1").val())){--%>
                    <%--dialog.style.display="block";--%>
                    <%--dialog.innerHTML="请输入数字!";--%>
                <%--}--%>

                <%--$.ajax({--%>
                    <%--"url" : "${contextPath}/moneymag/hq/onBluerdq",--%>
                    <%--"type" : "post",--%>
                    <%--"data" : {"money":  $("#input_check1").val(),--%>
                              <%--"id": ${dod.id}},--%>
                    <%--"dataType" : "json",--%>
                    <%--"success" : function(result) {--%>
                        <%--console.log("===result.result:" + result.data.object.result);--%>
                        <%--if(result.data.object.result != null && result.data.object.result != "") {--%>
                            <%--dialog.style.display="block";--%>
                            <%--dialog.innerHTML=result.data.object.result;--%>

                        <%--}--%>
                        <%--else {--%>
                            <%--dialog.style.display="none";--%>
                        <%--}--%>

                    <%--},--%>
                    <%--"error" : function(result) {--%>
                        <%--console.log("error:" + result);--%>

                    <%--},--%>
                <%--});--%>

            <%--},--%>
        }
    }

    $(function() {
        yqss.fn.init();
    });
</script>
</body>
</html>
