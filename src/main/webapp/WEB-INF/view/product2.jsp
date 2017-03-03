<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="base.jsp" %>     

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>product</title>
<%-- <link rel="stylesheet" href="<%=basePath%>/css/demo.css"> --%>
<script src="<%=basePath%>/viewJs/product3.js"></script>
</head>
<body>
	 <input id="ctxHiidenId" type="hidden" value="${ctx}"/>
	 <div>
		 <form id="queryForm" style="margin:10;text-align: center;">  
	        <table width="100%">  
	            <tr>  
	            <td>名字：<input name="product" style="width: 200"></td>  
	            <td>属性：<input name="attr" style="width: 200"></td>  
	            <td align="center"><a href="#" onclick="clearForm();" class="easyui-linkbutton" iconCls="icon-search">清空</a></td>  
	            </tr>  
	            <tr>  
	            <td>价格：<input name="unit" style="width: 200" class="Wdate"></td>  
	            <td>状态：<input id="deptCombo" name="status" style="width: 200"></td>  
	            <td align="center"><a href="#" onclick="searchProduct();" class="easyui-linkbutton" iconCls="icon-search">查询</a></td>  
	            </tr>  
	        </table>  
	    </form>  
	 </div>
    <div style="padding:10" id="tabdiv">  
        <table id="dataTable"></table>  
    </div>  

	<!-- addElement -->
	<div id="addProductDialog">
	
	</div>
	<div id="addProductContent" class="easyui-panel" title="Register" style="width:100%;max-width:400px;padding:30px 60px;">
		<div style="margin-bottom:20px">
			<label for="username" class="label-top">User Name:</label>
			<input id="username" class="easyui-validatebox vali_input_style" data-options="required:true,validType:'length[3,10]',validateOnCreate:true,err:inputErrShow">
		</div>
		<div style="margin-bottom:20px">
			<label for="email" class="label-top">Email:</label>
			<input id="email" class="easyui-validatebox vali_input_style" data-options="required:true,validType:'email',validateOnCreate:false,err:inputErrShow">
		</div>
		<div style="margin-bottom:20px">
			<label for="url" class="label-top">Url:</label>
			<input id="url" class="easyui-validatebox vali_input_style" data-options="required:true,validType:'url',validateOnCreate:false,err:inputErrShow">
		</div>
		<div style="margin-bottom:20px">
			<label for="phone" class="label-top">Phone:</label>
			<input id="phone" class="easyui-validatebox vali_input_style" data-options="required:true,validateOnCreate:false,err:inputErrShow">
		</div>
	</div>
	<!-- editElement --> 
	
	<div id="editElement" class="easyui-dialog" closed="true" title="edit Element"  style="width:400px;height:500px;padding:10px">
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