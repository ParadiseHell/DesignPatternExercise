package com.ct.factory.base;

import com.ct.factory.impl.TVProduct;

/**
 * �����Ʒ��
 * @author ChengTao
 *
 */
public abstract class AbstractTVProduct implements TVProduct{
	//--------------����
	//��Ʒ����
	protected String TvName;
	
	public AbstractTVProduct(String name) {
		TvName = name;
	}
}
