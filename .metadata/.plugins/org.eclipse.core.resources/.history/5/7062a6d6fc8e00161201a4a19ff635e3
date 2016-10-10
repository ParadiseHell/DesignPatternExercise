package com.ct.factory.main;

import com.ct.factory.impl.TVFactory;
import com.ct.factory.impl.TVProduct;
import com.ct.factory.utils.StringUtils;
import com.ct.factory.utils.XMLUtils;

/**
 * 工厂模式主体类
 * 
 * @author ChengTao
 *
 */
public class FactoryMain {
	private final static String TVName = "TCL";
	private static TVFactory factory;
	private static TVProduct product;

	public static void main(String[] args) {
		try {
			factory = XMLUtils.getClassFromTVName(TVName);
			product = factory.createTV();
			product.play();
		} catch (Exception e) {
			StringUtils.printlnString("There is no such a TV with this name : " + TVName);
		}
	}

}
