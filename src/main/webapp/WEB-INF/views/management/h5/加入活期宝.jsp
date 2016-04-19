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

    <title>加入活期宝</title>
</head>
<body>
<div class="back" style="background: #dd403b;">
    <a href="javascript:history.go(-1)"><img src="${contextPath}/resources/h5/image/Fill 6.png"></a>
    <span>加入活期宝</span>
</div>
<div class="container">
    <div class="form-inline">
        <input type="hidden" id="id" name="id" value>
        <div  class="form-group">
            <label for="hqId" class="form-group-lable">投资金额</label>
            <span>元</span>
            <input type="text" id="hqId" placeholder="请输入投资金额"style="text-align: right;" onblur="yqss.fn.doOnBluer();" ></input>
        </div>
        <div  class="form-group form-group2">
            <label for="input_check1">预计每日收益</label>
            <span style="color: #de433f">元</span>
            <input type="text" id="input_check1" readonly="readonly" placeholder=""/>
        </div>
        <div class="danger" id="dialog">
            <span style="color: red;">请填写投资金额!!!</span>
        </div>
        <div class="danger" id="dialog_tips">
            <span style="color: red;">请输入数字！！！</span>
        </div>
        <div class="btn_box">
            <button type="submit" class="btn-default1" >立即加入</button>
        </div>
    </div>
</div>
<script type="application/javascript">
    var yqss = {
        v : {},
        fn : {
            init : function() {
                $(".btn-default1").click(function() {
                    yqss.fn.doSubmit();
                });
            },
            doOnBluer : function() {
                $("#input_check1").val(50);
            },

            doSubmit : function() {

                window.location.href = "${contextPath}/moneymag/hq/addHQBao?money=" + $("#hqId").val();
            }
        }
    }

    $(function() {
        yqss.fn.init();
    });
</script>
</body>
</html>
