package project2002;

import project2002.MenuUI.ItemType;

public class PromoSet extends MenuItem {
	ItemType itemType = ItemType.PROMO;

	PromoSet(String name, double price, ItemType itemType, String description) {
		super(name, price, itemType, description);
	}
}
