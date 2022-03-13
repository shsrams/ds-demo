package com.dsdemo.sort;

import java.util.Arrays;

public class MergeSort<T extends Comparable<T>> {
	public T[] sort(T[] inputData) {
		T[] data = copyOf(inputData);
		
		mergeSort(data);
		
		return data;
	}
	
	private void mergeSort(T[] data) {
		int inputSize = data.length;
		if(inputSize < 2) {
			return;
		}
		
		int midIndex = inputSize / 2;
		T[] leftHalf = Arrays.copyOfRange(data, 0, midIndex);
		T[] rightHalf = Arrays.copyOfRange(data, midIndex, inputSize);
		
		mergeSort(leftHalf);
		mergeSort(rightHalf);
		
		merge(data, leftHalf, rightHalf);
		
	}

	private void merge(T[] data, T[] leftHalf, T[] rightHalf) {
		int leftSize = leftHalf.length, rightSize = rightHalf.length;
		int i=0, j=0, k=0;
		while(i < leftSize && j < rightSize) {
			if(leftHalf[i].compareTo(rightHalf[j]) <= 0) {
				data[k] = leftHalf[i];
				i++;
			} else {
				data[k] = rightHalf[j];
				j++;
			}
			k++;
		}
		while(i < leftSize) {
			data[k] = leftHalf[i];
			i++;k++;
		}
		while(j < rightSize) {
			data[k] = rightHalf[j];
			j++;k++;
		}
		
	}

	private T[] copyOf(T[] inputData) {
		return Arrays.copyOf(inputData, inputData.length);
	}
}
