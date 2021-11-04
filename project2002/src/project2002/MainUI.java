package project2002;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainUI extends UI {

	public MainUI() {
	}

	public static void main(String[] args) {
		int choice=0;
		MenuUI menuUI; 
		TableUI tableUI;
		OrderUI orderUI;
		Restaurant rest = new Restaurant();
		menuUI= new MenuUI(rest);
		tableUI= new TableUI(rest);
		orderUI= new OrderUI(rest);
		rest.addUI(tableUI,menuUI,orderUI);
		
		Scanner scan= new Scanner(System.in);
		do {
		System.out.printf("-----Welcome to Restaurant XXX-------\n"
						+ "Please select one of this 4 options: \n"
						+ "1.	Menu\n"
						+ "2.	Order\n"
						+ "3.	Table and Reservation\n"
						+ "4.	Quit");
		try {
			choice =scan.nextInt();
			}
		catch(InputMismatchException e) {
			System.out.println("Invalid Input.");
			}
		switch(choice) {
		case 1:
			menuUI.printMenuOptions();
			break;
		case 2:
			orderUI.printOrderOptions();
			break;
		case 3:
			tableUI.printTableOptions();
			break;
		case 4:
			break;
		default:
			System.out.println("Invalid Input.");
		}
		} while(choice!=4);
		System.out.println("THANK YOU.");
		scan.close();

	}

}
