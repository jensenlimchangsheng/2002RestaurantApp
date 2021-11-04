package project2002;

import java.util.InputMismatchException;
import java.util.Scanner;

public class OrderUI extends UI {
	Restaurant rest;

	public OrderUI(Restaurant rest) {
		this.rest=rest;
	}

	public void printOrderOptions() {
		int choice =0;
		Scanner scan= new Scanner(System.in);
		do {
			System.out.printf("-------------Order Options-----------\n"
					+ "Please select one of this 7 options: \n"
					+ "1.	Create Order For New Customer\n"
					+ "2.	Create Order For Reserved Customer\n"
					+ "3.	Add Order Item\n"
					+ "4.	Remove Order Item\n"
					+ "5.	Print Order Invoice\n"
					+ "6.	Print Sales Report\n"
					+ "7.	Quit");	
			try {
				choice =scan.nextInt();
				}
			catch(InputMismatchException e) {
				System.out.println("Invalid Input.");
				}
			switch(choice) {
			case 1:
				
				System.out.println("Please enter number of pax: ");
				int pax=this.getInt();
				System.out.println("Please enter staff name: ");
				String name=this.getString();
				System.out.println("Please enter staff ID: ");
				int id=this.getInt();
				System.out.println("Please enter staff title: ");
				String title=this.getString();
				System.out.printf("The orderID is : %d.\n",rest.newCustomerOrder(pax,name,id,title));
				break;
			case 2:
				System.out.println("Please enter number of pax: ");
				pax=this.getInt();
				System.out.println("Please enter staff name: ");
				name=this.getString();
				System.out.println("Please enter staff ID: ");
				id=this.getInt();
				System.out.println("Please enter staff title: ");
				title=this.getString();
				System.out.println("Please enter customer name: ");
				String customername=this.getString();
				System.out.println("Please enter customer phone number: ");
				int number=this.getInt();
				System.out.printf("The orderID is : %d.\n",rest.reservedCustomerOrder(pax,name,id,title,customername,number));
				break;
			case 3:
				System.out.println("Please enter orderID: ");
				id=scan.nextInt();
				if(rest.addOrderItem(id)==-1) {
					System.out.println("Menu Item does not exist");
				}
				else if(rest.addOrderItem(id)==0) System.out.printf("Menu Item could not be added to the order %d.\n",id);
				else if(rest.addOrderItem(id)==1) System.out.printf("Menu Item was added to the order %d.\n",id);
				break;
			case 4:
				System.out.println(rest.removeOrderItem());
			case 5:
				System.out.println(rest.printOrderInvoice());
			case 6:
				
				System.out.println(rest.printSalesRevenue());
			case 7:
				break;
			default:
				System.out.println("Invalid Input.");
			}
			} while(choice!=7);
	}

	public int getItemID() {
		Scanner scan=new Scanner(System.in);
		int itemID;
		System.out.printf("Please enter the item ID: \n");
		itemID=scan.nextInt();
		return itemID;
	}

	public int getQty() {
		Scanner scan=new Scanner(System.in);
		int qty;
		System.out.printf("Please enter the quantity: \n");
		qty=scan.nextInt();
		return qty;
	}
	
	

}
