package com.dsdemo.sort;

import java.util.Arrays;

public class BubbleSort<T extends Comparable<T>> {

	public T[] sort(T[] inputData) {
		T[] data = copyOf(inputData);
		
		boolean swapped;
		do {
			swapped = false;
			for(int i=0;i<data.length - 1;i++) {
				int compareResult = data[i].compareTo(data[i+1]);
				if(compareResult > 0) {
					swap(data, i, i+1);
					swapped=true;
				} 
			}
		} while(swapped == true);
		
		return data;
	}

	private void swap(T[] data, int firstIndex, int nextIndex) {
		T temp = data[firstIndex];
		data[firstIndex] = data[nextIndex];
		data[nextIndex] = temp;
	}

	private T[] copyOf(T[] inputData) {
		return Arrays.copyOf(inputData, inputData.length);
	}
}
