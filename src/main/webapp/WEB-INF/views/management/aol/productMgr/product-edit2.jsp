<!DOCTYPE html>
<!-- release v4.1.8, copyright 2014 - 2015 Kartik Visweswaran -->
<html lang="en">
<head>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<meta charset="UTF-8" />
<title>Krajee JQuery Plugins - &copy; Kartik</title>
<link href="${contextPath}/resources/bootstrapfileinput/css/bootstrap.min.css" rel="stylesheet">
<link href="${contextPath}/resources/bootstrapfileinput/css/fileinput.css" media="all" rel="stylesheet" type="text/css" />

<script src="${contextPath}/resources/js/jquery-2.1.4.min.js"></script>
<script src="${contextPath}/resources/bootstrapfileinput/js/fileinput.js" type="text/javascript"></script>
<script src="${contextPath}/resources/bootstrapfileinput/js/bootstrap.min.js" type="text/javascript"></script>
</head>
<body>
		<form enctype="multipart/form-data">
			<div class="form-group">
				<input type="file" id="file-1" name="image" class="file"/>
			</div>
		</form>
</body>
<script>
	$("#file-1").fileinput({
		uploadUrl : '#', // you must set a valid URL here else you will get an error
		allowedFileExtensions : [ 'jpg', 'png', 'gif' ],
		overwriteInitial : false,
		maxFileSize : 1000,
		maxFilesNum : 10,
		//allowedFileTypes: ['image', 'video', 'flash'],
		slugCallback : function(filename) {
			return filename.replace('(', '_').replace(']', '_');
		}
	});
</script>
</html>