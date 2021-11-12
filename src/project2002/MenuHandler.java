package project2002;

import java.util.ArrayList;
import project2002.MenuUI.ItemType;
import project2002.Restaurant.handlerType;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Represents Menu Handler from the restaurant.
 * 
 * @author Jiam Tzi Yu
 * @version 1.0
 * @since 2021-11-09
 */

public class MenuHandler extends Handler {

	/**
	 * Initializes a menu with preloaded menu items
	 */
	public MenuHandler() {
		type = handlerType.MENU;
		addMenuItem("The Feather Blade Steak", 21, "Signature", MenuUI.ItemType.MAIN);
		addMenuItem("Potatoes", 7, "Hand Smashed, Deep Fried in Beef Fat", MenuUI.ItemType.SIDE);
		addMenuItem("Madagascan Vallina Ice Cream", 8, "Sweet Devil", MenuUI.ItemType.DESSERT);
		addMenuItem("Death on Weekends", 19, "Prosecco with a splash of green fairy", MenuUI.ItemType.DRINK);
		addMenuItem("Lunch Special", 45,
				"The Feather Blade Steak, Potatoes, Madagascan Vallina Ice Cream, Death on Weekends",
				MenuUI.ItemType.PROMO);
	}

	/**
	 * The array list of menu items for this menu handler.
	 */
	ArrayList<MenuItem> MenuItems = new ArrayList<MenuItem>();
	/**
	 * The starting ID for MAIN for this menu handler.
	 */
	private int maxMainID = 100;
	/**
	 * The starting ID for SIDE for this menu handler.
	 */
	private int maxSideID = 200;
	/**
	 * The starting ID for DRINK for this menu handler.
	 */
	private int maxDrinkID = 300;
	/**
	 * The starting ID for DESSERT for this menu handler.
	 */
	private int maxDessertID = 400;
	/**
	 * The starting ID for PROMO for this menu handler.
	 */
	private int maxPromoID = 500;

	/**
	 * Creates a new menu item with name, price, type and description.
	 * 
	 * @param name        This menu item's name.
	 * @param price       This menu item's price.
	 * @param itemType    This menu item's type.
	 * @param description This menu item's description.
	 * @return this menu item
	 */
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

	/**
	 * Generates a unique ID for the menu item depending on its type.
	 * 
	 * @param itemType This menu item's type.
	 * @return this menu item unique ID.
	 */
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

	/**
	 * Checks if the menu item already exists using its ID.
	 * 
	 * @param itemID the menu item's ID.
	 * @return 1 or 0.
	 */
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

	/**
	 * Removes menu item using its ID.
	 * 
	 * @param itemID the menu item's ID.
	 * @return this menu item's name if exist else null.
	 */
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

	/**
	 * Prints the array of menu items.
	 */
	public void printMenu() {
		System.out.println(
				"============================================================  MENU ============================================================ ");
		String format = "%1$-7s | %2$3s | %3$-30s | %4$6s | %5$-100s\n";
		System.out.format(format, "Type", "ID", "Name", "Price", "Description");
		doSelectionSort(MenuItems);
		for (int i = 0; i < MenuItems.size(); i++) {
			MenuItem eachItem = MenuItems.get(i);
			// String format = "%1$-7s | %2$3s | %3$-30s | %4$4s | %5$-100s\n";
			System.out.format(format, eachItem.getItemType(), eachItem.getID(), eachItem.getName(), eachItem.getPrice(),
					eachItem.getDescription());
		}
		System.out.println(
				"===============================================================================================================================");
		;
	}

	/**
	 * Sorts the array of menu items based on the IDs of the menu items.
	 * 
	 * @param arr The array of menu items.
	 */
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

	/**
	 * Gets the menu item using its ID.
	 * 
	 * @param itemID the menu item's ID.
	 * @return menu item.
	 */
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

	/**
	 * Gets the name of the menu item using its ID.
	 * 
	 * @param id the menu item's ID.
	 * @return menu item name.
	 */
	public String getName(int id) {
		MenuItem item = getItem(id);
		return item.getName();
	}

	/**
	 * Updates the name of the menu item using its ID, and new name.
	 * 
	 * @param iD      the menu item's ID.
	 * @param newname the menu item's new name.
	 */
	public void updateName(int iD, String newname) {
		MenuItem item = getItem(iD);
		item.setName(newname);

	}

	/**
	 * Updates the price of the menu item using its ID and the new price.
	 * 
	 * @param iD    the menu item's ID.
	 * @param price the menu item's new price.
	 */
	public void updatePrice(int iD, double price) {
		MenuItem item = getItem(iD);
		item.setPrice(price);

	}

	/**
	 * Updates the description of the menu item using its ID.
	 * 
	 * @param iD          the menu item's ID.
	 * @param description the menu item's new description
	 */
	public void updateDescription(int iD, String description) {
		MenuItem item = getItem(iD);
		item.setDescription(description);
	}

	/**
	 * Outputs the menu as a txt file
	 */
	public void outputMenu() {
		try {
			FileWriter myWriter = new FileWriter("/Users/jiamtziyu/Desktop/menu.txt");
			myWriter.write(
					"============================================================  MENU ============================================================\n");
			String line = String.format("%1$-7s | %2$3s | %3$-30s | %4$6s | %5$-100s\n", "Type", "ID", "Name", "Price",
					"Description");
			myWriter.write(line);
			doSelectionSort(MenuItems);
			for (int i = 0; i < MenuItems.size(); i++) {
				MenuItem eachItem = MenuItems.get(i);
				line = String.format("%1$-7s | %2$3s | %3$-30s | %4$6s | %5$-100s\n", eachItem.getItemType(),
						eachItem.getID(), eachItem.getName(), eachItem.getPrice(), eachItem.getDescription());
				myWriter.write(line);
			}
			myWriter.write(
					"===============================================================================================================================");
			myWriter.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
		}
		System.out.println("Menu saved as txt file.");
	}

}
