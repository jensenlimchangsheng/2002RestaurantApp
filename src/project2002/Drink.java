package project2002;

import project2002.MenuUI.ItemType;
/**
 * Drink subclass of Menu Item where it contains the attributes hot to allow customers to specify if they want hot version of the same drink.
 * 
 * @author Tzi Yu
 * @version 1.0
 * @since 2021-11-09
 */
public class Drink extends MenuItem {
	/**
	 * Enum itempType Drink.
	 */
	ItemType itemType = ItemType.DRINK;
	/**
	 * Hot true or false.
	 */
	private Boolean hot;
	/**
	 * Creating a drink with name, price, itemType, and description extending the menu item class
	 * @param name
	 * @param price
	 * @param itemType
	 * @param description
	 */
	Drink(String name, double price, ItemType itemType, String description) {
		super(name, price, itemType, description);
	}


	/**
	 * Sets the private value of hot
	 * @param hot
	 */
	public void setHot(Boolean hot) {
		this.hot = hot;
	}
	/**
	 * Gets the private value of hot if it is true or false.
	 * @return hot This drink if it is hot or cold.
	 */
	public Boolean getHot() {
		return hot;
	}
}
