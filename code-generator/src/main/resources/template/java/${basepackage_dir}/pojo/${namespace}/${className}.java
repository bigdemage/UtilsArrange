package ${basepackage}.pojo.${namespace};
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 

import java.io.Serializable;
import java.util.Date;

import com.chtwm.base.tools.utils.common.DateUtils;

/**
 * ${table.remarks}
 */
public class ${className}  implements Serializable  {
    private static final long serialVersionUID = 5454155825314635342L;
    
    <#list table.columns as column>
    /**
     * ${column.remarks}
     */
    private ${column.simpleJavaType} ${column.columnNameLower};
    </#list>

<@generateJavaColumns/>
/**
 * 创建人名称
 */
private String createName;
/**
 * 修改人名称
 */
private String updateName;
<#macro generateJavaColumns>
    <#list table.columns as column>
    <#if column.isDateTimeColumn>
	public String get${column.columnName}String() {
	  return DateUtils.formatDateToString(get${column.columnName}(), "yyyy-MM-dd HH:mm:ss");
	}
	public void set${column.columnName}String(String ${column.columnNameLower}Str) {
	  set${column.columnName}(DateUtils.parse(${column.columnNameLower}Str, "yyyy-MM-dd HH:mm:ss"));
	}
     </#if>   
    
    /**
     *${column.columnNameLower}的setter方法
     **/     
    public void set${column.columnName}(${column.simpleJavaType} ${column.columnNameLower}) {
        this.${column.columnNameLower} = ${column.columnNameLower};
    }
     /**
     *${column.columnNameLower}的getter方法
     **/ 
    public ${column.simpleJavaType} get${column.columnName}() {
        return this.${column.columnNameLower};
    }
    </#list>
</#macro>
    
    public String getCreateName() {
        return createName;
    }
    public void setCreateName(String createName) {
        this.createName = createName;
    }
    public String getUpdateName() {
        return updateName;
    }
    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }
}