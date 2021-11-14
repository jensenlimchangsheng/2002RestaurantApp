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
	 * size string: Large, Small
	 */
	String size;
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
	 * Gets the private value of size.
	 * @return size The size of this side.
	 */
	public String getSize(){
		return size;
	}
	/**
	 * Sets the private value of size.
	 * @param size
	 */
	public void setSize(String size){
		this.size = size;
	}
}
