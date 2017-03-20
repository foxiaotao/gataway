<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>首页</title>
<style type="text/css">
    <!--
    body {
        margin-left: 0px;
        margin-top: 0px;
        margin-right: 0px;
        margin-bottom: 0px;
        background-color: #EEF2FB;
    }
    -->
</style>
</head>

<body>

        <table width="100%" border="0" cellpadding="0" cellspacing="0">
            
            <tr>
                <!--background="images/mail_leftbg.gif"-->
                <td valign="middle">&nbsp;</td>
                <td valign="top" bgcolor="#F7F8F9">
                    <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
                        <tr>
                            <td colspan="2" valign="top">&nbsp;</td>
                            <td>&nbsp;</td>
                            <td valign="top">&nbsp;</td>
                        </tr>
                        <tr>
                            <td colspan="2" valign="top">
                                <span class="left_bt">欢迎您使用 xxx信息化管理平台</span><br>
                                <br>                                
                            </td>
                            <td width="7%">&nbsp;</td>
                            <td width="40%" valign="top">
                                
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <td colspan="2" valign="top">
                                <!--JavaScript部分-->
                                <script language=javascript>
                                    function secBoard(n) {
                                        for (i = 0; i < secTable.cells.length; i++)
                                            secTable.cells[i].className = "sec1";
                                        secTable.cells[n].className = "sec2";
                                        for (i = 0; i < mainTable.tBodies.length; i++)
                                            mainTable.tBodies[i].style.display = "none";
                                        mainTable.tBodies[n].style.display = "block";
                                    }
                                </script>
                                <!--HTML部分-->
             	</table>
        	</td>
       </table>
</body>
</html>