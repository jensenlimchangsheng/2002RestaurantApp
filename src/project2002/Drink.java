package project2002;

import project2002.MenuUI.ItemType;

public class Drink extends MenuItem {
	ItemType itemType = ItemType.DRINK;

	Drink(String name, double price, ItemType itemType, String description) {
		super(name, price, itemType, description);
	}
}
