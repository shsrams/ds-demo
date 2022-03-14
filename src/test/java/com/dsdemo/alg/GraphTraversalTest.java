package com.dsdemo.alg;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GraphTraversalTest {

	private GraphTraversal graphTraversal;
	
	@BeforeEach
	void setUp() {
		Map<String, Collection<String>> graph = new HashMap<>();
		graph.put("a", Arrays.asList("b","c"));
		graph.put("b", Arrays.asList("d"));
		graph.put("c", Arrays.asList("e"));
		graph.put("d", Arrays.asList("f"));
		graph.put("e", Collections.emptyList());
		graph.put("f", Collections.emptyList());
		graphTraversal = new GraphTraversal(graph);
	}
	
	
	@DisplayName("Depth first traversal test")
	@Test 
	void testDFSNoRecursion() {
		graphTraversal.depthFirstTraversalNoRecursion("a", s -> System.out.print(s+",")); //a,c,e,b,d,f,
		System.out.println();
	}
	
	@DisplayName("Depth first traversal test using recursion")
	@Test 
	void testDFS() {
		graphTraversal.depthFirstTraversal("a", s -> System.out.print(s+",")); //a,b,d,f,c,e,
		System.out.println();
	}
	
	@DisplayName("Depth first traversal test using recursion")
	@Test 
	void testBFS() {
		graphTraversal.breadthFirstTraversal("a", s -> System.out.print(s+",")); //a,b,c,d,e,f
		System.out.println();
	}
	
	@DisplayName("test hasPath - true") 
	@Test
	void testHasPathDFSWhenAPathExists() {
		assertTrue(graphTraversal.hasPathDFS("a", "e"));
	}
	
	@DisplayName("test hasPath - false") 
	@Test
	void testHasPathDFSWhenNoPathExists() {
		assertFalse(graphTraversal.hasPathDFS("e", "f"));
		assertFalse(graphTraversal.hasPathDFS("b", "e"));
	}
	
	@DisplayName("test hasPathBFS - true") 
	@Test
	void testHasPathBFSWhenAPathExists() {
		assertTrue(graphTraversal.hasPathBFS("a", "e"));
	}
	
	@DisplayName("test hasPathBFS - false") 
	@Test
	void testHasPathBFSWhenNoPathExists() {
		assertFalse(graphTraversal.hasPathBFS("e", "f"));
		assertFalse(graphTraversal.hasPathBFS("b", "e"));
	}
	
	@DisplayName("test build graph") 
	@Test
	void testBuildGraph() {
		List<List<String>> pairs = new ArrayList<>();
		pairs.add(Arrays.asList("i", "j"));
		pairs.add(Arrays.asList("k", "i"));
		pairs.add(Arrays.asList("m", "k"));
		pairs.add(Arrays.asList("k", "l"));
		pairs.add(Arrays.asList("o", "n"));
		
		Map<String, Collection<String>> graph = GraphTraversal.buildGraph(pairs);
		graphTraversal = new GraphTraversal(graph);
		assertTrue(graphTraversal.hasPathDFS("i", "k"));
		assertTrue(graphTraversal.hasPathDFS("k", "i"));
		assertFalse(graphTraversal.hasPathDFS("o", "k"));

	}
	
	@DisplayName("test counting the # of islands") 
	@Test
	void testIslandCount() {
		Map<String, Collection<String>> graph = new HashMap<>();
		graph.put("0", Arrays.asList("8", "1", "5"));
		graph.put("1", Arrays.asList("0"));
		graph.put("5", Arrays.asList("0", "8"));
		graph.put("8", Arrays.asList("0","5"));
		graph.put("2", Arrays.asList("3","4"));
		graph.put("3", Arrays.asList("2", "4"));
		graph.put("4", Arrays.asList("3", "2"));
		graphTraversal = new GraphTraversal(graph);
		
		assertEquals(2, graphTraversal.countComponents());
		
	}

}
