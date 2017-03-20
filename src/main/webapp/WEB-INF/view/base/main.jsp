<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="org.apache.shiro.SecurityUtils" %>
<%@ page import="simon.demo.core.shiro.ShiroUser" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>xxx管理平台</title>
<%@ include file="../include/include.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<script type="text/javascript">
	
	  
		Ext.onReady(function() {
			//数据权限用户
			var store = Ext.create("Ext.data.TreeStore",{
				fields:["id","name","url"],
// 				root:"menuTree",
				proxy:{
					type: "ajax",
		            url: "${pageContext.request.contextPath }/Home/menuList.do"
				}
			});
			
			var treePanel = new Ext.tree.TreePanel({
				border:false,
				autoScroll:true,
				store:store,
				displayField:'name',
				rootVisible:false,
				enable:false,
				dataType:"json",
				listeners:{
					'itemclick':function(view,re){
						//权限判断
						
						//判断tab是否已经创建，若已创建则将此tab显示，否则创建tab
						var tabId = 'm'+re.data.id;
						if(!tabpanel.items.get(tabId)){
							tabpanel.add({
// 								renderTo:Ext.getBody(),
								autoScroll:false,
								height:document.documentElement.clientHeight - 50,
								title:re.data.name,
								id:tabId,
								closable:true,
								layout:'fit',
								html:'<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src=${pageContext.request.contextPath }/'+re.data.url+'></iframe>'
							}).show();
							tabpanel.setActiveTab(tabId);
						}else{
							tabpanel.setActiveTab(tabId);
						}
					}
				}
			});
			treePanel.expandAll();
			
			var tabpanel = new Ext.TabPanel({
				activeTab:0,
// 				closable:true,
				id:'defaultTabPanel',
				items:[{ title: "首页", html: "<iframe scrolling='auto' frameborder='0' width='100%' height='" + (document.documentElement.clientHeight - 100) + "' src='${pageContext.request.contextPath }/Home/welcome.do'></iframe>" }]
			});
			
	    	var viewport = new Ext.Viewport({
	    		title:'Viewport',
	    		header:false,
				layout: 'border',
				defaults:{
					bodyStyle:'background-color:#FFFFFF',
					frame:true
				},
				items: [
								{
									region:'north',
									height:100,
									bodyStyle:"background: #a8b6ca url(${pageContext.request.contextPath }/Resource/Images/headbg.png)     no-repeat;",//<a href="#" id="editpass" style="color: #fff;">修改密码</a>&nbsp;&nbsp;客户不使用修改密码
			                        html: '<span style="float: right; padding-right: 20px;padding-top: 25px;color: #fff;" class="head"><span id="invoiceAccount">${INVOICEADMIN}</span>&nbsp;&nbsp<a id="loginOut" style="color: #fff;cursor:pointer" onclick="doGetSession()"></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;欢迎 <b id="curname">' + '<%=((ShiroUser) SecurityUtils.getSubject().getPrincipal()).getName() %>' + '</b>&nbsp;&nbsp;&nbsp;&nbsp&nbsp;&nbsp;<span style="padding-right: 20px;padding-top: 25px;color: #fff;cursor:pointer" onClick="cPwd()">修改密码 </span>&nbsp;&nbsp; <a id="loginOut" style="color: #fff;cursor:pointer" onclick="logout()">安全退出</a></span>"'
								},
								{
									region:'west',
									title:'导航菜单',
									autoScroll:true,
									width:200,
									split:false,
									collapsible:true,
									items:[treePanel]
								},
								{
									region:'center',
									layout:'fit',
									items:[tabpanel]
								}
						]
			});
	    });	
	 </script>
</head>
<body>
	
</body>
</html>