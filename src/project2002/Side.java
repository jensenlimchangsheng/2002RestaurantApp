package project2002;

import project2002.MenuUI.ItemType;

public class Side extends MenuItem {
	ItemType itemType = ItemType.SIDE;

	Side(String name, double price, ItemType itemType, String description) {
		super(name, price, itemType, description);
	}
}
