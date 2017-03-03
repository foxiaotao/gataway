/**
 * 封装了easyui常用的控件
 */
$.extend({
	DataGridePanel:function(options){
		//datagrid控件默认的属性
		var defaults={
				width:'auto',
				height:400,
				dialogHeight:'auto',
				stripted:true,
				fit:true,//自动填充布局，true的时候设置高度无效
				fitColumns:false,
				loadMsg:'加载中……',
				singleSelect:true,
				pagination:true,
				rownumbers:false,
				modal:true,
				pageSize:10,
				pageList:[5,10,20,50]
		}
		
		var options=$.extend(defaults,options);
		
		$('body').css("overflow-x","auto");
		$('body').css("overflow-y","auto");
		
		var obj=$('<table id='+options.typename+'></table>');
		$('body').append(obj);
		if(options.childTypename){
			$('body').append($("<hr/>"));
			var objchild=$('<table id='+options.childTypename+'></table>');
			$('body').append(objchild);
		}
		
		
		$('body').append('<div id="tb" style="padding:0px;height:auto"><div style="margin:0px;border-top:1px solid #D4D4D4"></div></div>');
		
		//＊＊＊＊＊＊＊＊按钮开始＊＊＊＊＊＊＊＊＊
		function inner_add(){
			add(options);
		}
		
		$.each(options.T_bar,function(key,value){
			if(key==0&&value){
				$('#tb').find('div').append('<a iconCls="icon-search" plain="true" href="javascript:search(\''+options.typename+'\')">查询</a>');
			}else if(key==1&&value){
				$('#tb').find('div').append('<a iconCls="icon-add" plain="true" href="javascript:add(\''+options.typename+'\',\''+options.dialogHeight+'\')">添加</a>');
			}else if(key==2&&value){
				$('#tb').find('div').append('<a iconCls="icon-edit" plain="true" href="javascript:edit(\''+options.typename+'\',\''+options.dialogHeight+'\')">编辑</a>');
			}else if(key==3&&value){
				$('#tb').find('div').append('<a iconCls="icon-remove" plain="true" href="javascript:del(\''+options.typename+'\')">删除</a>');
			}else if(key>=4){
				$('#tb').find('div').append(value);
			}
		})
		$('#tb').find('a').linkbutton({});
		//＊＊＊＊＊＊＊＊按钮结束＊＊＊＊＊＊＊＊＊

		if(typeof(objects[options.typename].findCondition)!="undefined"){
			var fidcon=objects[options.typename].findCondition;
			for (var i = fidcon.length-1;  i>= 0; i--) {
				$('#tb').prepend('<div id="tbar'+i+'" style="padding-top:5px;padding-bottom:5px;height:auto;"></div>');
				for(var j=0;j<fidcon[i].length;j++){
					$('#tbar'+i).append(fidcon[i][j].tbar);
					if(fidcon[i][j].type=="textbox"){
						$('#tbar'+i).find('input:last').textbox({})
					}else if(fidcon[i][j].type=="combo"){
						
						$('#tbar'+i).find('input:last').combobox({
			                valueField:fidcon[i][j].valueField,
			                textField:fidcon[i][j].textField,
		                    panelHeight: 'auto',
			                url: fidcon[i][j].url,
			                data:fidcon[i][j].data
						})
					}else if(fidcon[i][j].type=="datebox"){
						$('#tbar'+i).find('input:last').datebox({})
					}
				}
			}
		}
		
		$('#'+options.typename).datagrid({
			title:options.title||'',
			width:options.width,
			height:options.height,
			stripted:options.stripted,
			singleSelect:options.singleSelect,
			url:options.url,
			loadMsg:options.loadMsg,
			fit:options.fit,
			pagination:options.pagination,
			rownumbers:options.rownumbers,
			pageSize:options.pageSize,
			pageList:options.pageList,
			toolbar:'#tb',
			columns:objects[options.typename].column,
			onLoadSuccess:function(data){
				
			},
            onLoadError:function(data){
            	if(data.responseText.indexOf("form id=\"loginform\"") > 0){
            		top.location.href="/login.do";
            	}
            },
			onBeforeLoad:function(param){
				//param.id=id;
				if(typeof(objects[options.typename].findCondition)!="undefined"){
					fidcon=objects[options.typename].findCondition;
					for (var i = fidcon.length-1;  i>= 0; i--) {
						for(var j=0;j<fidcon[i].length;j++){
							param[fidcon[i][j].field]=$('input[name=_'+fidcon[i][j].field+']').val();	
						}
					}
				}
			},
			//点击每一行触发
			onClickRow:options.rowClick||function(index,data){
				var param = {};
				if(options.childLoadParam){
					for (var i = 0; i < options.childLoadParam.length; i++) {
						param[options.childLoadParam[i]] = data[options.childLoadParam[i]]
					}
					$('#'+options.childTypename).datagrid('reload',param);
				}else{
					$('#'+options.childTypename).datagrid('reload',data);
				}
			}
		});
		
		if(options.childTypename){
			$('#'+options.childTypename).datagrid({
				title:options.title||'',
				width:options.width,
				height:options.height,
				stripted:options.stripted,
				singleSelect:options.singleSelect,
				url:options.childUrl,
				loadMsg:options.loadMsg,
				fit:options.fit,
				pagination:options.pagination,
				rownumbers:options.rownumbers,
				pageSize:options.pageSize,
				pageList:options.pageList,
				toolbar:'',
				columns:objects[options.childTypename].column,
				onLoadSuccess:function(data){
					
				}
			});
		}
		
	},
	/*************单对话框******************/
	sinWin:function(options){
		var defaults={
				width:450,
				height:'auto',
				type:'text'
		}
		
		options = $.extend(defaults, options);
		
		if ($('#sin_dlg_'+options.typename).length == 0) {
            $('body').append('<div id=sin_dlg_'+options.typename+' style="width:400px;height:280px;padding:10px 20px"><form id=sin_fr_'+options.typename+' method="post"></form></div>');
        }
		
		$('#sin_fr_'+options.typename+'').form('clear');
		$('#sin_fr_'+options.typename+'').form('reset');//clear清不掉 表单中的t_number所以加了一句reset
		for (i = objects[options.typename].items.length - 1; i >= 0; i--) {
			$('#sin_fr_'+options.typename+'').prepend(objects[options.typename].items[i].div);
			if (objects[options.typename].items[i].type == "textbox") {
	            $('#' + objects[options.typename].items[i].field + '').textbox({
	                required:objects[options.typename].items[i].required,
	                validType: objects[options.typename].items[i].validType,
	                missingMessage: objects[options.typename].items[i].missingMessage,
	                type:objects[options.typename].items[i].boxType||'text'
	            }).textbox('clear');
	        }else if(objects[options.typename].items[i].type == "combo"){
	        	$('#' + objects[options.typename].items[i].field + '').combobox({
	        		valueField:objects[options.typename].items[i].valueField,
	                textField: objects[options.typename].items[i].textField,
	                panelHeight: 'auto',
	                url: objects[options.typename].items[i].url,
	                data:objects[options.typename].items[i].data
	            }).combobox('clear');
	        }
		}
		
		 $('#sin_dlg_'+options.typename+'').dialog({
		        title: options.title||'对话框',
		        width: 300,
		        height: 'auto',
		        closed: false,
		        modal: true,
		        buttons: [{
		            text: "确定",
		            handler: function () {
		            	$.messager.progress();	// 显示进度条
		                $('#sin_fr_'+options.typename+'').form('submit', {
		                    url:options.url,
		                    onSubmit: function (param) {
		                    	if(typeof(options.paramid)!="undefined"){
		                    		param[options.paramid]=options.paramvalue;
		                    	}
		                        var isValid = $(this).form('validate');
		                		if (!isValid){
		                			$.messager.progress('close');	// 如果表单是无效的则隐藏进度条
		                		}
		                		return isValid;	// 返回false终止表单提交
		                    },
		                    success: function (result) {
		                    	$.messager.progress('close');	// 如果提交成功则隐藏进度条
		                    	if(result.indexOf("form id=\"loginform\"") > 0 ){//跳转登录页面
		                    		top.location.href="/login.do";
		                    	}else{ 
		                    		options.submit(result);
		                    	 }
		                    }
		                });
		            }
		        }, {
		            text: "关闭",
		            handler: function () {$('#sin_dlg_'+options.typename+'').dialog('close'); }
		        }]
		    });
	},
	/*************双tab对话框******************/
	DouTabWin:function(options){
		//datagrid控件默认的属性
		var defaults={
				width:450,
				height:400,
				stripted:true,
				fitColumns:true,
				loadMsg:'加载中……',
				singleSelect:false,
				pagination:true,
				rownumbers:false,
				modal:true,
				pageSize:20,
				pageList:[5,10,20,50]
		}
		
		options = $.extend(defaults, options);
		
		if ($('body').find('.doutab').length == 0) {
			$('<div class="doutab"><div class="tab"><div class="tab1"><div class="tab1_div"></div></div><div class="tab2"><div class="tab2_div"></div></div></div></div>').appendTo('body');
		}
		$('body').find('.tab1').attr('title', format("已分配的", options.title));
		$('.tab1').find('.tab1_div').datagrid({
            fit: true,
            striped: options.striped,
            singleSelect: options.singleSelect,
            url: options.url1,
            loadMsg: options.loadMsg,
            sortName: objects[options.typename].id,
            pagination: options.pagination,
            rownumbers: options.rownumbers,
            pageSize: options.pageSize,
            pageList: options.pageList,
            toolbar: options.toolbar1,
            queryParams: {
            },
            columns: objects[options.typename].column,
            onBeforeLoad: function (param) {}
        });
		
		$('body').find('.tab2').attr('title', format("未分配的", options.title));
		$('.tab2').find('.tab2_div').datagrid({
            fit: true,
            striped: options.striped,
            singleSelect: options.singleSelect,
            url: options.url2,
            loadMsg: options.loadMsg,
            sortName: objects[options.typename].id,
            pagination: options.pagination,
            rownumbers: options.rownumbers,
            pageSize: options.pageSize,
            pageList: options.pageList,
            toolbar: options.toolbar2,
            queryParams: {
            },
            columns: objects[options.typename].column,
            onBeforeLoad: function (param) {}
        });
		
		$('.doutab').window({
            title: options.title,
            width: options.width,
            modal: options.modal,
            height: options.height
        });
		
		$('body').find('.tab').tabs({
            fit: true
        });
	},
	/*************tree对话框******************/
	TreeWin:function(options){
		var defaults={
				animate:true,
				checkbox:true,
				lines:true,
				modal:true,
				width:300,
				height:350
		}
		
		options = $.extend(defaults, options);
		
		if ($('body').find('#tree').length == 0) {
			$('<div class="tree_win"><ul id="tree"></ul></div>').appendTo('body');
		}
		
		$('#tree').tree({   
		    url:options.url,
		    animate:options.animate,
		    checkbox:options.checkbox,
		    lines:options.lines,
		    fit:true
		}); 
		
		$('.tree_win').dialog({
            title: options.title||'树形对话框',
            width: options.width,
            modal: options.modal,
            height: options.height,
            buttons:[{text:"提交",handler:options.submit},{text:"取消",handler:function(){$('.tree_win').dialog('close');}}]
        });
	},
	/*********ajax封装**********/
	myAjax:function(options){
		var defaults={
				type:"post"
		}
		
		options = $.extend(defaults, options);
		
		$.ajax({
            type: options.type,
            url:options.url,
            data: options.data,
            success: function (result) {
            	if(typeof result== "string"&&result.indexOf("form id=\"loginform\"") > 0 ){//跳转登录页面
            		top.location.href="/login.do";
            	}else{
            		options.success(result);
            	}
            }
        });
	},
	/*******print封装*********/
	myPrint:function(options){
		var defaults={
		}
		
		options = $.extend(defaults, options);
		
        var printAreaHtml = getNewWindowHtml(options.body);
		
		var version = parseInt(getBrowserVersion());
		
        var winPrint = window.open("","myWin");
		winPrint.document.write(printAreaHtml);
		if(version > 99){
			winPrint.print();
			winPrint.close();
		}
	}
})

