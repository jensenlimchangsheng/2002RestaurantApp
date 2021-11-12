package project2002;

import java.util.Scanner;

import project2002.Restaurant.UIType;
/**
Represents each menu item from the restaurant.
A menu item can consist of MAIN, SIDE, DRINK, DESSERT, PROMO.
@author Jiam Tzi Yu
@version 1.0
@since 2021-11-09
*/

public class MenuUI extends UI {
	/**
	 * The menu manager for this menu UI.
	 */
	private MenuManager menuManager;

	/**
	 * Enum ItemType Class
	 */
	public enum ItemType {
		MAIN, DRINK, DESSERT, SIDE, PROMO;

		/**
		 * Converts enum ItemType to string type
		 * @return string version of ItemType
		 */
		public String toString() {
			switch (this) {
			case MAIN:
				return "MAIN";
			case DRINK:
				return "DRINK";
			case DESSERT:
				return "DESSERT";
			case SIDE:
				return "SIDE";
			case PROMO:
				return "PROMO";
			}
			return null;
		}
	}

	/**
	 * Creates a menuUI with scanner.
	 * @param scanner
	 */
	public MenuUI(Scanner scanner) {
		super(scanner);
		this.type = UIType.MENU;
	}


	/**
	 * Printing menu options.
	 */
	public void printOptions() {
		int choice = 0;
		do {
			System.out.printf("-------------Menu Options------------\n" + "Please select one of this 8 options: \n"
					+ "1.	Print Menu\n" + "2.	Add Menu Item\n" + "3.	Remove Menu Item\n" + "4.	Update Menu Item\n"
					+ "5.	Add Promo Set\n" + "6.	Remove Promo Set\n" + "7.	Update Promo Set\n" + "8.	Quit\n");
			choice = this.getInt("Please enter your choice: ");

			switch (choice) {

				case 1:
					menuManager.printMenu();
					break;

				case 2:
					String name2 = this.getString("Please enter name of item: ");
					int price2 = this.getInt("Please enter price of item: ");
					String description2 = this.getString("Please enter description of item: ");
					ItemType itemtype2 = this.getItemType();
					MenuItem item2 = menuManager.addMenuItem(name2, price2, description2, itemtype2);

					if (item2 == null) {
						System.out.println(name2 + " could not be added/ duplicate item exist...");
					} else {
						System.out.println(item2.getName() + " is successfully added.");
					}
					break;

				case 3:
					String name3 = menuManager.removeMenuItem();
					if (name3 == null) {
						System.out.println("Item does not exist in the menu");
					} else {
						System.out.println(name3 + " successfully removed");
					}
					break;

				case 4:
					String name4 = menuManager.updateMenuItem();
					if (name4 == null) {
						System.out.println("Item does not exist in the menu");
					} else {
						System.out.println(name4 + " successfully updated");
					}
					break;

				case 5:
					String name5 = this.getString("Please enter name of promoset: ");
					int price5 = this.getInt("Please enter price of promoset: ");
					String description5 = this.getString("Please enter description of promoset: ");
					ItemType itemtype5 = ItemType.PROMO;
					MenuItem item5 = menuManager.addPromoSet(name5, price5, description5, itemtype5); 
					if (item5 == null) {
						System.out.println("Promo could not be added/ duplicate item exist...");
						
					} else {
						System.out.println(item5.getName() + " is successfully added.");
					}
					break;

				case 6:
					String name6 = menuManager.removePromoSet();
					if (name6 == null) {
						System.out.println("Promo does not exist in the menu");
					} else {
						System.out.println(name6 + " successfully removed");
					}
					break;

				case 7:
					String name7 = menuManager.updatePromoSet();
					if (name7 == null) {
						System.out.println("Promo does not exist in the menu");
					} else {
						System.out.println(name7 + " successfully updated");
					}
					break;

				case 8:
					break;

				default:
					System.out.println("");
					
			}
		} while (choice != 8);
		menuManager.menu.outputMenu();
	}

	/**
	 * Gets item type based on user input choice
	 * @return itemType 
	 */
	public ItemType getItemType() {
		int index = 1;
		int choice = 0;
		System.out.println("Available item types:");
		for (ItemType i : ItemType.values()) {
			System.out.printf("%d.	%s\n", index, i.toString());
			index++;
			if(index == 5) break; //hide promo
		}
		choice = this.getInt("Please enter choice: ");
		switch(choice){
			case 1:
				return ItemType.MAIN;
			case 2:
				return ItemType.DRINK;
			case 3:
				return ItemType.DESSERT;
			case 4:
				return ItemType.SIDE;
		}
		return null;
	}

	/**
	 * Gets item ID.
	 * @return itemID This item's ID.
	 */
	public int getItemID() {
		int itemID = this.getInt("Please enter the item ID:");
		return itemID;
	}

	/**
	 * Assigns manager to UI;
	 * @param m
	 */
	protected void assignUIManager(Manager m) {
		menuManager = (MenuManager) m;
	}

	/**
	 * UI for updating menu items' attributes
	 * @param name This menu item's name.
	 * @param ID This menu item's ID.
	 */
	public void updateItem(String name, int ID) {
		System.out.println("You have selected " + name + ".");
		System.out.println("Select:\n1. Update Name\n2. Update Price\n3. Update Description\n4. Quit");
		int choice = getInt("Please select an option: ");
		while (choice != 4) {
			switch (choice) {
			case 1:
				String newname = getString("Please enter the new name: ");
				menuManager.updateName(ID, newname);
				System.out.println("Name updated.");
				break;
			case 2:
				double price = getDouble("Please enter the new price: ");
				menuManager.updatePrice(ID, price);
				System.out.println("Price updated.");
				break;
			case 3:
				String description = getString("Please enter the new description: ");
				menuManager.updateDescription(ID, description);
				System.out.println("Description updated.");
				break;
			case 4:
				break;
			default:
				System.out.println("Please select again.");
				break;
			}
			choice = getInt("Please select an option: ");
		}
		System.out.println("Update Complete.");
		
	}
}

