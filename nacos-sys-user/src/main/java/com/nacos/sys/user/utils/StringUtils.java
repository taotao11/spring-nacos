package com.nacos.sys.user.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.UUID;

/**
 * 字符类
 */
public class StringUtils {
    public static String getUuid(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
    /**
     * 字符串加密
     * @param str
     * @return
     */
    public static String strTobCrypt(String str){
        if (str == null || "".equals(str)){
            return null;
        }
        return new BCryptPasswordEncoder().encode(str);
    }

    public static boolean isBlank(CharSequence cs) {
        int strLen;
        if (cs != null && (strLen = cs.length()) != 0) {
            for(int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(cs.charAt(i))) {
                    return false;
                }
            }

            return true;
        } else {
            return true;
        }
    }

    public static boolean isNotBlank(CharSequence cs) {
        return !isBlank(cs);
    }
}
