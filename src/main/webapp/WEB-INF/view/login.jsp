<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%
	String url = request.getRequestURL().toString();
	url = url.substring(0, url.indexOf('/', url.indexOf("//") + 2));
	String context = request.getContextPath();
	url += context;
	application.setAttribute("ctx", url);
	
%>
<%-- <%@ include file="base.jsp" %>   --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>shiro login</title>
</head>

<body>
	<form action="${ctx }/login_login.do" method="post">
		username: <input type="text" name="username" value="admin"><br>
		password: <input type="password" name="password" value="1"><br>
		<input type="submit" value="登录" width="100px" height="40px"/>
		
	</form>
</body>
</html>