package com.chengtao.impl;

public interface BaseImpl extends Cloneable{
	/**
	 * ���㷽��
	 * @author ChengTao
	 *
	 */
	enum CalculateType{
		/**
		 * �ӷ�
		 */
		ADD,
		/**
		 * ����
		 */
		SUB,
		/**
		 * �˷�
		 */
		MULTI,
		/**
		 * �෴��
		 */
		NEG,
		/**
		 * ��¡
		 */
		COPY,
		/**
		 * �����ַ���
		 */
		TRIM
	}
	/**
	 * ����
	 * @param obj T���͵ı���
	 * @param type ���㷨��
	 * @return
	 */
	<T extends Object> T calculate(Object obj,CalculateType type);
	
}