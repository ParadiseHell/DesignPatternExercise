package com.ct.factory;

import com.ct.factory.base.AbstractTVFctory;
import com.ct.factory.impl.TVProduct;
import com.ct.factory.product.HairTV;
import com.ct.factory.utils.StringUtils;

/**
 * ��������
 * 
 * @author ChengTao
 *
 */
public class HairFactory extends AbstractTVFctory {
	// -----------����
	private final static String name = "Hair_Fctory";

	@Override
	public void setFactoryName() {
		factoryName = name;
	}

	@Override
	public TVProduct createTV() {
		StringUtils.printlnString(factoryName + productStr);
		return new HairTV();
	}

}
