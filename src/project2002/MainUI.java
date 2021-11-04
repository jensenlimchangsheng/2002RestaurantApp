package project2002;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MainUI {

	public static void main(String[] args) {
		String temp = "";
		Scanner scan = new Scanner(System.in);
		ArrayList<UI> uiList = new ArrayList<UI>();
		int choice = 0;
		MenuUI menuUI = new MenuUI(scan);
		uiList.add(menuUI);
		OrderUI orderUI = new OrderUI(scan);
		uiList.add(orderUI);
		SalesReportUI srUI = new SalesReportUI(scan);
		uiList.add(srUI);
		CustomerUI customerUI = new CustomerUI(scan);
		uiList.add(customerUI);
		TableUI tableUI = new TableUI(scan);
		uiList.add(tableUI);
		Restaurant rest = new Restaurant();
		rest.assignUI(uiList);
		do {
			System.out.printf("-----Welcome to Restaurant XXX-------\n" + "Please select one of this 5 options: \n"
					+ "1.	Menu\n" + "2.	Order\n" + "3.	SalesReport\n" + "4.	New Customer\n"
					+ "5.	Table and Reservation\n" + "6.	Quit\n" + "Please enter your choice: ");
			try {
				choice = scan.nextInt();
			} catch (InputMismatchException e) {
				temp = scan.nextLine();
				System.out.println("Invalid Input.");
			}
			switch (choice) {
			case 1:
				menuUI.printOptions();
				break;
			case 2:
				orderUI.printOptions();
				break;
			case 3:
				srUI.printOptions();
				break;
			case 4:
				customerUI.printOptions();
				break;
			case 5:
				tableUI.printOptions();
				break;
			default:
				System.out.println("");
			}
		} while (choice != 6);
		System.out.println("THANK YOU.");
		scan.close();
	}

}
