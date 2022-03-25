package com.dsdemo.ds;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import lombok.AllArgsConstructor;
import lombok.Data;
@DisplayName("Given a LinkedList")
class MyLinkedListTest {
	private MyLinkedList<Cargo> train = new MyLinkedList<>();
	
	@Nested
	@DisplayName("When it is empty")
	class EmptyLinkedList {
		@DisplayName("Then first and last are null")
		@Test
		void checkFirst() {
			assertNull(train.getFirst());
			assertNull(train.getLast());
		}
		
		@DisplayName("Then size is zero")
		@Test
		void checkSize() {
			assertEquals(0, train.getSize());
		}
		
		@DisplayName("Remove throws error") 
		@Test
		void checkRemove() {
			assertThrows(IllegalStateException.class, () -> train.remove());
		}
	}
	
	@Nested
	@DisplayName("When trying to add elements")
	class FilledLinkedList{
		private Cargo boxCar = new Cargo(1, "TN");
		private Cargo flatBed = new Cargo(2, "PA");
		private Cargo refrigerated = new Cargo(3, "CA");
		private Cargo container = new Cargo(4, "NY");
		
		@DisplayName("test after adding one element")
		@Test
		void addAnElement() {
			train.add(boxCar);
			assertNotNull(train.getFirst());
			assertNotNull(train.getLast());
			
			assertEquals(boxCar, train.getFirst().getData());
			assertEquals(boxCar, train.getLast().getData());
			assertEquals(1, train.getSize());
		}
		
		@DisplayName("Test after adding more elements")
		@Test
		void addMoreElements() {
			train.add(boxCar);
			train.add(flatBed);
			train.add(refrigerated);
			
			assertEquals(3, train.getSize());
			
			assertNotNull(train.getFirst());
			assertNotNull(train.getLast());
			
			assertEquals(boxCar, train.getFirst().getData());
			assertEquals(refrigerated, train.getLast().getData());
			
			train.print();
			
		}
		
		@DisplayName("Test remove after adding more elements")
		@Test
		void removeAfterAddingMoreElements() {
			train.add(boxCar);
			train.add(flatBed);
			train.add(refrigerated);
			
			assertEquals(3, train.getSize());
			
			train.remove();
			
			assertNotNull(train.getFirst());
			assertNotNull(train.getLast());
			
			assertEquals(flatBed, train.getFirst().getData());
			assertEquals(refrigerated, train.getLast().getData());
			
			train.remove();
			
			assertNotNull(train.getFirst());
			assertNotNull(train.getLast());
			
			assertEquals(refrigerated, train.getFirst().getData());
			assertEquals(refrigerated, train.getLast().getData());
			
		}
		
		@DisplayName("Test insertAt")
		@Test
		void testInsertAt() {
			train.add(boxCar);
			train.add(flatBed);
			train.add(refrigerated);
			
			assertEquals(3, train.getSize());
			
			train.insertAt(container, 2);
			assertEquals(4, train.getSize());
			
			assertNotNull(train.getFirst());
			assertNotNull(train.getLast());
			
			assertEquals(boxCar, train.getFirst().getData());
			assertEquals(refrigerated, train.getLast().getData());
			
			train.remove();
			
			assertNotNull(train.getFirst());
			assertNotNull(train.getLast());
			
			assertEquals(flatBed, train.getFirst().getData());
			assertEquals(refrigerated, train.getLast().getData());
			
			train.remove();
			
			assertNotNull(train.getFirst());
			assertNotNull(train.getLast());
			
			assertEquals(container, train.getFirst().getData());
			assertEquals(refrigerated, train.getLast().getData());
		}
		
		@DisplayName("Test removeAt first position")
		@Test
		void testRemoveAtFirstPosition() {
			train.add(boxCar);
			train.add(flatBed);
			train.add(refrigerated);
			
			assertEquals(3, train.getSize());
			
			train.removeAt(1);
			assertEquals(2, train.getSize());
			
			assertNotNull(train.getFirst());
			assertNotNull(train.getLast());
					
			assertEquals(flatBed, train.getFirst().getData());
			assertEquals(refrigerated, train.getLast().getData());
		}
		
		@DisplayName("Test removeAt anywhere else other than first")
		@Test
		void testRemoveAtNonFirstPosition() {
			train.add(boxCar);
			train.add(flatBed);
			train.add(refrigerated);
			
			assertEquals(3, train.getSize());
			
			train.removeAt(2);
			assertEquals(2, train.getSize());
			
			assertNotNull(train.getFirst());
			assertNotNull(train.getLast());
					
			assertEquals(boxCar, train.getFirst().getData());
			assertEquals(refrigerated, train.getLast().getData());
		}
		
		@DisplayName("Test reversing")
		@Test
		void testReverse() {
			train.add(boxCar);
			train.add(flatBed);
			train.add(refrigerated);
			
			assertEquals(3, train.getSize());
			train.print();
			
			train.reverse();
			System.out.println("Post reversal...");
			train.print();
					
			assertEquals(refrigerated, train.getFirst().getData());
			assertEquals(boxCar, train.getLast().getData());
		}
		
		@DisplayName("Test reversing recursively")
		@Test
		void testRecursiveReverse() {
			train.add(boxCar);
			train.add(flatBed);
			train.add(refrigerated);
			
			assertEquals(3, train.getSize());
			train.print();
			
			train.recursiveReverse();
			System.out.println("Post reversal...");
			train.print();
					
			assertEquals(refrigerated, train.getFirst().getData());
			assertEquals(boxCar, train.getLast().getData());
		}
		
		@DisplayName("Test reverse n nodes")
		@Test
		void testRecursiveReverseNNodes() {
			train.add(boxCar);
			train.add(flatBed);
			train.add(refrigerated);
			train.add(container);
			
			assertEquals(4, train.getSize());
			train.print();
			
			train.recursiveReverseNNodes(3);
			System.out.println("Post reversal...");
			train.print();
			assertEquals(refrigerated, train.getFirst().getData());
			assertEquals(container, train.getLast().getData());
			
		}
	}
	
	@Data
	@AllArgsConstructor
	private class Cargo {
		private int id;
		private String destination;
	}
}
