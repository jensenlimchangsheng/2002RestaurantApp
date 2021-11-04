package project2002;

import project2002.MenuUI.ItemType;

public class MenuItem {

	private int itemID;
	private String name;
	private double price;
	private String description;
	private ItemType itemType; 
	
	MenuItem(String name, double price,ItemType itemType, String description){
		this.name = name;
		this.price = price;
		this.itemType = itemType;
		this.description = description;
	}
	
	
	int getID() {
		return itemID;
	}
	
	void setID(int itemID) {
		this.itemID = itemID;
	}

	String getName() {
		return name;
	}
	
	void setName(String name) {
		this.name = name;
	}
	
	double getPrice() {
		return price;
	}
	
	void setPrice(double price) {
		this.price = price;
	}

	String getDescription() {
		return description;
		
	}
	void setDescription(String description) {
		this.description = description;
	}

	ItemType getItemType() {
		return itemType;
		
	}
	void setItemType(ItemType itemType) {
		this.itemType = itemType;
	}


}
