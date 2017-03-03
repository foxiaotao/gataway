$(function() {
	$('#UserdataTable').datagrid({
		url : $("#ctxHiidenId").val() + "/User/findByPage.do",
		columns : [ [ 
		{field:'ck',checkbox:true,width:2}, 
		{
			field : 'id', // 这个对应的是实体类类里面属性
			title : 'id号',
			width : '10%',
			align : 'center'
		}, 
		{
			field : 'name',
			title : '名称',
			width : '10%',
			align : 'center'
		}, {
			field : 'addr',
			title : '地址',
			width : '10%',
			align : 'center'
		}, {
			field : 'desc',
			title : '描述',
			width : '10%',
			align : 'center'
		}, {
			field : 'birth',
			title : '出生',
			width : '10%',
			align : 'center'
		}, {
			field : 'qq',
			title : 'QQ',
			width : '10%',
			align : 'center'
		}, {
			field : 'gender',
			title : '性别',
			width : '10%',
			align : 'center'
		}, {
			field : 'cellphone',
			title : '电话',
			width : '10%',
			align : 'center'
		}, {
			field : 'username',
			title : '登录名',
			width : '10%',
			align : 'center'
		}, {
			field : 'isdelete',
			title : '装态',
			width : '10%',
			align : 'center'
		}
		] ],
		toolbar : [ {
			text : '新增',
			iconCls : 'icon-add',
			handler : function() {
				$('#addUser').dialog('open');
			}
		}, '-', {
			text : '更新',
			iconCls : 'icon-edit',
			handler : function() {
				var selections = $('#UserdataTable').datagrid('getSelections');//返回第一个被选中的行
				if( selections.length !=1){
					showWarningDialog("请选择一行数据！");
					return;
				}
				$('#editUser').dialog('open');
				
				var row = selections[0];
				var ele = $(".editUserField");
				var fieldName = "";
				for (var i = 0; i < ele.length; i++) {
					var input = $(ele[i]);
					fieldName = input.attr("textboxname");
					input.next().children("input[type=text]").click();
					input.next().children("input[type=text]").focus();
					input.next().children("input[type=text]").val(row[fieldName])
				}
			}
		}, '-', {
			text : '删除',
			iconCls : 'icon-remove',
			handler : function() {
				var selections = $('#UserdataTable').datagrid('getSelections');//返回第一个被选中的行
				if( selections.length < 1){
					showWarningDialog("请至少选择一行数据！");
					return;
				}
				deleteSubmit(selections);
			}
		}, '-' ],
		onLoadSuccess : function() {
			$('#UserdataTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		},
		iconCls:'icon-edit', //图标  
        singleSelect:false, //多选  
        height:360, //高度  
        fitColumns: true, //自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。  
        striped: true, //奇偶行颜色不同  
        collapsible:true,//可折叠  
		pagination : true,
		rownumbers : true,
		pageNumber : 1,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50 ]

	});
	//初始化关闭
//	closeDialog();
})

function closeDialog(){
	if($('#addUser')){
		$('#addUser').dialog('close');
	}
	if($('#editUser')){
		$('#editUser').dialog('close');
	}
}
//添加提交
function submitAddForm(){
	var ele = $(".addUserField");
	var param = {};
	for (var i = 0; i < ele.length; i++) {
		var input = $(ele[i]);
		var dop = input.attr("data-options");//属性名，是否必须
		var fieldNameChn = "";//属性中文名称
		var fieldValue = input.val();//属性的值
		if(dop!=undefined){
			fieldNameChn = input.attr("label");
			fieldNameChn = fieldNameChn.replace(":","");
			//如果包含required:true 就是必填字段，注意jsp页面，data-options属性的值不要有多余的空格
			if(dop.indexOf("required:true") >-1 || (dop.indexOf("required") > -1 && dop.indexOf("true") > -1)){
				if(fieldValue==undefined || fieldValue==null || fieldValue==""){
					showWarningDialog(fieldNameChn + " 不能为空！");
					return;
				}
			}
		}
		param[input.attr("textboxname")] = fieldValue;
		
	}
	$.ajax({
		url : $("#ctxHiidenId").val() + "/User/insert.do",
		cache : false,
		type : "POST",
		dataType : "json",
		data :param,
		success : function(result) {  
			if (result != null && result != undefined && result.success != null && result.success != undefined) {
				showSuccessDialog(result.success);
				searchUser();
				$('#addUserForm').form('clear');
				$('#addUser').dialog('close');
			}else{
				showErrorDialog(result.msg);
//				showWarningDialog("警告消息提示");
			}
		},
		error : function() {
			showErrorDialog("添加失败");
			return;
		}
	}); 
}
//修改提交
function submitEditForm(){
	var ele = $(".editUserField");
	var param = {};
	for (var i = 0; i < ele.length; i++) {
		var input = $(ele[i]);
		var dop = input.attr("data-options");//属性名，是否必须
		var fieldNameChn = "";//属性中文名称
		var fieldValue = input.val();//属性的值
		if(dop!=undefined){
			fieldNameChn = input.attr("label");
			fieldNameChn = fieldNameChn.replace(":","");
			//如果包含required:true 就是必填字段，注意jsp页面，data-options属性的值不要有多余的空格
			if(dop.indexOf("required:true") >-1 || (dop.indexOf("required") > -1 && dop.indexOf("true") > -1)){
				if(fieldValue==undefined || fieldValue==null || fieldValue==""){
					showWarningDialog(fieldNameChn + " 不能为空！");
					return;
				}
			}
		}
		param[input.attr("textboxname")] = fieldValue;
		
	}
	$.ajax({
		url : $("#ctxHiidenId").val() + "/User/update.do",
		cache : false,
		type : "POST",
		dataType : "json",
		data :param,
		success : function(result) {  
			if (result != null && result != undefined && result.success != null && result.success != undefined) {
				showSuccessDialog(result.success);
				searchUser();
				$('#editUserForm').form('clear');
				$('#editUser').dialog('close');
			}else{
				showErrorDialog(result.msg);
//				showWarningDialog("警告消息提示");
			}
		},
		error : function() {
			showErrorDialog("编辑失败");
			return;
		}
	}); 
}
//删除提交
function deleteSubmit(selections){
	var idsStr = "";
	for (var i = 0; i < selections.length; i++) {
		if(i>0){
			idsStr += ",";
		}
		idsStr += selections[i].id;
	}
	var param = {};
	param['ids'] = idsStr;
	$.ajax({
		url : $("#ctxHiidenId").val() + "/User/delete.do",
		cache : false,
		type : "POST",
		dataType : "json",
		data :param,
		success : function(result) {  
			if (result != null && result != undefined && result.success != null && result.success != undefined) {
				showSuccessDialog(result.success);
				searchUser();
				$('#addUserForm').form('clear');
				$('#addUser').dialog('close');
			}else{
				showErrorDialog(result.msg);
//				showWarningDialog("警告消息提示");
			}
		},
		error : function() {
			showErrorDialog("删除失败");
			return;
		}
	}); 
}

// 表格查询
function searchUser() {
	var params = $('#UserdataTable').datagrid('options').queryParams; // 先取得
	// datagrid
	// 的查询参数
	var fields = $('#queryUserForm').serializeArray(); // 自动序列化表单元素为JSON对象
	$.each(fields, function(i, field) {
		params[field.name] = field.value; // 设置查询参数
	});
	$('#UserdataTable').datagrid('reload'); // 设置好查询参数 reload 一下就可以了
}
// 清空查询条件
function clearUserForm() {
	$('#queryUserForm').form('clear');
	searchUser();
}
