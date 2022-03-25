package com.dsdemo.dp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class GridTravelerTest {
	
	GridTraveler gridTraveler = new GridTraveler();

	@Test
	void test() {
		assertEquals(1, gridTraveler.findStepCount(1, 1));//1
		assertEquals(2, gridTraveler.findStepCount(2, 2));//2
		assertEquals(3, gridTraveler.findStepCount(2, 3));//3
		assertEquals(2333606220l, gridTraveler.findStepCount(18, 18));
	}

}
