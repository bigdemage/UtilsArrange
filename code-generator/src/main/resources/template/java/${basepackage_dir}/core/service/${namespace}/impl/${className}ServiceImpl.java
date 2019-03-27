package ${basepackage}.core.service.${namespace}.impl;
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chtwm.mall.commons.exception.DataExecuteResultRuntimeException;
import com.chtwm.mall.commons.exception.DataQueryRuntimeException;
import com.chtwm.mall.pojo.recommand.ProductRecommend;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

import ${basepackage}.pojo.${namespace}.${className};
import ${basepackage}.core.dao.mapper.${namespace}.${className}Mapper;
import ${basepackage}.core.service.${namespace}.${className}Service;


@Service("${classNameLower}Service")
public class ${className}ServiceImpl implements ${className}Service   {
    @Resource
    private ${className}Mapper ${classNameLower}Mapper;
    
    @Override
    public void insert(${className} ${classNameLower}){
    	int flag = ${classNameLower}Mapper.insert(${classNameLower});
    	if(flag!=1){
    	    throw new DataExecuteResultRuntimeException("insert",flag);
    	}
    }
    
    
    @Override
    public PageList<${className}> find${className}ListByPage(int curPage,int pageSize,Map<String,Object> conditionMap) throws DataQueryRuntimeException {
        
        PageBounds pageBounds = new PageBounds(curPage,pageSize,true);
        PageList<${className}> ${classNameLower}List;
        try {
            ${classNameLower}List = ${classNameLower}Mapper.find${className}ListByPage(pageBounds,conditionMap);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataQueryRuntimeException(e);
        }
        return ${classNameLower}List;
    }
    
    @Override
    public ${className} find${className}ById(Long id) {
        return ${classNameLower}Mapper.find${className}ById(id);
    }


    @Override
    public void updateById(${className}  ${classNameLower}) {
        int flag = ${classNameLower}Mapper.update( ${classNameLower});
        if(flag!=1){
            throw new DataExecuteResultRuntimeException("update",flag);
        }
    }


    @Override
    public void deletes(Long[] ids) {
        if(ids==null||ids.length==0){
            throw new IllegalArgumentException("ids数组不能为空");
        }
        int flag = 0;
        if(ids.length==1){
            flag= ${classNameLower}Mapper.delete(ids[0]);
            if(flag!=1){
                throw new DataExecuteResultRuntimeException("deletes",flag);
            }
        }else{
            flag = ${classNameLower}Mapper.deletes(ids);
            if(flag!=ids.length){
                throw new DataExecuteResultRuntimeException("deletes",ids.length,flag);
            }
        }
      
    }
    
    @Override
    public void updateEnables(Long[] ids, int enables, Long updateId){
        int flag = ${classNameLower}Mapper.updateEnables(ids, enables, updateId);
        if(flag <= 0){
            throw new DataExecuteResultRuntimeException("updates",flag);
        }
    }
    
    @Override
    public boolean find${className}ByIdAndStatus(Map<String, Object> conditionMap){
        boolean flag = false;
        ${className} ${classNameLower} = ${classNameLower}Mapper.find${className}ByIdAndStatus(conditionMap);
        if(${classNameLower} != null){
            flag = true;
        }
        
        return flag;
    }

}
