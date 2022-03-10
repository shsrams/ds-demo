package com.dsdemo.ds;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.dsdemo.ds.MyStack;
@DisplayName("Given a stack")
class MyStackTest {

	private MyStack<Card> stackOfCards = new MyStack<>();
	@BeforeAll
	static void setup() {

	}
	
	@Nested
	@DisplayName("When it is empty")
	class EmptyStack {
		@DisplayName("Pop throws exception")
		@Test
		void testEmptyPop() {
			assertThrows(IllegalStateException.class, () ->	stackOfCards.pop());
		}
	}
	
	@Nested
	@DisplayName("After cards are pushed")
	class WhenCardsArePushed {
		@BeforeEach
		void pushCards() {
			stackOfCards.push(new Card("A", Card.Category.CLUBS));
			stackOfCards.push(new Card("2", Card.Category.SPADE));
		}
		
		@DisplayName("size is valid")
		@Test
		void checkSize() {
			assertEquals(2, stackOfCards.size());
		}
		
		@DisplayName("popping one element gets the expected ones and keeps the size intact")
		@Test
		void popOne() {
			Card pop1 = stackOfCards.pop();
			assertEquals("2", pop1.getName());
			assertEquals(1, stackOfCards.size());
		}
		
		@DisplayName("popping two elements get the expected ones and keeps the size intact")
		@Test
		void popTwo() {
			Card pop1 = stackOfCards.pop();
			assertEquals("2", pop1.getName());
			assertEquals(1, stackOfCards.size());
			
			Card pop2 = stackOfCards.pop();
			assertEquals("A", pop2.getName());
			assertEquals(0, stackOfCards.size());
		}
		
		@DisplayName("looking for an existing element does get it")
		@Test
		void testGet() {
			Card cardToGet = new Card("A", Card.Category.CLUBS);
			Card foundCard = stackOfCards.get(cardToGet);
			
			assertNotNull(foundCard);
			assertEquals(0, stackOfCards.size());
			assertEquals(cardToGet, foundCard);
		}
		
		@DisplayName("looking for a non-existing element does not get it")
		@Test
		void testGetNonExisting() {
			Card cardToGet = new Card("x", Card.Category.CLUBS);
			Card foundCard = stackOfCards.get(cardToGet);
			
			assertNull(foundCard);
			assertEquals(0, stackOfCards.size());
		}
		
		@DisplayName("peeking for an existing element does get it")
		@Test
		void testPeek() {
			Card cardToPeek = new Card("A", Card.Category.CLUBS);
			Optional<Card> foundCard = stackOfCards.peek(cardToPeek);
			
			assertTrue(foundCard.isPresent());
			assertEquals(2, stackOfCards.size());
			assertEquals(cardToPeek, foundCard.get());
		}
		
		@DisplayName("peeking for a non-existing element does not get it and does not change the size")
		@Test
		void testPeekNonExisting() {
			Card cardToPeek = new Card("x", Card.Category.CLUBS);
			Optional<Card> foundCard = stackOfCards.peek(cardToPeek);
			
			assertFalse(foundCard.isPresent());
			assertEquals(2, stackOfCards.size());
		}
	}
	
	@Nested
	@DisplayName("When capacity is not explicitly defined")
	class WithDefaultCapacity {
		@DisplayName("it is of the default value")
		@Test
		void defaultValue() {
			assertEquals(MyStack.DEFAULT_CAPACITY, stackOfCards.capacity());
		}
	}
	
	@Nested
	@DisplayName("When capacity is explicitly defined")
	class WithExplicitCapacity {
		@BeforeEach
		void setup() {
			stackOfCards = new MyStack<>(1000);
		}
		
		@DisplayName("it is of the explicitly defined value")
		@Test
		void defaultValue() {
			assertEquals(1000, stackOfCards.capacity());
		}
	}
	

	

}
