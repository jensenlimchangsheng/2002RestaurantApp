package project2002;

import project2002.MenuUI.ItemType;

public class Dessert extends MenuItem {
	ItemType itemType = ItemType.DESSERT;
	private Boolean nuts;
	
	Dessert(String name, double price, ItemType itemType, String description) {
		super(name, price, itemType, description);
		// TODO Auto-generated constructor stub
	}

	boolean getNuts(){
		return nuts;
	}

	void setNuts(boolean nuts){
		this.nuts = nuts;
	}
}
