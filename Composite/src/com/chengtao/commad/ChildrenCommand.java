package com.chengtao.commad;

import java.util.ArrayList;

import com.chengtao.entity.ChinaNode;

/**
 * the command class to get node's children
 * @author ChengTao
 *
 */
public class ChildrenCommand extends AbsCommand{

	@Override
	public void execute(String arg) {
		ChinaNode node = getSearchNode(arg);
		if (NodeNotNull(node)) {
			ArrayList<ChinaNode> nodes = node.getChildren();
			for (int i = 0; i < nodes.size(); i++) {
				if (i != nodes.size() - 1) {
					System.out.print(nodes.get(i).getSelfName() + ", ");
				}else {
					System.out.println(nodes.get(i).getSelfName() + ".");
				}
			}
		}
	}

}