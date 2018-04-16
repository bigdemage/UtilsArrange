package com.chtwm.mall.common;

import org.apache.commons.beanutils.PropertyUtils;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Clob;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @description <p>类文件描述</p>
 * @author heshiyuan @date 2017年3月10日 下午1:45:49
 * @path: javase-common/com.javase.util/ConvertHelper.java
 * @price ￥：5元
 * @copyright 如有复制粘贴请通知本人或者捐赠，微信号：hewei1109
 * @email heshiyuan@chtwm.com
 * @callnumber 15910868535
 */
public class ConvertHelper {
	private ConvertHelper(){}
	
	public static Map<String,Object> beanToMap(Object... object){
		Map<String,Object> returnMap = new HashMap<>() ;
		try {
			for(Object obj : object){
				Class<? extends Object> class1 = obj.getClass() ;
				Method[] method = class1.getMethods() ;
				Pattern pattern = Pattern.compile("^get*") ;
				Matcher matcher = null ;
				for (Method method2 : method) {
					matcher = pattern.matcher(method2.getName()) ;
					if(matcher.find()){
						String key = method2.getName().replace("get", "") ;
						//将首字母大写变消息
						key = (key.charAt(0)+"").toLowerCase().concat(key.substring(1));
						if("class".equals(key)){
							continue ;
						}
						Object value = method2.invoke(obj) ;
						if(null == value){
							continue ;
						}
						returnMap.put(camel2LowerWithUnderscores(key), value) ;
					}
				}
			}
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return returnMap ;
	}
	/**
	 * 小写命名规则中，单词与单词之间的间隔
	 */
	private static final char ATTRIBUTE_NAME_SEPARATOR = '_';
	/**
	 * @description <p>骆驼命名转换小写下划线命名</p>
	 * @param input
	 * @return
	 * @returnType String
	 * @author heshiyuan @date 2017年3月11日 下午3:42:48
	 * @path javase-common/com.javase.util/ConvertHelper.java
	 * @date 2017年3月11日 下午3:42:48
	 * @price ￥：三毛三
	 * @copyright 如有复制粘贴请通知本人或者捐赠，微信号：hewei1109
	 * @email heshiyuan@chtwm.com
	 * @callnumber 15910868535
	 */
	public static String camel2LowerWithUnderscores(String input) {
		if (input == null)
			return input; // garbage in, garbage out
		int length = input.length();
		StringBuilder result = new StringBuilder(length * 2);
		int resultLength = 0;
		boolean wasPrevTranslated = false;
		for (int i = 0; i < length; i++) {
			char c = input.charAt(i);
			if (i > 0 || c != ATTRIBUTE_NAME_SEPARATOR) // skip first starting underscore
			{
				if (Character.isUpperCase(c)) {
					if (!wasPrevTranslated && resultLength > 0 && result.charAt(resultLength - 1) != ATTRIBUTE_NAME_SEPARATOR) {
						result.append(ATTRIBUTE_NAME_SEPARATOR);
						resultLength++;
					}
					c = Character.toLowerCase(c);
					wasPrevTranslated = true;
				} else {
					wasPrevTranslated = false;
				}
				result.append(c);
				resultLength++;
			}
		}
		return resultLength > 0 ? result.toString() : input;
	}
	/**
	 * @description <p>利用commons-beanutils工具类转换</p>
	 * @param params
	 * @return
	 * @returnType Map<String,Object>
	 * @author heshiyuan @date 2017年3月11日 下午3:59:31
	 * @path javase-common/com.javase.util/ConvertHelper.java
	 * @date 2017年3月11日 下午3:59:31
	 * @price ￥：三毛三
	 * @copyright 如有复制粘贴请通知本人或者捐赠，微信号：hewei1109
	 * @email heshiyuan@chtwm.com
	 * @callnumber 15910868535
	 */
	public static Map<String,Object> beanToMapByCommonsBeanUtils(Object... params){
	    try {
            Map<String,Object> param = new HashMap<String,Object>();
            Map<String, Object> describe=null;
            for(Object obj:params){
                describe = PropertyUtils.describe(obj);
                param.putAll(describe);
            }
            return param;
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);	
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
	}
	/**
	 * @description <p></p>
	 * @param
	 * @return 
	 * @author heshiyuan
	 * @date 2018/3/27 16:50
	 */
	public static Object mapToBean(Map map, Class cls) {
		Object obj = null;
		try {
			obj = cls.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 取出bean里的所有方法
		Method[] methods = cls.getMethods();
		for (int i = 0; i < methods.length; i++) {
			// 取方法名
			String method = methods[i].getName();
			// 取出方法的类型
			Class[] cc = methods[i].getParameterTypes();
			if (cc.length != 1)
				continue;

			// 如果方法名没有以set开头的则退出本次for
			if (method.indexOf("set") < 0)
				continue;
			// 类型
			String type = cc[0].getSimpleName();

			try {
				// 转成小写
				// Object value = method.substring(3).toLowerCase();
				Object value = method.substring(3, 4).toLowerCase()
						+ method.substring(4);
				// 如果map里有该key
				if (map.containsKey(value) && map.get(value) != null) {
					// 调用其底层方法
					setValue(type, map.get(value), i, methods, obj);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return obj;
	}

	private static void setValue(String type, Object value, int i,
								 Method[] method, Object bean) {
		if (value != null && !value.equals("")) {
			try {
				if (type.equals("String")) {
					// 第一个参数:从中调用基础方法的对象 第二个参数:用于方法调用的参数
					method[i].invoke(bean, new Object[] { value });
				} else if (type.equals("int") || type.equals("Integer")) {
					method[i].invoke(bean, new Object[] { new Integer(""
							+ value) });
				} else if (type.equals("double") || type.equals("Double")) {
					method[i].invoke(bean,
							new Object[] { new Double("" + value) });
				} else if (type.equals("float") || type.equals("Float")) {
					method[i].invoke(bean,
							new Object[] { new Float("" + value) });
				} else if (type.equals("long") || type.equals("Long")) {
					method[i].invoke(bean,
							new Object[] { new Long("" + value) });
				} else if (type.equals("boolean") || type.equals("Boolean")) {
					method[i].invoke(bean,
							new Object[] { Boolean.valueOf("" + value) });
				} else if (type.equals("BigDecimal")) {
					method[i].invoke(bean, new Object[] { new BigDecimal(""
							+ value) });
				} else if (type.equals("Date")) {
					Date date = null;
					if (value.getClass().getName().equals("java.util.Date")) {
						date = (Date) value;
					} else if(value.getClass().getName().equals("java.sql.Date")){
						date = (java.sql.Date) value;
					}else{
					    /**
					     * @TODO
                         * yyyy-MM-dd hh:mm
                         * yyyy-MM-dd
                         * yyyy-MM-dd hh:mm:ss
                         * 需要支持三种 时间格式
					     */
						String format = ((String) value).indexOf(":") > 0 ? "yyyy-MM-dd hh:mm" : "yyyy-MM-dd";
						SimpleDateFormat sf = new SimpleDateFormat();
						sf.applyPattern(format);
						date = sf.parse((String) (value));
					}
					if (date != null) {
						method[i].invoke(bean, new Object[] { date });
					}
				} else if (type.equals("byte[]")) {
					method[i].invoke(bean,
							new Object[] { new String(value + "").getBytes() });
				}
			} catch (Exception e) {
				System.out
						.println("将linkHashMap 或 HashTable 里的值填充到javabean时出错,请检查!");
				e.printStackTrace();
			}
		}
	}

	/**
	 * @description <p></p>
	 * @param 
	 * @return 
	 * @author heshiyuan
	 * @date 2018/3/27 16:48
	 */
	public static String ClobToString(Clob clob) {
		if(clob == null) return "";
		StringBuffer strClob=new StringBuffer();
		try {
			Reader reader=clob.getCharacterStream();
			char[] buffer=new char[1024];
			int length=0;
			while((length=reader.read(buffer, 0, 1024))!=-1){
				strClob.append(buffer, 0, length);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return strClob.toString();
	}
}

