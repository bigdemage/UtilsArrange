package com.lyn.testCode;

import org.apache.commons.lang3.StringUtils;

import javax.sound.midi.Soundbank;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName DomainMatch
 * @Deacription TODO
 * @Author wrx
 * @Date 2021/5/26/026 15:50
 * @Version 1.0
 **/
public class DomainMatch {

    private static String domainReg = "((com.cn)|(com)|(org)|(net)|(cn))";


    public static void main(String[] args) {
        String url="https://rbac-qasa.chtwm.com/index.shtml";

        Pattern pattern = Pattern.compile(domainReg);
        Matcher matcher = pattern.matcher(url);
        String domain = null;
        while(matcher.find()){
            domain = matcher.group();
            if (!StringUtils.isBlank(domain)) {
                break;
            }
        }
        System.out.println(domain);

        final Map<String, String> domainMap = new HashMap<>();
        domainMap.put("chtwm","chtwm.com");
        final Set<String> domainFlagSet = domainMap.keySet();
        for (String domainFlag : domainFlagSet) {
            final String domainValue = domainMap.get(domainFlag);
            if (domainValue.equals(domain)) {
                System.out.println(domainFlag);
            }
        }
        System.out.println("没有");
    }


}
