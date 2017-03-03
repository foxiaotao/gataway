<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="../include/taglib.jsp"%> 
<%@ include file="../include/include.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
   <script type="text/javascript">
     /*税率类型参数管理**/
     $(function(){
    	  $.DataGridePanel({
    		  //取数据的地址
    	    url:'${ctx}/Product/findByPage.do',
    	    //dialogHeight:300,
  			T_bar:[true,true,true,true], 
  			fit:false,//false的时候height属性才有意义
  			typename:"Product",
  			dialogHeight:200,
  			height:400,
  			childTypename:"ProductChild",//子项typename
  			childUrl:'${ctx}/tProduct/findDetail.do',//子项访问后台url
  			childLoadParam:["id"],//子项查询参数属性名
  			rowClick:function(index,data){//行点击事件。index行索引，data，点击行的json对象,这里会覆盖basedemo.js的onClickRow:function
  				alert(data.id);
  			}
    	  });
    	  
      })
   </script>
</body>
</html>