function search(typename){
	$('#' + typename + '').datagrid('reload');
}

function add(typename,dialogHeight){
	if ($('#add_dlg').length == 0) {//判断id为add_dlg的元素是否存在
		$('body').append('<div id=add_dlg style="width:400px;height:280px;padding:10px 20px"><form id=add_fr method="post"></form></div>');
	}
	$('#add_fr').form('clear');
	$('#add_fr').form('reset');//clear清不掉 表单中的t_number所以加了一句reset
	for (i = objects[typename].items.length - 1; i >= 0; i--) {
		$('#add_fr').prepend(objects[typename].items[i].div);
		if (objects[typename].items[i].type == "textbox") {
            $('#' + objects[typename].items[i].field + '').textbox({
                required:objects[typename].items[i].required,
                validType: objects[typename].items[i].validType,
                missingMessage: objects[typename].items[i].missingMessage
            }).textbox('clear');
        }else if(objects[typename].items[i].type == "combo"){
        	$('#' + objects[typename].items[i].field + '').combobox({
        		valueField:objects[typename].items[i].valueField,
                textField: objects[typename].items[i].textField,
                panelHeight: 'auto',
                url: objects[typename].items[i].url,
                data:objects[typename].items[i].data
            }).combobox('clear');
        }
	}
	
	 $('#add_dlg').dialog({
	        title: "添加",
	        width: 330,
	        height: dialogHeight,
	        closed: false,
	        modal: true,
	        buttons: [{
	            text: "确定",
	            handler: function () {
	            	$.messager.progress();	// 显示进度条
	                $('#add_fr').form('submit', {
	                    url:rootName+'/'+typename+'/insert.do',
	                    onSubmit: function (param) {
	                        var isValid = $(this).form('validate');
	                		if (!isValid){
	                			$.messager.progress('close');	// 如果表单是无效的则隐藏进度条
	                		}
	                		return isValid;	// 返回false终止表单提交
	                    },
	                    success: function (result) {
	                    	$.messager.progress('close');	// 如果提交成功则隐藏进度条
	                    	if(result.indexOf("form id=\"loginform\"") > 0 ){//跳转登录页面
	                    		top.location.href="/login.do";
	                    	}else{ 
		                    	 var result = eval('(' + result + ')');
		                    	 if(!result.success){
		                    		 $.messager.alert("提示", result.msg, 'error');
		                    	 }else{
			                    	 $('#add_dlg').dialog('close');
			                    	 $.messager.alert("提示", result.msg, 'info');
			                    	 $('#' + typename + '').datagrid('reload');
		                    	 }
	                    	 }
	                    }
	                });
	            }
	        }, {
	            text: "关闭",
	            handler: function () {$('#add_dlg').dialog('close'); }
	        }]
	    });
}

