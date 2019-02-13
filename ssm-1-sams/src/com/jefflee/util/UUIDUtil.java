package com.jefflee.util;

import java.util.UUID;

/**
 * Created by TGL on 2018/4/10.
 */
public class UUIDUtil {

    public static String getOneUUID(){
        UUID uuid=UUID.randomUUID();
        String str = uuid.toString();
        String uuidStr=str.replace("-", "");
        return uuidStr;
    }
}
