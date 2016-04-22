<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<!doctype html>
<html lang="zh-cn" >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<link href="${contextPath}/resources/h5/css/bootstrap.min.css" rel="stylesheet">
<link href="${contextPath}/resources/h5/css/aui.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${contextPath}/resources/h5/css/style.css">
<script type="text/javascript" src="${contextPath}/resources/h5/js/jquery-2.1.4.min.js"></script>
<title>活期宝</title>
</head>
<body style="background: #f7f7f7">
	<div class="back" style="background: #f3736b;">
		<a href="javascript:history.go(-1)"><img src="${contextPath}/resources/h5/image/Fill 6.png"></a>
		<span>活期宝</span>
	</div>
	<header>
		<div class="header_tip">
			<span class="header_tip1">${dod.yearYield}0</span>
			<span class="header_tip3">%</span>
			<br>
			<span class="header_tip2">年化收益率</span>
		</div>
	</header>
	<section class="content">
		<div class="content_tip"><span>已认购${dod.residue2}元</span></div>
		<div class="aui-progress_content">
			<div class="aui-progress_box">

				<div class="aui-progress bg" style="height: 12px;">
    				<div class="aui-progress-bar main1" >
    				</div>
				</div>
			</div>
			<span class="num aui-progress_count" style="color: #797979;">${dod.percent}</span>
			<span style="font-size: 1.2rem;color: #797979;">%</span>
	<script type="text/javascript">	
		$(function(){	
	var _width = $('.num');//获取百分比数值
	var width = _width.text();
	var t = 50;
    $('.main1').css('width',width+'%');//将数值赋值给main的width
    $('.main1').css('background',"#ffb80c");
     if (width < t) {
    	$('.main1').css('background',"#ffb80c");
    }
    else if (width > t) {
    	$('.main1').css('background',"#4edcc4");
	};
});
</script>
</div>

		<div class="bottom_tip">
			<span class="bottom_tip_left"><img src="${contextPath}/resources/h5/image/Path + Stroke 111 (2).png">100%本息保障</span>
			<span class="bottom_tip_right">可买${dod.residue}元</span>
		</div>
	</section>
	<section class="menu">
		<div class="menu_tip">
		<a href="#"><img src="${contextPath}/resources/h5/image/Fill 372.png"><br><span>当日计息</span></a>
		</div>
		<div class="menu_tip"><a href="#"><img src="${contextPath}/resources/h5/image/Fill 390.png" style="padding-bottom: 11px"><br><span>快速提现</span></a></div>
		<div class="menu_tip"><a href="#"><img src="${contextPath}/resources/h5/image/Group.png"><br><span>真实借款</span></a></div>
	</section>
	<section class="products">
		<form class="products_introduce">
			<ul>
			<li class="caption_intro">产品介绍</li>
				<li><span class="caption_tip">活期宝&nbsp;</span>|&nbsp;随存随取 按日计息</li>
				<li>100%本息保障的活期理财产品</li>
				<li>年化收益稳定6%-8%、秒杀各路宝宝</li>
				<li>即时体现，方便快捷</li>
				<li>适合人群：资金短暂闲置的朋友</li>
			</ul>
		</form>
		<div class="products_img">
		<img src="${contextPath}/resources/h5/image/IMG_4947.png">
		</div>
	</section>
	<section class="about">
	<div class="about_us">
		<span class="about_us_tip"><a href="${contextPath}/moneymag/about">关于财蜜理财</a></span>
		<a href="${contextPath}/moneymag/about"><img class="about_us_img" src="${contextPath}/resources/h5/image/Fill%206%20Copy.png"></a>
	</div>
	</section>
	<footer class="footer_btn">
		<a href="${contextPath}/moneymag/hq/addHQIndex"><button class="btn_buy" type="button">我要购买</button></a>
	</footer>


</body>
</html>