package project2002;

import project2002.MenuUI.ItemType;

public class Side extends MenuItem {
	ItemType itemType = ItemType.SIDE;
	String size;

	Side(String name, double price, ItemType itemType, String description) {
		super(name, price, itemType, description);
	}

	public String getSize(){
		return size;
	}

	public void setSize(String size){
		this.size = size;
	}
}
