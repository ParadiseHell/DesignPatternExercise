package com.ct.factory.utils;

/**
 * ������
 * @author ChengTao
 *
 */
public class StringUtils {
	/**
	 * ����ַ���������
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
