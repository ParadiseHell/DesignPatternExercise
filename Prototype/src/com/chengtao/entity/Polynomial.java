package com.chengtao.entity;

import java.util.ArrayList;

import com.chengtao.impl.PolymainlImpl;

/**
 * 多项式类
 * 
 * @author ChengTao
 *
 */
public class Polynomial implements Cloneable,PolymainlImpl{
	/**
	 * 多项式变量
	 */
	private char arg;
	/**
	 * 复数数组，用于记录多项式每一项
	 */
	private Complex[] complexs;
	/**
	 * 用于显示参数不同的多项式
	 */
	private ArrayList<Polynomial> differPolynomial;
	/**
	 * 当多项式变量不一样时，则新的多项式为 0
	 */
	private final static char DEFAULT_DIFFER_POLYNOMIAL_ARG = '0';
	/**
	 * 默认的当多项式变量不相等时的复数数组
	 */
	private final static Complex[] DEFAULT_NULL_COMPLEX = {};

	/**
	 * 
	 * @param arg
	 *            变量
	 * @param complexs
	 *            多个复数
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
	 * 拷贝方法
	 */
	@Override
	public Polynomial clone() {
		return new Polynomial(this.arg, this.complexs);
	}

	/**
	 * 多项式加法
	 * 
	 * @param p
	 */
	public Polynomial add(Polynomial p) {
		return calculatePolymail(p, CalculateType.ADD);
	}

	/**
	 * 多项式减法
	 * 
	 * @param p
	 */
	public Polynomial sub(Polynomial p) {
		return calculatePolymail(p, CalculateType.SUB);
	}

	/**
	 * 
	 * @param p
	 *            多项式
	 * @param type
	 *            运算方法
	 * @return 新的多项式
	 */
	@Override
	public Polynomial calculatePolymail(Polynomial p, CalculateType calculateType) {
		Polynomial newP = null;
		// 判断变量是否相同
		if (this.arg != p.arg) {// 为不同类型的多项式
			newP = calculatePolynomialWithDifferArg(p, calculateType);
		} else {// 为相同类型的多项式
			newP = calculatePolynomialWithSameArg(p, calculateType);
		}
		return newP;
	}

	/**
	 * 计算具有不同变量的多项式
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
	 * 合并多项式没有变量的部分
	 * 
	 * @param p1
	 *            前一个操作多项式
	 * @param p2
	 *            后一个操作多项式
	 * @param calculateType
	 *            计算方法
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
	 * 计算具有相同变量的多项式
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
		// 判断是否添加剩余
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
		// 此处不能使用.toArray方法，父类不能进行上转型变成子类
		Complex[] cs = new Complex[cList.size()];
		for (int j = 0; j < cs.length; j++) {
			cs[j] = cList.get(j);
		}
		newP.setComplexs(cs);
		return newP;
	}

	/**
	 * 设置复数数组
	 * 
	 * @param complexs
	 */
	private void setComplexs(Complex[] complexs) {
		this.complexs = complexs;
	}

	/**
	 * 显示多项式
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
	 * 获取多项式的相反数
	 * 
	 * @param arg
	 *            变量
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
