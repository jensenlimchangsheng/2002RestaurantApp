package project2002;

import java.util.Scanner;

import project2002.Restaurant.UIType;

public class CustomerUI extends UI {
	/**
	 * The customer manager for this customer UI.
	 */
	private CustomerManager customerManager;

	/**
	 * Constructor for customer UI.
	 * 
	 * @param scanner
	 * @return customerUI
	 */
	public CustomerUI(Scanner scanner) {
		super(scanner);
		this.type = UIType.CUSTOMER;
	}

	/**
	 * Printing customer options.
	 */
	@Override
	protected void printOptions() {
		int choice = 0;
		do {
			System.out.printf("-------------Customer Options-----------\n" + "Please select one of this 2 options: \n"
					+ "1.	Create Order For New Customer\n" + "2.	Create Order For Reserved Customer\n"
					+ "3.	Quit\n");
			choice = this.getInt("Please enter your choice: ");
			switch (choice) {
			case 1:
				int pax = this.getInt("Please enter number of pax: ");
				String name = this.getString("Please enter staff name: ");
				int id = this.getInt("Please enter staff ID: ");
				String title = this.getString("Please enter staff title: ");
				System.out.printf("The orderID is : %d.\n", customerManager.newCustomerOrder(pax, name, id, title));
				break;
			case 2:
				pax = this.getInt("Please enter number of pax: ");
				name = this.getString("Please enter staff name: ");
				id = this.getInt("Please enter staff ID: ");
				title = this.getString("Please enter staff title: ");
				String customername = this.getString("Please enter customer name: ");
				int number = this.getInt("Please enter customer phone number: ");
				System.out.printf("The orderID is : %d.\n",
						customerManager.reservedCustomerOrder(pax, name, id, title, customername, number));
				break;
			case 3:
				break;
			default:
				System.out.println("Invalid Input.");
			}
		} while (choice != 3);

	}

	/**
	 * Assigns customer manager to UI;
	 * 
	 * @param m
	 */
	@Override
	protected void assignUIManager(Manager m) {
		customerManager = (CustomerManager) m;
	}

}
