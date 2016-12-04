package com.chengtao.commad;

import com.chengtao.entity.ChinaNode;
import com.chengtao.iterator.CompositeIterator;
import com.chengtao.utils.NodeUtils;

/**
 * the command class to get the tree which root node is current node
 * @author ChengTao
 *
 */
public class SubtreeCommand extends AbsCommand{

	@Override
	public void execute(String arg) {
		ChinaNode node = getSearchNode(arg);
		if (NodeNotNull(node)) {
			int cureentLayer = node.getLayer();
			CompositeIterator iterator = NodeUtils.getNodeIterator(node);
			boolean isFirst = true;
			while (iterator.hasNext()) {
				ChinaNode chinaNode = iterator.next();
				if (chinaNode.getLayer() != cureentLayer) {
					System.out.println();
					cureentLayer = chinaNode.getLayer();
					isFirst = true;
				}
				if (chinaNode.getLayer() == cureentLayer) {
					if (!isFirst) {
						System.out.print(", ");
					}
					isFirst = false;
				}
				System.out.print(chinaNode.getSelfName());
			}
			System.out.println();
		}
	}

}