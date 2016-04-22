<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<!doctype html>
<html lang="zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<link href="${contextPath}/resources/h5/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${contextPath}/resources/h5/css/style.css">
<title>关于财蜜理财</title>
</head>
<body>
	<div class="back" style="background: #dd403b;">
        <a href="javascript:history.go(-1)"><img src="${contextPath}/resources/h5/image/Fill 6.png"></a>
        <span>关于财蜜理财</span>
    </div>
	<header class="header">
		<img src="${contextPath}/resources/h5/image/p6-1-1 banner.png">
	</header>
	<section class="container main">
		<p>
			${common.content}
		</p>
	</section>
</body>
</html>