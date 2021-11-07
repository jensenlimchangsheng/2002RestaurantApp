package project2002;
import java.util.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class SalesReport {
	private HashMap<Integer, ArrayList<Order>> Year = new HashMap<Integer, ArrayList<Order>>();
	private HashMap<String, ArrayList<Order>> Month = new HashMap<String, ArrayList<Order>>();
	private HashMap<LocalDate, ArrayList<Order>> Day = new HashMap<LocalDate, ArrayList<Order>>();
	
	public void InsertOrder(Order order){
		LocalDateTime temp = order.getDate();
		int y = temp.getYear();
		int m = temp.getMonthValue();
		int d = temp.getDayOfMonth();
		LocalDate date= LocalDate.of(y, m, d);
		String monthformat = m + "-" + y;
		if(Day.size() == 0) {
			ArrayList<Order> i = new ArrayList<Order>();
			i.add(order);
			Day.put(date, i);
			Month.put(monthformat, i);
			Year.put(y,i);
		}
		else {
			boolean s = false;
			for(LocalDate j : Day.keySet()) {
				if(s = j.isEqual(date)) {
					ArrayList<Order> i = new ArrayList<Order>();
					i = Day.get(j);
					i.add(order);
					Day.put(j, i);
					break;
				}
			}
			if(!s) {
				ArrayList<Order> i = new ArrayList<Order>();
				i.add(order);
				Day.put(date, i);
			}
			s = false;
			for(String x: Month.keySet()) {
				if(x == monthformat) {
					s = true;
					ArrayList<Order> i = new ArrayList<Order>();
					i = Month.get(x);
					i.add(order);
					Month.put(x, i);
					break;
				}
			}
			if(!s) {
				ArrayList<Order> i = new ArrayList<Order>();
				i.add(order);
				Month.put(monthformat, i);
			}
			s = false;
			for(int z: Year.keySet()) {
				if(z == y) {
					s = true;
					ArrayList<Order> i = new ArrayList<Order>();
					i = Year.get(z);
					i.add(order);
					Year.put(z, i);
					break;
				}
			}
			if(!s) {
				ArrayList<Order> i = new ArrayList<Order>();
				i.add(order);
				Year.put(y, i);
			}
		}
	}
	
	public void printYearlyReport(int y) {
		ArrayList<Order> temp = Year.get(y);
		if(temp == null) {
			System.out.println("There are no records in Year "+ y);
			return;
		}
		double totalrevenue = 0;
		double totalGST = 0;
		double totalServiceCharge = 0;
		HashMap<MenuItem, Integer> Orders = new HashMap<MenuItem, Integer>();
		for(int i = 0; i<temp.size();i++) {
			Order indivOrder = temp.get(i);
			totalrevenue += indivOrder.getTotalPriceWithTax();
			totalGST += indivOrder.getGST();
			totalServiceCharge += indivOrder.getServiceTax();
			HashMap<MenuItem,Integer> Items = indivOrder.getItems();
			
			for(MenuItem item:Items.keySet()) {
				if(Orders.get(item) == null) {
					Orders.put(item,Items.get(item));
				}
				else{
					int Qty = Orders.get(item);
					Orders.put(item, Qty+Items.get(item));
				}
			}
		}
		System.out.println("==============================");
		System.out.println("These are the following items in year" + y);
		System.out.println("Qty: MenuItem: ");
		for(MenuItem item : Orders.keySet()) {
			System.out.println(Orders.get(item) + " " + item.getName());
		}
		String line = String.format("%24s $%.2f", "Total Revenue:", totalrevenue);
		System.out.println(line);
		line = String.format("%24s $%.2f", "Total GST Collected:", totalGST);
		System.out.println(line);
		line = String.format("%24s $%.2f", "Total Service Charge Collected:", totalServiceCharge);
	}
	public void printMonthlyReport(int Y, int M) {
		String MonthFormat = M + "-" + Y;
		ArrayList<Order> temp = Month.get(MonthFormat);
		
		if(temp == null) {
			System.out.println("There are no records in "+ temp);
			return;
		}
		double totalrevenue = 0;
		double totalGST = 0;
		double totalServiceCharge = 0;
		HashMap<MenuItem, Integer> Orders = new HashMap<MenuItem, Integer>();
		for(int i = 0; i<temp.size();i++) {
			Order indivOrder = temp.get(i);
			totalrevenue += indivOrder.getTotalPriceWithTax();
			totalGST += indivOrder.getGST();
			totalServiceCharge += indivOrder.getServiceTax();
			HashMap<MenuItem,Integer> Items = indivOrder.getItems();
			
			for(MenuItem item:Items.keySet()) {
				if(Orders.get(item) == null) {
					Orders.put(item,Items.get(item));
				}
				else{
					int Qty = Orders.get(item);
					Orders.put(item, Qty+Items.get(item));
				}
			}
		}
		System.out.println("==============================");
		System.out.println("These are the following items in year" + Y);
		System.out.println("Qty: MenuItem: ");
		for(MenuItem item : Orders.keySet()) {
			System.out.println(Orders.get(item) + " " + item.getName());
		}
		String line = String.format("%24s $%.2f", "Total Revenue:", totalrevenue);
		System.out.println(line);
		line = String.format("%24s $%.2f", "Total GST Collected:", totalGST);
		System.out.println(line);
		line = String.format("%24s $%.2f", "Total Service Charge Collected:", totalServiceCharge);
	}
	public void printDailyReport(int Y, int M, int D) {
		LocalDate Date = LocalDate.of(Y, M, D);
		ArrayList<Order> temp = Day.get(Date);
		
		if(temp == null) {
			System.out.println("There are no records in "+ Y + "-" + M + "-" + D);
			return;
		}
		double totalrevenue = 0;
		double totalGST = 0;
		double totalServiceCharge = 0;
		HashMap<MenuItem, Integer> Orders = new HashMap<MenuItem, Integer>();
		for(int i = 0; i<temp.size();i++) {
			Order indivOrder = temp.get(i);
			totalrevenue += indivOrder.getTotalPriceWithTax();
			totalGST += indivOrder.getGST();
			totalServiceCharge += indivOrder.getServiceTax();
			HashMap<MenuItem,Integer> Items = indivOrder.getItems();
			
			for(MenuItem item:Items.keySet()) {
				if(Orders.get(item) == null) {
					Orders.put(item,Items.get(item));
				}
				else{
					int Qty = Orders.get(item);
					Orders.put(item, Qty+Items.get(item));
				}
			}
		}
		System.out.println("==============================");
		System.out.println("These are the following items in year" + Y);
		System.out.println("Qty: MenuItem: ");
		for(MenuItem item : Orders.keySet()) {
			System.out.println(Orders.get(item) + " " + item.getName());
		}
		String line = String.format("%24s $%.2f", "Total Revenue:", totalrevenue);
		System.out.println(line);
		line = String.format("%24s $%.2f", "Total GST Collected:", totalGST);
		System.out.println(line);
		line = String.format("%24s $%.2f", "Total Service Charge Collected:", totalServiceCharge);
	}
	
}
