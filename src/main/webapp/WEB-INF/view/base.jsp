<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% 
	String basePath = request.getContextPath(); 
%>
<c:set var="ctx" value="${pageContext.request.contextPath}/"/>
<link rel="stylesheet" href="<%=basePath%>/plugins/jquery-easyui/themes/default/easyui.css">
<link rel="stylesheet" href="<%=basePath%>/plugins/jquery-easyui/themes/icon.css">
<link rel="stylesheet" href="<%=basePath%>/plugins/dialog/assets/css/dialog.css">
<!-- 自定义css ：myeasyui-base.css-->
<link rel="stylesheet" href="<%=basePath%>/css/myeasyui-base.css">

<script src="<%=basePath%>/plugins/jquery-easyui/jquery.min.js"></script>
<script src="<%=basePath%>/plugins/jquery-easyui/easyloader.js"></script>
<script src="<%=basePath%>/plugins/jquery-easyui/jquery.easyui.min.js"></script>
<script src="<%=basePath%>/plugins/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
<script src="<%=basePath%>/plugins/dialog/assets/js/dialog/jquery.gritter.min.js"></script>
<script src="<%=basePath%>/plugins/dialog/assets/js/dialog/common.js"></script>
<!-- 自定义js ：myeasyui-base.js-->
<script src="<%=basePath%>/viewJs/myeasyui-base.js"></script>
<html>

	<body>
		<input id="ctxHiidenId" type="hidden" value="${ctx}"/>
	</body>
</html>