package com.ct.factory.utils;

/**
 * 工具类
 * @author ChengTao
 *
 */
public class StringUtils {
	/**
	 * 输出字符串并换行
	 * @param s
	 */
	public static void printlnString(String s) {
		if (isStrNotNUll(s)) {
			System.out.println(s);
		}
	}
	
	private static boolean isStrNotNUll(String s) {
		return !s.isEmpty() && s != null;
	}
}
