/**
 * 
 */
var objects={
	User:{
		id:"userId",
		items:[
               T_field("用户编码","userCode",{required:true}),
               T_field("用户姓名","userName",{required:true})
		      ],
		//findCondition:[[T_field("内容","text"),T_date("日期","text")],[T_combo("内容3","text"),T_field("内容4","text")]],
		findCondition:[[T_field("用户编码","userCode"),T_field("用户姓名","userName")]],
		column:[[
		         {field:'ck',checkbox:true},
		         T_cm("ID","userId",{width:100}),
		         T_cm("用户编码","userCode",{width:150}),
		         T_cm("用户姓名","userName",{width:150})
		]]
	},
	modifyPwd:{
		items:[
               T_field("旧密码","oldPwd",{required:true,boxType:"password"}),
               T_field("新密码","newPwd",{required:true,boxType:"password"}),
               T_field("新密码确认","comfirmNewPwd",{required:true,missingMessage:"请再次输入新密码",boxType:"password"})
		      ],
	},
	Role:{
		id:"roleId",
		items:[
               T_field("角色名称","name",{required:true})
		      ],
		findCondition:[[T_field("角色名称","name")]],
		column:[[
		         {field:'ck',checkbox:true},
		         T_cm("ID","roleId",{width:100}),
		         T_cm("角色名称","name",{width:150})
		]]
	},
	CusromerInfo:{
		column:[[
		         {field:'ck',checkbox:true},
		         T_cm("ID","id",{width:150}),
		         T_cm("客户编号","userCode",{width:100}),
		         T_cm("用户姓名","userName",{width:150}),
		         T_cm("name","name",{width:150})
		]]
	},
	ProcessDefinition:{
		id:"deploymentId",
		column:[[
		         {field:'ck',checkbox:true},
		         T_cm("流程定义id","id",{width:150}),
		         T_cm("流程部署id","deploymentId",{width:100}),
		         T_cm("流程定义key","key",{width:100}),
		         T_cm("流程定义名称","name",{width:150}),
		         T_cm("流程定义版本","version",{width:150})
		]]
	},
	ProcessInstance:{
		id:"processInstanceId",
		column:[[
		         {field:'ck',checkbox:true},
		         T_cm("流程实例id","processInstanceId",{width:120}),
		         T_cm("流程实例名称","instanceName",{width:100}),
		         T_cm("开始时间","startTime",{width:150,formatter:function(val){return DateTimeFormatter(val);}}),
		         T_cm("结束时间","endTime",{width:150,formatter:function(val){return DateTimeFormatter(val);}}),
		         T_cm("流程状态","status",{width:150}),
		         T_cm("流程状态图","opt",{width:150,
		        	 align:'center',
		        	 formatter:function(value,rec){
		        		 return "<a plain=true class=\"easyui-linkbutton\"" +
		        		 		"href=\"javascript:window.location.href='"+rootName+"/Flow/queryActivityMap.do?processInstanceId="+rec.processInstanceId+"'\">查看流程</a>";
		        	 }
		         })
		]]
	},
	Task:{
		id:"id",
		column:[[
		         {field:'ck',checkbox:true},
		         T_cm("id","id",{width:120}),
		         T_cm("流程实例id","processInstanceId",{width:120}),
		         T_cm("名称","name",{width:100})
		]]
	},
    SaleInfo:{
		id:"saleinfoCode",
	    items:[
	              T_field("销方编码","saleinfoCode",{width:150},{required:true}),
				  T_field("销方名称","saleinfoName",{width:150},{required:true}),
				  T_field("销方地址","saleinfoAddress",{width:150}),
				  T_field("销方电话","saleinfoPhone",{width:150}),
				  T_field("销方税号","saleinfoTaxNo",{width:150}),
				  T_field("开户行账号","saleinfoBankAccount",{width:150},{required:true}),
				  T_field("开户行","saleinfoBank",{width:150},{required:true})
			  ],
	    findCondition:[[T_field("销方编码","saleinfoCode"),T_field("销方名称","saleinfoName")]],
		column:[[
				 {field:'ck',checkbox:true},
				  T_cm("销方编码","saleinfoCode",{width:150}),
				  T_cm("销方名称","saleinfoName",{width:150}),
				  T_cm("销方地址","saleinfoAddress",{width:150}),
				  T_cm("销方电话","saleinfoPhone",{width:150}),
				  T_cm("销方税号","saleinfoTaxNo",{width:150}),
				  T_cm("销方开户行账号","saleinfoBankAccount",{width:150}),
				  T_cm("开户行","saleinfoBank",{width:150})
				 ]]
	 },	
    SaleInfoConfig:{
		id:"saleinfoConfigId",
	    items:[
                 T_field("销方编码","saleinfoCode",{required:true}),
	             T_field("销方名称","saleinfoName",{required:true}),
	             T_field("专票限额(金额)","specialInvoiceLimit"),
	             T_field("普票限额(金额)","generalInvoiceLimit"),
	             T_combo("纳税人状态","taxPayerState",
            		     {data:[
	    				       {text:"一般纳税人",value:"0",selected:true},
	    				       {text:"小规模纳税人",value:"1"},
	    				       {text:"其他个人",value:"2"}
	    				       ]}),
	    		 T_field("普票库存预警","generalInventory"),
	    		 T_field("专票库存预警","specialBillStore"),
	             T_field("发票剩余天数预警","invoiceCaDate")
			  ],
	    findCondition:[[T_combo("销方","saleinfoName",{url:rootName+'/SaleInfoConfig/findSaleByUserCode.do',textField:"saleinfoName",valueField:"saleinfoCode"}),
	                    T_combo("纳税人状态","taxPayerState",
	    	    				{data:[{text:"全部",value:"all",selected:true},
	    	    				       {text:"一般纳税人",value:"0"},
	    	    				       {text:"小规模纳税人",value:"1"},
	    	    				       {text:"其他个人",value:"2"}
	    	    				        ]}),
	                    T_combo("审核状态","state",
	    				{data:[{text:"全部",value:"all","selected":true},
	    				       {text:"未提交",value:"0"},
	    				       {text:"审核中",value:"1"},
	    				       {text:"已拒绝",value:"2"},
	    				       {text:"已审核",value:"3"}]})]],
		 column:[[
				  {field:'ck',checkbox:true},
				  T_cm("序号","saleinfoConfigId",{width:100}),
				  T_cm("销方编码","saleinfoCode",{width:150}),
				  T_cm("销方名称","saleinfoName",{width:150}),			
				  T_cm("普票限额(金额)","generalInvoiceLimit",{width:150}),
				  T_cm("专票限额(金额)","specialInvoiceLimit",{width:100}),
				  T_cm("普票库存预警","generalInventory",{width:150}),
				  T_cm("专票库存预警","specialBillStore",{width:150}),
				  T_cm("纳税人状态","taxPayerState",{width:150, formatter:function(value,rec){return taxStatus[value];}}),
				  T_cm("发票剩余天数预警","invoiceCaDate",{width:150}),
				  T_cm("审核时间","checkTime",{width:150,formatter:function(value,rec){return DateTimeFormatter(value);}}),
				  T_cm("审核状态","state",{width:150, formatter:function(value,rec){return approvalStatus[value];}}),
				  T_cm("审核原因","checkReason",{width:150}),
				  T_cm("生效时间","effectiveTime",{width:150,formatter:function(value,rec){return DateTimeFormatter(value);}}) 
				]]
	   },
	GoodsCodeRule:{
		id:"goodCodeRuleId",
	    items:[
	           	 T_combo("商品编码","goodCode",{required:true},{url:rootName+'/GoodsCodeRule/findGoodsCodePara.do',textField:"goodCode",valueField:"goodCode"}),
	           	 T_combo("商品服务名称","goodName",{required:true},{url:rootName+'/GoodsCodeRule/findGoodName.do',textField:"goodName",valueField:"goodName"}),
	           	 T_combo("收入类型","incomeType",{required:true},{url:rootName+'/GoodsCodeRule/findIncomeType.do',textField:"inputTypePara",valueField:"inputTypePara"}),
	             T_combo("特殊或优惠政策","vatkprty",{required:true},{url:rootName+'/GoodsCodeRule/findVatkprty.do',textField:"vatkprty",valueField:"vatkprty"}),
	             T_combo("零税率标识","lslbs",
	            		 {data:[{text:"零税",value:"1","select":true},
	            		        {text:"非零税",value:"0"}]}),
	             T_combo("是否享受特殊或优惠政策","yhzc",
	            		 {data:[{text:"是",value:"1","select":true},
	            		        {text:"否",value:"0"}]}),   
	             T_field("优惠政策内容","yhzcnr",{required:true})
			  ],
	    findCondition:[[T_field("商品编码","goodCode")
	                   ]],
		column:[[
				 {field:'ck',checkbox:true},
				  T_cm("序号","goodCodeRuleId",{width:150}),
				  T_cm("商品编码","goodCode",{width:150}),
				  T_cm("商品服务名称","goodName",{width:150}),
				  T_cm("收入类型","incomeType",{width:150}),
				  T_cm("特殊或优惠政策","vatkprty",{width:100}),
				  T_cm("是否享受特殊优惠政策","yhzc",{width:150,formatter:function(value,rec){return YhzcFormatter[value]}}),
				  T_cm("享受优惠政策内容","yhzcnr",{width:150}),
				  T_cm("零税标识","lslbs",{width:150,formatter:function(value,rec){return lslbsFormatter[value]}}),				  
				  T_cm("最后更新时间","updateTime",{width:150,formatter:function(value,rec){return DateTimeFormatter(value)}})
				]]
	},
	GoodsCodePara:{
		id:"goodCodeParaId",
	    items:[
                 T_field("商品编码","goodCode",{required:true}),
	             T_field("商品服务名称","goodName",{required:true})          
			  ],
	    findCondition:[[T_field("商品编码","goodCode")
	                   ]],
		column:[[
				 {field:'ck',checkbox:true},
				  T_cm("序号","goodCodeParaId",{width:150}),
				  T_cm("商品编码","goodCode",{width:150}),
				  T_cm("商品服务名称","goodName",{width:150}),
				  T_cm("最后更新时间","updateTime",{width:150,formatter:function(value,rec){return DateTimeFormatter(value)}})
				]]
	},
	Customer:{
		id:"customerId",
	    items:[
                 T_field("客户名称","customerName",{required:true}),
	             T_field("客户税号","customerTaxCode",{required:true}),
	             T_combo("客户类型","customerType",
            		     {data:[
	    				       {text:"一般纳税人",value:"0",selected:"selected"},
	    				       {text:"小规模纳税人",value:"1"},
	    				       {text:"其他个人",value:"2"}
	    				       ]}),
	    		 T_field("开户行","bank"),
	    		 T_field("银行账号","account"),
	    		 T_combo("开票频率","billRate",
	    				 {data:[   				      
	    				      {text:"无频率",value:"0",selected:true},
	    		        	  {text:"天",value:"1"},
	    		        	  {text:"月",value:"2"},
	    		        	  {text:"季度",value:"3"},
	    		        	  {text:"半年",value:"4"},
	    		        	  {text:"年",value:"5"}
	    		        	     ]}),
	    		 T_field("注册地址","address"),
	             T_field("注册电话","tel"),
	             T_field("客户编号","customerCode",{required:true}),
	             T_field("收件人","getPeople"),
	             T_field("收件人地址","getAdress"),
	             T_field("收件人电话","getTel"),
	             T_field("客户电话","cuntomerTel")  
			  ],
	    findCondition:[[T_field("客户名称","customerName"),
	                    T_field("客户税号","customerTaxCode"),
	                    T_combo("客户来源","customerRes",
	    	    				{data:[{text:"请选择",value:"all"},
	    	    				       {text:"手动",value:"0"},
	    	    				       {text:"自动",value:"1"},
	    	    				       {text:"全部",value:"all"}
	    	    				        ]})
	    	    		],[
	                    T_combo("补全状态","completionStatus",
	    				{data:[{text:"请选择",value:"all",selected:true},
	    				       {text:"已补全",value:"0"},
	    				       {text:"未补全",value:"1"},
	    				       {text:"全部",value:"all"}
	    				        ]}),
	    	            T_combo("审核状态","status",
	    	    	    	  {data:[{text:"全部",value:"all",selected:true},
	    	    	    		{text:"未提交",value:"0"},
	    	    	    		{text:"审核中",value:"1"},
	    	    	    		{text:"已拒绝",value:"2"},
	    	    	    		{text:"已审核",value:"3"}]}),
	                    T_combo("手动冲抵状态","handStatus",
	                    		{data:[{text:"请选择",value:"all","selected":true},
	                    		        {text:"是",value:"0"},
	                    		        {text:"否",value:"1"},
	                    		        {text:"全部",value:"all"}]})
	                    ],[
	                    T_combo("冲抵锁定状态","lockStatus",		        
	            	            {data:[{text:"请选择",value:"all","selected":true},
	            	                    {text:"是",value:"0"},
	            	                    {text:"否",value:"1"},
	            	                    {text:"全部",value:"all"}]})
	            	                    ]],
		column:[[
				  {field:'ck',checkbox:true},
				  T_cm("序号","customerId",{width:100}),
				  T_cm("客户名称","customerName",{width:150}),
				  T_cm("客户税号","customerTaxCode",{width:150}),
				  T_cm("客户类型","customerType",{width:150, formatter:function(value,rec){return taxStatus[value];}}),
				  T_cm("客户编码","customerCode",{width:150}),
				  T_cm("开户行","bank",{width:100}),
				  T_cm("银行账号","account",{width:150}),
				  T_cm("注册地址","address",{width:150}),
				  T_cm("注册电话","tel",{width:150}),
				  T_cm("开票频率","billRate",{width:150,formatter:function(value,rec){return billRate[value];}}),
				  T_cm("冲抵锁定状态","lockStatus",{width:150}),
				  T_cm("手工锁定状态","handStatus",{width:150}),
				  T_cm("客户源","customerRes",{width:150}),
				  T_cm("补全状态","completionStatus",{width:150}),
				  T_cm("附件一","accessory1",{width:150}),
				  T_cm("附件二","accessory2",{width:150}),
				  T_cm("审核状态","status",{width:150, formatter:function(value,rec){return approvalStatus[value];}}),
				  T_cm("审核原因","checkReason",{width:150}),
				  T_cm("最后更新时间","updateTime",{width:150,formatter:function(value,rec){return DateTimeFormatter(value);}}) 
				]]
	},
	lnkcustPop:{
		items:[T_combo("客户类型","cutType",{data:[{value:"9", text:"无客户信息"},{value:"0", text:"个人"},{value:"1", text:"对公"}]}),
		       T_field("客户名称","customerName"),
		       T_combo("证件类型", "custCertTtype",
		    		   {data: [                          
                				{value:"1", text:"个人身份证"},
                  				{value:"2", text:"户口簿"},
                  				{value:"3", text:"护照"},
                  				{value:"4", text:"军人证"},
                  				{value:"5", text:"港澳台通行证"},
                  				{value:"6", text:"武警"},
                  				{value:"7", text:"边民出入境通行证"},
                  				{value:"0", text:"无证件"}
                           ]}
		       ),T_field("证件号码","custCertNo"),T_field("组织机构代码","entOrgNum"),
		       T_field("对公客户名称","entOrgCustomerName"),T_field("客户编号","customerCode")]
	},
	OriginalData:{
		id:"originalDataId",
	    findCondition:[[T_date("交易日期:","channelDateStart"),T_date("","channelDateEnd",{labelwidth:1}),T_field("交易流水","tradingFlow")],
	                    [T_combo("收入类型","incomeType",{data:[
	                	        {value:"ALL", text:"全部"},
	                	        {value:"A", text:"对公贷款利息"},
	                	        {value:"B", text:"个人贷款利息"},
	                	        {value:"C", text:"中间业务收入"},
	                	        {value:"D", text:"金融同业往来"},
	                	        {value:"E", text:"金融商品交易"},
	                	        {value:"F", text:"一般商品买卖"},
	                	        {value:"G", text:"债券持有收入"}
	                	    ]}),
	                	T_field("货币编码","currency"),T_field("收入机构","saleinfoCode")],
	                	[T_field("交易网点","channelOutlets"),T_combo("客户类型","cutType",{data:[{value:"9", text:"无客户信息"},{value:"0", text:"个人"},{value:"1", text:"对公"}]})]],
		column:[[
				 {field:'ck',checkbox:true},
				  T_cm("序号","originalDataId",{width:100}),
				  T_cm("系统名称","systemName",{width:150}),
				  T_cm("销方名称","saleinfoName",{width:150}),
				  T_cm("销方编码","saleinfoCode",{width:150}),
				  T_cm("客户编号","customerCode",{width:150}),
				  T_cm("客户名称","customerName",{width:150}),
				  T_cm("客户税号","customerTaxCode",{width:150}),
				  T_cm("商品名称","goodName",{width:150}),
				  T_cm("商品编码","goodCode",{width:150}),
				  T_cm("交易流水","tradingFlow",{width:150}),
				  T_cm("交易网点","traChannel",{width:150}),
				  T_cm("本币价税合计","sumAmount",{width:150}),
				  T_cm("本币价税金额","amount",{width:150}),
				  T_cm("本币税额","taxAmount",{width:150}),
				  T_cm("本币税率","taxRate",{width:150}),
				  T_cm("交易日期","channelDate",{width:150,formatter:function(value,rec){return DateTimeFormatter(value);}}),
				  T_cm("原币编码","currency",{width:150}),
				  T_cm("原币价税合计金额","oriInAmount"),
				  T_cm("原币未税金额","oriPrice"),
				  T_cm("原币税额","oriTax"),
				  T_cm("原交易渠道日期","oriChannelDate"),
				  T_cm("原交易渠道网点","oriChannelOutlets"),
				  T_cm("原交易渠道流水","oriTradingNumber")
				  //T_cm("发票类型","billType",{width:150,formatter:function(value,rec){return billType[value]}}),
				]]
	   },
	ExchangeRate:{
			id:"exchangeRateId",
		    findCondition:[[T_date("税率年月:","yearMonth")
		                 ]],
			column:[[
					 {field:'ck',checkbox:true},
					  T_cm("序号","exchangeRateId",{width:100}),
					  T_cm("月份","yearMonth",{width:150}),
					  T_cm("中间价","multIplier",{width:150}),
					  T_cm("目标币种","currency",{width:150}),
					  T_cm("更新时间","updateTime",{width:150,formatter:function(value,rec){return DateTimeFormatter(value)}})
					]]
		   },   	                   
    TaxRule:{
		   id:"taxRuleId",
		   items:[
				  T_field("税率代码","taxCode",{width:150},{required:true}),
				  T_combo("收入类型","incomeType",{width:150},
						  {data:[ {text:"对公贷款利息",value:"A",selected:true},
				                  {text:"个人贷款利息",value:"B"},
				                  {text:"中间业务收入",value:"C"},
				                  {text:"金融同业往来",value:"D"},
				                  {text:"金融商品交易",value:"E"},
				                  {text:"一般商品买卖",value:"F"},
				                  {text:"债券持有收入",value:"G"},
				                  {text:"不产生增值税应税收入",value:"H"},
				                  {text:"不动产租金收入",value:"I"},
				                  {text:"固定资产处置（减税）",value:"J"},
				                  {text:"固定资产处置（一般）",value:"K"}
				                  ]}),
            	  T_field("客户属地","customerArea",{width:150}),
            	  T_combo("特殊优惠政策","vatkprty",{width:150},
            			  {data:[
            			          {text:"政府补贴",value:"1",selected:true},
				                  {text:"国债利息收入",value:"2"},
				                  {text:"农村小额贷款",value:"3"}
				                  ]}),
            	  T_combo("税率类型","taxType",{width:150},
            			  {data:[
            			          {text:"一般征税 6%",value:"A",selected:true},
				                  {text:"一般征税 11%",value:"B"},
				                  {text:"一般征税 13%",value:"C"},
				                  {text:"一般征税 17%",value:"D"},
				                  {text:"0 税率",value:"E"}
				                  ]}),
				  T_field("标准账号","stdAcctNo",{width:150})
				  ],
		  findCondition:[[T_field("税率代码","taxCode")]],
		  column:[[
							  {field:'ck',checkbox:true},
							  T_cm("序号","taxRuleId",{width:150}),
							  T_cm("税率代码","taxCode",{width:150}),
							  T_cm("收入类型","incomeType",{width:150,formatter:function(value,rec){return incomeType[value]}}),
                        	  T_cm("客户属地","customerArea",{width:150}),
                        	  T_cm("特殊优惠政策","vatkprty",{width:150,formatter:function(value,rec){return varkprty[value]}}),
							  T_cm("税率类型","taxType",{width:150,formatter:function(value,rec){return taxType[value]}}),
							  T_cm("税率（%）","taxVal",{width:150}),
                        	  T_cm("标准账号","stdAcctNo",{width:150}),
                        	  T_cm("生效日期","effectiveTime",{width:150}),
							  T_cm("失效日期","inEffectiveTime",{width:150}),
							  T_cm("最后更新时间","updateTime",{width:150,formatter:function(value,rec){return DateTimeFormatter(value)}})
                        	
					]]
	   },
	TaxType:{
		   id:"taxTypeId",
		   items:[
				  T_field("税率代码","taxCode",{width:150},{required:true}),
            	  T_combo("税率类型","taxType",{width:150},
            			  {data:[
            			          {text:"一般征税 6%",value:"A",selected:true},
				                  {text:"一般征税 11%",value:"B"},
				                  {text:"一般征税 13%",value:"C"},
				                  {text:"一般征税 17%",value:"D"},
				                  {text:"0 税率",value:"E"}
				                  ]},{required:true}),
				  T_field("税率","taxVal",{width:150})
				  ],
		  findCondition:[[T_field("税率代码","taxCode")]],
		  column:[[
							  {field:'ck',checkbox:true},
							  T_cm("序号","taxTypeId",{width:150}),
							  T_cm("税率代码","taxCode",{width:150}),
							  T_cm("税率类型","taxType",{width:150,formatter:function(value,rec){return taxType[value]}}),
                        	  T_cm("税率","taxVal",{width:150}),
							  T_cm("最后更新时间","updateTime",{width:150,formatter:function(value,rec){return DateTimeFormatter(value)}}) 
                        	
					]]
	   },
    SpecialPoliciy:{
		   id:"policiesId",
		   items:[
				  T_field("特殊优惠代码","code",{width:150},{required:true}),
				  T_field("特殊优惠政策","vatkprty",{width:150},{required:true})
				  ],
		  findCondition:[[T_field("特殊优惠代码","code")]],
		  column:[[
							  {field:'ck',checkbox:true},
							  T_cm("序号","policiesId",{width:150}),
							  T_cm("特殊优惠代码","code",{width:150}),
							  T_cm("特殊优惠政策","vatkprty",{width:150}),
							  T_cm("最后更新时间","updateTime",{width:150,formatter:function(value,rec){return DateTimeFormatter(value)}}) 
                        	
					]]
	   },
	InputType:{
		   id:"inputTypeId",
		   items:[
				  T_field("收入类型参数代码","inputTypeCode",{width:150},{required:true}),
				  T_field("收入类型参数","inputTypePara",{width:150},{required:true})	 
				  ],
		  findCondition:[[T_field("收入类型参数代码","inputTypeCode")]],
		  column:[[
							  {field:'ck',checkbox:true},
							  T_cm("序号","inputTypeId",{width:150}),
							  T_cm("收入类型参数代码","inputTypeCode",{width:150}),
							  T_cm("收入类型参数","inputTypePara",{width:150}),
							  T_cm("最后更新时间","updateTime",{width:150,formatter:function(value,rec){return DateTimeFormatter(value)}}) 
                        	
					]]
	   },
	OpenDocumentManage:{
			id:"openDocumentId",
		    findCondition:[[T_date("生成日期:","channelDateStart"),T_date("","channelDateEnd",{labelwidth:1}),T_field("客户名称","tradingFlow"),T_field("客户税号","tradingFlow")],
		                   [ T_field("个人客户名称","tradingFlow",{labelwidth:80}),
		                     T_combo("客户类型","incomeType",{data:[
		                	        {value:"ALL", text:"全部"},
		                	        {value:"A", text:"对公贷款利息"},
		                	        {value:"B", text:"个人贷款利息"},
		                	        {value:"C", text:"中间业务收入"},
		                	        {value:"D", text:"金融同业往来"},
		                	        {value:"E", text:"金融商品交易"},
		                	        {value:"F", text:"一般商品买卖"},
		                	        {value:"G", text:"债券持有收入"}
		                	    ]}),
		                    T_combo("个人证件类型","incomeType",{labelwidth:80},{data:[
		                	     {value:"ALL", text:"全部"},
		                	     {value:"A", text:"对公贷款利息"},
		                	     {value:"B", text:"个人贷款利息"},
		                	     {value:"C", text:"中间业务收入"},
		                	     {value:"D", text:"金融同业往来"}
		                	    ]})],[
		                	T_field("个人证件号","currency",{labelwidth:80}),T_field("金额","saleinfoCode",{labelwidth:65}),
		                	T_combo("业务单据类型","incomeType",{labelwidth:80},{data:[
		                	     {value:"ALL", text:"全部"},
		                	     {value:"A", text:"对公贷款利息"},
		                	     {value:"B", text:"个人贷款利息"},
		                	     {value:"C", text:"中间业务收入"},
		                	     {value:"D", text:"金融同业往来"}]})],[
		                  T_combo("业务单据状态","cutType",{labelwidth:80},
		                		 {data:[{value:"9", text:"无客户信息"},
		                		        {value:"0", text:"个人"},
		                		        {value:"1", text:"对公"}]}),
		                  T_combo("发票类型","cutType",{labelwidth:65},
		                		 {data:[{value:"9", text:"无客户信息"},
		                		        {value:"0", text:"个人"},
		                		        {value:"1", text:"对公"}]})]],
			column:[[
					 {field:'ck',checkbox:true},
					  T_cm("序号","originalDataId",{width:100}),
					  T_cm("客户名称","customerCode",{width:150}),
					  T_cm("个人客户明名称","CUSTOMER_NAME",{width:150}),
					  T_cm("个人证件类型","PERS_CUST_CERT_TYPE",{width:150}),
					  T_cm("个人证件号","PERS_CUST_CERT_NO",{width:150}),
					  T_cm("购方开户行以及账号","SALEINFO_BANK",{width:150}),
					  T_cm("购方地址电话","SALEINFO_ADDRESS",{width:150}),
					  T_cm("未税金额","AMOUNT",{width:150}),
					  T_cm("税额","TAX_AMOUNT",{width:150}),
					  T_cm("价税合计","SUM_AMOUNT",{width:150}),
					  T_cm("备注","traChannel",{width:150}),
					  T_cm("数据来源","sumAmount",{width:150}),
					  T_cm("业务单据状态","amount",{width:150}),
					  T_cm("异常信息","taxAmount",{width:150}) 
					]]
		   }, 
		   Product:{
				id:'id',
				items:[
				       T_field("名称","product",{required:true}),
				       T_field("属性","attr",{required:true}),
				       T_field("价格","unit",{required:true}),
				       T_field("状态","status",{required:true}),
				       ],//添加修改
				findCondition:[[T_field("名称","product"),T_field("状态","status")]],
				column:[[
				         {field:'ck',checkbox:true,width:2},
				         T_cm("ID","id",{width:50}),
				         T_cm("名称","product",{width:100}),
				         T_cm("价格","unit",{width:100}),
				         T_cm("属性","attr",{width:100}),
				         T_cm("状态","status",{width:100})
				         ]],
			}, 
			ProductChild:{
					id:'id',
					items:[
					       T_field("名称","product",{required:true}),
					       T_field("属性","attr",{required:true}),
					       T_field("价格","unit",{required:true}),
					       T_field("状态","status",{required:true}),
					       ],//添加修改
					findCondition:[[T_field("名称","product"),T_field("状态","status")]],
					column:[[
					         {field:'ck',checkbox:true,width:2},
					         T_cm("ID","id",{width:50}),
					         T_cm("名称","product",{width:100}),
					         T_cm("价格","unit",{width:100}),
					         T_cm("属性","attr",{width:100}),
					         T_cm("状态","status",{width:100})
					         ]],
				}
}