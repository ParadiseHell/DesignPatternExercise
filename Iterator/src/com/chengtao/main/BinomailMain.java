package com.chengtao.main;

import java.util.Scanner;

import com.chengtao.entity.Binomial;

public class BinomailMain {
	// ------------����
	private static final String tipStr = "���������ֲ����������������һ��m��" + "�������t���ʾterminate������";

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		String m;
		Scanner in = new Scanner(System.in);
		while (true) {
			System.out.println(tipStr);
			m = in.next();
			if (m.equals("t")) {
				break;
			}
			try {
				Binomial binomial = new Binomial(Integer.parseInt(m));
				float result = 1;
				while (binomial.hasNext()) {
					result *= binomial.next();
				}
				System.out.println("C( " + (Integer.parseInt(m) * 2) + " , " + Integer.parseInt(m) + " ) * 0.25^"
						+ Integer.parseInt(m) + "��ֵ�ǣ�" + result);
			} catch (Exception e) {
				System.out.println("��������");
			}
		}
	}

}
