package ${basepackage}.sdk.controller.${namespace};
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chtwm.base.tools.utils.common.ConvertUtils;
import com.chtwm.platform.bean.common.pojo.PageBeanResult;
import com.chtwm.platform.pojo.sys.User;
import com.chtwm.platform.sdk.controller.common.constant.BackGlobalConstant;
import com.chtwm.platform.sdk.controller.common.constant.BackGlobalResultCode;
import com.chtwm.platform.sdk.controller.common.domain.BackAppResponseBody;
import com.chtwm.platform.sdk.controller.common.domain.RequestJsonToBean;
import com.chtwm.mall.sdk.controller.common.exception.TransformJsonParamsException;
import com.chtwm.mall.sdk.controller.common.exception.ValidateParamsException;
import com.chtwm.platform.sdk.controller.sys.BaseAction;
import com.chtwm.mall.commons.exception.DataQueryRuntimeException;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

import ${basepackage}.core.service.${namespace}.${className}Service;
import ${basepackage}.pojo.${namespace}.${className};
import ${basepackage}.pojo.${namespace}.request.${className}Condition;

@Controller
@RequestMapping("/${namespace}/${classNameLower}")
public class ${className}Action  extends BaseAction{

    private static final Logger logger = LoggerFactory.getLogger(${className}.class);
    
    @Autowired
    private ${className}Service ${classNameLower}Service;
    
    /**
     * 列表查询.
     * @param json
     * @return
     */
    @RequestMapping("/pageList.action")
    public @ResponseBody BackAppResponseBody<Object>  find${className}PageList(@RequestBody String json ){
        BackAppResponseBody<Object> backAppResponseBody = new BackAppResponseBody<Object>();
        PageList<${className}> ${classNameLower}List=null;
        PageBeanResult<${className}> pageBeanResult = new PageBeanResult<${className}>();
        try {
            ${className}Condition ${classNameLower}Condition = RequestJsonToBean.requestJsonToBean(json, ${className}Condition.class);
            Map<String,Object> map = ConvertUtils.beanToMap(${classNameLower}Condition);
            ${classNameLower}List = ${classNameLower}Service.find${className}ListByPage(${classNameLower}Condition.getCurPage(),${classNameLower}Condition.getPageSize(),map);
            pageBeanResult.setPageList(${classNameLower}List);
            backAppResponseBody.setData(pageBeanResult);
        }catch (DataQueryRuntimeException e) {
            e.printStackTrace();
            logger.error(e.getMessage(),e);
            return defaultFail();
        }catch (TransformJsonParamsException e) {
            return defaultFail(e.getMsg(),e.getCode()); 
        }catch(Exception e){
            logger.error("分页查询状态发生异常,请求json数据:{},异常信息:{},异常对象{}",json,e.getMessage(),e);
            e.printStackTrace();
            return defaultFail();
        }
        return backAppResponseBody;
    }
    
    /**
     * 新增记录.
     * @param json
     * @param session
     * @return
     */
    @RequestMapping("/add.action")
    public @ResponseBody BackAppResponseBody<Object>  add(@RequestBody String json, HttpSession session){
        BackAppResponseBody<Object> backAppResponseBody = new BackAppResponseBody<Object>();
        User user = (User) session.getAttribute(BackGlobalConstant.SESSION_USER_KEY);
        try {
            ${className}Condition ${classNameLower}Condition = RequestJsonToBean.requestJsonToBean(json, ${className}Condition.class);
            if(${classNameLower}Condition.getId()==null){
                    ${classNameLower}Condition.setCreateId(user.getId());
                    ${classNameLower}Service.insert(${classNameLower}Condition);
            }
        }catch (TransformJsonParamsException e) {
            return defaultFail(e.getMsg(),e.getCode()); 
        }catch(Exception e){
            e.printStackTrace();
            logger.error(e.getMessage(), e);
            return defaultFail();
        }
        return backAppResponseBody;
    }
    
