package project2002;

import java.util.ArrayList;

import project2002.MenuUI.ItemType;
import project2002.Restaurant.handlerType;

public class MenuHandler extends Handler {
	public MenuHandler() {
		type = handlerType.MENU;
	}

	ArrayList<MenuItem> MenuItems = new ArrayList<MenuItem>();
	private int maxMainID = 100;
	private int maxSideID = 200;
	private int maxDrinkID = 300;
	private int maxDessertID = 400;
	private int maxPromoID = 500;

	// no need feed itemID -> auto generated base on what kind of item you want to
	// add
	// if got duplicate name return 0
	public int addMenuItem(String name, double price, ItemType itemType, String description) {
		MenuItem newItem = null;
		for (int i = 0; i < MenuItems.size(); i++) {
			if (name == MenuItems.get(i).getName()) {
				return 0;
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
		return 1;
	}

	// generate new ID for different food type
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

	// update fooditem by inputting its ID, if successful return 1 else 0
	// public int updateItem(int itemID){
	// MenuItem item = null;
	// for(int i = 0; i < MenuItems.size(); i++) {
	// item = MenuItems.get(i);
	// if(item.getID() == itemID) {
	// //go to menuUI to ask for changes
	// return 1;
	// }
	// }
	// return 0;
	// }

	// check if item exist
	public int checkExist(int itemID) {
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

	public int removeItem(int itemID) {
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
				return 0;
		}
		MenuItems.remove(index);
		return 1;
	}

	// print entire menu
	public void printMenu() {
		System.out.println("============================== MENU ==============================");
		doSelectionSort(MenuItems);
		for (int i = 0; i < MenuItems.size(); i++) {
			MenuItem eachItem = MenuItems.get(i);
			System.out.println(eachItem.getID() + " | " + eachItem.getItemType() + " | " + eachItem.getName() + " | $"
					+ eachItem.getPrice() + " | " + eachItem.getDescription());
		}
		System.out.println("==================================================================");
	}

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

	public void updateName(int iD, String newname) {
		MenuItem item = getItem(iD);
		item.setName(newname);

	}

	public void updatePrice(int iD, double price) {
		MenuItem item = getItem(iD);
		item.setPrice(price);

	}

	public void updateDescription(int iD, String description) {
		MenuItem item = getItem(iD);
		item.setDescription(description);
	}

}
