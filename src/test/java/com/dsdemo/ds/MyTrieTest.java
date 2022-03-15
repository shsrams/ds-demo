package com.dsdemo.ds;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MyTrieTest {
	
	private MyTrie trie;
	
	@BeforeEach
	void setUp() {
		trie = new MyTrie();
	}

	@DisplayName("Test Insert")
	@Test
	void test() {
		trie.insert("Chain");
		trie.insert("char");
	}
	
	@DisplayName("Test find")
	@Test
	void testFind() {
		trie.insert("Chain");
		assertTrue(trie.isFound("Chain"));
		
		trie.insert("char");
		assertTrue(trie.isFound("char"));
		
		assertFalse(trie.isFound("Chainsaw"));
		assertFalse(trie.isFound("saw"));
	}
	
	@DisplayName("Test count nodes") 
	@Test
	void testCountWords() {
		trie.insert("Chain");
		trie.insert("Char");
		trie.insert("hat");
		trie.insert("China");
		
		assertEquals(4, trie.countWords());
		
	}
	
	@DisplayName("Test delete word") 
	@Test
	void testDeleteWord() {
		trie.insert("Chain");
		trie.insert("Char");
		trie.insert("hat");
		trie.insert("China");
		
		trie.delete("hat");
		
		assertEquals(3, trie.countWords());
		
		trie.delete("Char");
		assertEquals(2, trie.countWords());
		
	}

}
