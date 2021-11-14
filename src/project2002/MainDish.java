package project2002;

import project2002.MenuUI.ItemType;
/**
 * Main subclass of Menu Item where it contains the attributes doneness to allow customers to specify their doneness preference.
 * 
 * @author Jiam Tzi Yu
 * @version 1.0
 * @since 2021-11-09
 */
public class MainDish extends MenuItem {
	/**
	 * Enum itempType Main.
	 */
	ItemType itemType = ItemType.MAIN;
	/**
	 * Level of doneness: Well Done, Medium Well, Medium, Medium Rare.
	 */
	private String doneness;
	/**
	 * Creating a main with name, price, itemType, and description extending menu item class
	 * @param name
	 * @param price
	 * @param itemType
	 * @param description
	 */
	MainDish(String name, double price, ItemType itemType, String description) {
		super(name, price, itemType, description);
	}
	/**
	 * Gets the private value of doneness.
	 * @return doneness This doneness of this main.
	 */
	public String getDoneness(){
		return doneness;
	}

	/**
	 * Sets the private value of doneness.
	 * @param doneness
	 */
	void setDoneness(String doneness){
		this.doneness = doneness;
	}
}
