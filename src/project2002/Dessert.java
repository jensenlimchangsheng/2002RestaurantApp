package project2002;

import project2002.MenuUI.ItemType;
/**
 * Dessert subclass of Menu Item where it contains the attributes nuts to allow customers to specify if they want nuts.
 * 
 * @author Tzi Yu
 * @version 1.0
 * @since 2021-11-09
 */

public class Dessert extends MenuItem {
	/**
	 * Enum itempType Dessert.
	 */
	ItemType itemType = ItemType.DESSERT;
	/**
	 * Nuts true or false.
	 */
	private Boolean nuts;

	/**
	 * Creating a dessert with name, price, itemType, and description extending menu item class
	 * @param name
	 * @param price
	 * @param itemType
	 * @param description
	 */
	Dessert(String name, double price, ItemType itemType, String description) {
		super(name, price, itemType, description);
	}

	/**
	 * Gets the private value of nuts if it is true or false.
	 * @return nuts This dessert presence of nuts.
	 */
	public boolean getNuts(){
		return nuts;
	}

	/**
	 * Sets the private value of nuts.
	 * @param nuts
	 */
	public void setNuts(boolean nuts){
		this.nuts = nuts;
	}
}
