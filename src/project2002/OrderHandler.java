/**
 * Represent the handler that handles all the orders in the restaurant
 */
package project2002;

import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import project2002.Restaurant.handlerType;

public class OrderHandler extends Handler {
	/**
	 * contains an arraylist of orders that are in the restaurant at the current
	 * moment
	 */
	private ArrayList<Order> Orders = new ArrayList<Order>();
	/**
	 * A order counter to count the amount of order and provide each order with a
	 * unique order identification
	 */
	private int OrderCounter = 1;

	/**
	 * to construct this handler in the manager
	 */
	public OrderHandler() {
		type = handlerType.ORDER;
	}

	/**
	 * To create an order in the restaurant
	 * 
	 * @param TableID the Table identification of the particular order
	 * @param staff   the staff of the particular order
	 * @return the order identification of this order
	 */
	public int createOrder(String TableID, Staff staff) {
		Order temp = new Order(staff, TableID, OrderCounter);
		Orders.add(temp);
		OrderCounter++;
		return OrderCounter - 1;
	}

	/**
	 * To print a particular order where it shows the quantity, MenuItem and the
	 * respective menu item id
	 * 
	 * @param OrderID the particular order, order identification
	 */
	public void printOrder(int OrderID) {
		int temp = -1;
		for (int j = 0; j < Orders.size(); j++) {
			if (Orders.get(j).getOrderID() == OrderID) {
				temp = j;
				break;
			}
		}
		if (temp == -1) {
			System.out.println("OrderID doesnt exist");
			return;
		} else {
			Orders.get(temp).PrintOrder();
		}
	}

	/**
	 * To add an item to a specific order
	 * 
	 * @param OrderID the Order identification of the order you want to add
	 * @param item    the menu item you want to add to the order
	 * @param Qty     the quantity of the menuitem you want to add
	 * @return a boolean value to show whether the addition was successful or not
	 */
	public boolean AddItem(int OrderID, MenuItem item, int Qty) {
		int temp = -1;
		for (int j = 0; j < Orders.size(); j++) {
			if (Orders.get(j).getOrderID() == OrderID) {
				temp = j;
				break;
			}
		}
		if (temp == -1) {
			return false;
		} else {
			Orders.get(temp).AddItems(item, Qty);
			return true;
		}
	}

	/**
	 * To remove an menu Item from an order
	 * 
	 * @param OrderID the Order identification of the order you want to remove from
	 * @param item    the menu item you want to remove from that order
	 * @param Qty     the quantity of the menu item you want to remove
	 * @return
	 */
	public boolean RemoveItem(int OrderID, MenuItem item, int Qty) {
		int temp = -1;
		for (int j = 0; j < Orders.size(); j++) {
			if (Orders.get(j).getOrderID() == OrderID) {
				temp = j;
				break;
			}
		}
		if (temp == -1) {
			return false;
		} else {
			return Orders.get(temp).RemoveItems(item, Qty);
		}
	}

	/**
	 * To print the invoice of a certain order
	 * 
	 * @param OrderID  the Order identification of the order you would like to print
	 *                 the invoice
	 * @param discount the amount of discount of the order
	 * @return the Order which invoice got printed
	 */
	public Order printInvoice(int OrderID, double discount) {
		int temp = -1;
		for (int j = 0; j < Orders.size(); j++) {
			if (Orders.get(j).getOrderID() == OrderID) {
				temp = j;
				break;
			}
		}
		if (temp == -1) {
			System.out.println("This is invalid OrderID");
			return null;
		}

		Orders.get(temp).CalculateTotalPriceWithoutTax(discount);
		Orders.get(temp).CalculateServiceTax();
		Orders.get(temp).CalculateGST();
		Orders.get(temp).CalculateTotalPrice();
		System.out.println("=========================================");
		System.out.println("Attended to by: " + Orders.get(temp).getStaff().getName());
		System.out.println("Table: " + Orders.get(temp).getTableID());
		System.out.println("=========================================");
		System.out.println("Orders: ");
		HashMap<MenuItem, Integer> Items = Orders.get(temp).getItems();
		for (MenuItem i : Items.keySet()) {
			String line = String.format("%2d %-30s $%.2f", Items.get(i), i.getName(), i.getPrice() * Items.get(i));
			System.out.println(line);
		}
		System.out.println("=========================================");
		String line = String.format("   %30s $%.2f", "SubTotal:", Orders.get(temp).getTotalPriceWithoutTax());
		System.out.println(line);
		line = String.format("   %30s -$%.2f", "Discount:", Orders.get(temp).getDiscount());
		System.out.println(line);
		line = String.format("   %30s $%.2f", "GST:", Orders.get(temp).getGST());
		System.out.println(line);
		line = String.format("   %30s $%.2f", "Service Charge:", Orders.get(temp).getServiceTax());
		System.out.println(line);
		line = String.format("   %30s $%.2f", "Total:", Orders.get(temp).getTotalPriceWithTax());
		System.out.println(line);
		System.out.println("=========================================");
		System.out.println("Thank you for dining with us!");
		File receipt = new File("receipt.txt");
		try {
			FileWriter myWriter = new FileWriter("receipt.txt");
			myWriter.write("=========================================\n");
			myWriter.write("Attended to by: " + Orders.get(temp).getStaff().getName() + "\n");
			myWriter.write("Table: " + Orders.get(temp).getTableID() + "\n");
			myWriter.write("=========================================\n");
			myWriter.write("Orders: \n");
			for (MenuItem i : Items.keySet()) {
				line = String.format("%2d %-30s $%.2f\n", Items.get(i), i.getName(), i.getPrice() * Items.get(i));
				myWriter.write(line);
			}
			myWriter.write("=========================================\n");
			line = String.format("   %30s $%.2f\n", "SubTotal:", Orders.get(temp).getTotalPriceWithoutTax());
			myWriter.write(line);
			line = String.format("   %30s -$%.2f\n", "Discount:", Orders.get(temp).getDiscount());
			myWriter.write(line);
			line = String.format("   %30s $%.2f\n", "GST:", Orders.get(temp).getGST());
			myWriter.write(line);
			line = String.format("   %30s $%.2f\n", "Service Charge:", Orders.get(temp).getServiceTax());
			myWriter.write(line);
			line = String.format("   %30s $%.2f\n", "Total:", Orders.get(temp).getTotalPriceWithTax());
			myWriter.write(line);
			myWriter.write("=========================================\n");
			myWriter.write("Thank you for dining with us!");
			myWriter.close();

		} catch (IOException e) {
			System.out.println("An error occurred.");
		}

		Order j = Orders.get(temp);
		Orders.remove(temp);
		return j;
	}

}
