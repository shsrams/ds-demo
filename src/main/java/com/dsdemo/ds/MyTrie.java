package com.dsdemo.ds;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

public class MyTrie {
	private TrieNode root;
	public MyTrie() {
		root = new TrieNode();
	}
	public void insert(String word) {
		char[] chars = word.toCharArray();
		
		TrieNode current = root;
		for(Character c : chars) {
			current.getChildren().putIfAbsent(c, new TrieNode()); //Add a nodeMap to the currentNode if the given character map doesn't exist
			current = current.getChildren().get(c);
		}
		
		current.setTerminalNode(true);
	}
	
	public boolean isFound(String word) {
		TrieNode current = root;
		char[] chars = word.toCharArray();
		
		for(Character c : chars) {
			if(current.getChildren().containsKey(c)) {
				current = current.getChildren().get(c);
			} else {
				return false;
			}
		}
		
		return current.isTerminalNode();
	}
	
	public void delete(String word) {
		delete(root, word, 0);
	}
	
	private boolean delete(TrieNode currentNode, String word, int index) {
		if(currentNode.isTerminalNode()) {
			System.out.println("Reached a terminal node, stopping and returning true");
			return true;
		}
		
		char charToInspect = word.charAt(index);
		if(currentNode.getChildren().containsKey(charToInspect) && !currentNode.isTerminalNode()) { //given letter is found, now proceed to the next letter
			System.out.println("Found "+charToInspect);
			if(delete(currentNode.getChildren().get(charToInspect), word, index+1)) {
				System.out.println("Char is "+charToInspect);
				if(!currentNode.isTerminalNode()) {
					System.out.println("Removing "+currentNode.getChildren().get(charToInspect));
					currentNode.getChildren().remove(charToInspect);
					return currentNode.getChildren().isEmpty();
				}
				return true;
			}
			else {
				return false;
			}
			
			
		} else {//given letter is not there, so the word we are tyring to find is not here, so abort
			System.out.println("cannot find "+charToInspect+"; so aborting");
			return false;
		}

	}
	
	public int countWords() {
		Counter counter = new Counter();
		explore(root, counter);
		return counter.getCount();
	}
	
	private void explore(TrieNode current, Counter counter) {
		if(current.isTerminalNode()) {
			//System.out.println("Hit end");
			counter.inc();
			return;
		}
		
		for(Character c : current.getChildren().keySet()) { //for 
			//System.out.println("Now exploring "+c);
			explore(current.getChildren().get(c), counter);
		}
	}
	
	
	@Data
	private class TrieNode {
		private Map<Character, TrieNode> children;
		private boolean terminalNode;
		
		public TrieNode() {
			children = new HashMap<>();
			terminalNode = false;
		}
	}
	
	@Data
	private class Counter {
		private int count;
		
		public Counter() {
			count = 0;
		}
		
		public void inc() {
			++count;
		}
		
		public String toString() {
			return ""+count;
		}
	}
}
