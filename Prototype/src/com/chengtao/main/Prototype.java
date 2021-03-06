package com.chengtao.main;

import java.util.Scanner;

import com.chengtao.entity.Polynomial;

public class Prototype {
	// ------------------常量
	private final static String START_STR = "请输入2个多项式的加法、减法和乘法。输入finish结束：";
	private final static String EXCUTE_STR = "两个多项式的运算结果是：";
	private final static String ERROR_STR = "输入有误，请重新输入";
	private final static String EXCEPTION_STR = "输入的多项式算式有误";
	private final static String FINISH_STR = "finish"; // 结束标志
	/**
	 * 正则表达式，找出多项式算式的计算方法（如 + 、 - 、*）
	 */
	private final static String REGEX_COMPUTE_SIGN = "\\" + "[(\\" + "+{0,1}\\" + "(\\" + "-{0,1}[0-9]+\\"
			+ ".{0,1}[0-9]{0,1}\\" + "-{0,1}\\" + "+{0,1}[0-9]+\\" + ".{0,1}[0-9]{0,1}i\\" + ")[a-z]\\"
			+ "^[0-9]+){1,}\\" + "]";
	/**
	 * 正则表达式，分出多项式算式中的多项式<br/>
	 * 但分开后的多项式字符串包含（[、]），需在进行处理
	 */
	private final static String REGEX_POLYNOMIAL = "\\" + "]\\" + "+?\\" + "-?\\" + "*?\\" + "[";
	private final static String ADD = "+";// 加法
	private final static String SUB = "-";// 减法
	private final static String MUTIL = "*";// 乘法
	private final static Scanner input = new Scanner(System.in);

	public static void main(String[] arg) {
		while (true) {
			Polynomial p1 = null;// 第一个多项式
			Polynomial p2 = null;// 第二个多项式
			Polynomial p3 = null;// 通过计算所得的多项式
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
