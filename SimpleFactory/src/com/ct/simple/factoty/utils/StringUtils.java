package com.ct.simple.factoty.utils;

/**
 * �ַ���������
 * 
 * @author ChengTao
 *
 */
public class StringUtils {

	/**
	 * ��ӡ�ַ���������
	 * @param s �ַ���
	 */
	public static void print(String s) {
		if (isStrNotNull(s)) {
			System.out.print(s);
		}
	}

	/**
	 * ��ӡ�ַ�������
	 * @param s �ַ���
	 */
	public static void println(String s) {
		if (isStrNotNull(s)) {
			System.out.println(s);
		}
	}

	/**
	 * �ж��ַ����Ƿ�Ϊnull
	 * 
	 * @param s
	 * @return
	 */
	private static boolean isStrNotNull(String s) {
		return !s.isEmpty() && s != null;
	}

}
