package com.chengtao.entity;

import com.chengtao.impl.BinomialIterator;

/**
 * ����ʽ�ֲ�������<br/>
 * ֻ��Ը���Ϊ1/2���ҳɹ�����Ϊ�ܴ�����һ��
 * 
 * @author ChengTao
 *
 */
public class Binomial implements BinomialIterator {
	private int fontPos = 1;
	private int backPos = -1;
	private int successNum = 0;
	private float testResult =  1;
	private float currentResult = 1;

	public Binomial(int successNum) {
		this.successNum = successNum;
		backPos = successNum;
	}

	@Override
	public boolean hasNext() {
		return (fontPos-1) != backPos;
	}

	@Override
	public float next() {
		float result = 1;
		testResult *= (float)(successNum + fontPos) / ((float)fontPos * 4);
		if (testResult > 1) {
			currentResult *= (float)(successNum + backPos) / ((float)backPos * 4);
			testResult = currentResult;
			result = (float)(successNum + backPos) / ((float)backPos * 4);
			backPos--;
		}else {
			currentResult = testResult;
			result = (float)(successNum + fontPos) / ((float)fontPos * 4);
			fontPos++;
		}
		return result;
	}
}