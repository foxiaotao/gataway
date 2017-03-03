<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="base.jsp" %> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>home</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript">
		jQuery.ajaxSetup({cache:false});//ajax不缓存
		
		var index = 0;
		function addPanel(url,title){
			if(!$("#tt").tabs('exists',title)){
				$("#tt").tabs('add',{
					title:title,
					content:'<iframe class="layoutContentIframeCls" src="'+url+'" scrolling="auto" frameborder="0" width="100%" height="100%"></iframe>',
					closable:true,
				});
			}else{
				$("#tt").tabs('select',title);
				var tab = $("#tt").tabs('getSelected');
				tab.panel('refresh',url);
			}
		}
		function removePanel(){
			var tab = $("#tt").tabs('getSelect',title);
			if(tab){
				var index = $("#tt").tabs('getTabIndex',tab);
				$("#tt").tabs('close',index);
			}
		}
		function setmain(url,title){
			addPanel(url,title);
		}
	</script>	
  </head>
  <!-- easyui-layout 可分上下左右中五部分，中间的是必须的，支持href，这样就可以不用iframe了 -->
  <body class="easyui-layout" id="mainBody">
		<!-- 上 -->
		<div region="north" split="false" style="height:70px;text-align: center;" border="false">
			<h1>欢迎： ${userSessionInfo.name}</h1>
		</div>
		
		<!-- 左-->
		<div region="west" class="menudiv" split="true" title="系统菜单" style="width:200px;overflow:hidden;">
			<div id="menuDiv" class="easyui-accordion" data-options="multiple:true" fit="false" border="false" animate="false">
				<c:forEach var="item" items="${menuTree}">
					<div title="${item.name}" style="overflow:hidden;">
						<c:forEach var="childit" items="${item.children}">
							<ul>					
								<li id="rightLi${child.id}" style="cursor: pointer;" 
									onclick="setmain('${ctx}${childit.url}','${childit.name}')" >${childit.name}</li>
							</ul>
						</c:forEach>
					</div>
				</c:forEach>
			</div>
		</div>
		
		<!-- 中 -->
		<div region="center" id="tt" class="easyui-tabs"  style="overflow-x:hidden;padding: 0px;" >
			
		</div>
<!-- 		<div id="MyPopWindow" modal="true" shadow="false" minimizable="false" cache="false" maximizable="false" collapsible="false" resizable="false" style="margin: 0px;padding: 0px;overflow: auto;"></div> -->
		<script language="JavaScript">
			//窗口size 改变
			window.onresize=function(){  
			     changeFrameHeight();  
			} 
			function changeFrameHeight(){
			    var height = document.documentElement.clientHeight-70;
			    var width = document.documentElement.clientWidth-200;
			    $("iframep[class='layoutContentIframeCls']").attr("height",height);
			    $("iframep[class='layoutContentIframeCls']").attr("width",width);
			    
			    $('#mainBody').layout('resize', {
			    	width:width,
			    	height:height
			    })
			    $('#tt').tabs('resize', {
			    	width:width,
			    	height:height
			    })
			    
			}
		</script>
  </body>
</html>
