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
    	    url:'${ctx}/TaxType/findByPage.do',
    	    //dialogHeight:300,
  			T_bar:[true,true,true,true],    
  			typename:"TaxType",
  			dialogHeight:200,
    	  })
      })
   </script>
</body>
</html>