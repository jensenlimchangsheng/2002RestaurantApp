package project2002;

import project2002.MenuUI.ItemType;

public class MainDish extends MenuItem {
	ItemType itemType = ItemType.MAIN;
	private String doneness;

	MainDish(String name, double price, ItemType itemType, String description) {
		super(name, price, itemType, description);
		// TODO Auto-generated constructor stub
	}

	public String getDoneness(){
		return doneness;
	}

	void setDoneness(String doneness){
		this.doneness = doneness;
	}
}
