package com.ct.factory;

import com.ct.factory.base.AbstractTVFctory;
import com.ct.factory.impl.TVProduct;
import com.ct.factory.product.TCLTV;
import com.ct.factory.utils.StringUtils;

/**
 * TCL����
 * 
 * @author ChengTao
 *
 */
public class TCLFactory extends AbstractTVFctory {

	// --------------����
	private final static String name = "TCL_Fctory";

	public TCLFactory() {
		super(name);
	}

	@Override
	public TVProduct createTV() {
		StringUtils.printlnString(factoryName + productStr);
		return new TCLTV();
	}

}
