package com.chengtao.iterator;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import com.chengtao.entity.ChinaNode;

/**
 * the Composite Iterator to search every node of the tree
 * @author ChengTao
 *
 */
public class CompositeIterator implements Iterator<ChinaNode>{
	private Queue<Iterator<ChinaNode>> queue = new LinkedList<>();
	
	public CompositeIterator(Iterator<ChinaNode> iterator) {
		queue.offer(iterator);
	}

	@Override
	public boolean hasNext() {
		if (queue.isEmpty()) {
			return false;
		}
		Iterator<ChinaNode> iterator = queue.peek();
		if (!iterator.hasNext()) {
			queue.poll();
			return hasNext();
		}else {
			return true;
		}
	}

	@Override
	public ChinaNode next() {
		ChinaNode node = queue.peek().next();
		if (node.getChildren().size() != 0) {
			queue.offer(node.getIterator());
		}
		return node;
	}
}