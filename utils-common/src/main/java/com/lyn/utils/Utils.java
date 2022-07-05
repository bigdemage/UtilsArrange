package com.lyn.utils;

import com.alibaba.fastjson.JSONObject;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 工具类
 */
public class Utils {

    /**
     * 把对象中的null修改成""
     * 对象中的复杂对象不修改
     * @param target
     */
    public static void setDefaultValue(Object target) {
        if (target != null) {
            try {
                BeanInfo beanInfo = Introspector.getBeanInfo(target.getClass(), Object.class);
                PropertyDescriptor[] propertyDescripions = beanInfo.getPropertyDescriptors();
                PropertyDescriptor[] var3 = propertyDescripions;
                int var4 = propertyDescripions.length;

                for(int var5 = 0; var5 < var4; ++var5) {
                    PropertyDescriptor propertyDescripion = var3[var5];
                    Object value = propertyDescripion.getReadMethod().invoke(target);
                    if (value == null && "java.lang.String".equals(propertyDescripion.getPropertyType().getName())) {
                        propertyDescripion.getWriteMethod().invoke(target, "");
                    }
                }
            } catch (IntrospectionException var8) {
                ;
            } catch (IllegalArgumentException var9) {
                ;
            } catch (IllegalAccessException var10) {
                ;
            } catch (InvocationTargetException var11) {
                ;
            }
        }

    }


    /**
     * 实体bean转成map
     * String则set
     * 非String先转成jsonObjectString再set
     * @param params
     * @return
     */
    public static Map<String, Object> beanToMap(Object... params) {
        try {
            Map<String, Object> param = new HashMap();
            Object[] var2 = params;
            int var3 = params.length;
            for(int var4 = 0; var4 < var3; ++var4) {
                Object obj = var2[var4];
                Class<?> clazz = obj.getClass();
                Method[] methods = clazz.getMethods();
                Pattern pattern = Pattern.compile("^get*");
                Matcher matcher = null;
                Method[] var10 = methods;
                int var11 = methods.length;

                for(int var12 = 0; var12 < var11; ++var12) {
                    Method m = var10[var12];
                    matcher = pattern.matcher(m.getName());
                    if (matcher.find()) {
                        String key = m.getName().replace("get", "");
                        key = (key.charAt(0) + "").toLowerCase().concat(key.substring(1));
                        if (!"class".equals(key)) {
                            Object res = m.invoke(obj);
                            if (res != null) {
                                if(res instanceof String){
                                    param.put(key, res);
                                }else{
                                    param.put(key, JSONObject.toJSONString(res));
                                }
                            }
                        }
                    }
                }
            }
            return param;
        } catch (SecurityException var16) {
            throw new RuntimeException(var16);
        } catch (IllegalAccessException var17) {
            throw new RuntimeException(var17);
        } catch (IllegalArgumentException var18) {
            throw new RuntimeException(var18);
        } catch (InvocationTargetException var19) {
            throw new RuntimeException(var19);
        }
    }
}
