package project2002;

import java.util.InputMismatchException;
import java.util.Scanner;

import project2002.Restaurant.UIType;

public class OrderUI extends UI {

	private OrderManager orderManager;

	public OrderUI(Scanner scanner) {
		super(scanner);
		this.type = UIType.ORDER;
	}

	public void printOptions() {
		int choice = 0;
		do {
			System.out.printf("-------------Order Options-----------\n" + "Please select one of this 4 options: \n"
					+ "1.	Add Order Item\n" + "2.	Remove Order Item\n" + "3.	Print Order Invoice\n" + "4.	Quit");
			try {
				choice = scan.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Invalid Input.");
			}
			switch (choice) {
			case 1:
				System.out.println("Please enter orderID: ");
				int id = scan.nextInt();
				if (orderManager.addOrderItem(id) == -1) {
					System.out.println("Menu Item does not exist");
				} else if (orderManager.addOrderItem(id) == 0)
					System.out.printf("Menu Item could not be added to the order %d.\n", id);
				else if (orderManager.addOrderItem(id) == 1)
					System.out.printf("Menu Item was added to the order %d.\n", id);
				break;
			case 2:
				System.out.println("Please enter orderID: ");
				id = scan.nextInt();
				orderManager.removeOrderItem(id);
				break;
			case 3:
				System.out.println("Please enter orderID: ");
				id = scan.nextInt();
				orderManager.printOrderInvoice(id);
				break;
			case 4:
				break;
			default:
				System.out.println("Invalid Input.");
			}
		} while (choice != 4);
	}

	public int getItemID() {
		int itemID;
		System.out.printf("Please enter the item ID: \n");
		itemID = scan.nextInt();
		return itemID;
	}

	public int getQty() {
		int qty;
		System.out.printf("Please enter the quantity: \n");
		qty = scan.nextInt();
		return qty;
	}

	@Override
	protected void assignUIManager(Manager m) {
		orderManager = (OrderManager) m;
	}

	public Double getDiscount() {
		// TODO Auto-generated method stub
		return null;
	}

}
