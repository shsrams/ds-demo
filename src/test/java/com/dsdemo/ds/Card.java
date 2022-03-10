package com.dsdemo.ds;

import lombok.Data;

@Data
public class Card {
	private String name;
	private Category type;
	
	public enum Category {SPADE, CLUBS, HEARTS, DIAMONDS}
	
	public Card(String name, Category type) {
		this.name = name;
		this.type = type;
	}

}
