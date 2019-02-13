package com.jefflee.util;

import java.util.ArrayList;
import java.util.List;

public class StringUtil {

	public static String camel2Underline(String camelString) {
		if (isBlank(camelString)) {
			return "";
		}
		String mysqlAttrName = "";
		for (int i = 0; i < camelString.length(); i++) {
			char ch = camelString.charAt(i);
			if (Character.isUpperCase(ch)) {
				mysqlAttrName += "_" + Character.toLowerCase(ch);
			} else {
				mysqlAttrName += ch;
			}
		}
		return mysqlAttrName;
	}

	public static String capitalize(String word) {
		if (isBlank(word)) {
			return "";
		}
		String firstLetter = word.substring(0, 0);
		return firstLetter.toUpperCase() + word.substring(1, word.length() - 1);
	}

	public static String getFirst(Object strings) {
		if (strings instanceof String[]) {
			return ((String[]) strings)[0];
		}
		return new String();
	}

	public static boolean isBlank(String string) {
		return null == string || "".equals(string.trim());
	}

	public static List<String> strings2List(String[] strings) {
		List<String> list = new ArrayList<String>();
		for (String string : strings) {
			list.add(string);
		}
		return list;
	}

	public static String strings2String(String[] strings) {
		return strings2String(strings, "|");
	}

	public static String strings2String(String[] strings, String seperator) {
		String string = "";
		int length = strings.length;
		if (length > 0) {
			string += strings[0];
		}
		for (int i = 1; i < length; i++) {
			string += seperator + strings[i];
		}
		return string;
	}

	public static String underline2Camel(String underlineString) {
		if (isBlank(underlineString)) {
			return "";
		}
		String[] words = underlineString.split("_");
		String camelString = words[0];
		for (int i = 1; i < words.length; i++) {
			camelString += StringUtil.capitalize(words[i]);
		}
		return camelString;
	}

}
