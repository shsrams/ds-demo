package com.dsdemo.alg;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

public class GraphTraversal {

	private Map<String, Collection<String>> graph;

	public GraphTraversal(Map<String, Collection<String>> graph) {
		this.graph = graph;
	}

	public void depthFirstTraversalNoRecursion(String start, Consumer<String> consumer) {
		Deque<String> stack = new ArrayDeque<>();
		stack.addFirst(start);

		while (!stack.isEmpty()) {
			String currentNode = stack.removeFirst();
			consumer.accept(currentNode);

			for (String neighbor : graph.get(currentNode)) {
				stack.addFirst(neighbor);
			}
		}
	}

	public void breadthFirstTraversal(String start, Consumer<String> consumer) {
		Deque<String> queue = new ArrayDeque<>();
		queue.addLast(start);

		while (!queue.isEmpty()) {
			String currentNode = queue.removeFirst();
			consumer.accept(currentNode);

			for (String neighbor : graph.get(currentNode)) {
				queue.addLast(neighbor);
			}
		}
	}

	public void depthFirstTraversal(String start, Consumer<String> consumer) {
		consumer.accept(start);
		for (String neighbor : graph.get(start)) {
			depthFirstTraversal(neighbor, consumer);
		}
	}

	public boolean hasPathDFS(String source, String dest) {
		return hasPathDFS(source, dest, new HashSet<>());
	}

	private boolean hasPathDFS(String source, String dest, Set<String> visited) {
		visited.add(source);
		if (source.equals(dest)) {
			return true;
		}

		for (String neighbor : graph.get(source)) {
			if (!visited.contains(neighbor) && hasPathDFS(neighbor, dest, visited)) {
				return true;
			}
		}

		return false;
	}

	public int countComponents() { //counts the number of islands in the contained graph
		Set<String> visited = new HashSet<>();
		int count = 0;
		for(String node : graph.keySet()) {
			if(depthFirstTraverseCyclicGraphs(node, visited)) {
				count++;
			}
		}
		
		return count;
	}

	private boolean depthFirstTraverseCyclicGraphs(String currentNode, Set<String> visited) {

		if (visited.contains(currentNode)) {
			return false;
		}

		visited.add(currentNode);

		for (String neighborNode : graph.get(currentNode)) {
			depthFirstTraverseCyclicGraphs(neighborNode, visited);
		}

		return true; // traversal of this island is complete
	}

	public boolean hasPathBFS(String source, String dest) {
		Deque<String> queue = new ArrayDeque<>();
		queue.addLast(source);

		while (!queue.isEmpty()) {
			String nextNeighbor = queue.removeFirst();
			if (nextNeighbor.equals(dest)) {
				return true;
			}

			for (String neighbor : graph.get(nextNeighbor)) {
				queue.addLast(neighbor);
			}
		}

		return false;
	}

	public static Map<String, Collection<String>> buildGraph(List<List<String>> pairs) {
		Map<String, Collection<String>> graph = new HashMap<>();
		pairs.stream().forEach(pair -> {
			String first = pair.get(0);
			addToGraph(graph, first);
			String second = pair.get(1);
			addToGraph(graph, second);
			link(graph, first, second);
			link(graph, second, first);

		});

		return graph;
	}

	private static void link(Map<String, Collection<String>> graph, String aNode, String anotherNode) {
		graph.get(aNode).add(anotherNode);
	}

	private static void addToGraph(Map<String, Collection<String>> graph, String node) {
		graph.computeIfAbsent(node, s -> graph.put(s, new HashSet<>()));
	}

}
