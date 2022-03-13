package com.dsdemo.sort;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
@DisplayName("With an array of Strings")
class SortTest {
	private BubbleSort<String> bubbleSort = new BubbleSort<>();
	private MergeSort<String> mergeSort = new MergeSort<>();

	private String[] inputData = {"Kennedy","Adams","Madison","McKinley","Cleveland","Washington","Jackson","Obama","Trump"};
	private String[] expectedData = {"Adams","Cleveland","Jackson","Kennedy","Madison","McKinley","Obama","Trump","Washington"};
	

	@DisplayName("sort using bubble sort")
	@Test
	void testBubbleSort() {
		String[] sortedData = bubbleSort.sort(inputData);

		for(int i = 0; i < sortedData.length; i++) {
			assertEquals(expectedData[i], sortedData[i]);
		}
	}
	
	@DisplayName("sort using merge sort") 
	@Test
	void testMergeSort() {
		String[] sortedData = mergeSort.sort(inputData);

		for(int i = 0; i < sortedData.length; i++) {
			assertEquals(expectedData[i], sortedData[i]);
		}
	}

}
