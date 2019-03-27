<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
xps.${classNameLower} = function(){
	var _box = null;
	var _this = {
		config:{
		    action : {
		        /**
		         * 增加页面或增加按钮
		         */
		        toAdd:htee_dynamicResourceBaseUrlJs + '/${namespace}/${classNameLower}/toAdd.action',
		        /**
		         * 保存提交
		         */
		        add : htee_dynamicResourceBaseUrlJs + '/${namespace}/${classNameLower}/add.action',
		        /**
		         * 更新提交
		         */
                update:htee_dynamicResourceBaseUrlJs + '/${namespace}/${classNameLower}/update.action',
                /**
                 * 更新页面或更新按钮
                 */
                toUpdate : htee_dynamicResourceBaseUrlJs + '/${namespace}/${classNameLower}/toUpdate.action',
                /**
		         * 更新提交
		         */
                updateEnables:htee_dynamicResourceBaseUrlJs + '/${namespace}/${classNameLower}/updateEnables.action',
                /**
                 * 删除按钮
                 */
                remove : htee_dynamicResourceBaseUrlJs + '/${namespace}/${classNameLower}/delete.action',
                /**
                 * 列表
                 */
                pageList:htee_dynamicResourceBaseUrlJs + '/${namespace}/${classNameLower}/pageList.action'
            },
            
            event:{
                edit:function(callback){
                    var record = DataGridUtils.getCheckedRows();
                    if (DataGridUtils.checkSelectOne(record)){
                        xps.progress();
                        var idKey = "id";
                        var data={};
                        data[idKey] = (record[0][idKey]);
                        var dataStr = xps.getRequestParams(data);
                        xps.ajaxJsonRequest(_this.config.action.toUpdate,dataStr,function(result){
                        	//填充form
                            $("#editForm").form('load',result.data);
                            $("#edit-win").dialog('open'); 
                            //回调函数
                            if(jQuery.isFunction(callback)){
                                callback(result);
                            }
                        });
                    }
                },

              //删除记录
    			remove: function(callback){
    				var records = DataGridUtils.getCheckedRows();
    				if (DataGridUtils.checkSelectOne(records)){
    					if(records[0].enables == '1'){
							xps.alert('警告', '数据处于启用状态，不允许删除，请确认！', 'warning');
                        	return;
						}
						_box.handler.remove();
    				}
    			},
    			//刷新Grid 数据
    			search: function(callback){
                    var pageObj = _box.grid.datagrid('getPager');
                    var pageop = pageObj.data("pagination").options;
                    pageop.pageNumber = 1;
                    _box.handler.search(callback);
    			},
                save: function(callback){
                    if($("#editForm").form('validate')){
                        //Form.edit.attr('action',Action.add);
                        var formJsonObject=$("#editForm").serializeObject();
                        var dataStr=xps.getRequestParams(formJsonObject);
                        var url ='';
                        if(formJsonObject.id){
                            url = _this.config.action.update;
                        }else{
                            url = _this.config.action.add;
                        }
                        
                        xps.ajaxJsonRequest(url,dataStr,function(data){
                            xps.closeProgress();
                            $("#edit-win").dialog('close');
                            _box.handler.refresh();
                            $("#editForm").resetForm();
                            //回调函数
                            if(jQuery.isFunction(callback)){
                                callback(data);
                            }
                        });
                        
                     }
                }
            },
            
			/**
			 * 数据表格
			 */
  			dataGrid:{
  				title:'${table.remarks}列表',
	   			columns:[[
        			<#list table.columns as column>	
        				<#if column.columnNameLower=="id">
        					{field:'id',checkbox:true,align:'center'},
            			<#elseif column.columnNameLower=="enables">
        					{field:'enables',title:'状态',width:80,sortable:true,align:'center',formatter:function(value,row,index){
        						if(value == 0){
        							return "禁用";
        						}
        						if(value == 1){
        							return "启用";
        						}
        					}},
        				<#elseif column.isDateTimeColumn>
        					{field:'${column.columnNameLower}String',title:'${column.remarks}',width:150,sortable:true,align:'center'},
        				<#else>
        					{field:'${column.columnNameLower}',title:'${column.remarks}',width:120,sortable:true,align:'center'}<#if column_has_next>,</#if>
        				</#if>
        			</#list>
				]],
				toolbar : [ {
					id:"button-stop",
					btnType:"stop",
					iconCls : 'icon-add',
					text:'禁用',
					handler :function(){
						var record = DataGridUtils.getCheckedRows();
						var ids=new Array();
						if(record.length<=0){
							xps.alert('警告', '请选择禁用记录', 'warning');
							return;
						}
						for(var i=0;i<record.length;i++){
							ids.push(record[i]["id"]);
						}
							var idKey = 'ids'; //主键名称
							var data={enables:0};
		 					data[idKey] =ids.toString();
		 					var dataStr = xps.getRequestParams(data);
		 					xps.debug("禁用按钮请求参数:"+dataStr);
							xps.ajaxJsonRequest(_this.config.action.updateEnables,dataStr,function(result){
								xps.closeProgress();
	    					    _box.handler.refresh();
	                            $("#editForm").resetForm();
	                            $('#edit-win').dialog('close');
							});
				  }},{
					id:"button-start",
					btnType:"start",
					iconCls : 'icon-add',
					text:'启用',
					handler :function(){
						var record = DataGridUtils.getCheckedRows();
						var ids=new Array();
						if(record.length<=0){
							xps.alert('警告', '请选择启用记录', 'warning');
							return;
						}
						for(var i=0;i<record.length;i++){
							ids.push(record[i]["id"]);
						}
							var idKey = 'ids'; //主键名称
							var data={enables:1};
		 					data[idKey] = ids.toString();//(record[0][idKey]);
		 					var dataStr = xps.getRequestParams(data);
		 					xps.debug("启用按钮请求参数:"+dataStr);
							xps.ajaxJsonRequest(_this.config.action.updateEnables,dataStr,function(result){
								xps.closeProgress();
	    					    _box.handler.refresh();
	                            $("#editForm").resetForm();
	                            $('#edit-win').dialog('close');
							});
				}		
			}]}
		},
		init:function(){
		    _box = new XPSDataGrid(_this.config);
            _box.init(_this.config.action.pageList);
		},
    	isEmptyObject:function(s){
			if(s == null || typeof(s) == "undefined" || s.replace(/\s/g, "").length ==0){
				return true;
			}
				return false;
		}
	}
	return _this;
}();

$(function(){
	xps.${classNameLower}.init();
});		