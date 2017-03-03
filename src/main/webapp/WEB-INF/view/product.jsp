<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="base.jsp" %>     

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>product</title>

<%-- <link rel="stylesheet" type="text/css" href="<%=basePath%>/plugins/jquery-easyui/themes/default/easyui.css"> --%>
<script src="<%=basePath%>/viewJs/product.js"></script>

</head>
<body>
	 <input id="ctxHiidenId" type="hidden" value="${ctx}"/>
	 <form id="queryForm" style="margin:10;text-align: center;">  
        <table width="100%">  
            <tr>  
            <td>名字p：<input name="product" style="width: 200"></td>  
            <td>属性：<input name="attr" style="width: 200"></td>  
<!--             <td>属性：<input class="easyui-numberspinner" name="attr" min="1" max="120" increment="1" style="width:200px;"></input></td>   -->
            <td align="center"><a href="#" onclick="clearForm();" class="easyui-linkbutton" iconCls="icon-search">清空</a></td>  
            </tr>  
            <tr>  
            <td>价格：<input name="unit" style="width: 200" class="Wdate"></td>  
            <td>状态：<input id="deptCombo" name="status" style="width: 200"></td>  
            <td align="center"><a href="#" onclick="searchProduct();" class="easyui-linkbutton" iconCls="icon-search">查询</a></td>  
            </tr>  
        </table>  
    </form>  
    <div style="padding:10" id="tabdiv">  
        <table id="dataTable"></table>  
    </div>  
    <div style="padding:10" id="tabdiv">  
        <table id="dataTable2"></table>  
    </div>  

	<!-- addElement -->
	
	<div id="addElement" class="easyui-dialog" closed="true" modal="true" shadow="false" title="add Element"  style="display:none;width:350px;height:400px;padding:10px">
		<div style="margin:20px 0;"></div>
			<form id="addElementForm" class="easyui-form" method="post" data-options="validate:true">
				<div style="margin-bottom:20px">
					<input class="easyui-textbox addField" label="名称:" name="product" style="width:100%" data-options="required:true">
				</div>
				<div style="margin-bottom:20px">
					<input class="easyui-textbox addField" label="属性:" name="attr" style="width:100%" data-options="required:true">
				</div>
				<div style="margin-bottom:20px">
					<input class="easyui-textbox addField" label="价格:" name="unit" style="width:100%" data-options="required:true">
				</div>
				<div style="margin-bottom:20px">
					<input class="easyui-textbox addField" label="状态:" name="status" style="width:100%" data-options="required:false">
				</div>
			</form>
			<div style="text-align:center;padding:5px 0">
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitAddForm()" style="width:80px">Submit</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="$('#addElement').dialog('close')" style="width:80px">Cancel</a>
			</div>
	</div>
	<!-- editElement --> 
	
	<div id="editElement" class="easyui-dialog" closed="true" modal="true" shadow="false" title="edit Element"  style="width:350px;height:400px;padding:10px">
		<div style="margin:20px 0;"></div>
			<form id="editElementForm" class="easyui-form" method="post" data-options="validate:false">
				<input type="text" class="easyui-textbox editField" label="id:" name="id" style="width:100%" data-options="required:true">
				<div style="margin-bottom:20px">
					<input class="easyui-textbox editField" label="名称:" name="product" style="width:100%" data-options="required:true">
				</div>
				<div style="margin-bottom:20px">
					<input class="easyui-textbox editField" label="属性:" name="attr" style="width:100%" data-options="required:true">
				</div>
				<div style="margin-bottom:20px">
					<input class="easyui-textbox editField" label="价格:" name="unit" style="width:100%" data-options="required:true">
				</div>
				<div style="margin-bottom:20px">
					<input class="easyui-textbox editField" label="状态:" name="status" style="width:100%" data-options="required:true">
				</div>
			</form>
			<div style="text-align:center;padding:5px 0">
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitEditForm()" style="width:80px">Submit</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="$('#editElement').dialog('close')" style="width:80px">Cancel</a>
			</div>
	</div>
	
</body>
</html>