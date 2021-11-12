package project2002;

import project2002.MenuUI.ItemType;

public class PromoSet extends MenuItem {
	ItemType itemType = ItemType.PROMO;
	String specialReq;

	PromoSet(String name, double price, ItemType itemType, String description) {
		super(name, price, itemType, description);
	}

	public String getSpecialReq() {
		return specialReq;
	}

	public void setSpecialReq(String specialReq){
		this.specialReq = specialReq;
	}
}
