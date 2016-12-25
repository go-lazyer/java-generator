package com.shadowh.lazy.util;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public class StringUtil {
	/**
	 * 产生随机字符串
	 */
	private static Random randGen = null;
	private static char[] numbersAndLetters = null;

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
			numbersAndLetters = ("0123456789abcdefghijklmnopqrstuvwxyz" + "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ")
					.toCharArray();
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
	 * @param name
	 * @return
	 * @author hanchanghong
	 * @date 2016年5月9日 下午2:27:16
	 */
	public static String firstUpperCase(String name) {
		name = name.substring(0, 1).toUpperCase() + name.substring(1);
		return name;

	}
	
	public static Boolean isEmpty(Object o){
		if(o==null||"".equals(o)||"null".equals(o)){
			return true;
		}
		return false;
	}
	public static Boolean isNotEmpty(Object o){
		return !isEmpty(o);
	}
	public static void main(String[] args) {
		System.out.println(underlineToCamel("user_id"));
	}
}
