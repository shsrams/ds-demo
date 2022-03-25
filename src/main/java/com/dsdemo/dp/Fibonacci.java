package com.dsdemo.dp;

import java.util.HashMap;
import java.util.Map;

public class Fibonacci {
	Map<Integer, Long> memo = new HashMap<>();

	public Long fib(int n) {
		if(n <= 2) {
			return 1l;
		}
		
		return memoizedFib(n -2) + memoizedFib(n-1);
	}
	
	private Long memoizedFib(Integer n) {
		return memo.computeIfAbsent(n, k -> fib(n));
	}
}
