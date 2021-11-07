package project2002;

import java.util.Scanner;

import project2002.Restaurant.UIType;

public class MenuUI extends UI {
	public enum ItemType {
		MAIN, DRINK, DESSERT, SIDE, PROMO;

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

	private MenuManager menuManager;

	public MenuUI(Scanner scanner) {
		super(scanner);
		this.type = UIType.MENU;
	}

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
				String name = this.getString("Please enter name of item: ");
				int price = this.getInt("Please enter price of item: ");
				String description = this.getString("Please enter description of item: ");
				ItemType i = this.getItemType();
				if (menuManager.addMenuItem(name, price, description, i) == 1) {
					System.out.println("Item is successfully added.");
				} else {
					System.out.println("Item could not be added/ duplicate item exist...");
				}
				break;
			case 3:
				if (menuManager.removeMenuItem() == 0) {
					System.out.println("Item does not exist in the menu");
				} else
					System.out.println("Item successfully removed");
				break;
			case 4:
				if (menuManager.updateMenuItem() == 0) {
					System.out.println("Item does not exist in the menu");
				} else
					System.out.println("Item successfully updated");
				break;
			case 5:
				String name1 = this.getString("Please enter name of promoset: ");
				int price1 = this.getInt("Please enter price of promoset: ");
				String description1 = this.getString("Please enter description of promoset: ");
				ItemType i1 = ItemType.PROMO;
				if (menuManager.addPromoSet(name1, price1, description1, i1) == 1) {
					System.out.println("Promo is successfully added.");
				} else {
					System.out.println("Promo could not be added/ duplicate item exist...");
				}
				break;
			case 6:
				if (menuManager.removePromoSet() == 0) {
					System.out.println("Promo does not exist in the menu");
				} else
					System.out.println("Promo successfully removed");
				break;
			case 7:
				if (menuManager.updatePromoSet() == 0) {
					System.out.println("Promo does not exist in the menu");
				} else
					System.out.println("Promo successfully updated");
				break;
			case 8:
				break;
			default:
				System.out.println("");
			}
		} while (choice != 8);

	}

	public ItemType getItemType() {
		int index = 1;
		String choice = "";
		System.out.println("Available item types:");
		for (ItemType i : ItemType.values()) {
			System.out.printf("%d. %s\n", index, i.toString());
			index++;
		}
		choice = this.getString("Please enter menu item type: ");
		for (ItemType i : ItemType.values()) {
			if (choice.equalsIgnoreCase(i.toString())) {
				return i;
			}
		}
		return null;
	}

	public int getItemID() {
		int itemID;
		System.out.printf("Please enter the item ID: \n");
		itemID = scan.nextInt();
		return itemID;
	}

	@Override
	protected void assignUIManager(Manager m) {
		menuManager = (MenuManager) m;
	}

	public void updateItem(String name, int ID) {
		System.out.println("You have selected " + name + ".");
		System.out.println("Select:\n1. Update Name\n2. Update Price\n3. Update Description\nInsert -1 when done.");
		int choice = getInt("Please select an option: ");
		while (choice != -1) {
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
			default:
				System.out.println("Please select again.");
				break;
			}
			choice = getInt("Please select an option: ");
		}
		System.out.println("Update Complete.");
	}
}
