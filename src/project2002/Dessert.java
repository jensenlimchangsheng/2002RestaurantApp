package project2002;

import project2002.MenuUI.ItemType;

public class Dessert extends MenuItem {
	ItemType itemType = ItemType.DESSERT;
	private Boolean nuts;

	Dessert(String name, double price, ItemType itemType, String description) {
		super(name, price, itemType, description);
	}

	public boolean getNuts(){
		return nuts;
	}

	public void setNuts(boolean nuts){
		this.nuts = nuts;
	}
}
