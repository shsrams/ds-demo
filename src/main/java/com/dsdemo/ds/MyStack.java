package com.dsdemo.ds;

import java.util.Optional;
import java.util.stream.Stream;

import lombok.Data;

@Data
public class MyStack<T> {
	public static final int DEFAULT_CAPACITY = 100;
	private T[] data;
	private int stackPointer = 0;
	
	public MyStack() {
		this(DEFAULT_CAPACITY);
	}
	
	@SuppressWarnings("unchecked")
	public MyStack(int capacity) {
		data = (T[])new Object[capacity];
	}

	public void push(T element) {
		data[stackPointer++] = element;
	}
	
	public T pop() {
		if(stackPointer == 0) {
			throw new IllegalStateException("Empty stack");
		}
		return data[--stackPointer];
	}
	
	public T get(T element) {
		while(size() > 0) {
			T poppedElement = pop();
			if(element.equals(poppedElement)) {
				return poppedElement;
			}
		}
		return null;
	}
	
	public Optional<T> peek(T element) {
		return Stream.of(data)
			.filter(element::equals)
			.findFirst();
	}

	public int size() {
		return stackPointer;
	}
	
	public int capacity() {
		return data.length;
	}
}
