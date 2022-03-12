package com.dsdemo.ds;

import java.util.function.Consumer;

import lombok.Data;

public class MyBinaryTree<T extends Comparable<T>> {
	private int size;
	private Node<T> root;
	
	public void add(T item) {
		Node<T> newNode = new Node<>(item);
		
		if(root == null) {
			root = newNode;
			size++;
			System.out.println("Parent: "+item);
		} else {
			insert(root, newNode);
		}
	}
	
	public T fetch(T item) {
		Node<T> matchedNode =  match(root, item);
		if (matchedNode != null) {
			return matchedNode.getItem();
		}
		return null;
	}
	
	public Node<T> fetchNode(T item) {
		return match(root, item);
	}
	
	public void delete(T item) {
		Node<T> nodeToDelete = match(root, item);
		if(nodeToDelete == null) {
			throw new IllegalArgumentException("Node not found for delete");
		}
		
		if(nodeToDelete.getLeft() == null && nodeToDelete.getRight() == null) {//no children
			unlink(nodeToDelete, null);
		} else if(nodeToDelete.getLeft() != null && nodeToDelete.getRight() == null) {
			unlink(nodeToDelete, nodeToDelete.getLeft());
		} else if(nodeToDelete.getLeft() == null && nodeToDelete.getRight() != null) {
			unlink(nodeToDelete, nodeToDelete.getRight());
		} else {//both children are present
			//get the rightmost leaf of the left node
			Node<T> replacementCandidate = nodeToDelete.getLeft();
			while(replacementCandidate.getRight() != null) {
				replacementCandidate = replacementCandidate.getRight();
			}
			unlink(nodeToDelete, replacementCandidate);
		}
	}
	
	public void inOrderTraverse(Consumer<T> consumer) {
		inOrderTraverse(root, consumer);
	}
	
	private void inOrderTraverse(Node<T> node, Consumer<T> consumer) {
		if(node != null) {
			inOrderTraverse(node.getLeft(), consumer);
			consumer.accept(node.getItem());
			inOrderTraverse(node.getRight(), consumer);
		}
	}
	public void inOrderTraverseDesc(Consumer<T> consumer) {
		inOrderTraverseDesc(root, consumer);
	}
	

	private void inOrderTraverseDesc(Node<T> node, Consumer<T> consumer) {
		if(node != null) {
			inOrderTraverseDesc(node.getRight(), consumer);
			consumer.accept(node.getItem());
			inOrderTraverseDesc(node.getLeft(), consumer);
		}
		
	}

	private void unlink(Node<T> nodeToDelete, Node<T> newNode) {
		if(nodeToDelete.getParent() == null) { //this is the root node
			newNode.setLeft(nodeToDelete.getLeft());
			newNode.setRight(nodeToDelete.getRight());
			this.root = newNode;
		} else {
			if(nodeToDelete.getParent().getRight().equals(nodeToDelete)) {
				nodeToDelete.getParent().setRight(newNode);
			} else {
				nodeToDelete.getParent().setLeft(newNode);
			}
		}
		size--; 
	}

	private Node<T> match(Node<T> currentNode, T item) {
		int compareResult = item.compareTo(currentNode.getItem());

		if(compareResult == 0) {//match found
			return currentNode;
		} else if(compareResult > 0) { // continue searching right
			if(currentNode.getRight() == null) {
				return null;
			}
			return match(currentNode.getRight(), item);

		} else if(compareResult < 0) { // continue searching left
			if(currentNode.getLeft() == null) {
				return null;
			}
			return match(currentNode.getLeft(), item);
		}
		return null;
	}
	
	private void insert(Node<T> parent, Node<T> child) {
		if(child.getItem().compareTo(parent.getItem()) < 0) { //Add left
			
			if(parent.getLeft() == null) {
				parent.setLeft(child);
				child.setParent(parent);
				size++;
				System.out.println("Adding "+child.getItem()+" to the left of "+parent.getItem());
			} else {
				insert(parent.getLeft(), child);
			}
			
		} else if (child.getItem().compareTo(parent.getItem()) > 0) { //Add right
			if(parent.getRight() == null) {
				parent.setRight(child);
				child.setParent(parent);
				size++;
				System.out.println("Adding "+child.getItem()+" to the right of "+parent.getItem());
			} else {
				insert(parent.getRight(), child);
			}
		}
	}
	

	@Data
	public class Node <T> {
		
		public Node(T item) {
			this.item = item;
		}
		private T item;
		private Node<T> parent;
		private Node<T> left;
		private Node<T> right;
	}


	public int size() {
		return size;
	}
}
