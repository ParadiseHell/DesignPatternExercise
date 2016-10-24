package com.chengtao.entity;

import java.util.ArrayList;

import com.chengtao.impl.PolymainlImpl;

/**
 * ����ʽ��
 * 
 * @author ChengTao
 *
 */
public class Polynomial implements Cloneable,PolymainlImpl{
	/**
	 * ����ʽ����
	 */
	private char arg;
	/**
	 * �������飬���ڼ�¼����ʽÿһ��
	 */
	private Complex[] complexs;
	/**
	 * ������ʾ������ͬ�Ķ���ʽ
	 */
	private ArrayList<Polynomial> differPolynomial;
	/**
	 * ������ʽ������һ��ʱ�����µĶ���ʽΪ 0
	 */
	private final static char DEFAULT_DIFFER_POLYNOMIAL_ARG = '0';
	/**
	 * Ĭ�ϵĵ�����ʽ���������ʱ�ĸ�������
	 */
	private final static Complex[] DEFAULT_NULL_COMPLEX = {};

	/**
	 * 
	 * @param arg
	 *            ����
	 * @param complexs
	 *            �������
	 */
	public Polynomial(char arg, Complex... complexs) {
		this.arg = arg;
		this.complexs = complexs;
	}

	@Override
	public String toString() {
		String polynomialStr = "";
		for (int i = 0; i < complexs.length; i++) {
			if (i != 0) {
				if (!complexs[i - 1].isZero()) {
					polynomialStr += " + ";
				}
			}
			if (!complexs[i].toString().equals("")) {
				polynomialStr += "( " + complexs[i].toString() + " )"
						+ (i > 0 ? (this.arg + (i > 1 ? "^" + i : "")) : "");
			}
		}
		if (polynomialStr.equals("")) {
			polynomialStr = "0";
		}
		return polynomialStr;
	}

	/**
	 * ��������
	 */
	@Override
	public Polynomial clone() {
		return new Polynomial(this.arg, this.complexs);
	}

	/**
	 * ����ʽ�ӷ�
	 * 
	 * @param p
	 */
	public Polynomial add(Polynomial p) {
		return calculatePolymail(p, CalculateType.ADD);
	}

	/**
	 * ����ʽ����
	 * 
	 * @param p
	 */
	public Polynomial sub(Polynomial p) {
		return calculatePolymail(p, CalculateType.SUB);
	}

	/**
	 * 
	 * @param p
	 *            ����ʽ
	 * @param type
	 *            ���㷽��
	 * @return �µĶ���ʽ
	 */
	@Override
	public Polynomial calculatePolymail(Polynomial p, CalculateType calculateType) {
		Polynomial newP = null;
		// �жϱ����Ƿ���ͬ
		if (this.arg != p.arg) {// Ϊ��ͬ���͵Ķ���ʽ
			newP = calculatePolynomialWithDifferArg(p, calculateType);
		} else {// Ϊ��ͬ���͵Ķ���ʽ
			newP = calculatePolynomialWithSameArg(p, calculateType);
		}
		return newP;
	}

	/**
	 * ������в�ͬ�����Ķ���ʽ
	 * 
	 * @param p
	 * @param calculateType
	 * @return
	 */
	private Polynomial calculatePolynomialWithDifferArg(Polynomial p, CalculateType calculateType) {
		Polynomial newP = new Polynomial(DEFAULT_DIFFER_POLYNOMIAL_ARG, DEFAULT_NULL_COMPLEX);
		newP.differPolynomial = new ArrayList<>();
		Polynomial polynomial1 = this.clone();
		Polynomial polynomial2 = p.clone();
		mergePolynomialFirstComplex(polynomial1, polynomial2, calculateType);
		newP.differPolynomial.add(polynomial1);
		if (calculateType == CalculateType.SUB) {
			newP.differPolynomial.add(polynomial2.getOppositePolynomial(p.arg));
		} else {
			newP.differPolynomial.add(polynomial2);
		}
		return newP;
	}

	/**
	 * �ϲ�����ʽû�б����Ĳ���
	 * 
	 * @param p1
	 *            ǰһ����������ʽ
	 * @param p2
	 *            ��һ����������ʽ
	 * @param calculateType
	 *            ���㷽��
	 */
	private void mergePolynomialFirstComplex(Polynomial p1, Polynomial p2, CalculateType calculateType) {
		switch (calculateType) {
		case ADD:
			p1.complexs[0] = p1.complexs[0].add(p2.complexs[0]);
			break;
		case SUB:
			p1.complexs[0] = p1.complexs[0].sub(p2.complexs[0]);
			break;
		default:
			break;
		}
		p2.complexs[0].setReal(0);
		p2.complexs[0].setImaginary(0);
	}

	/**
	 * ���������ͬ�����Ķ���ʽ
	 * 
	 * @param p
	 * @param calculateType
	 * @return
	 */
	private Polynomial calculatePolynomialWithSameArg(Polynomial p, CalculateType calculateType) {
		Polynomial newP = new Polynomial(this.arg, DEFAULT_NULL_COMPLEX);
		ArrayList<Complex> cList = new ArrayList<>();
		int oldComplexsLength = this.complexs.length;
		int newComplexsLength = p.complexs.length;
		int i = 0;
		for (; i < oldComplexsLength; i++) {
			if (i == newComplexsLength) {
				break;
			}
			Complex c = null;
			switch (calculateType) {
			case ADD:
				c = this.complexs[i].add(p.complexs[i]);
				break;
			case SUB:
				c = this.complexs[i].sub(p.complexs[i]);
				break;

			default:
				break;
			}

			if (c != null) {
				cList.add(c);
			}
		}
		// �ж��Ƿ����ʣ��
		if (oldComplexsLength > newComplexsLength) {
			while (i < oldComplexsLength) {
				cList.add(this.complexs[i]);
				i++;
			}
		} else if (oldComplexsLength < newComplexsLength) {
			while (i < newComplexsLength) {
				if (calculateType == CalculateType.SUB) {
					cList.add(p.complexs[i].getOppositeComplex());
				} else {
					cList.add(p.complexs[i]);
				}
				i++;
			}
		}
		// �˴�����ʹ��.toArray���������಻�ܽ�����ת�ͱ������
		Complex[] cs = new Complex[cList.size()];
		for (int j = 0; j < cs.length; j++) {
			cs[j] = cList.get(j);
		}
		newP.setComplexs(cs);
		return newP;
	}

	/**
	 * ���ø�������
	 * 
	 * @param complexs
	 */
	private void setComplexs(Complex[] complexs) {
		this.complexs = complexs;
	}

	/**
	 * ��ʾ����ʽ
	 */
	public void display() {
		if (this.differPolynomial != null) {
			String differStr = "";
			for (int i = 0; i < this.differPolynomial.size(); i++) {
				if (i != 0) {
					differStr += " + ";
				}
				differStr += this.differPolynomial.get(i).toString();
			}
			System.out.println(differStr);
		} else {
			System.out.println(this.toString());
		}
	}

	/**
	 * ��ȡ����ʽ���෴��
	 * 
	 * @param arg
	 *            ����
	 * @return
	 */
	private Polynomial getOppositePolynomial(char arg) {
		Complex[] c = new Complex[this.complexs.length];
		for (int i = 0; i < this.complexs.length; i++) {
			c[i] = this.complexs[i].getOppositeComplex();
		}
		return new Polynomial(arg, c);
	}
}
