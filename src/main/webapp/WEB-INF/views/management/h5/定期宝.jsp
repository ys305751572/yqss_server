<!doctype html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/aui.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/style.css">
<script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
<title>定期宝</title>
</head>
<body>
	<div class="back" style="background: #f3736b;">
		<a href="javascript:history.go(-1)"><img src="image/Fill 6.png"></a>
		<span>定期宝</span>
	</div>
	<header>
	<div class="header_tip_box">
		<div class="header_tip">
			<span class="header_tip1">5.00</span>
			<span class="header_tip3">%</span>
			<br>
			<span class="header_tip2">年化收益率</span>
		</div>
		<div class="header_tip">
			<span class="header_tip1">6</span>
			<span class="header_tip3">个月</span>
			<br>
			<span class="header_tip2">期限</span>
		</div>
	</div>
	</header>
	<section class="content">
		<div class="content_tip"><span style="color: #777777;">已认购100.000.00元</span></div>
		<div class="aui-progress_content">
			<div class="aui-progress_box">

				<div class="aui-progress bg" style="height: 12px;">
    				<div class="aui-progress-bar main1" >
    				</div>
				</div>
			</div>
			<span class="num aui-progress_count" style="color: #797979;">60.00</span><span style="font-size: 1.2rem;color: #797979;">%</span>
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
			<span class="bottom_tip_left"><img src="image/Path + Stroke 111 (2).png">100%本息保障</span>
			<span class="bottom_tip_right">可买6.000.000.00元</span>
		</div>
	</section>
	<section class="menu">
		<div class="menu_tip">
		<a href="#"><img src="image/Fill 372.png"><br><span>当日计息</span></a>
		</div>
		<div class="menu_tip"><a href="#"><img src="image/Fill 390.png" style="padding-bottom: 11px"><br><span>快速提现</span></a></div>
		<div class="menu_tip"><a href="#"><img src="image/Group.png"><br><span>真实借款</span></a></div>
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
		<img src="image/IMG_4947.png">
		</div>
	</section>
	<section class="about">
	<div class="about_us">
		<span class="about_us_tip"><a href="关于财蜜理财.html">关于财蜜理财</a></span>
		<a href="关于财蜜理财.html"><img class="about_us_img" src="image/Fill%206%20Copy.png"></a>
	</div>
	</section>
	<footer class="footer_btn">
		<button class="btn_buy" type="button" disabled="disabled" style="border:1px solid #b5b5b5;color: #cccccc;">本期已售罄</button>
	</footer>
</div>
</div>
</body>
</html>