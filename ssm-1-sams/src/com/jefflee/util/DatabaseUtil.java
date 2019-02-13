package com.jefflee.util;

import java.util.UUID;

public class DatabaseUtil {

	public static String gnr32Uuid() {
		String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
		return uuid;
	}

	public static String mysql2Std(String mysqlAttrName) {
		String stdAttrName = StringUtil.underline2Camel(mysqlAttrName);
		return stdAttrName;
	}

	public static String std2Mysql(String stdAttrName) {
		String mysqlAttrName = StringUtil.camel2Underline(stdAttrName);
		return mysqlAttrName;
	}

}
