<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
<html>
  <head>
   <%@include file="/view/resource.jsp" %>
  </head>
	<body class="easyui-layout">

	<div class="ui-search-panel" region="north">
		<p class="search-title">${table.remarks}</p>
		<form id="searchForm" class="form-inline">
				<div class="form-group">
					<#list table.columns as column>	
						<#if column.columnNameLower!="id">
							<label>${column.remarks}:</label> 
							<input class="form-control" type="text" name="${column.columnNameLower}" > 
						</#if>
					</#list>
					<a href="#" id="btn-search" class="">查询</a>
				</div>
		</form>
	</div>

	<!--数据表格  -->
     <div region="center" border="false" >
     	<table id="data-list"></table>
     </div>

     <!-- 编辑form -->
     <div id="edit-win" class="hidden easyui-dialog" title="编辑" data-options="closed:true,iconCls:'icon-save',modal:true" style="width: 600%;top: 30%;">  
     	<form id="editForm" class="ui-form form-horizontal" method="post" style="height: 400px;">   
     		<input type="hidden" class="hidden" name="id">
          	  <#list table.columns as column>
          	  	<#if column.columnNameLower=="enables">
					<div class="form-group clx">
						<label class="control-label">状态</label>
						<select name="enables"  class="form-control">
							<option value="1" selected="selected">启用</option>
							<option value="0">禁用</option>
						</select>
					</div>
				<#elseif column.columnNameLower!="id" >
					<div class="form-group clx">
						<label class="control-label">${column.remarks}:</label>
						<div class="box">
							<input class="form-control" type="text" name="${column.columnNameLower}">
						</div>
					</div>
				</#if>
			  </#list>
     	</form>
  	 </div> 
  </body>
   <script type="text/javascript" src="${r'$'}${r'{'}staticResourceBaseUrl${r'}'}/resources/business/${namespace}/${classNameLower}.js"></script>
</html>
