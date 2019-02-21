package com.sams.util;

public class StringUtils {

    public static boolean isNotBlank(String str){
        return str!=null && !"".equals(str.trim());
    }

    public static boolean isEmpty(String str){
        return str==null || "".equals(str.trim());
    }
}
