package project2002;

import java.time.LocalDateTime;
import java.util.*;

public class Order {
	private int OrderID;
	/** Represents the staff attending to the order */
	private Staff staff;
	/** Stores the TableID of the order */
	private String TableID;
	/** the time that the order was created */
	private LocalDateTime Time;
	/** storing the MenuItems and the respective quatity in the order */
	private HashMap<MenuItem, Integer> Items = new HashMap<MenuItem, Integer>();
	/**To store the total net price of the order */
	private double TotalPriceWithoutTax = 0;
	private double TotalPriceWithTax = 0;
	private double GST = 0;
	private double ServiceTax = 0;
	private double Discount = 0;

	// constructor
	public Order(Staff staff, String TableID, int OrderID) {
		this.staff = staff;
		this.TableID = TableID;
		this.OrderID = OrderID;
		this.Time = LocalDateTime.now();
	}

	// getter
	public double getTotalPriceWithoutTax() {
		return this.TotalPriceWithoutTax;
	}

	public double getTotalPriceWithTax() {
		return this.TotalPriceWithTax;
	}

	public double getGST() {
		return this.GST;
	}

	public double getServiceTax() {
		return this.ServiceTax;
	}

	public LocalDateTime getDate() {
		return this.Time;
	}

	public Staff getStaff() {
		return this.staff;
	}

	public String getTableID() {
		return this.TableID;
	}

	public int getOrderID() {
		return this.OrderID;
	}

	public HashMap<MenuItem, Integer> getItems() {
		return this.Items;
	}

	public double getDiscount() {
		return this.Discount;
	}

	// operations

	// adding items
	public void AddItems(MenuItem item, int Qty) {
		if (Items.get(item) == null) {
			Items.put(item, Qty);
		} else {
			Items.put(item, Items.get(item) + Qty);
		}
	}

	// removing items
	public boolean RemoveItems(MenuItem item, int Qty) {
		if (Items.get(item) == null || Items.get(item) < Qty) {
			return false;
		} else if (Items.get(item) < Qty || Items.get(item) == Qty) {
			Items.remove(item);
			return true;
		} else {
			Items.put(item, Items.get(item) - Qty);
			return true;
		}
	}

	// printing order
	public void PrintOrder() {
		System.out.println("==============================");
		System.out.print("Order ID: ");
		System.out.println(this.getOrderID());
		System.out.print("Table ID: ");
		System.out.println(this.getTableID());
		System.out.println("Qty || MenuItem || Item ID");
		for (MenuItem i : Items.keySet()) {
			System.out.println(Items.get(i) + " " + i.getName() + " " + i.getID());
		}
		System.out.println("==============================");
	}

	// calculating the total price
	public void CalculateTotalPriceWithoutTax(double discount) {
		double price = 0;
		for (MenuItem i : Items.keySet()) {
			price += i.getPrice() * Items.get(i);
		}
		this.Discount = price * discount;
		this.TotalPriceWithoutTax = price - this.Discount;
	}

	public void CalculateServiceTax() {
		double tax = 0;
		tax += this.TotalPriceWithoutTax * 0.07;
		this.ServiceTax = tax;
	}

	public void CalculateGST() {
		double tax = 0;
		tax += this.TotalPriceWithoutTax * 0.1;
		this.GST = tax;
	}

	public void CalculateTotalPrice() {
		double price = this.GST + this.ServiceTax + this.TotalPriceWithoutTax;
		this.TotalPriceWithTax = price;
	}
}
