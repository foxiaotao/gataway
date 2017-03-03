<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="base.jsp" %>     

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>product</title>

<%-- <link rel="stylesheet" type="text/css" href="<%=basePath%>/plugins/jquery-easyui/themes/default/easyui.css"> --%>
<script src="<%=basePath%>/viewJs/role.js"></script>

</head>
<body>
	 <input id="ctxHiidenId" type="hidden" value="${ctx}"/>
	 <form id="queryRoleForm" style="margin:10;text-align: center;">  
        <table width="100%">  
            <tr>  
	            <td>名字：<input name="name" style="width: 200"></td>  
	            <td>描述：<input name="descp" style="width: 200"></td>  
	            <td align="center"><a href="#" onclick="clearRoleForm();" class="easyui-linkbutton" iconCls="icon-search">清空</a></td>  
	            <td align="center"><a href="#" onclick="searchRole();" class="easyui-linkbutton" iconCls="icon-search">查询</a></td> 
            </tr>  
        </table>  
    </form>  
    <div style="padding:10" id="tabdiv">  
        <table id="roleDataTable"></table>  
    </div>  

	<!-- addElement -->
	
	<div id="addRole" class="easyui-dialog" closed="true" title="添加角色"  style="display:none;width:400px;height:500px;padding:10px">
		<div style="margin:20px 0;"></div>
			<form id="addRoleForm" class="easyui-form" method="post" data-options="validate:true">
				<div style="margin-bottom:20px">
					<input class="easyui-textbox addRoleField" label="名称:" name="name" style="width:100%" data-options="required:true">
				</div>
				<div style="margin-bottom:20px">
					<input class="easyui-textbox addRoleField" label="描述:" name="descp" style="width:100%" data-options="required:true">
				</div>
			</form>
			<div style="text-align:center;padding:5px 0">
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitRoleAddForm()" style="width:80px">Submit</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="$('#addRole').dialog('close')" style="width:80px">Cancel</a>
			</div>
	</div>
	<!-- editElement --> 
	
	<div id="editRole" class="easyui-dialog" closed="true" title="编辑角色"  style="width:400px;height:500px;padding:10px">
		<div style="margin:20px 0;"></div>
			<form id="editRoleForm" class="easyui-form" method="post" data-options="validate:false">
				<input type="text" class="easyui-textbox editRoleField" label="id:" name="rid" style="width:100%" data-options="required:true">
				<div style="margin-bottom:20px">
					<input class="easyui-textbox editRoleField" label="名称:" name="name" style="width:100%" data-options="required:true">
				</div>
				<div style="margin-bottom:20px">
					<input class="easyui-textbox editRoleField" label="描述:" name="descp" style="width:100%" data-options="required:true">
				</div>
			</form>
			<div style="text-align:center;padding:5px 0">
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitRoleEditForm()" style="width:80px">Submit</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="$('#editRole').dialog('close')" style="width:80px">Cancel</a>
			</div>
	</div>
	
</body>
</html>