    /**
     * 修改保存.
     * @param json
     * @param session
     * @return
     */
    @RequestMapping("/update.action")
    public @ResponseBody BackAppResponseBody<Object>  update(@RequestBody String json, HttpSession session){
        BackAppResponseBody<Object> backAppResponseBody = new BackAppResponseBody<Object>();
        User user = (User) session.getAttribute(BackGlobalConstant.SESSION_USER_KEY);
        try {
            ${className}Condition ${classNameLower}Condition = RequestJsonToBean.requestJsonToBean(json,  ${className}Condition.class);
            if(${classNameLower}Condition.getId()!=null){
                    ${classNameLower}Condition.setUpdateId(user.getId());
                    ${classNameLower}Service.updateById(${classNameLower}Condition);
            }
        }catch (DataQueryRuntimeException e) {
            e.printStackTrace();
            logger.error(e.getMessage(),e);
            return defaultFail();
        }catch (TransformJsonParamsException e) {
            return defaultFail(e.getMsg(),e.getCode()); 
        }catch(Exception e){
            e.printStackTrace();
            return defaultFail();
        }
        return backAppResponseBody;
    }
    
    /**
     * 删除记录.
     * @param json
     * @return
     * @throws ValidateParamsException
     */
    @RequestMapping("/delete.action")
    public @ResponseBody BackAppResponseBody<Object>  delete(@RequestBody String json ) throws ValidateParamsException{
        BackAppResponseBody<Object> backAppResponseBody = new BackAppResponseBody<Object>();
        try {
            ${className}Condition ${classNameLower}Condition = RequestJsonToBean.requestJsonToBean(json, ${className}Condition.class);
            if(${classNameLower}Condition.getIds()!=null){
                   Long[] ids =  stringsToLongs(${classNameLower}Condition.getIds(), "id非法,请刷新后重试");
                   ${classNameLower}Service.deletes(ids);
            }
        }catch (DataQueryRuntimeException e) {
            e.printStackTrace();
            logger.error(e.getMessage(),e);
            return defaultFail();
        }catch (TransformJsonParamsException e) {
            return defaultFail(e.getMsg(),e.getCode()); 
        }catch(Exception e){
            e.printStackTrace();
            logger.error(e.getMessage(), e);
            return defaultFail();
        }
        return backAppResponseBody;
    }
    
    /**
     * 修改保存.
     * @param json
     * @return
     */
    @RequestMapping("/toUpdate.action")
    public @ResponseBody BackAppResponseBody<Object>  toUpdate(@RequestBody String json ){
        BackAppResponseBody<Object> appResponseBody = new BackAppResponseBody<Object>();
        try {
            ${className}Condition ${classNameLower}Condition = RequestJsonToBean.requestJsonToBean(json, ${className}Condition.class);
            ${className}  ${classNameLower}= ${classNameLower}Service.find${className}ById(${classNameLower}Condition.getId());
            appResponseBody.setData( ${classNameLower});
        }catch (DataQueryRuntimeException e) {
            e.printStackTrace();
            logger.error(e.getMessage(),e);
            return defaultFail();
        }catch (TransformJsonParamsException e) {
            return defaultFail(e.getMsg(),e.getCode()); 
        }catch(Exception e){
            e.printStackTrace();
            logger.error(e.getMessage(), e);
            return defaultFail();
        }
        return appResponseBody;
    }
    
    /**
     * 更新状态.
     * @param json
     * @param session
     * @return
     */
    @RequestMapping("/updateEnables.action")
    public @ResponseBody BackAppResponseBody<Object>  updateEnables(@RequestBody String json, HttpSession session){
        BackAppResponseBody<Object> backAppResponseBody = new BackAppResponseBody<Object>();
        User user = (User) session.getAttribute(BackGlobalConstant.SESSION_USER_KEY);
        try {
            ${className}Condition ${classNameLower}Condition = RequestJsonToBean.requestJsonToBean(json, ${className}Condition.class);
            if(${classNameLower}Condition.getIds()!=null){
                Long[] ids =  stringsToLongs(${classNameLower}Condition.getIds(), "id非法,请刷新后重试");
                ${classNameLower}Service.updateEnables(ids, ${classNameLower}Condition.getEnables(), user.getId());
             }
        }catch (DataQueryRuntimeException e) {
            e.printStackTrace();
            logger.error(e.getMessage(),e);
            return defaultFail();
        }catch (TransformJsonParamsException e) {
            return defaultFail(e.getMsg(),e.getCode()); 
        }catch(Exception e){
            e.printStackTrace();
            logger.error(e.getMessage(), e);
            return defaultFail();
        }
        return backAppResponseBody;
    }
    
}
