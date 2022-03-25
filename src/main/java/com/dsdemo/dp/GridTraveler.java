package com.dsdemo.dp;

import java.util.HashMap;
import java.util.Map;

public class GridTraveler {
	
	Map<String, Long> memoizedStepCount = new HashMap<>();

	/**
	 * calc # of steps needed to reach the bottom right namely 1,1
	 * @param m the starting row
	 * @param n the starting column
	 * @return
	 */
	public Long findNumberOfPossibleWays(int m, int n) {
		
		if(m == 1 && n == 1) return 1l;//base case. we have reached the destination
		if(m == 0 || n == 0) return 0l; //impossible case
		
		
		return getMemoizedStepCount(m-1, n) + getMemoizedStepCount(m, n-1);//(m-1, n) means going down and (n-1 ,m) means going right
	}
	
	public Long getMemoizedStepCount(int m, int n) {
		String key = m + "," + n;
		return memoizedStepCount.computeIfAbsent(key, s -> findNumberOfPossibleWays(m, n));
	}
	
}
