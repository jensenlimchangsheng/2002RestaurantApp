package project2002;

import java.time.LocalDateTime;
import java.util.*;


/**
 * Represent a single order in the restaurant
 * @author Jensen Lim
 * @version 1.0
 * @since 2021-11-01
 */
public class Order {
	/** The unique OrderID for each order */
	private int orderID;
	/** Represents the staff attending to the order */
	private Staff staff;
	/** Stores the TableID of the order */
	private String tableID;
	/** the time that the order was created */
	private LocalDateTime time;
	/** storing the MenuItems and the respective quatity in the order */
	private HashMap<MenuItem, Integer> items = new HashMap<MenuItem, Integer>();
	/**To store the total net price of the order */
	private double totalPriceWithoutTax = 0;
	/**The calculated total price including GST and Service Tax */
	private double totalPriceWithTax = 0;
	/**The GST collected for this order */
	private double GST = 0;
	/**The service tax for this order */
	private double serviceTax = 0;
	/**Amount of dicsount in for this order */
	private double discount = 0;

	/**
	 * Creates a new order with a given staff, table Id and order Id
	 * @param staff The staff handling this respective Order
	 * @param TableID The table identification of this respective order
	 * @param OrderID the order identification of this respective order
	 */
	public Order(Staff staff, String TableID, int OrderID) {
		this.staff = staff;
		this.tableID = TableID;
		this.orderID = OrderID;
		this.time = LocalDateTime.now();
	}

	/**
	 * Gets the total price without tax of this order to show in the receipt
	 * @return this order's total price without tax
	 */
	public double getTotalPriceWithoutTax() {
		return this.totalPriceWithoutTax;
	}
	/**
	 * Gets the total price with tax of this order to show in the receipt
	 * @return this order's total price with tax
	 */
	public double getTotalPriceWithTax() {
		return this.totalPriceWithTax;
	}
	/**
	 * Gets the total GST of this order to show in the receipt
	 * @return this order's GST
	 */
	public double getGST() {
		return this.GST;
	}
	/**
	 * gets the service tax of this order to show in the receipt
	 * @return this order's service tax
	 */
	public double getServiceTax() {
		return this.serviceTax;
	}
	/**
	 * gets the time of this order when the order is made so that it can be organised 
	 * for the salesreport when the receipt is preinted
	 * @return	the time the order was created
	 */
	public LocalDateTime getDate() {
		return this.time;
	}
	/**
	 * gets the staff who attended this order
	 * @return the order's staff
	 */
	public Staff getStaff() {
		return this.staff;
	}
	/**
	 * gets the table identification of this order
	 * @return the order's table id
	 */
	public String getTableID() {
		return this.tableID;
	}
	/**
	 * gets the order identification of this order
	 * @return the order's id
	 */
	public int getOrderID() {
		return this.orderID;
	}
	/**
	 * get the menu Item and quantity of this order
	 * @return the hashmap that contain the menutems and the respective quantity
	 */
	public HashMap<MenuItem, Integer> getItems() {
		return this.items;
	}
	/**
	 * get the discount of this order
	 * @return the order's discount
	 */
	public double getDiscount() {
		return this.discount;
	}

	

	/**
	 * Too add menu item into the order when a customer wants to edit their order
	 * @param item the menu item that you want to add into this respective order
	 * @param Qty the amount of this menu item you would like to add into the order
	 */
	public void addItems(MenuItem item, int Qty) {
		if (items.get(item) == null) {
			items.put(item, Qty);
		} else {
			items.put(item, items.get(item) + Qty);
		}
	}

	/**
	 * to remove a menu item from the order when a customer wants to edit their order
	 * @param item the menu item that you want to remove from this respective order
	 * @param qty the amount of this menu item you would like to remove from this order
	 * @return a boolean which shows whether the item was successfully removed the item or not
	 */
	public boolean removeItems(MenuItem item, int qty) {
		if (items.get(item) == null || items.get(item) < qty) {
			return false;
		} else if (items.get(item) < qty || items.get(item) == qty) {
			items.remove(item);
			return true;
		} else {
			items.put(item, items.get(item) - qty);
			return true;
		}
	}

	/**
	 * To print this order to showing the Order ID table ID and the quantity of menu items and their respective item ID in this order
	 */
	public void printOrder() {
		System.out.println("==============================");
		System.out.print("Order ID: ");
		System.out.println(this.getOrderID());
		System.out.print("Table ID: ");
		System.out.println(this.getTableID());
		System.out.println("Qty || MenuItem || Item ID");
		for (MenuItem i : items.keySet()) {
			System.out.println(items.get(i) + " " + i.getName() + " " + i.getID());
		}
		System.out.println("==============================");
	}

	/**
	 * To calculate the total price of this order including discounts without tax to be shown in the receipt
	 * @param discount the amount of discount that is included in this 
	 */
	public void calculateTotalPriceWithoutTax(double discount) {
		double price = 0;
		for (MenuItem i : items.keySet()) {
			price += i.getPrice() * items.get(i);
		}
		this.discount = price * discount;
		this.totalPriceWithoutTax = price - this.discount;
	}
	/**
	 * Calculate the service tax for this order to be shown in the receipt
	 */
	public void calculateServiceTax() {
		double tax = 0;
		tax += this.totalPriceWithoutTax * 0.07;
		this.serviceTax = tax;
	}
	/**
	 * Calculate the GST for this order to be shown in the receipt
	 */
	public void calculateGST() {
		double tax = 0;
		tax += this.totalPriceWithoutTax * 0.1;
		this.GST = tax;
	}
	/**
	 * calculate the total price of this order including tax and discounts to be shown in the receipt and factor in all the discounts 
	 * of this order
	 */
	public void calculateTotalPrice() {
		double price = this.GST + this.serviceTax + this.totalPriceWithoutTax;
		this.totalPriceWithTax = price;
	}
}
