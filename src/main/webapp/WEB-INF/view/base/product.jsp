<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../include/include.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script src="${ctx}/viewJs/product.js"></script>
<style type="text/css">
    .button_cls_all{border:0px},
</style>
<title>product</title>
	<script type="text/javascript">
	    var ctx = '${ctx}';
		Ext.onReady(function() {
	    	var Product = new  com.simon.gataway.Product({
				region: 'center',
				ctx: ctx,
			});
	    	var viewport = new Ext.Viewport({
				layout: 'border',
				items: [Product]
			});
	    });	
	 </script>
</head>
<body>
	
</body>
</html>