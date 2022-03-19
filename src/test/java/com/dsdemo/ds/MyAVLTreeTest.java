package com.dsdemo.ds;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MyAVLTreeTest {
	
	private MyAVLTree<Integer> avlTree;
	
	@BeforeEach
	void setUp() {
		avlTree = new MyAVLTree<>();
	}

	@Test
	void testInsert() {
		assertTrue(avlTree.insert(1));
		assertTrue(avlTree.insert(9));
		assertTrue(avlTree.insert(8));
		assertTrue(avlTree.insert(6));
		assertTrue(avlTree.insert(5));
		
		assertFalse(avlTree.insert(6));
		assertEquals(5, avlTree.getSize());
		
		avlTree.printTree();
	}

}
