<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/include.inc.jsp" %>
<!doctype html>
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport"
          content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <link href="${contextPath}/resources/h5/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/h5/css/style.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/h5/css/lrtk.css"/>
    <script type="text/javascript" src="${contextPath}/resources/h5/js/jquery-2.1.4.min.js"></script>
    <script type="text/javascript" src="${contextPath}/resources/h5/js/koala.min.1.5.js"></script>
    <title>理财</title>
</head>
<body style="background: #f7f7f7">
<div class="back" style="background: #dd403b;">
    <a href="javascript:history.go(-1)"><img src="${contextPath}/resources/h5/image/Fill 6.png"></a>
    <span>理财</span>
</div>
<header class="header banner" style="height: 180px;">
    <div id="fsD1" class="focus">
        <div id="D1pic1" class="fPic">
            <c:forEach items="${list}" var="banner">
                <div class="fcon" style="display: none;">
                    <a target="_blank" href="#"><img src="${banner.path}" style="opacity: 1; "></a>
                    <span class="shadow"><a target="_blank" href="#"></a></span>
                </div>
            </c:forEach>
            <div class="fbg">
                <div class="D1fBt" id="D1fBt">
                    <c:forEach items="${list}" var="banner">
                    <a href="javascript:void(0)" hidefocus="true" target="_self" class=""><i>1</i></a>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript">
        Qfast.add('widgets', {path: "${contextPath}/resources/h5/js/terminator2.2.min.js", type: "js", requires: ['fx']});
        Qfast(false, 'widgets', function () {
            K.tabs({
                id: 'fsD1',   //焦点图包裹id
                conId: "D1pic1",  //** 大图域包裹id
                tabId: "D1fBt",
                tabTn: "a",
                conCn: '.fcon', //** 大图域配置class
                auto: 1,   //自动播放 1或0
                effect: 'fade',   //效果配置
                eType: 'click', //** 鼠标事件
                pageBt: true,//是否有按钮切换页码
                bns: ['.prev', '.next'],//** 前后按钮配置class
                interval: 3000  //** 停顿时间
            })
        })
    </script>
    <!-- 代码 结束 -->
</header>


<section class="section">
    <a href="${contextPath}/moneymag/hq/detail" class="content_box">
        <div class="topbar">
            <div class="topbar_left">
                <span class="topbar_left_tips">${map.hq.name}</span>
                <span class="topbar_left_tips1">当日计息</span>
            </div>
            <div class="topbar_right">
                <span>${map.hq.introduce}</span>
            </div>
        </div>
        <div class="flex">
            <div class="flex_box">
                <ul>
                    <li>年化收益率</li>
                    <li style="color: #ea433e;font-size: 1.5rem;">${map.hq.yearYield}</li>
                </ul>
            </div>
            <div class="flex_box">
                <ul>
                    <li>期限</li>
                    <li style="font-size: 1.5rem;color: #797979;">${map.hq.timelimit}</li>
                </ul>
            </div>
        </div>
    </a>
</section>
<section class="section">
    <a href="${contextPath}/moneymag/dq/list">
        <div class="topbar">
            <div class="topbar_left">
                <span class="topbar_left_tips2">${map.dq.name}</span>
                <span class="topbar_left_tips1">当日计息</span>
            </div>
            <div class="topbar_right">
                <span>${map.dq.introduce}</span>
            </div>
        </div>
        <div class="flex">
            <div class="flex_box">
                <ul>
                    <li>年化收益率</li>
                    <li style="color: #ea433e;font-size: 1.5rem;">${map.dq.yearYield}</li>
                </ul>
            </div>
            <div class="flex_box">
                <ul>
                    <li>期限</li>
                    <li style="font-size: 1.5rem;color: #797979;">${map.dq.timelimit}</li>
                </ul>
            </div>
        </div>
    </a>
</section>
</body>

<script type="text/javascript">
    var yqss = {
        v: {},
        fn: {
            init: function () {
                $("topbar_left_tips").click(function () {
                    yqss.fn.doSubmit();
                });
                $("topbar_left_tips2").click(function () {
                    yqss.fn.doSubmit2();
                })
            },

            doSubmit: function () {
                window.location.href = "${contextPath}/monevmag/hq/detail";
            },

            doSubmit2: function () {
                window.location.href = "${contextPath}/monevmag/dq/detail";
            }
        }
    }

    $(document).ready(function () {
        yqss.fn.init();
    });
</script>


</html>
