package com.chengtao.commad;

import com.chengtao.entity.ChinaNode;

/**
 * the command class to get the node's layer of the tree
 * @author ChengTao
 *
 */
public class LayerCommand extends AbsCommand{

	@Override
	public void execute(String arg) {
		ChinaNode node = getSearchNode(arg);
		if (NodeNotNull(node)) {
			System.out.println("�ڵ� "+arg+" �������ĵ� "+node.getLayer()+" ��.");
		}
	}
}