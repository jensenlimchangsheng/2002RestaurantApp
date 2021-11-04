package project2002;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

import project2002.Restaurant.UIType;

public class TableUI extends UI {
	
	private TableManager tableManager;

	public TableUI(Scanner scanner) {
		super(scanner);
		this.type=UIType.TABLE;
	}

	@Override
	protected void printOptions() {
		int choice=0;
		Scanner scan= new Scanner(System.in);
		do {
			System.out.printf("----Table and Reservation Options----\n"
					+ "Please select one of this 10 options: \n"
					+ "1.	Print Available Tables Now\n"
					+ "2.	Add New Tables\n"
					+ "3.	Remove Table\n"
					+ "4.	Update Table\n"
					+ "5.	Book Table\n"
					+ "6.	Remove Reservation\n"
					+ "7.	Update Reservation\n"
					+ "8.	Check Reservation\n"
					+ "9.	Reserve Tables For The Day\n"
					+ "10.	Remove Reserved Tables\n"
					+ "11.	Quit");
			try {
				choice =scan.nextInt();
				}
			catch(InputMismatchException e) {
				System.out.println("Invalid Input.");
				}
			switch(choice) {
			case 1:
				tableManager.printAvailableTablesNow();
				break;
			case 2:
				int pax = 0;
				tableManager.addNewTable(pax);
				break;
			case 3:
				pax=0;
				tableManager.removeTable(pax);
				break;
			case 4:
				int tableID=0;
				pax=0;
				tableManager.updateTable(tableID,pax);
				break;
			case 5:
				String name="";
				int number=0;
				pax=0;
				LocalDateTime date=null;
				LocalDateTime time=null;
				tableManager.addReservation(pax,name,number,date,time);
				break;
			case 6:
				pax=0;
				name="";
				number=0;
				date=null;
				time=null;
				tableManager.removeReservation(pax,name,number,date,time);
				break;
			case 7:
				pax=0;
				name="";
				number=0;
				date=null;
				time=null;
				tableManager.updateReservation(pax,name,number,date,time);
				break;
			case 8:
				pax=0;
				name="";
				number=0;
				date=null;
				time=null;
				tableManager.checkReservation(pax,name,number,date,time);
				break;
			case 9:
				tableManager.reserveTables();
				break;
			case 10:
				tableManager.removeReservedTables();
				break;
			case 11:
				tableManager.reserveTables();
				break;
			default:
				System.out.println("Invalid Input.");
			}
			} while(choice!=11);
		
	}

	@Override
	protected void assignUIManager(Manager m) {
		// TODO Auto-generated method stub
		
	}
	
	public int getPax() {
		System.out.println("Please enter number of pax: ");
		int pax=this.getInt();
		return pax;
	}

	public LocalDate getDate() {
		LocalDate date=null;
		System.out.println("Please enter a date d MMMM, yyyy: ");
		String datestring=this.getString();
        DateTimeFormatter format
        = DateTimeFormatter.ofPattern("d MMMM, yyyy");
	    // Try block tp check for exceptions
	    try {
	
	        // Getting the Date from String
	    	date = LocalDate.parse(datestring, format);
	    }
	
	    // Block 1
	    // Catch block to handle exceptions occuring
	    // if the String pattern is invalid
	    catch (IllegalArgumentException e) {
	
	        // Display the exception
	        System.out.println("Exception: " + e);
	    }
	
	    // Block 2
	    // If the String was unable to be parsed
	    catch (DateTimeParseException e) {
	
	        // Display the exception
	        System.out.println("Exception: " + e);
	    }
	    return date;
	}

	public LocalDateTime getTime() {
		// TODO Auto-generated method stub
		return null;
	}

}
