package com.chengtao.commad;

import com.chengtao.entity.ChinaNode;

/**
 * the command class for get the node's ancestors
 * @author ChengTao
 *
 */
public class AncestorsCommand extends AbsCommand{

	@Override
	public void execute(String arg) {
		ChinaNode node = getSearchNode(arg);
		if (NodeNotNull(node)) {
			ChinaNode pNode = node;
			while ((pNode = pNode.getParent()) != null) {
				if (pNode.getParent() != null) {
					System.out.print(pNode.getSelfName() + ", ");
				}else {
					System.out.println(pNode.getSelfName() + ".");
				}
			}
		}
	}

}
