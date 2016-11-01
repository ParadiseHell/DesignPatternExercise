package com.chengtao.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chengtao.impl.BaseImpl;

/**
 * 多项式类
 * 
 * @author ChengTao
 *
 */
public class Polynomial implements BaseImpl, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5607289628151831201L;
	/**
	 * 变量
	 */
	private char variable;
	/**
	 * 复系数数组，从0次幂开始不间断
	 */
	private Complex[] complexs = null;
	/**
	 * 记录第一个为0的复数位置
	 */
	private int firstZeroPos = -1;

	/**
	 * 空的构造函数
	 */
	public Polynomial() {
		super();
	}

	/**
	 * 带参数的多项式
	 * 
	 * @param variable
	 *            变量
	 * @param coeffs
	 *            复数数组
	 */
	public Polynomial(char variable, Complex... complexs) {
		super();
		this.variable = variable;
		this.complexs = complexs;
	}

	/**
	 * 将多项式字符串转化成多项式对象
	 * 
	 * @param strPolynomial
	 * @return
	 */
	public static Polynomial parsePolynomial(String strPolynomial) {
		Polynomial polynomial = null;
		// 获取复数字符串数组
		String complexStr[] = strPolynomial.replace("(", "").replace(" ", "")
				.split("\\" + ")[a-z]\\" + "^[0-9]*[\\" + "+]?");
		// 获取变量系数数组
		String[] complexPos = strPolynomial.replace(" ", "")
				.split("(\\" + "+{0,1}\\" + "(\\" + "-?[0-9]*\\" + "+?\\" + "-?[0-9]*i\\" + ")[a-z]\\" + "^)+");
		// 获取多项式复数数组的长度
		int complexLength = 0;
		for (int i = 0; i < complexPos.length; i++) {
			if (!complexPos[i].equals("")) {
				if (complexLength < Integer.parseInt(complexPos[i])) {
					complexLength = Integer.parseInt(complexPos[i]);
				}
			}
		}
		// 创建复数数组并赋值
		Complex complexs[] = new Complex[++complexLength];
		int realComplexPos = 0;
		List<String> al = Arrays.asList(complexPos);
		for (int i = 0; i < complexLength; i++) {
			if (al.contains("" + i)) {
				complexs[i] = Complex.parseComplex(complexStr[realComplexPos]);
				realComplexPos++;
			} else {
				complexs[i] = new Complex(0, 0);
			}
		}
		polynomial = new Polynomial(strPolynomial.charAt(strPolynomial.replace(" ", "").indexOf("^") - 1), complexs);
		return polynomial;
	}

	@Override
	public String toString() {
		this.trim();
		String polynomialStr = "";
		for (int i = 0; i < complexs.length; i++) {
			if ((complexs[i].isZero() && i == firstZeroPos) || !complexs[i].isZero()) {
				if (i != 0) {
					polynomialStr += " + ";
				}
				polynomialStr += "( " + complexs[i].toString() + " )" + this.variable + "^" + i;
			}
		}
		return polynomialStr;
	}

	/**
	 * 多项式深克隆
	 */
	public Polynomial clone() {
		return new Polynomial(this.variable, this.complexs.clone());
	}

	/**
	 * 多项式浅克隆
	 * 
	 * @return
	 */
	public Polynomial shallowCopy() {
		return new Polynomial(this.variable, this.complexs);
	}

	/**
	 * 多项式设置某个参数
	 * 
	 * @param pos
	 *            位置
	 * @param val
	 *            复数
	 */
	public void setCoeff(int pos, Complex val) {
		this.complexs[pos + 1] = val;
	}

	/**
	 * 获取多项式相反数
	 * 
	 * @return
	 */
	public Polynomial negative() {
		return calculate(this, CalculateType.NEG);
	}

	/**
	 * 多项式加法
	 * 
	 * @param addend
	 * @return
	 */
	public Polynomial add(Polynomial addend) {
		return calculate(addend, CalculateType.ADD);
	}

	/**
	 * 多项式减法
	 * 
	 * @param subtract
	 * @return
	 */
	public Polynomial subtract(Polynomial subtract) {
		return calculate(subtract, CalculateType.SUB);
	}

	/**
	 * 多项式乘法
	 * 
	 * @param multiplier
	 * @return
	 */
	public Polynomial multiply(Polynomial multiplier) {
		return calculate(multiplier, CalculateType.MULTI);
	}

	/**
	 * 多项式整理，把高次幂去掉。
	 * 
	 * @return
	 */
	public Polynomial trim() {
		return calculate(this, CalculateType.TRIM);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T calculate(Object obj, CalculateType type) {
		Polynomial polynomail = null;
		if (obj instanceof Polynomial) {
			Polynomial afterPolynomail = (Polynomial) obj;
			switch (type) {
			case ADD:
				polynomail = simpleCalculate(CalculateType.ADD, afterPolynomail);
				break;
			case SUB:
				polynomail = simpleCalculate(CalculateType.SUB, afterPolynomail);
				break;
			case MULTI:
				// 计算复数数组的个数并创建复数数组
				int newComplexsLength = (this.complexs.length - 1) + afterPolynomail.complexs.length;
				Complex newComplexs[] = new Complex[newComplexsLength];
				// 初始化新的复数数组
				for (int i = 0; i < newComplexsLength; i++) {
					newComplexs[i] = new Complex(0, 0);
				}
				// 将所得系数相等的位置保存在HasMap中
				HashMap<String, List<TwoComplexPosition>> posMap = new HashMap<>();
				for (int i = 0; i < complexs.length; i++) {
					for (int j = 0; j < afterPolynomail.complexs.length; j++) {
						if (!posMap.containsKey((i + j) + "")) {
							List<TwoComplexPosition> list = new ArrayList<>();
							list.add(new TwoComplexPosition(i, j));
							posMap.put((i + j) + "", list);
						} else {
							posMap.get((i + j) + "").add(new TwoComplexPosition(i, j));
						}
					}
				}
				// 遍历HasMap求出响应的复数并保存在复数数组的响应的位置
				for (Map.Entry<String, List<TwoComplexPosition>> entry : posMap.entrySet()) {
					String key = entry.getKey();
					List<TwoComplexPosition> list = entry.getValue();
					for (TwoComplexPosition twoComplexPosition : list) {
						newComplexs[Integer.parseInt(key)] = newComplexs[Integer.parseInt(key)].add(complexs[twoComplexPosition.posFirst]
								.multiply(afterPolynomail.complexs[twoComplexPosition.posSecond]));
					}
				}
				//生成新的多项式
				polynomail = new Polynomial(this.variable, newComplexs);
				break;
			case TRIM:
				for (int i = 0; i < complexs.length; i++) {
					if (complexs[i].isZero()) {
						firstZeroPos = i;
						break;
					}
				}
				break;
			case NEG:
				for (Complex complex : complexs) {
					complex = complex.negative();
				}
				break;
			default:
				break;
			}
		}
		return (T) polynomail;
	}

	/**
	 * 简单的计算方法，用于计算加法和减法
	 * 
	 * @param targetPolynomial
	 *            目标多项式
	 * @param type
	 *            计算方法
	 * @param afterPolynomail
	 *            被操作的多项式
	 */
	private Polynomial simpleCalculate(CalculateType type, Polynomial afterPolynomail) {
		Polynomial targetPolynomial = null;
		if (this.complexs.length >= afterPolynomail.complexs.length) {// 前者的复数数组长度达
			Complex newComplexs[] = new Complex[this.complexs.length];
			int i = 0;
			switch (type) {
			case ADD:
				// 计算新的复数
				for (; i < afterPolynomail.complexs.length; i++) {
					newComplexs[i] = complexs[i].add(afterPolynomail.complexs[i]);
				}
				// 添加剩余
				for (; i < this.complexs.length; i++) {
					newComplexs[i] = complexs[i];
				}
				break;
			case SUB:
				// 计算新的复数
				for (; i < afterPolynomail.complexs.length; i++) {
					newComplexs[i] = complexs[i].subtract(afterPolynomail.complexs[i]);
				}
				// 添加剩余
				for (; i < this.complexs.length; i++) {
					newComplexs[i] = complexs[i];
				}
				break;
			default:
				break;
			}
			targetPolynomial = new Polynomial(variable, newComplexs);
		} else if (this.complexs.length < afterPolynomail.complexs.length) {// 后者复数数组长度长
			Complex newComplexs[] = new Complex[afterPolynomail.complexs.length];
			int i = 0;
			switch (type) {
			case ADD:
				// 计算新的复数
				for (; i < complexs.length; i++) {
					newComplexs[i] = complexs[i].add(afterPolynomail.complexs[i]);
				}
				// 添加剩余
				for (; i < afterPolynomail.complexs.length; i++) {
					newComplexs[i] = afterPolynomail.complexs[i];
				}
				break;
			case SUB:
				// 计算新的复数
				for (; i < complexs.length; i++) {
					newComplexs[i] = complexs[i].subtract(afterPolynomail.complexs[i]);
				}
				// 添加剩余
				for (; i < afterPolynomail.complexs.length; i++) {
					newComplexs[i] = afterPolynomail.complexs[i].negative();
				}
				break;
			default:
				break;
			}
			targetPolynomial = new Polynomial(variable, newComplexs);
		}
		return targetPolynomial;
	}

	/**
	 * 显示多项式
	 */
	public void display() {
		System.out.println(this.toString());
	}

	/**
	 * 记录两个复数数组乘积系数相等的每个复数的位置
	 * @author ChengTao
	 *
	 */
	private class TwoComplexPosition {
		/**
		 * 操作复数的位置
		 */
		int posFirst;
		/**
		 * 被操作复数的位置
		 */
		int posSecond;

		public TwoComplexPosition(int posFirst, int posSecond) {
			super();
			this.posFirst = posFirst;
			this.posSecond = posSecond;
		}

	}
}
