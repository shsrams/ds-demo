package com.dsdemo.ds;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.dsdemo.ds.MyLinkedList.Node;

@DisplayName("Given a binary tree")
class MyBinaryTreeTest {

	@DisplayName("test")
	@Nested
	class AddandFetch {
		MyBinaryTree<String> presidents;
		
		@BeforeEach
		void initTestValues() {
			presidents = new MyBinaryTree<>();
			presidents.add("Kennedy");
			presidents.add("Adams");
			presidents.add("Madison");
			presidents.add("McKinley");
			presidents.add("Cleveland");
			presidents.add("Washington");
			presidents.add("Jackson");
			presidents.add("Obama");
			presidents.add("Trump");
		}
		
		@DisplayName("test Fetch")
		@Test
		void addAndFetch() {
		
			String president = presidents.fetch("Madison");
			assertNotNull(president);
			
			assertEquals("Madison", president);
			System.out.println("Sorted Order");
			presidents.inOrderTraverse(System.out::println);
			presidents.inOrderTraverseDesc(System.out::println);
		}
		
		@DisplayName("test delete root") 
		@Test
		void testDeleteRoot() {
			
			presidents.delete("Kennedy");
			
			assertNull(presidents.fetch("Kennedy"));
			assertEquals(8, presidents.size());
			
			MyBinaryTree<String>.Node<String> jacksonNode = presidents.fetchNode("Jackson");
			assertEquals("Madison", jacksonNode.getRight().getItem());
			assertEquals("Adams", jacksonNode.getLeft().getItem());
		}
		
		@DisplayName("test delete a one child parent") 
		@Test
		void testDeleteOneChildParent() {
			
			presidents.delete("Madison");
			
			assertNull(presidents.fetch("Madison"));
			assertEquals(8, presidents.size());
			
			MyBinaryTree<String>.Node<String> mcKinleyNode = presidents.fetchNode("McKinley");
			assertNull(mcKinleyNode.getLeft());
			assertEquals("Washington",mcKinleyNode.getRight().getItem());
		}
		
		@DisplayName("test printing tree")
		@Test
		void testTreePrint() {
			presidents.printTree();
		}
	}

}
