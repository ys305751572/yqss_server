<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<!doctype html>
<html lang="zh-cn" >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<link href="${contextPath}/resources/h5/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${contextPath}/resources/h5/css/style.css">
	<script type="text/javascript" src="${contextPath}/resources/h5/js/jquery-2.1.4.min.js"></script>
	<title>理财</title>
</head>
<body style="background: #f7f7f7">
	<div class="back" style="background: #dd403b;">
        <a href="javascript:history.go(-1)"><img src="${contextPath}/resources/h5/image/Fill 6.png"></a>
        <span>理财</span>
    </div>
	<header class="header">
		<img src="${contextPath}/resources/h5/image/P6  banner.png">
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
				$("topbar_left_tips").click(function() {
					yqss.fn.doSubmit();
				});
				$("topbar_left_tips2").click(function(){
					yqss.fn.doSubmit2();
				})
			},

			doSubmit:function(){
				window.location.href ="${contextPath}/monevmag/hq/detail";
			},

			doSubmit2:function(){
				window.location.href ="${contextPath}/monevmag/dq/detail";
			}
		}
	}

	$(document).ready(function () {
		yqss.fn.init();
	});
</script>



</html>
