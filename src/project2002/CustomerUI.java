package project2002;

import java.util.InputMismatchException;
import java.util.Scanner;

import project2002.Restaurant.UIType;

public class CustomerUI extends UI {
	
	private CustomerManager customerManager;

	public CustomerUI(Scanner scanner){
		super(scanner);
		this.type=UIType.CUSTOMER;
	}

	@Override
	protected void printOptions() {
		int choice=0;
		do {
			System.out.printf("-------------Order Options-----------\n"
					+ "Please select one of this 2 options: \n"
					+ "1.	Create Order For New Customer\n"
					+ "2.	Create Order For Reserved Customer\n"
					+ "3.	Quit");	
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
				System.out.printf("The orderID is : %d.\n",customerManager.newCustomerOrder(pax,name,id,title));
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
				System.out.printf("The orderID is : %d.\n",customerManager.reservedCustomerOrder(pax,name,id,title,customername,number));
				break;
			case 3:
				break;
			default:
				System.out.println("Invalid Input.");
			}
			} while(choice!=7);
		
	}

	@Override
	protected void assignUIManager(Manager m) {
		// TODO Auto-generated method stub
		
	}

}
