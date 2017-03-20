Ext.namespace("com.simon.gataway.Product");
com.simon.gataway.Product = Ext.extend(Ext.Panel,{
	initComponent:function(){
		var searchForm = this.searchForm =new Ext.FormPanel({
			frame:true,
			autoHeight:true,
			height:130,
			labelWidth:80,
			margins:"3 3 3 3",
			collapseMode:"mini",
			region:"north",
			split:true,
			labelAlign:"right",
			items:[{
				xtype:'fieldset',
				title:'查询条件',
				collapsible:false,
				autoHeight:true,
				layout:'form',
				labelAlign:'right',
				labelWidth:80,
				items:[
				      	{
				      		layout:'column',
				      		border:0,
				      		items:[
				      		       	{
				      		       		columnWidth:.25,
				      		       		layout:'form',
				      		       		border:0,
				      		       		items:[
				      		       		       	{
				      		       		       		xtype:'textfield',
				      		       		       		anchor:'100%',
				      		       		       		name:'product',
				      		       		       		hiddenName:'product',
				      		       		       		triggerAction:'all',
				      		       		       		forceSelection:true,
				      		       		       		mode:'local',
				      		       		       		allowBlank:true,
				      		       		       		fieldLabel:'产品名称',
				      		       		       		emptyText:'产品名称',
				      		       		       	}
				      		       		       ],
				      		       	},
				      		       	{
				      		       		columnWidth:.25,
				      		       		layout:'form',
				      		       		border:0,
				      		       		items:[
				      		       		       {
				      		       		    	   xtype:'textfield',
				      		       		    	   anchor:'100%',
				      		       		    	   name:'attr',
				      		       		    	   hiddenName:'attr',
				      		       		    	   triggerAction:'all',
				      		       		    	   forceSelection:true,
				      		       		    	   mode:'local',
				      		       		    	   allowBlank:true,
				      		       		    	   fieldLabel:'产品属性',
				      		       		    	   emptyText:'产品属性',
				      		       		       }
				      		       		       ],
				      		       	},
				      		       	{
				      		       		columnWidth:.25,
				      		       		layout:'form',
				      		       		border:0,
				      		       		items:[
				      		       		       {
				      		       		    	   xtype:'combo',
				      		       		    	   anchor:'100%',
				      		       		    	   name:'status',
				      		       		    	   hiddenName:'status',
				      		       		    	   triggerAction:'all',
				      		       		    	   forceSelection:true,
				      		       		    	   mode:'local',
				      		       		    	   allowBlank:true,
				      		       		    	   fieldLabel:'产品状态',
				      		       		    	   displayField:'name',
				      		       		    	   valueField:'value',
				      		       		    	   emptyText:'产品状态',
				      		       		    	   store:new Ext.data.Store({
				      		       		    		   fields:['value','name'],
				      		       		    		   data:[['1','活跃'],['2','不活跃']],
					      		       		    		proxy: {  
					      		                          type: 'memory',  
					      		                          reader: {  
					      		                              type: 'json',  
					      		                          }  
					      		                      }
				      		       		    	   }),
				      		       		       }
				      		       		       ],
				      		       	},
				      		       	{
					      		    	   columnWidth:.2,
					      		    	   layout:'form',
					      		    	   border:0,
					      		    	   items:[
					      		    	          		new Ext.button.Button({
				                                            text: '查询',
				                                            width: 100,
				                                            height:25,
//				                                            cls:'button_cls_all',
				                                            iconCls :"search",
				                                            anchor: '80%',
				                                            scope: this,
				                                            handler: function () {
				                                                if (!this.searchForm.getForm().isValid()) return;
				                                                    this.search();
				                                            }
				                                        })
					      		    	          ],
					      		       }
				      		       ]
				      	},
				      	//line 2
				      /*	{
				      		layout:'column',
				      		border:0,
				      		items:[
				      		       {
				      		    	   columnWidth:.3,
				      		    	   layout:'form',
				      		    	   border:0,
				      		    	   items:[
				      		    	          ],
				      		       }
				      		       ]
				      	},*/
				     ],
			}],
		});
		
		this.gridPanel = new Ext.panel.Panel({
			region:'center',
			border:false,
			layout:'fit',
			items:[]
		}),
		
		this.initMethod();
		
		Ext.apply(this,{
			autoScroll:false,
			layout:'border',
			border:false,
			items:[searchForm,this.gridPanel]
		}),
		com.simon.gataway.Product.superclass.initComponent.apply(this,arguments);
	},
	
	createGrid : function(){
		var ds = new Ext.data.Store({
			proxy: {
		        type: "ajax",
		        url:this.ctx+'/Product/findByPage.do',
		        reader:{
					rootProperty:"invdata", 
					totalProperty:"total"
				}
		    },
		    pageSize:20,
			autoLoad:true,
			fields:['id','product','attr','unit','status'],
		});
		
		var grid = new Ext.grid.Panel({
//			xtype: "gridpanel",
			loadMask : true,
			store:ds,
			columnLines: true, // 加上表格线  
			selModel: {
		        injectCheckbox: 0,
		        mode: "SIMPLE",     //"SINGLE"/"SIMPLE"/"MULTI"
		        checkOnly: false     //只能通过checkbox选择
		    },
			selType: "checkboxmodel",
			columns:[
			        { text: '产品ID', dataIndex: 'id' },
			        { text: '产品名称', dataIndex: 'product'},
			        { text: '属性', dataIndex: 'attr' },
			        { text: 'xx', dataIndex: 'unit' },
			        { text: '状态', dataIndex: 'status' }],
			bbar: { xtype: "pagingtoolbar", store: ds, displayInfo: true },
			frame:true,
			stripeRows:true,
			viewConfig:{
				forceFit:true
			}
		});
		return grid;
	},
	initMethod : function(){
		var grid = this.grid = this.createGrid();
		this.gridPanel.add(this.grid);
        this.gridPanel.doLayout();
	},
	
	search :function(){
		var param = this.searchForm.getForm().getValues();
		Ext.apply(this.grid.getStore().proxy.extraParams, param);
		this.grid.getStore().loadPage(1);
	}
});