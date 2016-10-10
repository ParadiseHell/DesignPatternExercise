package com.ct.factory.utils;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.ct.factory.impl.TVFactory;

/**
 * XML������
 * 
 * @author ChengTao
 *
 */
public class XMLUtils {
	// ----------------����
	private final static String FACTORY_PACKAGE_PATH = "com.ct.factory";
	private final static String CONFIG_PATH = "config.xml";
	private final static String WRAP_TAG_NAME = "item";
	/*private final static String TV_TAG_NAME = "TVName";
	private final static String TV_TAG_CLASS = "TVClass";*/
	// ------------------����
	private static DocumentBuilderFactory dbFactory = null;
	private static DocumentBuilder builder = null;

	/**
	 * ���ݵ��������ص��ӹ�����
	 * 
	 * @param tvName
	 * @return
	 * @throws ParserConfigurationException
	 */
	public static TVFactory getClassFromTVName(String tvName) throws Exception {
		TVFactory factory = null;
		initDocumentBuilderFactory();
		Document doc = builder.parse(new File(CONFIG_PATH));
		NodeList list = doc.getElementsByTagName(WRAP_TAG_NAME);
		for (int i = 0; i < list.getLength(); i++) {
			Node node = list.item(i);
			if (node.hasChildNodes()) {
				NodeList innerList = node.getChildNodes();
				StringUtils.printlnString(innerList.item(0).getTextContent());
				if (node.getNodeValue().equalsIgnoreCase(tvName)) {
					node = list.item(i).getLastChild();
					String className = node.getNodeValue();
					@SuppressWarnings("rawtypes")
					Class c = Class.forName(FACTORY_PACKAGE_PATH + className);
					factory = (TVFactory) c.newInstance();
				}
			}
		}
		return factory;
	}

	/**
	 * ��ʼ��DocumentBuilderFactory
	 * 
	 * @throws ParserConfigurationException
	 */
	private static void initDocumentBuilderFactory()
			throws ParserConfigurationException {
		if (dbFactory == null) {
			synchronized (XMLUtils.class) {
				if (dbFactory == null) {
					dbFactory = DocumentBuilderFactory.newInstance();
					builder = dbFactory.newDocumentBuilder();
				}
			}
		}
	}
}