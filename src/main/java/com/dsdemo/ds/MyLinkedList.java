package com.dsdemo.ds;

import java.util.ArrayDeque;
import java.util.Deque;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class MyLinkedList<T> {
	private Node<T> first;
	private Node<T> last;
	private int size = 0;
	
	public void add(T data) {
		Node<T> newNode = new Node<>(data, null);
		if(first == null) {
			first = newNode;
			last = first;
		} else {
			last.setNext(newNode);//Whichever is the last node, connect the newNode to it
			last = newNode; //Now, make the newNode the last node;
		}
		size++;
	}
	
	public void remove() {
		if(first == null) {
			throw new IllegalStateException("Empty LinkedList, remove not allowed");
		}
		
		first = first.getNext();
		size--;
	}
	
	public void insertAt(T data, int position) {
		if(position > getSize()) {
			throw new IllegalArgumentException("the given position is out of bounds");
		}
		
		Node<T> desiredNode = fetchNodeAt(position);
		
		Node<T> newNode = new Node<>(data, desiredNode.getNext());
		desiredNode.setNext(newNode);
		size++;
	}

	public void removeAt(int position) {
		if(position > getSize()) {
			throw new IllegalArgumentException("the given position is out of bounds");
		}
		
		if(position == 1) {
			remove();
			return;
		}
		
		Node<T> nodeBeforeDesiredNode = fetchNodeAt(position-1);
		nodeBeforeDesiredNode.setNext(nodeBeforeDesiredNode.getNext().getNext());
		size--;
	}
	
	public void reverse() {
		Deque<Node<T>> stack = new ArrayDeque<>();
		Node<T> head = first;
		
		while(head != null) {
			stack.addFirst(head);
			head = head.getNext();
		}
		
		first = stack.removeFirst();
		
		head = first;
		while(!stack.isEmpty()) {
			Node<T> next = stack.removeFirst();
			head.setNext(next);
			head = next;
		}
		last = head;
		last.setNext(null);
	}
	
	public void recursiveReverse() {
		last = recursiveReverse(first);
	}
	
	public void recursiveReverseNNodes(int n) {
		if(n > size) {
			throw new IllegalArgumentException("Items count to reverse exceed list capacity");
		}
		
		if(n <= 1) {
			throw new IllegalArgumentException("# of nodes to reverse should be at least one");
		}
		
		if(n == size) {
			first = recursiveReverse(first);
		}
		
		Node<T> lostChain = null;
		Node<T> tailLink = recursiveReverseNNodes(first, n, 1, null);
		tailLink.setNext(lostChain);
		
		System.out.println("Hitching "+tailLink+" and "+lostChain);
	}
	
	
	private Node<T> recursiveReverse(Node<T> current) {
		if(current.getNext() == null) {
			first = current;
			System.out.println("Reached end where node = "+current);
			return current;
		}
		
		recursiveReverse(current.getNext());
		current.getNext().setNext(current);
		current.setNext(null);
		
		return current;
		
	}
	
	private Node<T> recursiveReverseNNodes(Node<T> current, int nodesToReverse, int startIndex, Node<T> lostChain) {
		System.out.println("current = "+current);
		if(startIndex == nodesToReverse) {
			System.out.println("Reached end now");
			lostChain = current.getNext();
			first=current;
			return current;
		}
		
		recursiveReverseNNodes(current.getNext(), nodesToReverse, startIndex+1, lostChain);
		
		System.out.println("Current = "+current+"; Next = "+current.getNext());
		current.getNext().setNext(current);
		current.setNext(null);
		
		return current;
	}

	public void print() {
		Node<T> head = first;
		while(head != null) {
			System.out.print(head.getData());
			head = head.getNext();
			if(head != null) {
				System.out.print("->");
			} else {
				System.out.println();
			}
		}
	}
	
	private Node<T> fetchNodeAt(int position) {
		Node<T> nextNode = first.getNext();
		for(int i=2;i<position;i++) {
			nextNode = nextNode.getNext();
		}
		return nextNode;
	}
	

	@Data
	@AllArgsConstructor
	public static class Node<T> {
		private T data;
		private Node<T> next;
	}
}
