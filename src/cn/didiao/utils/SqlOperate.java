package cn.didiao.utils;

import cn.didiao.component.BgmsConfig;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SqlOperate {

    public static String[] getParameter(Map<String, String> newMap) {
        String[] str=new String[newMap.size()];
        int index=0;
        for (String s : newMap.keySet()) {
            str[index++]=newMap.get(s);
        }
        return str;

    }

    public static String sqlPJ(Map<String, String> newMap,String path) {
        StringBuilder sb=new StringBuilder("update "+ path+" set ");

        Set<String> strings = newMap.keySet();
        for (String s : strings) {
            sb.append(s+"=? ,");
        }

        return sb.substring(0,sb.length()-1);
    }


    public static String insertSql(Map<String, String> newMap,String path){

        StringBuilder sb=new StringBuilder("insert into "+ path+" ( ");
        int index=0;
        Set<String> strings = newMap.keySet();
        for (String s : strings) {
            sb.append(s+",");
        index++;
        }
        sb.append("createtime");
        StringBuilder strSql=new StringBuilder(sb+ ") values(");
        for (int i = 0; i <index ; i++) {
            strSql.append("?,");
        }
        strSql.append("now())");
        return strSql.toString();

    }

    public static Map<String, String> MapZH(Map<String, String[]> parameterMap) {
        Map<String, String> newMap = new HashMap<>();
        for (String s : parameterMap.keySet()) {
            if (parameterMap.get(s)[0] != null && parameterMap.get(s)[0] != "") {
                newMap.put(s, parameterMap.get(s)[0]);
            }

        }
        return newMap;

    }


}