function edit(typename,dialogHeight){
	var row = $('#' + typename + '').datagrid('getSelections');
    if (row.length == 0) {
        $.messager.alert("错误", "请选择一条记录");
    }else if (row.length > 1) {
        $.messager.alert("错误", "编辑时只能选择一条记录");
    }else if(row.length == 1){
    	if ($('#edit_dlg').length == 0) {
            $('body').append('<div id=edit_dlg style="width:400px;height:280px;padding:10px 20px"><form id=edit_fr method="post"></form></div>');
        }
    	$('#edit_fr').form('reset');
    	for (i = objects[typename].items.length - 1; i >= 0; i--) {
    		$('#edit_fr').prepend(objects[typename].items[i].div);
    		if (objects[typename].items[i].type == "textbox") {
                $('input[name=' + objects[typename].items[i].field + ']').textbox({
                    required: objects[typename].items[i].required,
                    validType: objects[typename].items[i].validType,
                    missingMessage: objects[typename].items[i].missingMessage
                }).textbox('clear');
            }else if(objects[typename].items[i].type == "combo"){
            	$('#' + objects[typename].items[i].field + '').combobox({
            		valueField:objects[typename].items[i].valueField,
                    textField: objects[typename].items[i].textField,
                    panelHeight: 'auto',
                    url: objects[typename].items[i].url,
                    data:objects[typename].items[i].data
                }).combobox('clear');
            }
    	}
    	$('#edit_dlg').dialog({
	        title: "编辑",
	        width: 330,
	        height: dialogHeight,
	        closed: false,
	        modal: true,
	        buttons: [{
	            text: "确定",
	            handler: function () {
	                $('#edit_fr').form('submit', {
	                    url:rootName+'/'+typename+'/update.do',
	                    onSubmit: function (param) {
	                    	param[objects[typename].id]=row[0][objects[typename].id];
	                        return $(this).form('validate');
	                    },
	                    success: function (result) {
	                    	if(result.indexOf("form id=\"loginform\"") > 0 ){//跳转登录页面
	                    		top.location.href="/login.do";
	                    	}else{
	                    	 var result = eval('(' + result + ')');
	                    	 if(!result.success){
	                    		 $.messager.alert("提示", result.msg, 'error');
	                    	 }else{
		                    	 $('#edit_dlg').dialog('close');
		                    	 $.messager.alert("提示", result.msg, 'info');
		                    	 $('#' + typename + '').datagrid('reload');
	                    	 }
	                    	}
	                    }
	                });
	            }
	        }, {
	            text: "关闭",
	            handler: function () {$('#edit_dlg').dialog('close'); }
	        }]
	    });
    	 $('#edit_fr').form('load', row[0]);	
    }
}

