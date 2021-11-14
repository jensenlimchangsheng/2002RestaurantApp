package project2002;

import project2002.MenuUI.ItemType;

/**
 * Represents each menu item from the restaurant. A menu item can consist of
 * MAIN, SIDE, DRINK, DESSERT, PROMO.
 * 
 * @author Tzi Yu
 * @version 1.0
 * @since 2021-11-09
 */

public class MenuItem {

	/**
	 * The unique ID for this menu item.
	 */
	private int itemID;
	/**
	 * The name for this menu item.
	 */
	private String name;
	/**
	 * The price for this menu item.
	 */
	private double price;
	/**
	 * The description for this menu item.
	 */
	private String description;
	/**
	 * The type for this menu item.
	 */
	private ItemType itemType;

	/**
	 * Creates a new menu item with name, price, type and description.
	 * 
	 * @param name        This menu item's name.
	 * @param price       This menu item's price.
	 * @param itemType    This menu item's type.
	 * @param description This menu item's description.
	 */
	MenuItem(String name, double price, ItemType itemType, String description) {
		this.name = name;
		this.price = price;
		this.itemType = itemType;
		this.description = description;
	}

	/**
	 * Gets the ID of this menu item.
	 * 
	 * @return this menu item's ID.
	 */
	int getID() {
		return itemID;
	}

	/**
	 * Sets the ID of this menu item.
	 * 
	 * @param itemID This menu item's ID.
	 */
	void setID(int itemID) {
		this.itemID = itemID;
	}

	/**
	 * Gets the name of this menu item.
	 * 
	 * @return this menu item's name.
	 */
	String getName() {
		return name;
	}

	/**
	 * Sets the name of this menu item.
	 * 
	 * @param name This menu item's name.
	 */
	void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the price of this menu item.
	 * 
	 * @return this menu item's price.
	 */
	double getPrice() {
		return price;
	}

	/**
	 * Sets the price of this menu item.
	 * 
	 * @param name This menu item's price.
	 */
	void setPrice(double price) {
		this.price = price;
	}

	/**
	 * Gets the description of this menu item.
	 * 
	 * @return this menu item's description.
	 */
	String getDescription() {
		return description;

	}

	/**
	 * Sets the description of this menu item.
	 * 
	 * @param name This menu item's description.
	 */
	void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the type of this menu item.
	 * 
	 * @return this menu item's type.
	 */
	ItemType getItemType() {
		return itemType;
	}

	/**
	 * Gets the type of this menu item.
	 * 
	 * @return this menu item's type.
	 */
	void setItemType(ItemType itemType) {
		this.itemType = itemType;
	}

}
