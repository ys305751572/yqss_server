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
    <div class="form-inline" style="padding-top: 0px;">
        <div  class="form-group" style="margin-bottom: 0px;padding-bottom: 1.3em;padding-top: 1.3em;">
            <label for="hqId" class="form-group-lable" style="margin-bottom: 0px;font-weight: 300; font-size: 1.3rem;">投资金额</label>
            <span style="font-size: 1.3rem;">元</span>
            <input type="text" id="hqId"  style="text-align: right;font-size: 1.3rem;color: #b7b7b7;" onblur="yqss.fn.doOnBluer();" />
        </div>
        <div  class="form-group form-group2" style="padding-bottom: 1.3em;padding-top: 1.3em;">
            <label for="input_check1" style="margin-bottom: 0px;font-weight:300;font-size: 1.3rem;">预计每日收益</label>
            <span style="color: #de433f;font-size: 1.3rem;">元</span>
            <input type="text" id="input_check1" style="font-size: 1.3rem;color: #de433f" readonly="readonly" placeholder=""/>
        </div>
        <div class="danger" id="dialog">
            <span style="color: red;">请填写投资金额!!!</span>
        </div>
        <div class="danger" id="dialog_tips">
            <span style="color: red;">请输入数字！！！</span>
        </div>
        <div class="btn_box" style="font-size: 1.4rem;">
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
            doOnBluer : function() {
                if(!/^\d+(?:.\d{1,2})?$/.test($("#hqId").val())){
                    dialog.style.display="block";
                    dialog.innerHTML="请输入数字!";
                    $("#input_check1").val("");
                }

                $.ajax({
                    "url" : "${contextPath}/moneymag/hq/onBluerhq",
                    "type" : "post",
                    "data" : {"money":  $("#hqId").val()},
                    "dataType" : "json",
                    "success" : function(result) {
                        console.log("success:" + result.data.object.earnings + "===result.result:" + result.data.object.result);
                        if(result.data.object.result != null && result.data.object.result != "") {
                            $("#input_check1").val("");
                            dialog.style.display="block";
                            dialog.innerHTML=result.data.object.result;

                        }
                        else {
                            $("#input_check1").val(result.data.object.earnings);
                            dialog.style.display="none";
                        }

                    },
                    "error" : function(result) {
                        console.log("error:" + result);


                    },
                });

            },

            doSubmit : function() {
                var flag = true;
                var money = $("#hqId").val();
                var residue = ${map.residue};
                if(money == null || money == "") {
                    dialog.style.display="block";
                    dialog.innerHTML="请输入投资金额!";
                    flag = false;
                    return;
                }

                if(!/^\d+(?:.\d{1,2})?$/.test($("#hqId").val())){
                    $("#input_check1").val("");
                    dialog.style.display="block";
                    dialog.innerHTML="请输入数字!";
                    flag = false;
                    return;
                }

                if (money > residue) {
                    $("#input_check1").val("");
                    dialog.style.display="block";
                    dialog.innerHTML="投资金额超过上限，请重新输入!";
                    flag = false;
                    return;
                }

                if(flag){
                     window.location.href = "${contextPath}/moneymag/hq/addHQBao?money=" + $("#hqId").val();
                }
        }
    }
    }
    $(function() {
        yqss.fn.init();
    });
</script>
</body>
</html>