function del(typename){
	var rows = $('#' + typename + '').datagrid('getSelections');
    if (rows.length == 0) {
    	$.messager.alert("错误", "请选择一条记录");
    }else{
    	var ids="";
    	for(var i=0;i<rows.length;i++){
    		if(i>0){
    			ids = ids + ",";
    		}
    		ids=ids+rows[i][objects[typename].id];
    	}
    	$.messager.confirm('确认？','确定要删除这条记录吗?',function(r){
    		if(r){
    			$.ajax({
                    type: 'post',
                    url:rootName+'/'+typename+'/delete.do',
                    data: { ids: ids },
                    success: function (result) {
                        //var result = eval('(' + result + ')');
                    	if(typeof result== "string"&&result.indexOf("form id=\"loginform\"") > 0 ){//跳转登录页面
                    		top.location.href="/login.do";
                    	}else{
//                    		result = eval('(' + result + ')');
	                        if (!result.success) {
	                            $.messager.alert("提示", result.msg, 'error');
	                        } else {
	                        	$.messager.alert("提示", result.msg, 'info');
		                    	 $('#' + typename + '').datagrid('reload');
	                        }
                    	}
                    }
                });
    		}
    	});
    }
}

/*****拼接打印html的页面********/
function getNewWindowHtml(htmlStr){
	var version = parseInt(getBrowserVersion());
	var html = '<html lang="en">';
	html += '<head>';
	html += '<meta charset="utf-8"/>';
	html += '<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />';
	html += '<meta name="viewport" content="width=device-width, initial-scale=1.0">';
	html += '<meta http-equiv="X-UA-Compatible" content="IE=edge">';
	html += '<title></title>';
	html += '</head>';
	html += '<body>';
	html += '<div>';
	html += 	htmlStr;
	html += '</div>';
	if(version < 100){
		html += '<OBJECT id="WebBrowser" height="0" width="0" classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2" name="wb"></OBJECT>';
	}
	html += '</body>';
	if(version < 100){
		html += '<script>document.getElementById("WebBrowser").execWB(7,1);<\/script>';
	}
	html += '</html>';
	
	return html;
}

//ie or other brower
function getBrowserVersion(){
	var userAgent = navigator.userAgent.toLowerCase();
	if(userAgent.match(/msie ([\d.]+)/)!=null){
		//ie 6-9
		var uaMatch = userAgent.match(/msie ([\d.]+)/);
		//alert(uaMatch[1]);
		return uaMatch[1];
	}else if(userAgent.match(/trident\/([\w.]+)/)){
		var uaMatch = userAgent.match(/trident\/([\w.]+)/);
		switch(uaMatch[1]){
			case "4.0" : return '8';break;
			case "5.0" : return '9';break;
			case "6.0" : return 'IE10';break;
			case "7.0" : return 'IE11';break;
			default : return 100;
		}
	}
	return 100;
}