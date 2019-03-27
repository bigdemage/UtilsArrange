package  ${basepackage}.core.dao.mapper.${namespace};
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.chtwm.mall.commons.exception.DataQueryRuntimeException;
import ${basepackage}.pojo.${namespace}.${className};
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

import ${basepackage}.pojo.${namespace}.${className};

public interface ${className}Mapper{

    public int insert(${className} ${classNameLower});
    
    public int deletes(@Param("ids")Long[] ids);
    
    public int delete(@Param("id")Long id);
    
    public PageList<${className}> find${className}ListByPage(PageBounds pageBounds,Map<String,Object> conditionMap) ;
    
    public ${className} find${className}ById(@Param("id")Long id);
    
    public int update(${className} ${classNameLower});
    
    public ${className} find${className}ByIdAndCodeName(Map<String,Object> conditionMap);
    
    public int updateEnables(@Param("ids")Long[] ids, @Param("enables") int enables, @Param("updateId")Long updateId);
    
    public ${className} find${className}ByIdAndStatus(Map<String,Object> conditionMap);
}
    
    