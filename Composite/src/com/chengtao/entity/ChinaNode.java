package com.chengtao.entity;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * the entity to define the tree node
 * @author ChengTao
 *
 */
public class ChinaNode {
	/**
	 * the name of itself
	 */
	private String selfName;
	/**
	 * the name of its parent
	 */
	private String parentName;
	/**
	 * its parent node
	 */
	private ChinaNode parent;
	/**
	 * the layer of the node
	 */
	private int layer = -1;
	/**
	 * its children nodes list
	 */
	ArrayList<ChinaNode> children = new ArrayList<>();
	
	public ChinaNode() {
		
	}

	public ChinaNode(String selfName, String parentName) {
		this.selfName = selfName;
		this.parentName = parentName;
	}
	
	public String getSelfName() {
		return selfName;
	}

	public String getParentName() {
		return parentName;
	}

	public ChinaNode getParent() {
		return parent;
	}

	public void setParent(ChinaNode parent) {
		this.parent = parent;
	}
	
	public int getLayer() {
		return layer;
	}

	public void setLayer(int layer) {
		this.layer = layer;
	}

	public void addChild(ChinaNode node) {
		children.add(node);
	}
	
	public Iterator<ChinaNode> getIterator(){
		return children.iterator();
	}

	public ArrayList<ChinaNode> getChildren() {
		return children;
	}
	
}
