package project2002;

import project2002.MenuUI.ItemType;
/**
 * Main subclass of Menu Item where it contains the attributes doneness to allow customers to specify their doneness preference.
 * 
 * @author Tzi Yu
 * @version 1.0
 * @since 2021-11-09
 */
public class MainDish extends MenuItem {
	/**
	 * Enum itempType Main.
	 */
	ItemType itemType = ItemType.MAIN;
	/**
	 * Indicate if the customer wants sauce on this menu item.
	 */
	private boolean sauce;
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
	 * Gets the private value of sauce.
	 * @return sauce This presence of sauce for this main.
	 */
	public boolean getSauce(){
		return sauce;
	}

	/**
	 * Sets the private value of sauce.
	 * @param sauce
	 */
	void setSauce(boolean sauce){
		this.sauce = sauce;
	}
}
