package project2002;

import project2002.MenuUI.ItemType;

/**
 * Promoset subclass of Menu Item where it contains the attributes special
 * request to allow customers to specify if special request.
 * 
 * @author Tzi Yu
 * @version 1.0
 * @since 2021-11-09
 */
public class PromoSet extends MenuItem {
	/**
	 * Enum itemType Promo.
	 */
	ItemType itemType = ItemType.PROMO;
	/**
	 * Special Request.
	 */
	String specialReq;

	/**
	 * Creating a promo with name, price, itemType, and description extending menu
	 * item class
	 * 
	 * @param name
	 * @param price
	 * @param itemType
	 * @param description
	 */
	PromoSet(String name, double price, ItemType itemType, String description) {
		super(name, price, itemType, description);
	}

	/**
	 * Gets the private string value of special request.
	 * 
	 * @return special The special request for this promo set.
	 */
	public String getSpecialReq() {
		return specialReq;
	}

	/**
	 * Sets the special request for this promo set.
	 * 
	 * @param specialReq
	 */
	public void setSpecialReq(String specialReq) {
		this.specialReq = specialReq;
	}
}
