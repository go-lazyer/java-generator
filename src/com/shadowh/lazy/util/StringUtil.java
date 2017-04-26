package com.shadowh.lazy.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

	/**
	 * 驼峰转下划线
	 * @param param
	 * @return
	 * @author hanchanghong
	 * @date 2016年5月4日 下午10:35:22
	 */
	public static String camelToUnderline(String param) {
		if (param == null || "".equals(param.trim())) {
			return "";
		}
		int len = param.length();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			char c = param.charAt(i);
			if (Character.isUpperCase(c)) {
				sb.append("_");
				sb.append(Character.toLowerCase(c));
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	/**
	 * 下划线转驼峰
	 * @param param
	 * @return
	 * @author hanchanghong
	 * @date 2016年5月4日 下午10:34:30
	 */
	public static String underlineToCamel(String param) {
		if (param == null || "".equals(param.trim())) {
			return "";
		}
		StringBuilder sb = new StringBuilder(param);
		Matcher mc = Pattern.compile("_").matcher(param);
		int i = 0;
		while (mc.find()) {
			int position = mc.end() - (i++);
			// String.valueOf(Character.toUpperCase(sb.charAt(position)));
			sb.replace(position - 1, position + 1, sb.substring(position, position + 1).toUpperCase());
		}
		return sb.toString();
	}

	/**
	 * 首字母大写
	 * 
	 * @param name
	 * @return
	 * @author hanchanghong
	 * @date 2016年5月9日 下午2:27:16
	 */
	public static String firstUpperCase(String name) {
		name = name.substring(0, 1).toUpperCase() + name.substring(1);
		return name;

	}

	public static Boolean isEmpty(Object o) {
		if (o == null || "".equals(o) || "null".equals(o)) {
			return true;
		}
		return false;
	}

	public static Boolean isNotEmpty(Object o) {
		return !isEmpty(o);
	}
}
