package com.ct.simple.factoty.utils;

/**
 * ×Ö·û´´¹¤¾ßÀà
 * 
 * @author ChengTao
 *
 */
public class StringUtils {

	/**
	 * ´òÓ¡×Ö·û´®²»»»ÐÐ
	 * @param s ×Ö·û´®
	 */
	public static void print(String s) {
		if (isStrNotNull(s)) {
			System.out.print(s);
		}
	}

	/**
	 * ´òÓ¡×Ö·û´®»»ÐÐ
	 * @param s ×Ö·û´®
	 */
	public static void println(String s) {
		if (isStrNotNull(s)) {
			System.out.println(s);
		}
	}

	/**
	 * ÅÐ¶Ï×Ö·û´®ÊÇ·ñÎªnull
	 * 
	 * @param s
	 * @return
	 */
	private static boolean isStrNotNull(String s) {
		return !s.isEmpty() && s != null;
	}

}
