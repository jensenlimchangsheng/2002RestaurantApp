package project2002;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Table UI
 * @author 	Shun Yao and Jermyn
 * @version 1.0
 * @since	2021-11-02 
 */

public class TableUI extends UI {
	Restaurant rest;

	public TableUI(Restaurant rest) {
		this.rest=rest;
	}

	public void printTableOptions() {
		int choice = 0;
		int pax;
		LocalDateTime dateTime;
		String name;
		int number;

		Scanner scan= new Scanner(System.in);
		do {
			System.out.printf("----Table and Reservation Options----\n"
					+ "Please select one of this 9 options: \n"
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
					rest.callToPrintAvailableTablesNow(); // print current table status 
					break;
				case 2:
					pax = getPax();
					name = getTableID();
					System.out.println(rest.addNewTable(name,  pax));
					break;
				case 3:
					name = getTableID();
					switch(rest.removeTable(name)) {
					case 1:
						System.out.println("Table "+name+" removed successfully!");
						break;
					case 0:
						System.out.println("Table "+name+" cannot be found!");
						break;
					case -1:
						System.out.println("Table "+name+" is currently booked or occupied, try again later!");
						break;
					}
					break;
				case 4:
					pax = getPax();
					name = getTableID();
					switch(rest.updateTable(name,  pax)) {
					case 1:
						System.out.println("Table "+name+" updated successfully!");
						break;
					case 0:
						System.out.println("Table "+name+" cannot be found!");
						break;
					case -1:
						System.out.println("Table "+name+" is currently booked or occupied, try again later!");
						break;
					case -2:
						System.out.println("Bug in the update table code!");
						break;
					}
					break;
				case 5:
					name = getName();
					number = getNumber();
					pax = getPax();
					dateTime = getDateTime();
					if (rest.addReservation(pax, name, number, dateTime)) {
						System.out.println("Reservation for " + name + " at " + dateTime + " for " + pax + " people has been successfully added.");
					} else {
						System.out.println("Reservations for " + dateTime + " is full.");
					}
					break;
				case 6:
					name = getName();
					number = getNumber();
					dateTime = getDateTime();
					if (rest.removeReservation(name, number, dateTime)) {
						System.out.println("Reservation for " + name + " at " + dateTime + " for " + pax + " people has been successfully cancelled.");
					} else {						
						System.out.println("Reservation for " + name + " at " + dateTime + " for " + pax + " people does not exist.");
					}
					break;
				case 7:
					name = getName();
					number = getNumber();
					dateTime = getDateTime();
					switch (rest.updateReservation(name, number, dateTime)) {
						case 1:
							System.out.println("Reservation for " + name + " for " + pax + " people has been successfully updated.");
							break;
						case -1:
							System.out.println("Removing old reservation failed.");
							break;
						case -2:
							
							System.out.println("Adding new reservation failed.");
							break;
					}
					break;
				case 8:
					name = getName();
					number = getNumber();
					dateTime = getDateTime();
					if ( rest.checkReservation(name, number, dateTime)){ 
						System.out.println("Reservation for " + name + " for " + pax + " found.");
					} else {
						System.out.println("Reservation for " + name + " for " + pax + " not found.");
					}
					break;
				case 9:
					if(rest.reserveTables())
						System.out.println("Tables are all reserved!");
					System.out.println("Issues reserving tables!");
					break;
				case 10:
					if(rest.removeReservedTables())
						System.out.println("All specified reserved tables are unreserved");
					System.out.println("Issues unreserving tables!");
					break;
				default:
					System.out.println("Invalid Input.");
				}
			} while(choice!=4);
	}

	int getPax() {
		System.out.println("Please enter number of pax: ");
		int pax=this.getInt();
		return pax;
	}

	String getName() {
		System.out.println("Please enter customer name: ");
		String name=this.getString();
		return name;
	}
	
	String getTableID() {
		System.out.println("Please enter table ID: ");
		String name=this.getString();
		return name;
	}

	int getNumber() {
		System.out.println("Please enter customer phone number: ");
		int number = this.getInt();
		return number;
	}

	LocalDateTime getDateTime() {
		LocalDateTime dateTime = null;
		System.out.println("Please enter a date dd/MM/yyyy");
		String datestring=this.getString();
		System.out.println("Please enter a time HH:mm");
		String timeString=this.getString();
        DateTimeFormatter format
        = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
	    // Try block tp check for exceptions
	    try {
	
	        // Getting the Date from String
	    	dateTime = LocalDateTime.parse(datestring + " " + timeString,  format);
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
		
	    return dateTime;
	}
}
