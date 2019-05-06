package com.ssms.common.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 字符串工具类
 */
public class StringUtil {

	/**
	 * 是否为空
	 *
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str) {
		if (str == null || str.isEmpty() || str.replaceAll(" ", "").isEmpty()) {
			return true;
		}
		return false;
	}

	public static boolean isNotNull(String value) {
		if (value != null && !"".equals(value)) {
			return true;
		}
		return false;
	}

	public static boolean isBlank(String... strs) {
		for (int i = 0; i < strs.length; i++) {
			if (isBlank(strs[i])) {
				return true;
			}
		}
		return false;
	}

	public static boolean isNotBlank(String str) {
		return !isBlank(str);
	}

	public static String getStr(String str) {
		return str == null ? "" : str;
	}

	public static boolean contains(String str, String key) {
		if (str != null && str.contains(key)) {
			return true;
		}
		return false;
	}

	public static boolean contains(String... strs) {
		for (int i = 0; i < strs.length - 1; i++) {
			if (contains(strs[i], strs[strs.length - 1])) {
				return true;
			}
		}
		return false;
	}

	public static boolean contains(String[] strs, String key) {
		for (int i = 0; i < strs.length; i++) {
			if (contains(strs[i], key)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 首字母大写
	 *
	 * @param in
	 * @return
	 */
	public static String upperHeadChar(String in) {
		String head = in.substring(0, 1);
		String out = head.toUpperCase() + in.substring(1, in.length());
		return out;
	}

	public static String[] split(String input, String regex) {
		if (input == null) {
			return null;
		}
		String[] arr = input.split(regex);
		List<String> results = new ArrayList<>(arr.length);
		for (String a : arr) {
			if (!a.trim().isEmpty()) {
				results.add(a);
			}
		}
		return results.toArray(new String[0]);
	}

}
