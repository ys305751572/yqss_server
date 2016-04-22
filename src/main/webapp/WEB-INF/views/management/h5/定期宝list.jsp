<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
         pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/include.inc.jsp" %>
<!doctype html>
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport"
          content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <link href="${contextPath}/resources/h5/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/h5/css/style.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/h5/css/aui.css">
    <script type="text/javascript" src="${contextPath}/resources/h5/js/jquery-2.1.4.min.js"></script>
    <title>定期宝</title>
    <style type="text/css">
        .section:active {
            background: rgba(0, 0, 0, 0.011);
        }
    </style>
</head>
<body style="background: #f7f7f7;">
<div class="back" style="background: #dd403b;">
    <a href="javascript:history.go(-1)"><img src="${contextPath}/resources/h5/image/Fill 6.png"></a>
    <span>定期宝</span>
</div>
<c:forEach items="${list}" var="dod" varStatus="index">
    <section class="section">
        <a href="${contextPath}/moneymag/dq/detail?id=${dod.id}">
            <div class="topbar">
                <div class="topbar_left">
                    <input type="hidden" id="id" name="id" value="${dod.id}">
                    <span class="topbar_left_tips1">${dod.month}个月</span>
                </div>
                <div class="topbar_right">
                    <span style="color: #ffb80c;">
                    已认购
                        <c:if test="${dod.residue2 le dod.moneyLimit}">
                            ${dod.residue2}元
                        </c:if>
                         <c:if test="${dod.residue2 eq dod.moneyLimit}">
                             已售罄
                         </c:if>
                    </span>
                </div>
            </div>
            <div class="content_tip"><span style="color: #ea433e;font-size: 1.8rem;">${dod.yearYield}0%</span> <span
                    style="color: #777777;">年化</span></div>
            <div class="aui-progress_content">
                <div class="aui-progress_box">

                    <div class="aui-progress bg" style="height: 12px;">
                        <div class="aui-progress-bar main${index.index}">
                        </div>
                    </div>
                </div>
                <span class="num${index.index} aui-progress_count" style="color: #797979;">${dod.percent}</span><span
                    style="font-size: 1.2rem;color: #797979;">%</span>
                <script type="text/javascript">
                    $(function () {
                        var _width = $('.num' +${index.index});//获取百分比数值
                        var width = _width.text();
                        var t = 50;
                        $('.main' + ${index.index}).css('width', width + '%');//将数值赋值给main的width
                        $('.main' + ${index.index}).css('background', "#ffb80c");
                        if (width < t) {
                            $('.main' + ${index.index}).css('background', "#ffb80c");
                        }
                        else if (width > t) {
                            $('.main' + ${index.index}).css('background', "#4edcc4");
                        }
                        ;
                    });
                </script>
            </div>
            <div class="bottom_tip">
                <span class="bottom_tip_left"><img src="${contextPath}/resources/h5/image/Path + Stroke 111 (2).png">100%本息保障</span>
                <span class="bottom_tip_right">可买<span class="number">${dod.residue}</span>元</span>
            </div>
        </a>
    </section>
</c:forEach>
<br>
<br>
<br>
</body>
</html>