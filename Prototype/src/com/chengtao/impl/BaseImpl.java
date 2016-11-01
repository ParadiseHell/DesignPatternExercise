package com.chengtao.impl;

public interface BaseImpl extends Cloneable{
	/**
	 * 计算方法
	 * @author ChengTao
	 *
	 */
	enum CalculateType{
		/**
		 * 加法
		 */
		ADD,
		/**
		 * 减法
		 */
		SUB,
		/**
		 * 乘法
		 */
		MULTI,
		/**
		 * 相反数
		 */
		NEG,
		/**
		 * 克隆
		 */
		COPY,
		/**
		 * 整理字符串
		 */
		TRIM
	}
	/**
	 * 计算
	 * @param obj T类型的变量
	 * @param type 运算法则
	 * @return
	 */
	<T extends Object> T calculate(Object obj,CalculateType type);
	
}
