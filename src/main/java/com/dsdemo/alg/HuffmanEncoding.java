package com.dsdemo.alg;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import lombok.AllArgsConstructor;
import lombok.Data;

public class HuffmanEncoding {
	
	public HuffmanEncodingResult encode(String strToEncode) {
		Map<Character, Integer> frequencyTable = buildFrequencyTable(strToEncode);
		
		PriorityQueue<HuffmanNode> pq =  buildPriorityQueue(frequencyTable);
		
		HuffmanNode root = assemble(pq);
		
		Map<Character, String> codeMap = new HashMap<>();
		buildCodeMap(root, "", codeMap);
		
		//System.out.println("Code map : "+codeMap);
		
		StringBuilder sb = new StringBuilder();
		for(char c : strToEncode.toCharArray()) {
			sb.append(codeMap.get(c));
		}
		
		return new HuffmanEncodingResult(root, sb.toString());
	}
	
	public String decode(HuffmanNode root, String encodedString) {
		int index = -1;
		StringBuilder encoded = new StringBuilder(encodedString);
		StringBuilder decoded = new StringBuilder();
		
		while (index < encodedString.length() -1) { 
			index = decode(root, index, encoded, decoded);
		}
		
		return decoded.toString();
	}
	
	private int decode(HuffmanNode currentNode, int index, StringBuilder encoded, StringBuilder decoded) {
		
		if(currentNode.getLeft() == null && currentNode.getRight() == null) {
			decoded.append(currentNode.getC());
			return index;
		}

		++index;
		if(encoded.charAt(index) == '0') {
			currentNode = currentNode.getLeft();
		} else {
			currentNode = currentNode.getRight();
		}
		
		return decode(currentNode, index, encoded, decoded);
	}

	private Map<Character, Integer> buildFrequencyTable(String word) {
		Map<Character, Integer> frequencyMap = new HashMap<>();
		char[] chars = word.toCharArray();
		for(char c : chars) {
			int currentValue = frequencyMap.computeIfAbsent(c, s-> 0);
			frequencyMap.put(c, ++currentValue);
		}
		return frequencyMap;
	}
	
	private PriorityQueue<HuffmanNode> buildPriorityQueue(Map<Character, Integer> frequencyTable) {
		PriorityQueue<HuffmanNode> pq = new PriorityQueue<>();
		frequencyTable.keySet().stream()
			.forEach(s -> { 
				HuffmanNode node = new HuffmanNode(s, frequencyTable.get(s));
				pq.add(node);
			});
		
		return pq;
	}
	
	private HuffmanNode assemble(PriorityQueue<HuffmanNode> pq) {
		HuffmanNode root = null;
		while(pq.size() > 1) {
			HuffmanNode first = pq.poll();
			HuffmanNode second = pq.poll();
			
			int sum = first.getOccurrenceCount()+second.getOccurrenceCount();
			HuffmanNode parent = new HuffmanNode(null,sum);
			parent.left = first;
			parent.right = second;
			
			pq.add(parent);
			
			root = parent;
		}
		
		return root;
	}
	
	public void buildCodeMap(HuffmanNode currentNode, String s, Map<Character, String> codeMap) {
		
		if(currentNode.getLeft() == null && currentNode.getRight() == null) {
			codeMap.put(currentNode.getC(), s);
			return;
		}
		
		buildCodeMap(currentNode.left, s+"0", codeMap);
		buildCodeMap(currentNode.right, s+"1", codeMap);
	}

	
	@Data
	private class HuffmanNode implements Comparable<HuffmanNode> {
		private int occurrenceCount;
		private Character c;
		
		private HuffmanNode left;
		private HuffmanNode right;
		
		public HuffmanNode(Character c,int occurrenceCount) {
			this.occurrenceCount = occurrenceCount;
			this.c = c;
		}

		@Override
		public int compareTo(HuffmanNode o) {
			return Integer.compare(getOccurrenceCount(), o.getOccurrenceCount());
		}

		@Override
		public String toString() {
			return "HuffmanNode [occurrenceCount=" + occurrenceCount + ", c=" + c + ", left=" + left + ", right="
					+ right + "]";
		}



	}
	
	@Data
	@AllArgsConstructor
	public static class HuffmanEncodingResult {
		private HuffmanNode root;
		private String encoded;
	}

}
