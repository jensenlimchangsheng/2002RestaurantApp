package project2002;

import java.util.ArrayList;

import project2002.MenuUI.ItemType;
import project2002.Restaurant.handlerType;

public class MenuHandler extends Handler {
	public MenuHandler() {
		type = handlerType.MENU;
		addMenuItem("The Feather Blade Steak", 21, "Signature", MenuUI.ItemType.MAIN);
		addMenuItem("Potatoes", 7, "Hand Smashed, Deep Fried in Beef Fat", MenuUI.ItemType.SIDE);
		addMenuItem("Madagascan Vallina Ice Cream", 8, "Sweet Devil", MenuUI.ItemType.DESSERT);
		addMenuItem("Death on Weekends", 19, "Prosecco with a splash of green fairy", MenuUI.ItemType.DRINK);
		addMenuItem("Lunch Special", 45, "The Feather Blade Steak, Potatoes, Madagascan Vallina Ice Cream, Death on Weekends", MenuUI.ItemType.PROMO);
	}

	ArrayList<MenuItem> MenuItems = new ArrayList<MenuItem>();
	private int maxMainID = 100;
	private int maxSideID = 200;
	private int maxDrinkID = 300;
	private int maxDessertID = 400;
	private int maxPromoID = 500;



	// SETTLED
	public MenuItem addMenuItem(String name, double price, String description, ItemType itemType) {
		MenuItem newItem = null;
		for (int i = 0; i < MenuItems.size(); i++) {
			if (name.equals(MenuItems.get(i).getName())) {
				return null;
			}
		}
		switch (itemType) {
		case MAIN:
			newItem = new MainDish(name, price, itemType, description);
			break;
		case SIDE:
			newItem = new Side(name, price, itemType, description);
			break;
		case DRINK:
			newItem = new Drink(name, price, itemType, description);
			break;
		case DESSERT:
			newItem = new Dessert(name, price, itemType, description);
			break;
		case PROMO:
			newItem = new PromoSet(name, price, itemType, description);
		default:
			break;
		}

		int newID = generateID(itemType);
		newItem.setID(newID);
		MenuItems.add(newItem);
		return newItem;
	}



	// SETTLED
	private int generateID(ItemType itemType) {
		int newID = 0;
		switch (itemType) {
		case MAIN:
			newID = maxMainID;
			maxMainID++;
			break;
		case SIDE:
			newID = maxSideID;
			maxSideID++;
			break;
		case DRINK:
			newID = maxDrinkID;
			maxDrinkID++;
			break;
		case DESSERT:
			newID = maxDessertID;
			maxDessertID++;
			break;
		case PROMO:
			newID = maxPromoID;
			maxPromoID++;
			break;
		}
		return newID;
	}



	//check if item exist
	public int checkExist(int itemID){
		MenuItem item = null;
		for (int i = 0; i < MenuItems.size(); i++) {
			item = MenuItems.get(i);
			if (item.getID() == itemID) {
				break;
			}
			// if still havent found, means dont exist
			if (i == MenuItems.size() - 1)
				return 0;
		}
		return 1;
	}

	//SETTLED
	public String removeItem(int itemID) {
		MenuItem item = null;
		int index = 0;
		for (int i = 0; i < MenuItems.size(); i++) {
			item = MenuItems.get(i);
			if (item.getID() == itemID) {
				index = i;
				break;
			}
			// if still havent found, means dont exist
			if (i == MenuItems.size() - 1)
				return null;
		}
		MenuItems.remove(index);
		return item.getName();
	}

	

	// SETTLED
	public void printMenu() {
		System.out.println("============================================================  MENU ============================================================ ");
		doSelectionSort(MenuItems);
		for (int i = 0; i < MenuItems.size(); i++) {
			MenuItem eachItem = MenuItems.get(i);
			String format = "%1$-7s | %2$3s | %3$-30s | %4$4s | %5$-100s\n";
			System.out.format(format, eachItem.getItemType(), eachItem.getID(), eachItem.getName(), eachItem.getPrice(), eachItem.getDescription());
		}
		System.out.println("===============================================================================================================================");
	}


	// SETTLED
	public static void doSelectionSort(ArrayList<MenuItem> arr) {
		for (int i = 0; i < arr.size(); i++) {
			// find position of smallest itemID between (i + 1)th element and last element
			int pos = i;
			for (int j = i; j < arr.size(); j++) {
				if (arr.get(j).getID() < arr.get(pos).getID())
					pos = j;
			}
			// Swap min (smallest num) to current position on array
			MenuItem min = arr.get(pos);
			arr.set(pos, arr.get(i));
			arr.set(i, min);
		}
	}




	public MenuItem getItem(int itemID) {
		MenuItem item = null;
		for (int i = 0; i < MenuItems.size(); i++) {
			item = MenuItems.get(i);
			if (item.getID() == itemID) {
				break;
			}
		}
		return item;
	}

	public String getName(int id) {
		MenuItem item = getItem(id);
		return item.getName();
	}


	//SETTLED
	public void updateName(int iD, String newname) {
		MenuItem item = getItem(iD);
		item.setName(newname);

	}


	//SETTLED
	public void updatePrice(int iD, double price) {
		MenuItem item = getItem(iD);
		item.setPrice(price);

	}


	//SETTLED
	public void updateDescription(int iD, String description) {
		MenuItem item = getItem(iD);
		item.setDescription(description);
	}

}
