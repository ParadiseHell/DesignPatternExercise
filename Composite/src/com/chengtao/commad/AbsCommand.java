package com.chengtao.commad;

import com.chengtao.entity.ChinaNode;
import com.chengtao.iterator.CompositeIterator;
import com.chengtao.utils.FileUtils;
import com.chengtao.utils.NodeUtils;

/**
 * the father class for command group class<br>
 * every command class must implement the method execute
 * @author ChengTao
 *
 */
public abstract class AbsCommand {
	public abstract void execute(String arg);

	protected ChinaNode getSearchNode(String arg) {
		CompositeIterator iterator = NodeUtils
				.getNodeIterator(NodeUtils.getRootChinaNode(FileUtils.readTreeInfoFromFile()));
		ChinaNode node = null;
		while (iterator.hasNext()) {
			ChinaNode chinaNode = iterator.next();
			if (chinaNode.getSelfName().equals(arg)) {
				node = chinaNode;
			}
		}
		return node;
	}

	protected boolean NodeNotNull(ChinaNode node) {
		if (node == null) {
			System.out.println("����Ľڵ����ƴ���");
			return false;
		}
		return true;
	}
}