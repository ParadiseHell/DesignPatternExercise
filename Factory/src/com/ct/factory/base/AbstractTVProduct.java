package com.ct.factory.base;

import com.ct.factory.impl.TVProduct;

/**
 * 抽象产品类
 * @author ChengTao
 *
 */
public abstract class AbstractTVProduct implements TVProduct{
	//--------------变量
	//产品名字
	protected String TvName;
	
	public AbstractTVProduct(String name) {
		TvName = name;
	}
}
