package com.dsdemo.ds;

import java.util.ArrayList;
import java.util.Collection;

public class MyQueue<T> {
	public static final int DEFAULT_CAPACITY = 100;
	private T[] data;
	private int frontPointer = 0;
	private int backPointer = 0;
	
	
	public MyQueue() {
		this(DEFAULT_CAPACITY);
	}
	
	@SuppressWarnings("unchecked")
	public MyQueue(int capacity) {
		data = (T[]) new Object[capacity];
	}
	
	public void enQueue(T element) {
		data[backPointer++] = element;
	}
	
	public T deQueue() {
		T deQueuedElement = data[frontPointer];
		data[frontPointer++] = null; //trigger garbage collection
		return deQueuedElement;
	}
	
	public boolean contains(T element) {
		for(int i=frontPointer; i<backPointer; i++) {
			if(element.equals(data[i])) {
				return true;
			}
		}
		return false;
	}
	
	public Collection<T> peekAll() {
		//Arrays.copyOfRange(data, frontPointer, backPointer) would have been a more elegant solution but it also causes type erasure. 
		//Rather than expecting the type while constructing, we would rather add couple more lines of code 
		Collection<T> allItems = new ArrayList<>();
		for(int i=frontPointer; i<backPointer; i++ ) {
			allItems.add(data[i]);
		}
		return allItems;
	}
	
	public int size() {
		return backPointer - frontPointer;
	}
	
	public int capacity() {
		return data.length;
	}
}
