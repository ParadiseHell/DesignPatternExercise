package com.chengtao.main;

import java.util.Scanner;

import com.chengtao.entity.Polynomial;

public class Prototype {
	// ------------------����
	private final static String START_STR = "������2������ʽ�ļӷ��������ͳ˷�������finish������";
	private final static String EXCUTE_STR = "��������ʽ���������ǣ�";
	private final static String ERROR_STR = "������������������";
	private final static String EXCEPTION_STR = "����Ķ���ʽ��ʽ����";
	private final static String FINISH_STR = "finish"; // ������־
	/**
	 * �������ʽ���ҳ�����ʽ��ʽ�ļ��㷽������ + �� - ��*��
	 */
	private final static String REGEX_COMPUTE_SIGN = "\\" + "[(\\" + "+{0,1}\\" + "(\\" + "-{0,1}[0-9]+\\"
			+ ".{0,1}[0-9]{0,1}\\" + "-{0,1}\\" + "+{0,1}[0-9]+\\" + ".{0,1}[0-9]{0,1}i\\" + ")[a-z]\\"
			+ "^[0-9]+){1,}\\" + "]";
	/**
	 * �������ʽ���ֳ�����ʽ��ʽ�еĶ���ʽ<br/>
	 * ���ֿ���Ķ���ʽ�ַ���������[��]�������ڽ��д���
	 */
	private final static String REGEX_POLYNOMIAL = "\\" + "]\\" + "+?\\" + "-?\\" + "*?\\" + "[";
	private final static String ADD = "+";// �ӷ�
	private final static String SUB = "-";// ����
	private final static String MUTIL = "*";// �˷�
	private final static Scanner input = new Scanner(System.in);

	public static void main(String[] arg) {
		while (true) {
			Polynomial p1 = null;// ��һ������ʽ
			Polynomial p2 = null;// �ڶ�������ʽ
			Polynomial p3 = null;// ͨ���������õĶ���ʽ
			System.out.println(START_STR);
			String calclateStr = input.next();
			if (calclateStr != null && !calclateStr.equals("")) {
				if (calclateStr.equals(FINISH_STR)) {
					break;
				} else {
					try {
						String calculateType = calclateStr.replace(" ", "").split(REGEX_COMPUTE_SIGN)[1];
						String polynomialStrs[] = calclateStr.replace(" ", "").split(REGEX_POLYNOMIAL);
						p1 = Polynomial.parsePolynomial(polynomialStrs[0].replace("[", ""));
						p2 = Polynomial.parsePolynomial(polynomialStrs[1].replace("]", ""));
						if (calculateType.equals(ADD)) {
							p3 = p1.add(p2);
						} else if (calculateType.equals(SUB)) {
							p3 = p1.subtract(p2);
						} else if (calculateType.equals(MUTIL)) {
							p3 = p1.multiply(p2);
						}
						System.out.println(EXCUTE_STR);
						p3.display();
					} catch (Exception e) {
						System.out.println(EXCEPTION_STR);
					}
				}
			} else {
				System.out.println(ERROR_STR);
			}
		}
	}
}