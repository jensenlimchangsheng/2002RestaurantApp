package project2002;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;

public class Order {
	private Staff staff;
	private String TableID;
	private LocalDateTime Time;
	private HashMap<MenuItem,Integer> Items;
	private double TotalPriceWithoutTax = 0;
	private double TotalPriceWithTax = 0;
	private int OrderID;
	private double GST = 0;
	private double ServiceTax = 0;
	
	//constructor
	public Order(Staff staff,String TableID, int OrderID) {
		this.staff = staff;
		this.TableID = TableID;
		this.OrderID = OrderID;
		this.Time = LocalDateTime.now();
	}
	
	//getter
	public double getTotalPriceWithoutTax() {
		return this.TotalPriceWithoutTax;
	}
	
	public double getTotalPriceWithTax() {
		return this.TotalPriceWithTax;
	}
	
	public double getGST() {
		return this.GST;
	}
	
	public double getServiceTax(){
		return this.ServiceTax;
	}
	
	public LocalDateTime getDate() {
		return this.Time;
	}
	
	public Staff getStaff() {
		return this.staff;
	}
	
	public String getTableID(){
		return this.TableID;
	}
	
	public int getOrderID(){
		return this.OrderID;
	}
	
	public HashMap<MenuItem,Integer> getItems(){
		return this.Items;
	}
	
	//operations
	
	//adding items
	public void AddItems(MenuItem item,int Qty){
		if(Items.get(item) == null) {
			Items.put(item,Qty);
		}
		else {
			Items.put(item,Items.get(item)+Qty);
		}
	}
	
	//removing items
	public boolean RemoveItems(MenuItem item,int Qty) {
		if(Items.get(item) == null || Items.get(item) < Qty) {
			return false;
		}
		else if(Items.get(item) < Qty || Items.get(item) == Qty) {
			Items.remove(item);
			return true;
		}
		else {
			Items.put(item,Items.get(item)-Qty);
			return true;
		}
	}
	
	
	//printing order
	public void PrintOrder() {
		System.out.print("Order ID: ");
		System.out.println(this.getOrderID());
		System.out.print("Table ID: ");
		System.out.println(this.getTableID());
		System.out.println("Qty MenuItem");
		for(MenuItem i : Items.keySet()) {
				System.out.println(i.getName() + Items.get);
		}
	}

	//calculating the total price
	public void CalculateTotalPriceWithoutTax() {
		double price = 0;
		for(MenuItem i : Items.keySet()) {
			price += i.getPrice() * Items.get(i);
		}
		this.TotalPriceWithoutTax = price;
	}
	
	public void CalculateServiceTax() {
		double tax = 0;
		tax += this.TotalPriceWithoutTax*0.07;
		this.ServiceTax = tax;
	}
	public void CalculateGST() {
		double tax = 0;
		tax += this.TotalPriceWithoutTax*0.1;
		this.GST = tax;
	}
	
	public double CalculateTotalPrice() {
		double price = this.GST+this.ServiceTax+this.TotalPriceWithoutTax;
	}
}
