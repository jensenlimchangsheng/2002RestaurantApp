package project2002;

import java.util.Scanner;

import project2002.Restaurant.UIType;

public class SalesReportUI extends UI {
	
	private SalesReportManager srManager;

	public SalesReportUI(Scanner scanner){
		super(scanner);
		this.type=UIType.SALES;
	}

	@Override
	protected void printOptions() {
		int choice=0;
		do {
			System.out.printf("-------------Sales Report Options------------\n"
					+ "Please select one of this 8 options: \n"
					+ "1.	Print Daily Sales Report\n"
					+ "2.	Print Monthly Sales Report\n"
					+ "3.	Print Yearly Sales Report\n"
					+ "4.	Quit\n"
					+ "Please enter your choice: ");
			choice=this.getInt();
			switch(choice) {
			case 1:
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			default:
				System.out.println("");
			}
			} while(choice!=8);
		
	}

	@Override
	protected void assignUIManager(Manager m) {
		srManager=(SalesReportManager) m;
	}

}
