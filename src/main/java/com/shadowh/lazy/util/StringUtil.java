package com.shadowh.lazy.util;

import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public class StringUtil {
	public static String BEFORE = "before";
	public static String AFTER = "after";
	/**
	 * 产生随机字符串
	 */
	private static Random randGen = null;
	private static char[] numbersAndLetters = null;
	public static final String REG_MOBILE = "^((13[0-9])|(14[0-9])|(15[^4,\\D])|(17[0-9])|(18[0-9]))\\d{8}$";
	public static final String REG_INT = "^[0-9]{1,}$";

	public static boolean isMobile(Object mobile) {
		if (mobile == null || mobile.toString().trim().equals("")) {
			return false;
		}
		if (!mobile.toString().trim().matches(REG_MOBILE)) {
			return false;
		}
		return true;
	}

	public static boolean isNullOrTooLong(Object str, int maxLength) {
		if (str == null || str.toString().trim().equals("")) {
			return true;
		}
		if (str.toString().trim().length() > maxLength) {
			return true;
		}
		return false;
	}

	public static boolean isTooLong(Object str, int maxLength) {
		if (str == null || str.toString().trim().equals("")) {
			return false;
		}
		if (str.toString().trim().length() > maxLength) {
			return true;
		}
		return false;
	}

	public static boolean isInt(Object str) {
		if (str == null || str.toString().trim().equals("")) {
			return false;
		}
		if (!str.toString().trim().matches(REG_INT)) {
			return false;
		}
		return true;
	}

	public static boolean isDouble(Object str) {
		if (str == null || str.toString().trim().equals("")) {
			return false;
		}
		try {
			Double.parseDouble(str.toString());
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public static boolean getCheckParamAttr(Map<String, Object> param, String[] params) {
		for (int i = 0; i < params.length; i++) {
			if (param.get(params[i]) == null || StringUtils.isBlank(param.get(params[i]).toString())) {
				return false;
			}
		}
		return true;
	}

	public static String getRandomString(int length) { // length表示生成字符串的长度
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	/**
	 * @desc根据字符串数组放回该数组对用json格式的数据
	 * @param stringArray
	 * @return
	 */
	public static String returnString(String[] stringArray) {
		String str = "";
		if (null != stringArray && 0 < stringArray.length) {
			str += "[";
			for (String s : stringArray) {
				str = str + "\"" + s + "\", ";
			}
			str = str.substring(0, str.length() - 2);
			str += "]";
		}
		return str;
	}

	public static String getOperationNum(String createTime, Integer id) {
		String str = createTime.replace("-", "").replace(" ", "").replace(":", "");
		for (int i = 0; i < 10 - id.toString().length(); i++) {
			str += "0";
		}
		str += id;
		return str;
	}

	/**
	 * 补充字符串到 指定的长度
	 * 
	 * @param res
	 * @param sign
	 *            补充的字符串
	 * @param n
	 *            长度
	 * @param type
	 *            0:补充到 res 后面 1：补充到res 签名
	 * @return before after
	 */
	public static String addSign(String res, String sign, int n, String type) {
		for (int i = res.length(); i < n; i++) {
			if ("after".equals(type)) {
				res = res + sign;
			} else if ("before".equals(type)) {
				res = sign + res;
			}
		}
		return res;
	}

	public static String removeEnd(String str) {
		if (StringUtil.isEmpty(str)) {
			return "";
		}
		return str.substring(0, str.length() - 1);
	}

	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	public static boolean isEmpty(String str) {
		if ("".equals(str) || "null".equalsIgnoreCase(str) || str == null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @desc获取一个随机字符串
	 * @param length
	 *            随机字符串的长度
	 * @return
	 */
	public static final String randomString(int length) {
		if (length < 1) {
			return null;
		}
		if (randGen == null) {
			randGen = new Random();
			numbersAndLetters = ("0123456789abcdefghijklmnopqrstuvwxyz" + "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
			// numbersAndLetters =
			// ("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
		}
		char[] randBuffer = new char[length];
		for (int i = 0; i < randBuffer.length; i++) {
			randBuffer[i] = numbersAndLetters[randGen.nextInt(71)];
		}
		return new String(randBuffer);
	}

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
