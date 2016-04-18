<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<!doctype html>
<html lang="zh-cn" >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<link href="${contextPath}/resources/h5/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${contextPath}/resources/h5/css/style.css">
<title>理财</title>
</head>
<body style="background: #f7f7f7">
	<div class="back" style="background: #dd403b;">
        <a href="javascript:history.go(-1)"><img src="${contextPath}/resources/h5/image/Fill 6.png"></a>
        <span>理财</span>
    </div>
	<header class="header">
		<a href="关于财蜜理财.jsp"><img src="${contextPath}/resources/h5/image/P6  banner.png"></a>
	</header>
	<section class="section">
		 <div class="topbar">
		 	<div class="topbar_left">
		 		<span class="topbar_left_tips"><a href="活期宝.jsp">活期宝</a></span>
		 		<span class="topbar_left_tips1">当日计息</span>
		 	</div>
		 	<div class="topbar_right">
		 		<span>100%本息保障</span>
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
		 			<li style="font-size: 1.5rem;color: #797979;">随存随取</li>
		 		</ul>
		 	</div>
		 </div>
	</section>
	<section class="section">
		 <div class="topbar">
		 	<div class="topbar_left">
		 		<span class="topbar_left_tips2"><a href="定期宝list.jsp">定期宝</a></span>
		 		<span class="topbar_left_tips1">当日计息</span>
		 	</div>
		 	<div class="topbar_right">
		 		<span>100%本息保障</span>
		 	</div>
		 </div>
		 <div class="flex">
		 	<div class="flex_box">
		 		<ul>
		 			<li>年化收益率</li>
		 			<li style="color: #ea433e;font-size: 1.5rem;">8-12%</li>
		 		</ul>
		 	</div>
		 	<div class="flex_box">
		 		<ul>
		 			<li>期限</li>
		 			<li style="font-size: 1.5rem;color: #797979;">3-12个月</li>
		 		</ul>
		 	</div>
		 </div>
	</section>
</body>
</html>