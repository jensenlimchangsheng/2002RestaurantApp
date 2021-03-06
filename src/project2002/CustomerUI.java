package project2002;

import java.util.Scanner;

import project2002.Restaurant.UIType;

/**
 * CustomerUI class for interacting with the user with regards to the customer
 * options. This includes:
 * 
 * 1. Creating order for new customer
 * 
 * 2. Creating order for customer with prior reservation
 * 
 * 3. Closing a customer order
 * 
 * 4. Back
 * 
 * @author Zhi Kai
 * @version 1.0
 * @since 2021-11-09
 */

public class CustomerUI extends UI {
	/**
	 * The customer manager for this customer UI.
	 */
	private CustomerManager customerManager;

	/**
	 * Constructor for customer UI.
	 * 
	 * @param scanner used to retrieve user inputs
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
			System.out.printf("-------------Customer Options-----------\n" + "Please select one of this 3 options: \n"
					+ "1.	Create Order For New Customer\n" + "2.	Create Order For Reserved Customer\n"
					+ "3.	Close Order For Customer\n" + "4.	Back\n");
			choice = this.getInt("Please enter your choice: ");
			switch (choice) {
			case 1:
				int pax = this.getInt("Please enter number of pax: ");
				String name = this.getString("Please enter staff name: ");
				int id = this.getInt("Please enter staff ID: ");
				String title = this.getString("Please enter staff title: ");
				int orderID = customerManager.newCustomerOrder(pax, name, id, title);
				if (orderID == 0)
					System.out.printf("No available tables.\n");
				else
					System.out.printf("The orderID is : %d.\n", orderID);
				break;
			case 2:
				pax = this.getInt("Please enter number of pax: ");
				name = this.getString("Please enter staff name: ");
				id = this.getInt("Please enter staff ID: ");
				title = this.getString("Please enter staff title: ");
				String customername = this.getString("Please enter customer name: ");
				int number = this.getInt("Please enter customer phone number: ");
				orderID = customerManager.reservedCustomerOrder(pax, name, id, title, customername, number);
				if (orderID == 0)
					System.out.printf("No reservation found.\n");
				else
					System.out.printf("The orderID is : %d.\n", orderID);
				break;
			case 3:
				orderID = this.getInt("Please enter order ID: ");
				int temp = customerManager.closeCustomerOrder(orderID);
				if (temp == 0) {
					System.out.printf("The order %d has been closed.\n", orderID);
				}
				break;
			case 4:
				break;
			default:
				System.out.println("Invalid Input.\n");
			}
		} while (choice != 4);

	}

	/**
	 * to get the amount of discount for a certain customer by checking for
	 * membership status as well as additional discounts
	 * 
	 * @return a double which shows the amount of discount of a customer
	 */
	public Double getDiscount() {
		double totaldiscount = 0;
		System.out.println("Is the customer a member? Y/N");
		int temp = -1;
		while (temp == -1) {
			String in = scan.nextLine();
			switch (in) {
			case "Y":
			case "y":
				totaldiscount += 0.1;
				temp = 1;
				break;
			case "N":
			case "n":
				temp = 1;
				break;
			default:
				System.out.println("invalid input. Please try again");
				System.out.println("Is the customer a member? Y/N");
			}
		}
		System.out.println("Is there any more additional discounts? Y/N");
		temp = -1;
		while (temp == -1) {
			String in = scan.nextLine();
			switch (in) {
			case "Y":
			case "y":
				int percent = this.getInt("How many percent is the additional discount");
				totaldiscount += percent * 0.01;
				temp = 1;
				break;
			case "N":
			case "n":
				temp = 1;
				break;
			default:
				System.out.println("invalid input. Please try again");
				System.out.println("Is there any more additional discounts? Y/N");
			}
		}
		return totaldiscount;
	}

	/**
	 * Assigns customer manager to UI;
	 * 
	 * @param m customer manager
	 */
	@Override
	protected void assignUIManager(Manager m) {
		customerManager = (CustomerManager) m;
	}

}
