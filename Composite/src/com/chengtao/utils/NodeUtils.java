package com.chengtao.utils;

import java.util.ArrayList;

import com.chengtao.entity.ChinaNode;
import com.chengtao.iterator.CompositeIterator;

/**
 * the util to create node tree
 * @author ChengTao
 *
 */
public class NodeUtils {
	//fot get only one ChinaNode instance
	private static ChinaNode root = null;
	
	/**
	 * get the root node the tree
	 * @param chinaNodes the list of the nodes
	 * @return
	 */
	public static ChinaNode getRootChinaNode(ArrayList<ChinaNode> chinaNodes) {
		if (root == null) {
			synchronized (NodeUtils.class) {
				if (root == null) {
					for (ChinaNode chinaNodeParent : chinaNodes) {
						if (chinaNodeParent.getParentName().equals("null")) {
							root = chinaNodeParent;
							chinaNodeParent.setParent(null);
						}
						for (ChinaNode chinaNodeChild : chinaNodes) {
							if (chinaNodeChild.getParentName().equals(chinaNodeParent.getSelfName())) {
								chinaNodeParent.addChild(chinaNodeChild);
								chinaNodeChild.setParent(chinaNodeParent);
							}
						}
					}
					//init node's layer
					CompositeIterator iterator = getNodeIterator(root);
					while (iterator.hasNext()) {
						ChinaNode child = iterator.next();
						int layer = 0;
						ChinaNode pNode = child;
						while ((pNode = pNode.getParent()) != null) {
							layer++;
						}
						child.setLayer(layer);
					}
				}
			}
		}
		return root;
	}
	
	/**
	 * get the composite iterator 
	 * @param the node of the tree
	 * @return
	 */
	public static CompositeIterator getNodeIterator(ChinaNode chinaNode) {
		ArrayList<ChinaNode> nodes = new ArrayList<>();
		nodes.add(chinaNode);
		return new CompositeIterator(nodes.iterator());
	} 
}