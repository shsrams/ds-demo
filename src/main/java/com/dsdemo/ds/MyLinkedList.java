package com.dsdemo.ds;

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
