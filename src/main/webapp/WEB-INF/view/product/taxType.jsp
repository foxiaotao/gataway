<%-- <%@ page language="java" contentType="text/html; charset=utf-8" --%>
<%--     pageEncoding="utf-8"%> --%>
<%@ include file="../base.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
   <script type="text/javascript">
   if ($('#add_dlg').length == 0) {//判断id为add_dlg的元素是否存在
		$('body').append('<div id=add_dlg style="width:400px;height:280px;padding:10px 20px"><form id=add_fr method="post"></form></div>');
	}
	$('#add_fr').form('clear');
	$('#add_fr').form('reset');//clear清不掉 表单中的t_number所以加了一句reset
	$('#add_fr').prepend(field("search","vv").div);
   $('#vv').textbox({
       required:true,
       validType: '',
       missingMessage: 'this is required',
       type:'textbox'
   }).textbox('clear');
   
    function field(name,field){
		var defaults={labelwidth:65,width:150,required:false,height:20,type:'textbox',padding:'1px 1px',missingMessage:'请输入'+name};
		for(var i=2;i<arguments.length;i++){
			dafaults=$.extend(defaults,arguments[i]);
		}

		var tbar='&nbsp;&nbsp;<a style=display:inline-table;width:'+defaults.labelwidth+'px;>'+name+': </a><input name=_'+field+' style="width:'+defaults.width+'px;margin-top:0px">';
		var div=$('<div style=margin-bottom:5px;><a style=display:inline-block;width:80px;>'+name+':</a>'+
				'<input id='+field+' name='+field+' style=margin-left:0px;width:'+defaults.width+'px></input></div>');
		var divs={div:div,tbar:tbar,field:field}
		defaults=$.extend(defaults,divs);
		return defaults;
	}
     /*税率类型参数管理**/
//      $(function(){
//     	  $.DataGridePanel({
//     		  //取数据的地址
//     	    url:'${ctx}/TaxType/findByPage.do',
//     	    //dialogHeight:300,
//   			T_bar:[true,true,true,true],    
//   			typename:"TaxType",
//   			dialogHeight:200,
//     	  })
//       })
   </script>
   
   <div id="textfield">
   </div>
   
</body>
</html>