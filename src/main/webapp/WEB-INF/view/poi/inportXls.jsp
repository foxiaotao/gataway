<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../base.jsp" %>     

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>product</title>

<%-- <link rel="stylesheet" type="text/css" href="<%=basePath%>/plugins/jquery-easyui/themes/default/easyui.css"> --%>
<script src="<%=basePath%>/viewJs/role.js"></script>

</head>
<body>
<h2>Hello World!</h2>

	<span>导入数据</span>
	<form action="${ctx}/poi/inportXls.do" method="post" enctype="multipart/form-data">
		<span>选择excel文件：</span><input type="file" name="uploadfile" />
		<input type="submit" value="上传"/>
	</form>
	<h1>=============</h1>
	<form action="${ctx}/poi/fastexcel.do" method="post" enctype="multipart/form-data">
		<span>选择excel文件：</span><input type="file" name="uploadfile" />
		<input type="submit" value="上传-下载"/>
	</form>
	
	
	<h1>=======exportModelMapBySimonExcelUtil======</h1>
	<form action="${ctx}/poi/exportModelMapUtil.do" method="post">
		<input type="submit" value="下载"/>
	</form>
	<h1>=======inportMapper======</h1>
	<form action="${ctx}/poi/inportMapper.do" method="post"  enctype="multipart/form-data">
	<span>选择excel文件：</span><input type="file" name="uploadfile" />
		<input type="submit" value="上传"/>
	</form>
	<h1>=======mapperExcel_in_out======</h1>
	<form action="${ctx}/poi/mapperExcel.do" method="post"  enctype="multipart/form-data">
	<span>选择excel文件：</span><input type="file" name="uploadfile" />
		<input type="submit" value="上传-下载"/>
	</form>
	<h1>=======simonexcel_annotation======</h1>
	<form action="${ctx}/poi/simonexcel_annotation.do" method="post" enctype="multipart/form-data">
		<span>选择excel文件：</span><input type="file" name="uploadfile" />
		<input type="submit" value="上传-下载"/>
	</form>
</body>
</html>
