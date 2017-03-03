<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="base.jsp" %>     

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>product</title>

<%-- <link rel="stylesheet" type="text/css" href="<%=basePath%>/plugins/jquery-easyui/themes/default/easyui.css"> --%>
<script src="<%=basePath%>/viewJs/user.js"></script>

</head>
<body>
	 <input id="ctxHiidenId" type="hidden" value="${ctx}"/>
	 <form id="queryUserForm" style="margin:10;text-align: center;">  
        <table width="100%">  
            <tr>  
	            <td>名字：<input name="name" style="width: 200"></td>  
	            <td>登录名：<input name="username" style="width: 200"></td>  
	            <td align="center"><a href="#" onclick="clearUserForm();" class="easyui-linkbutton" iconCls="icon-search">清空</a></td>  
	            <td align="center"><a href="#" onclick="searchUser();" class="easyui-linkbutton" iconCls="icon-search">查询</a></td> 
            </tr>  
        </table>  
    </form>  
    <div style="padding:10" id="tabdiv">  
        <table id="UserdataTable"></table>  
    </div>  

	<!-- addElement -->
	
	<div id="addUser" class="easyui-dialog" closed="true" title="add Element"  style="display:none;width:400px;height:500px;padding:10px">
		<div style="margin:20px 0;"></div>
			<form id="addUserForm" class="easyui-form" method="post" data-options="validate:true">
				<div style="margin-bottom:20px">
					<input class="easyui-textbox addUserField" label="名称:" name="name" style="width:100%" data-options="required:true">
				</div>
				<div style="margin-bottom:20px">
					<input class="easyui-textbox addUserField" label="登陆名:" name="username" style="width:100%" data-options="required:true">
				</div>
				<div style="margin-bottom:20px">
					<input class="easyui-textbox addUserField" label="性别:" name="gender" style="width:100%" data-options="required:true">
				</div>
				<div style="margin-bottom:20px">
					<input class="easyui-textbox addUserField" label="电话:" name="cellphone" style="width:100%" data-options="required:false">
				</div>
			</form>
			<div style="text-align:center;padding:5px 0">
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitAddUserForm()" style="width:80px">Submit</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="$('#addUser').dialog('close')" style="width:80px">Cancel</a>
			</div>
	</div>
	<!-- editElement --> 
	
	<div id="editUser" class="easyui-dialog" closed="true" title="edit Element"  style="width:400px;height:500px;padding:10px">
		<div style="margin:20px 0;"></div>
			<form id="editUserForm" class="easyui-form" method="post" data-options="validate:false">
				<input type="text" class="easyui-textbox editUserField" label="id:" name="id" style="width:100%" data-options="required:true">
				<div style="margin-bottom:20px">
					<input class="easyui-textbox editUserField" label="名称:" name="name" style="width:100%" data-options="required:true">
				</div>
				<div style="margin-bottom:20px">
					<input class="easyui-textbox editUserField" label="属性:" name="username" style="width:100%" data-options="required:true">
				</div>
				<div style="margin-bottom:20px">
					<input class="easyui-textbox editUserField" label="密码:" name="password" style="width:100%" data-options="required:true">
				</div>
				<div style="margin-bottom:20px">
					<input class="easyui-textbox editUserField" label="性别:" name="gender" style="width:100%" data-options="required:true">
				</div>
			</form>
			<div style="text-align:center;padding:5px 0">
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitEditUserForm()" style="width:80px">Submit</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="$('#editUser').dialog('close')" style="width:80px">Cancel</a>
			</div>
	</div>
	
</body>
</html>