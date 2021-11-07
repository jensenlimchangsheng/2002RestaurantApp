package project2002;
import java.util.*;

import project2002.Restaurant.handlerType;

public class OrderHandler extends Handler {
	private ArrayList<Order> Orders = new ArrayList<Order>();
	private int OrderCounter = 0;
	
	public OrderHandler(){
		type=handlerType.ORDER;
	}
	
	public int createOrder(String TableID, Staff staff) {
		Order temp = new Order(staff,TableID,OrderCounter);
		Orders.add(temp);
		OrderCounter++;
		return OrderCounter-1;
	}
	
	public void printOrder(int OrderID) {
		int temp = -1;
		for(int j = 0; j<Orders.size();j++) {
			if(Orders.get(j).getOrderID() == OrderID) {
				temp = j;
				break;
			}
		}
		if(temp == -1) {
			return;
		}
		else {
			Orders.get(temp).PrintOrder();
		}
	}
	
	public boolean AddItem(int OrderID, MenuItem item, int Qty){
		int temp = -1;
		for(int j = 0; j<Orders.size();j++) {
			if(Orders.get(j).getOrderID() == OrderID) {
				temp = j;
				break;
			}
		}
		if(temp == -1) {
			return false;
		}
		else {
			Orders.get(temp).AddItems(item,Qty);
			return true;
		}
	}
	
	public boolean RemoveItem(int OrderID,MenuItem item, int Qty){
		int temp = -1;
		for(int j = 0; j<Orders.size();j++) {
			if(Orders.get(j).getOrderID() == OrderID) {
				temp = j;
				break;
			}
		}
		if(temp == -1) {
			return false;
		}
		else {
			return Orders.get(temp).RemoveItems(item, Qty);
		}
	}
	
	public Order printInvoice(int OrderID, double discount) {
		int temp = -1;
		for(int j = 0; j<Orders.size();j++) {
			if(Orders.get(j).getOrderID() == OrderID) {
				temp = j;
				break;
			}
		}
		if(temp == -1) {
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
		HashMap<MenuItem,Integer> Items = Orders.get(temp).getItems();
		for(MenuItem i : Items.keySet()) {
			String line = String.format("%2d %-30s $%.2f", Items.get(i), i.getName(), i.getPrice()* Items.get(i));
			System.out.println(line);
		}
		System.out.println("=========================================");
		String line = String.format("   %30s $%.2f","SubTotal:", Orders.get(temp).getTotalPriceWithoutTax());
		System.out.println(line);
		line = String.format("   %30s-$%.2f", "Discount: ", Orders.get(temp).getDiscount());
		System.out.println(line);
		line = String.format("   %30s $%.2f","GST:", Orders.get(temp).getGST());
		System.out.println(line);
		line = String.format("   %30s $%.2f","Service Charge:", Orders.get(temp).getServiceTax());
		System.out.println(line);
		line = String.format("   %30s $%.2f","Total:", Orders.get(temp).getTotalPriceWithTax());
		System.out.println(line);
		System.out.println("=========================================");
		System.out.println("Thank you for dining with us!");
		
		Order j = Orders.get(temp);
		Orders.remove(temp);
		return j;
	}

}


