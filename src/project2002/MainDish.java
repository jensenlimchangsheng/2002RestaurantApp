package project2002;

import project2002.MenuUI.ItemType;

public class MainDish extends MenuItem {
	ItemType itemType = ItemType.MAIN;

	MainDish(String name, double price, ItemType itemType, String description) {
		super(name, price, itemType, description);
	}
}
