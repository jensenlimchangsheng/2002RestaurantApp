package project2002;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UI {
	protected int getInt() {
		Scanner scan= new Scanner(System.in);
		int choice=0;
		try {
			choice =scan.nextInt();
			}
		catch(InputMismatchException e) {
			System.out.println("Invalid Input.");
			}
		scan.close();
		return choice;
	}
	
	protected String getString() {
		Scanner scan= new Scanner(System.in);
		String choice="";
		try {
			choice =scan.nextLine();
			}
		catch(InputMismatchException e) {
			System.out.println("Invalid Input.");
			}
		scan.close();
		return choice;
	}
	

}
