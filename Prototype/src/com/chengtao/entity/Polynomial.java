package com.chengtao.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chengtao.impl.BaseImpl;

/**
 * ����ʽ��
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
	 * ����
	 */
	private char variable;
	/**
	 * ��ϵ�����飬��0���ݿ�ʼ�����
	 */
	private Complex[] complexs = null;
	/**
	 * ��¼��һ��Ϊ0�ĸ���λ��
	 */
	private int firstZeroPos = -1;

	/**
	 * �յĹ��캯��
	 */
	public Polynomial() {
		super();
	}

	/**
	 * �������Ķ���ʽ
	 * 
	 * @param variable
	 *            ����
	 * @param coeffs
	 *            ��������
	 */
	public Polynomial(char variable, Complex... complexs) {
		super();
		this.variable = variable;
		this.complexs = complexs;
	}

	/**
	 * ������ʽ�ַ���ת���ɶ���ʽ����
	 * 
	 * @param strPolynomial
	 * @return
	 */
	public static Polynomial parsePolynomial(String strPolynomial) {
		Polynomial polynomial = null;
		// ��ȡ�����ַ�������
		String complexStr[] = strPolynomial.replace("(", "").replace(" ", "")
				.split("\\" + ")[a-z]\\" + "^[0-9]*[\\" + "+]?");
		// ��ȡ����ϵ������
		String[] complexPos = strPolynomial.replace(" ", "")
				.split("(\\" + "+{0,1}\\" + "(\\" + "-?[0-9]*\\" + "+?\\" + "-?[0-9]*i\\" + ")[a-z]\\" + "^)+");
		// ��ȡ����ʽ��������ĳ���
		int complexLength = 0;
		for (int i = 0; i < complexPos.length; i++) {
			if (!complexPos[i].equals("")) {
				if (complexLength < Integer.parseInt(complexPos[i])) {
					complexLength = Integer.parseInt(complexPos[i]);
				}
			}
		}
		// �����������鲢��ֵ
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
	 * ����ʽ���¡
	 */
	public Polynomial clone() {
		return new Polynomial(this.variable, this.complexs.clone());
	}

	/**
	 * ����ʽǳ��¡
	 * 
	 * @return
	 */
	public Polynomial shallowCopy() {
		return new Polynomial(this.variable, this.complexs);
	}

	/**
	 * ����ʽ����ĳ������
	 * 
	 * @param pos
	 *            λ��
	 * @param val
	 *            ����
	 */
	public void setCoeff(int pos, Complex val) {
		this.complexs[pos + 1] = val;
	}

	/**
	 * ��ȡ����ʽ�෴��
	 * 
	 * @return
	 */
	public Polynomial negative() {
		return calculate(this, CalculateType.NEG);
	}

	/**
	 * ����ʽ�ӷ�
	 * 
	 * @param addend
	 * @return
	 */
	public Polynomial add(Polynomial addend) {
		return calculate(addend, CalculateType.ADD);
	}

	/**
	 * ����ʽ����
	 * 
	 * @param subtract
	 * @return
	 */
	public Polynomial subtract(Polynomial subtract) {
		return calculate(subtract, CalculateType.SUB);
	}

	/**
	 * ����ʽ�˷�
	 * 
	 * @param multiplier
	 * @return
	 */
	public Polynomial multiply(Polynomial multiplier) {
		return calculate(multiplier, CalculateType.MULTI);
	}

	/**
	 * ����ʽ�������Ѹߴ���ȥ����
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
				// ���㸴������ĸ�����������������
				int newComplexsLength = (this.complexs.length - 1) + afterPolynomail.complexs.length;
				Complex newComplexs[] = new Complex[newComplexsLength];
				// ��ʼ���µĸ�������
				for (int i = 0; i < newComplexsLength; i++) {
					newComplexs[i] = new Complex(0, 0);
				}
				// ������ϵ����ȵ�λ�ñ�����HasMap��
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
				// ����HasMap�����Ӧ�ĸ����������ڸ����������Ӧ��λ��
				for (Map.Entry<String, List<TwoComplexPosition>> entry : posMap.entrySet()) {
					String key = entry.getKey();
					List<TwoComplexPosition> list = entry.getValue();
					for (TwoComplexPosition twoComplexPosition : list) {
						newComplexs[Integer.parseInt(key)] = newComplexs[Integer.parseInt(key)].add(complexs[twoComplexPosition.posFirst]
								.multiply(afterPolynomail.complexs[twoComplexPosition.posSecond]));
					}
				}
				//�����µĶ���ʽ
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
	 * �򵥵ļ��㷽�������ڼ���ӷ��ͼ���
	 * 
	 * @param targetPolynomial
	 *            Ŀ�����ʽ
	 * @param type
	 *            ���㷽��
	 * @param afterPolynomail
	 *            �������Ķ���ʽ
	 */
	private Polynomial simpleCalculate(CalculateType type, Polynomial afterPolynomail) {
		Polynomial targetPolynomial = null;
		if (this.complexs.length >= afterPolynomail.complexs.length) {// ǰ�ߵĸ������鳤�ȴ�
			Complex newComplexs[] = new Complex[this.complexs.length];
			int i = 0;
			switch (type) {
			case ADD:
				// �����µĸ���
				for (; i < afterPolynomail.complexs.length; i++) {
					newComplexs[i] = complexs[i].add(afterPolynomail.complexs[i]);
				}
				// ����ʣ��
				for (; i < this.complexs.length; i++) {
					newComplexs[i] = complexs[i];
				}
				break;
			case SUB:
				// �����µĸ���
				for (; i < afterPolynomail.complexs.length; i++) {
					newComplexs[i] = complexs[i].subtract(afterPolynomail.complexs[i]);
				}
				// ����ʣ��
				for (; i < this.complexs.length; i++) {
					newComplexs[i] = complexs[i];
				}
				break;
			default:
				break;
			}
			targetPolynomial = new Polynomial(variable, newComplexs);
		} else if (this.complexs.length < afterPolynomail.complexs.length) {// ���߸������鳤�ȳ�
			Complex newComplexs[] = new Complex[afterPolynomail.complexs.length];
			int i = 0;
			switch (type) {
			case ADD:
				// �����µĸ���
				for (; i < complexs.length; i++) {
					newComplexs[i] = complexs[i].add(afterPolynomail.complexs[i]);
				}
				// ����ʣ��
				for (; i < afterPolynomail.complexs.length; i++) {
					newComplexs[i] = afterPolynomail.complexs[i];
				}
				break;
			case SUB:
				// �����µĸ���
				for (; i < complexs.length; i++) {
					newComplexs[i] = complexs[i].subtract(afterPolynomail.complexs[i]);
				}
				// ����ʣ��
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
	 * ��ʾ����ʽ
	 */
	public void display() {
		System.out.println(this.toString());
	}

	/**
	 * ��¼������������˻�ϵ����ȵ�ÿ��������λ��
	 * @author ChengTao
	 *
	 */
	private class TwoComplexPosition {
		/**
		 * ����������λ��
		 */
		int posFirst;
		/**
		 * ������������λ��
		 */
		int posSecond;

		public TwoComplexPosition(int posFirst, int posSecond) {
			super();
			this.posFirst = posFirst;
			this.posSecond = posSecond;
		}

	}
}