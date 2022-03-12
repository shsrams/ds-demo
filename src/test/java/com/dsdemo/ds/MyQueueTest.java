package com.dsdemo.ds;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import lombok.AllArgsConstructor;
import lombok.Data;

@DisplayName("Given a Queue")
class MyQueueTest {

	private MyQueue<Person> persons = new MyQueue<Person>();

	@DisplayName("add elements and see them")
	@Test
	void addAndPeek() {
		persons.enQueue(new Person("f1", "l1"));
		persons.enQueue(new Person("f2", "l2"));
		persons.enQueue(new Person("f3", "l3"));
		
		Collection<Person> peekedPersons = persons.peekAll();
		assertEquals(peekedPersons.size(), 3);
		peekedPersons.forEach(System.out::println);
	}
	
	@Nested
	@DisplayName("When adding elements")
	class TestAddingElements {
		@BeforeEach
		void setUp() {
			persons.enQueue(new Person("f1", "l1"));
			persons.enQueue(new Person("f2", "l2"));
			persons.enQueue(new Person("f3", "l3"));
		}
		
		@Test
		@DisplayName("validate the size")
		void checkSize() {
			assertEquals(3, persons.size());
		}
		
		@Test
		@DisplayName("remove an element and check if it is the first")
		void removeAnElement() {
			Person deQueuedPerson = persons.deQueue();
			assertEquals(new Person("f1","l1"), deQueuedPerson);
			assertEquals(2, persons.size());
		}
		
		@Test
		@DisplayName("remove multiple elements and check if they are valid")
		void removeElements() {
			persons.deQueue();
			Person nextDeQueuedPerson = persons.deQueue();
			assertEquals(new Person("f2","l2"), nextDeQueuedPerson);
			assertEquals(1, persons.size());
		}
		
		@Test
		@DisplayName("check for elements' presence")
		void checkElementPresence() {
			assertTrue(persons.contains(new Person("f1","l1")));
			persons.deQueue(); //Remove the first person
			
			Person nextDeQueuedPerson = persons.deQueue();//Remove the second person
			assertEquals(new Person("f2","l2"), nextDeQueuedPerson);
			assertEquals(1, persons.size());
		}
	}
	
	@Data
	@AllArgsConstructor
	private static class Person {
		private String firstName;
		private String lastName;
		
		public String toString() {
			return firstName + " " + lastName;
		}
	}
}
