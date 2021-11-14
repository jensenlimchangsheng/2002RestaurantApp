package project2002;

import project2002.MenuUI.ItemType;
/**
 * Side subclass of Menu Item where it contains the attributes size to allow customers to specify if they want to upsize.
 * 
 * @author Jiam Tzi Yu
 * @version 1.0
 * @since 2021-11-09
 */
public class Side extends MenuItem {
	/**
	 * Enum itempType Side.
	 */
	ItemType itemType = ItemType.SIDE;
	/**
	 * Allows customer to request for less salt for the side.
	 */
	private boolean lessSalt;
	/**
	 * Creating a sude with name, price, itemType, and description extending menu item class
	 * @param name
	 * @param price
	 * @param itemType
	 * @param description
	 */
	Side(String name, double price, ItemType itemType, String description) {
		super(name, price, itemType, description);
	}
	/**
	 * Gets the private value of lessSalt.
	 * @return lessSalt The salt level of this side.
	 */
	public boolean getLessSalt(){
		return lessSalt;
	}
	/**
	 * Sets the boolean value of lessSalt.
	 * @param lessSalt
	 */
	public void setSize(boolean lessSalt){
		this.lessSalt = lessSalt;
	}
}
