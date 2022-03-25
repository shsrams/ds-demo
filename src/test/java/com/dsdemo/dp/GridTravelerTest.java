package com.dsdemo.dp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class GridTravelerTest {
	
	GridTraveler gridTraveler = new GridTraveler();

	@Test
	void test() {
		assertEquals(1, gridTraveler.findNumberOfPossibleWays(1, 1));//1
		assertEquals(2, gridTraveler.findNumberOfPossibleWays(2, 2));//2
		assertEquals(3, gridTraveler.findNumberOfPossibleWays(2, 3));//3
		assertEquals(2333606220l, gridTraveler.findNumberOfPossibleWays(18, 18));
	}

}
