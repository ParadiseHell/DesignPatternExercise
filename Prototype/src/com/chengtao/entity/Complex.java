package com.chengtao.entity;

import java.io.Serializable;

import com.chengtao.impl.BaseImpl;

public class Complex implements BaseImpl, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2281397264960916488L;
	/**
	 * ʵ��
	 */
	private float real;
	/**
	 * �鲿
	 */
	private float imaginary;

	/**
	 * 
	 * @param real
	 *            ʵ��
	 * @param imaginary
	 *            �鲿
	 */
	public Complex(float real, float imaginary) {
		super();
		this.real = real;
		this.imaginary = imaginary;
	}

	public float getReal() {
		return real;
	}

	public void setReal(float real) {
		this.real = real;
	}

	public float getImaginary() {
		return imaginary;
	}

	public void setImaginary(float imaginary) {
		this.imaginary = imaginary;
	}

	/**
	 * �ж��Ƿ�Ϊ0
	 * 
	 * @return
	 */
	boolean isZero() {
		return real == 0 && imaginary == 0;
	}

	/**
	 * �����ӷ�
	 * 
	 * @param c
	 *            һ������
	 * @return
	 */
	public Complex add(Complex c) {
		return calculate(c, CalculateType.ADD);
	}

	/**
	 * ��������
	 * 
	 * @param c
	 *            һ������
	 * @return
	 */
	public Complex subtract(Complex c) {
		return calculate(c, CalculateType.SUB);
	}

	/**
	 * �����˷�
	 * 
	 * @param c
	 *            һ������
	 * @return
	 */
	public Complex multiply(Complex c) {
		return calculate(c, CalculateType.MULTI);
	}


	/**
	 * ��ȡ�������෴��
	 * 
	 * @return
	 */
	public Complex negative() {
		return calculate(this, CalculateType.NEG);
	}
	
	/**
	 * 
	 * @param strComplex
	 * @return
	 */
	public static Complex parseComplex(String strComplex){
		Complex complex = null;
		String complexStrs = strComplex.replace(" ", "");
		if (complexStrs.indexOf('i') != -1) {// ����������
			if (complexStrs.indexOf("+") != -1) {
				try {
					complex = new Complex(Float.parseFloat(complexStrs.substring(0, complexStrs.indexOf("+"))),
							Float.parseFloat(
									complexStrs.substring(complexStrs.indexOf("+") + 1, complexStrs.length() - 1)));
				} catch (Exception e) {
					System.out.println("�����ַ���������");
				}
			} else if (complexStrs.indexOf("-") != -1) {
				try {
					complex = new Complex(Float.parseFloat(complexStrs.substring(0, complexStrs.indexOf("-"))),

							(-1 * Float.parseFloat(complexStrs.substring(complexStrs.indexOf("-") + 1,
									complexStrs.length() - 1))));
				} catch (Exception e) {
					System.out.println("�����ַ���������");
				}
			}
		} else {// ����������
			try {
				complex = new Complex(Float.parseFloat(complexStrs), 0);
			} catch (Exception e) {
				System.out.println("�����ַ���������");
			} finally {
				complex = null;
			}
		}
		return complex;
	}
	
	/**
	 * ��������
	 */
	@Override
	public Complex clone() {
		return calculate(this, CalculateType.COPY);
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
	 * ��ʾ����
	 */
	public void display() {
		System.out.println(this.toString());
	}
}