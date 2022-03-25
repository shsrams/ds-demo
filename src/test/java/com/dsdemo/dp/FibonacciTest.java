package com.dsdemo.dp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FibonacciTest {

	Fibonacci fibonacci = new Fibonacci();
	@Test
	void testFib() {
		//1,1,2,3,5,8,13
		assertEquals(3, fibonacci.fib(4));
		assertEquals(13, fibonacci.fib(7));
		assertEquals(12586269025l, fibonacci.fib(50));
	}

}
