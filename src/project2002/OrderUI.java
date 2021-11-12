/**
 * The user interface regarding any order issues in the restaurant
 */
package project2002;

import java.util.Scanner;

import project2002.Restaurant.UIType;

public class OrderUI extends UI {
	/**
	 * to implement the order manager which controls the restaurant orders
	 */
	private OrderManager orderManager;

	/**
	 * to create a constructor to input a scanner into this UI
	 * 
	 * @param scanner the scanner that is input into this UI
	 */
	public OrderUI(Scanner scanner) {
		super(scanner);
		this.type = UIType.ORDER;
	}

	/**
	 * to print the options that are regarding to the orders in the restaurant
	 */
	public void printOptions() {
		int choice = 0;
		do {
			System.out.printf("-------------Order Options-----------\n" + "Please select one of this 4 options: \n"
					+ "1.	Add Order Item\n" + "2.	Remove Order Item\n" + "3.	View Order\n" + "4.	Back\n");
			choice = this.getInt("Please enter your choice:");
			switch (choice) {
			case 1:
				int id = this.getInt("Please enter orderID: ");
				boolean temp = true;
				while (temp) {
					int result = orderManager.addOrderItem(id);
					if (result == -1) {
						System.out.println("Menu Item does not exist");
					} else if (result == 0)
						System.out.printf("Menu Item could not be added to the order %d.\n", id);
					else if (result == 1)
						System.out.printf("Menu Item was added to the order %d.\n", id);
					System.out.println("Is there more items you would like to add? Y/N");
					String in = scan.nextLine();
					int option = -1;
					while (option == -1) {
						switch (in) {
						case "Y":
						case "y":
							option = 1;
							break;
						case "N":
						case "n":
							option = 1;
							temp = false;
							break;
						default:
							System.out.println("invalid input. Please try again");
							System.out.println("Is there more items you would like to add? Y/N");
							in = scan.nextLine();
						}
					}
				}
				break;
			case 2:
				id = this.getInt("Please enter orderID: ");
				temp = true;
				while (temp) {
					boolean check = orderManager.removeOrderItem(id);
					if (check == true) {
						System.out.println("The item have been succesfully deleted");
					}
					if (check == false) {
						System.out.println("The quantity you wish to delete is more than what was ordered");
					}
					System.out.println("Is there more items you wish to delete Y/N");
					String in = scan.nextLine();
					int option = -1;
					while (option == -1) {
						switch (in) {
						case "Y":
						case "y":
							option = 1;
							break;
						case "N":
						case "n":
							option = 1;
							temp = false;
							break;
						default:
							System.out.println("invalid input. Please try again");
							System.out.println("Is there more items you would like to add? Y/N");
							in = scan.nextLine();
						}
					}

				}
				break;
			case 3:
				id = this.getInt("Please enter orderID: ");
				orderManager.viewOrder(id);
				break;
			case 4:
				break;
			default:
				System.out.println("Invalid Input.");
			}
		} while (choice != 4);
	}

	/**
	 * To get an item ID
	 * 
	 * @return an integer which is the item ID
	 */
	public int getItemID() {
		int itemID = this.getInt("Please enter the item ID: ");
		return itemID;
	}

	/**
	 * to get the quantity
	 * 
	 * @return an integer which represent the quantity
	 */
	public int getQty() {
		int qty = this.getInt("Please enter the quantity: ");
		return qty;
	}

	/**
	 * assign a UI manager to this UI
	 */
	@Override
	protected void assignUIManager(Manager m) {
		orderManager = (OrderManager) m;
	}

}
