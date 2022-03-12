package com.dsdemo.ds;

import lombok.AllArgsConstructor;
import lombok.Data;

public class MyHashTable <K, V> {
	private Entry<K,V>[] data;
	private int size;
	private int capacity;
	public static final int DEFAULT_CAPACITY = 1000;
	
	@SuppressWarnings("unchecked")
	public MyHashTable(int capacity) {
		this.data = new Entry[capacity];
		this.capacity = capacity;
	}
	
	public MyHashTable() {
		this(DEFAULT_CAPACITY);
	}
	
	public void put(K key, V value) {
		int slot = calcHash(key);
		
		data[slot] = new Entry<>(key, value);
		size++;
	}
	
	public V get(K key) {
		int slot = calcHash(key);
		
		if(data[slot] != null) {
			return data[slot].getValue();
		}
		
		return null;
	}
	
	public void remove(K key) {
		int slot = calcHash(key);
		if(data[slot] != null) {
			data[slot] = null;
			size--;
			
			slot = (slot+1) % capacity;
			while(data[slot] != null ) {
				Entry<K,V> entry = data[slot];
				data[slot] = null;
				
				put(entry.getKey(), entry.getValue());
				size--;// since the put() in previous line would have incremented the size; we are only moving items not adding
				
				slot = slot+1 % capacity;
			}
		}
	}
	
	private int calcHash(K key) {
		if(key == null) {
			throw new IllegalArgumentException("Key cannot be null");
		}
		
		int slot = key.hashCode() % capacity;
		while(data[slot] != null && !data[slot].getKey().equals(key)) {
			slot = (slot+1) % capacity;
		}
	
		return slot;
	}
	
	@Data
	@AllArgsConstructor
	private class Entry <K, V>{
		private K key;
		private V value;
	}
}
