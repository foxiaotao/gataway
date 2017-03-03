$(function() {
	$('#roleDataTable').datagrid({
		url : $("#ctxHiidenId").val() + "/Role/findByPage.do",
		columns : [ [ 
		{field:'ck',checkbox:true,width:2}, 
		{
			field : 'rid', // 这个对应的是实体类类里面属性
			title : 'id号',
			width : '8%',
			align : 'center'
		}, 
		{
			field : 'name',
			title : '名称',
			width : '18%',
			align : 'center'
		}, {
			field : 'descp',
			title : '描述',
			width : '18%',
			align : 'center'
		}, {
			field : 'parentid',
			title : '上级名称',
			width : '18%',
			align : 'center'
		}, {
			field : 'isdelete',
			title : '状态',
			width : '18%',
			align : 'center'
		}, {
			field : '_operate',
			width : '20%',
			align : 'center',
			formatter : function(value, rec) {
				return "<a href='' >编辑</a>";
			},
			title : '操作'
		} ] ],
		toolbar : [ {
			text : '新增',
			iconCls : 'icon-add',
			handler : function() {
				$('#addRole').dialog('open');
			}
		}, '-', {
			text : '更新',
			iconCls : 'icon-edit',
			handler : function() {
				var selections = $('#roleDataTable').datagrid('getSelections');//返回第一个被选中的行
				if( selections.length !=1){
					showWarningDialog("请选择一行数据！");
					return;
				}
				$('#editRole').dialog('open');
				
				var row = selections[0];
				var ele = $(".editRoleField");
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
				var selections = $('#roleDataTable').datagrid('getSelections');//返回第一个被选中的行
				if( selections.length < 1){
					showWarningDialog("请至少选择一行数据！");
					return;
				}
				deleteRoleSubmit(selections);
			}
		}, '-' ],
		onLoadSuccess : function() {
			$('#roleDataTable').datagrid('clearSelections'); // 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
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
	if($('#addRole')){
		$('#addRole').dialog('close');
	}
	if($('#editRole')){
		$('#editRole').dialog('close');
	}
}
//添加提交
function submitRoleAddForm(){
	var ele = $(".addRoleField");
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
		url : $("#ctxHiidenId").val() + "/Role/insert.do",
		cache : false,
		type : "POST",
		dataType : "json",
		data :param,
		success : function(result) {  
			if (result != null && result != undefined && result.success != null && result.success != undefined) {
				showSuccessDialog(result.success);
				searchRole();
				$('#addRoleForm').form('clear');
				$('#addRole').dialog('close');
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
function submitRoleEditForm(){
	var ele = $(".editRoleField");
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
		url : $("#ctxHiidenId").val() + "/Role/update.do",
		cache : false,
		type : "POST",
		dataType : "json",
		data :param,
		success : function(result) {  
			if (result != null && result != undefined && result.success != null && result.success != undefined) {
				showSuccessDialog(result.success);
				searchRole();
				$('#editRoleForm').form('clear');
				$('#editRole').dialog('close');
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
function deleteRoleSubmit(selections){
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
		url : $("#ctxHiidenId").val() + "/Role/delete.do",
		cache : false,
		type : "POST",
		dataType : "json",
		data :param,
		success : function(result) {  
			if (result != null && result != undefined && result.success != null && result.success != undefined) {
				showSuccessDialog(result.success);
				searchUser();
				$('#addRoleForm').form('clear');
				$('#addRole').dialog('close');
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
function searchRole() {
	var params = $('#roleDataTable').datagrid('options').queryParams; // 先取得
	// datagrid
	// 的查询参数
	var fields = $('#queryRoleForm').serializeArray(); // 自动序列化表单元素为JSON对象
	$.each(fields, function(i, field) {
		params[field.name] = field.value; // 设置查询参数
	});
	$('#roleDataTable').datagrid('reload'); // 设置好查询参数 reload 一下就可以了
}
// 清空查询条件
function clearRoleForm() {
	$('#queryRoleForm').form('clear');
	searchUser();
}
