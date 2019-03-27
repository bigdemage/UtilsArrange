package ${basepackage}.core.service.${namespace};
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.chtwm.mall.commons.exception.DataQueryRuntimeException;
import ${basepackage}.pojo.${namespace}.${className};
import java.util.Map;

public interface ${className}Service  {

	public void insert(${className} ${classNameLower});
  
	public PageList<${className}> find${className}ListByPage(int curPage,int pageSize,Map<String,Object> conditionMap) throws DataQueryRuntimeException;

    public ${className} find${className}ById(Long id);

    public void updateById(${className} ${classNameLower});

    public void deletes(Long[] ids);
    
    public void updateEnables(Long[] ids, int enables, Long updateId);

    public boolean find${className}ByIdAndStatus(Map<String,Object> conditionMap);
}
