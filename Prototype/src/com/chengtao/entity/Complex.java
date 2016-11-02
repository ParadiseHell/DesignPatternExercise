package com.chengtao.entity;

import java.io.Serializable;

import com.chengtao.impl.BaseImpl;

public class Complex implements BaseImpl, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2281397264960916488L;
	/**
	 * 实部
	 */
	private double real;
	/**
	 * 虚部
	 */
	private double imaginary;

	/**
	 * 
	 * @param real
	 *            实部
	 * @param imaginary
	 *            虚部
	 */
	public Complex(double real, double imaginary) {
		super();
		this.real = real;
		this.imaginary = imaginary;
	}

	public double getReal() {
		return real;
	}

	public void setReal(double real) {
		this.real = real;
	}

	public double getImaginary() {
		return imaginary;
	}

	public void setImaginary(double imaginary) {
		this.imaginary = imaginary;
	}

	/**
	 * 判断是否为0
	 * 
	 * @return
	 */
	boolean isZero() {
		return real == 0 && imaginary == 0;
	}

	/**
	 * 复数加法
	 * 
	 * @param c
	 *            一个复数
	 * @return
	 */
	public Complex add(Complex c) {
		return calculate(c, CalculateType.ADD);
	}

	/**
	 * 复数减法
	 * 
	 * @param c
	 *            一个复数
	 * @return
	 */
	public Complex subtract(Complex c) {
		return calculate(c, CalculateType.SUB);
	}

	/**
	 * 复数乘法
	 * 
	 * @param c
	 *            一个复数
	 * @return
	 */
	public Complex multiply(Complex c) {
		return calculate(c, CalculateType.MULTI);
	}


	/**
	 * 获取复数的相反数
	 * 
	 * @return
	 */
	public Complex negative() {
		return calculate(this, CalculateType.NEG);
	}
	
	/**
	 * 拷贝方法
	 */
	@Override
	public Complex clone() {
		return calculate(this, CalculateType.COPY);
	}
	
	/**
	 * 将复数字符串转化成复数对象
	 * @param strComplex
	 * @return
	 */
	public static Complex parseComplex(String strComplex){
		Complex complex = null;
		String complexStrs = strComplex.replace(" ", "");
		if (complexStrs.indexOf('i') != -1) {// 有虚数部分
			if (complexStrs.indexOf("+") != -1) {
				try {
					complex = new Complex(Double.parseDouble(complexStrs.substring(0, complexStrs.indexOf("+"))),
							Double.parseDouble(
									complexStrs.substring(complexStrs.indexOf("+") + 1, complexStrs.length() - 1)));
				} catch (Exception e) {
					System.out.println("复数字符串有问题");
				}
			} else if (complexStrs.lastIndexOf("-") != -1) {
				try {
					complex = new Complex(Double.parseDouble(complexStrs.substring(0, complexStrs.lastIndexOf("-"))),

							(-1 * Double.parseDouble(complexStrs.substring(complexStrs.lastIndexOf("-") + 1,
									complexStrs.length() - 1))));
				} catch (Exception e) {
					System.out.println("复数字符串有问题");
				}
			}
		} else {// 无虚数部分
			try {
				complex = new Complex(Double.parseDouble(complexStrs), 0);
			} catch (Exception e) {
				System.out.println("复数字符串有问题");
			}
		}
		return complex;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> T calculate(Object obj, CalculateType type) throws NullPointerException {
		Complex complex = null;
		if (obj instanceof Complex) {
			Complex c = (Complex) obj;
			switch (type) {
			case ADD:
				complex = new Complex(this.real + c.real, this.imaginary + c.imaginary);
				break;
			case SUB:
				complex = new Complex(this.real - c.real, this.imaginary - c.imaginary);
				break;
			case MULTI:
				complex = new Complex(((this.real * c.real) - (this.imaginary * c.imaginary)),
						((this.imaginary * c.real) + (this.real * c.imaginary)));
				break;
			case NEG:
				complex = new Complex((-1 * this.real), (-1 * this.imaginary));
				break;
			case COPY:
				complex = new Complex(this.real, this.imaginary);
				break;
			default:
				break;
			}
		}
		return (T) complex;
	}
	
	@Override
	public String toString() {
		String complexStr = "";
		complexStr += this.getReal();
		if (this.imaginary >= 0) {
			complexStr += " + " + this.getImaginary() + "i";
		} else if (this.imaginary < 0) {
			complexStr += " - " + (-1 * this.getImaginary()) + "i";
		}
		return complexStr;
	}

	/**
	 * 显示复数
	 */
	public void display() {
		System.out.println(this.toString());
	}
}
