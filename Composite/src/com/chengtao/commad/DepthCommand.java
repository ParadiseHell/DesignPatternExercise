package com.chengtao.commad;

import com.chengtao.entity.ChinaNode;
import com.chengtao.iterator.CompositeIterator;
import com.chengtao.utils.NodeUtils;

/**
 * the command class for get the depth of the node
 * @author ChengTao
 *
 */
public class DepthCommand extends AbsCommand{
	
	@Override
	public void execute(String arg) {
		ChinaNode node = getSearchNode(arg);
		if (NodeNotNull(node)) {
			CompositeIterator iterator = NodeUtils.getNodeIterator(node);
			ChinaNode last = null;
			while (iterator.hasNext()) {
				last = iterator.next();
			}
			if (last != null) {
				System.out.println("以节点 "+ arg + " 为根的子数的深度是 : "+ (last.getLayer() - node.getLayer() + 1));
			}
		}
	}
}
