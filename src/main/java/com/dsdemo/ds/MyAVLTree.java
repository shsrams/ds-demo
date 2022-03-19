package com.dsdemo.ds;

import java.util.stream.IntStream;

import lombok.Data;

public class MyAVLTree<T extends Comparable<T>> {
	private Node<T> root;
	private int size;
	
	public int getSize() {
		return size;
	}
	public boolean insert(T item) {
		if(root == null) {
			root = makeNode(item);
			size++;
			return true;
		}
		
		if(!contains(root, item)) {
			root = insertNode(root, item);
			return true;
		}
		
		return false;
	}

	private Node<T> insertNode(Node<T> current, T item) {

		if(current == null) { //We have reached a node whose right / left node is null
			size++;
			return makeNode(item);
		}
		
		int compareResult = item.compareTo(current.getItem());
		if(compareResult < 0) {
			current.setLeft(insertNode(current.getLeft(), item));
			//System.out.println("Adding "+current.getLeft()+" to the left of "+current);
		} else if (compareResult > 0) {
			current.setRight(insertNode(current.getRight(), item));
			//System.out.println("Adding "+current.getRight()+" to the right of "+current);
		}
		
		update(current);//recursively update the current node's height and balance factor
		return balance(current);
		
	}
	
	public void printTree() {
		inOrderTraverseDesc(root, 0);
	}
	
	private void inOrderTraverseDesc(Node<T> current, int level) {
		if(current != null) {
			inOrderTraverseDesc(current.getRight(), level+1);
			IntStream.range(0, level)
				.forEach(n -> System.out.print("\t\t"));
			System.out.println(current.getItem());

			inOrderTraverseDesc(current.getLeft(), level+1);
		}
	}
	
	private boolean contains(Node<T> current, T item) {
		if(item == null || current == null) {
			return false;
		}
		
		int compareResult = item.compareTo(current.getItem());
		if(compareResult < 0) {
			return contains(current.getLeft(), item);
		} else if(compareResult > 0) {
			return contains(current.getRight(), item);
		} else {
			return true;
		}
	}
	
	private void update(Node<T> node) {
		int leftHeight = height(node.getLeft());
		int rightHeight = height(node.getRight());
		
		int updatedNodeHeight = 1 + max(leftHeight, rightHeight);
		node.setHeight(updatedNodeHeight);
		node.setBf(rightHeight - leftHeight);

	}
	
	private Node<T> balance(Node<T> node) {
		if(node.getBf() == -2) { //left heavy
			if(node.getLeft().getBf() <= 0) {//left leaning
				return rightRotate(node);
			} else {
				return leftRightRotate(node);
			}
		} else if (node.getBf() == 2) {//right heavy
			if(node.getRight().getBf() >= 0) {//right leaning
				return leftRotate(node); 
			} else {
				return rightLeftRotate(node);
			}
		}
		return node;
	}
	
	private Node<T> rightLeftRotate(Node<T> node) {
		//System.out.println("rightleft rotation "+node);
		node.right = rightRotate(node.getRight());
		return leftRotate(node);
	}
	private Node<T> rightRotate(Node<T> node) {
		//System.out.println("Right rotating "+node);
		Node<T> newParent = node.getLeft();
		node.setLeft(newParent.getRight());
		newParent.setRight(node);
		update(node);
		update(newParent);
		return newParent;
	}
	private Node<T> leftRightRotate(Node<T> node) {
		//System.out.println("leftRight rotation "+node);
		node.left = leftRotate(node.getLeft());
		return rightRotate(node);
		
	}
	private Node<T> leftRotate(Node<T> node) {
		//System.out.println("left rotation "+node);
		Node<T> newParent = node.getRight();
		node.setRight(newParent.getLeft());
		newParent.setLeft(node);
		update(node);
		update(newParent);
		return newParent;
		
	}
	private Node<T> makeNode(T item) {
		return new Node<T>(item);
	}
	private int max(int leftHeight, int rightHeight) {
		return (leftHeight > rightHeight)? leftHeight : rightHeight;
	}	
	private int height(Node<T> node) {
		if(node == null) {
			return -1;
		}
		return node.getHeight();
	}
	
	@Data
	private class Node<T> {
		private T item;
		private Node<T> left;
		private Node<T> right;
		private Integer height;
		private int bf;
		
		public Node(T item) {
			this.item = item;
			this.height = 0;
		}

		@Override
		public String toString() {
			return "Node [item=" + item + ", height=" + height + ", bf=" + bf + "]";
		}
	}

}
