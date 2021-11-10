package project2002;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
// import java.util.InputMismatchException;
import java.util.Scanner;

import project2002.Restaurant.UIType;

public class TableUI extends UI {

	private TableManager tableManager;

	public TableUI(Scanner scanner) {
		super(scanner);
		this.type = UIType.TABLE;
	}

	@Override
	protected void printOptions() {
		int choice = 0;
		int pax;
		LocalDateTime dateTime;
		String name;
		String tableID;
		int number;
		do {
			System.out.printf("----Table and Reservation Options----\n" + "Please select one of this 9 options: \n"
					+ "1.	Print Available Tables Now\n" + "2.	Add New Tables\n" + "3.	Remove Table\n"
					+ "4.	Update Table\n" + "5.	Book Table\n" + "6.	Remove Reservation\n"
					+ "7.	Update Reservation\n" + "8.	Check Reservation\n" + "9.	Reserve Tables For The Day\n"
					+ "10.	Remove Reserved Tables\n" + "11.	Quit\n");
			choice = getInt("Please enter your choice: ");
			switch (choice) {
			case 1: // Print Available Tables Now
				tableManager.printAvailableTablesNow(); // print current table status
				break;
			case 2: // Add New Tables
				pax = getInt("Please enter number of pax: ");
				tableID = getString("Please enter table ID: ");
				switch (pax) {
				case 2:
				case 4:
				case 6:
				case 8:
				case 10:
					String result = tableManager.addNewTable(tableID, pax);
					if (result.equals("TableAlreadyExists"))
						System.out.println("TableID already exists. Retry with new ID!");
					else {
						System.out.println(tableID + " created for " + pax + " pax.");
					}
					break;
				default:
					System.out.println("Invalid pax size. Only 2, 4, 6, 8, 10 allowed.");
				}
				break;
			case 3: // Remove Table
				tableID = getString("Please enter table ID: ");
				switch (tableManager.removeTable(tableID)) {
				case 1:
					System.out.println("Table " + tableID + " removed successfully!");
					break;
				case 0:
					System.out.println("Table " + tableID + " cannot be found!");
					break;
				case -1:
					System.out.println("Table " + tableID + " is currently booked or occupied, try again later!");
					break;
				}
				break;
			case 4: // Update Table
				pax = getInt("Please enter number of pax: ");
				tableID = getString("Please enter table ID: ");
				switch (tableManager.updateTable(tableID, pax)) {
				case 1:
					System.out.println("Table " + tableID + " updated successfully!");
					break;
				case 0:
					System.out.println("Table " + tableID + " cannot be found!");
					break;
				case -1:
					System.out.println("Table " + tableID + " is currently booked or occupied, try again later!");
					break;
				case -2:
					System.out.println("Bug in the update table code!");
					break;
				}
				break;
			case 5: // Book Table
				name = getString("Please enter customer name: ");
				number = getInt("Please enter customer phone number: ");
				pax = getInt("Please enter number of pax: ");
				dateTime = getDateTime(false);
				switch (tableManager.addReservation(pax, name, number, dateTime)) {
					case 1:
					System.out.println("Reservation for " + name + " for " + pax + " people has been successfully added.");
						break;
					case -1: 
						System.out.println("This restaurant does not support group size of more than 10. Please do split bookings.");
						break;
					case -2:
						System.out.println("Reservations for " + dateTime + " is full.");
						break;
				}
				break;
			case 6: // Remove Reservation
				name = getString("Please enter customer name: ");
				number = getInt("Please enter customer phone number: ");
				dateTime = getDateTime(false);
				switch (tableManager.removeReservation(name, number, dateTime)) {
					case -1:
						System.out.println("Reservation for " + name + " at " + dateTime + " does not exist.");
						break;
					case -2:
						System.out.println("Reservation for " + name + " at " + dateTime + " cannot be deleted.");
						break;
					default: 
						System.out.println("Reservation for " + name + " at " + dateTime + " has been successfully cancelled.");
						break;
				}
			case 7: // Update Reservation
				name = getString("Please enter customer name: ");
				number = getInt("Please enter customer phone number: ");
				dateTime = getDateTime(false);
				int newPax = getInt("Please enter number of pax: ");
				LocalDateTime newDateTime = getDateTime(true);
				switch (tableManager.updateReservation(name, number, dateTime, newPax, newDateTime)) {
				case 1:
					System.out.println("Reservation for " + name + " for " + newPax + " people has been successfully updated.");
					break;
				case -1: 
					System.out.println("This restaurant does not support group size of more than 10. Please do split bookings.");
					break;
				case -2:
					System.out.println("Reservations for " + dateTime + " is full.");
					break;
				case -3:
					System.out.println("Removing old reservation failed. (Reservation doesn't exist)");
					break;
				case -4:
					System.out.println("Reservation cannot be updated. 1 hour to reservation time.");
					break;
				}
				break;
			case 8: // Check Reservation
				name = getString("Please enter customer name: ");
				number = getInt("Please enter customer phone number: ");
				dateTime = getDateTime(false);
				if (tableManager.checkReservation(name, number, dateTime)) {
					System.out.println("Reservation for " + name + " at " + dateTime + " found.");
				} else {
					System.out.println("Reservation for " + name + " at " + dateTime + " not found.");
				}
				break;
			case 9: // Reserve Tables For The Day
				if (tableManager.reserveTables())
					System.out.println("Table reservation complete!");
				else
					System.out.println("Issues reserving tables!");
				break;
			case 10: // Remove Reserved Tables
				if (tableManager.removeReservedTables())
					System.out.println("All specified reserved tables are unreserved");
				System.out.println("Issues unreserving tables!");
				break;
			default:
				System.out.println("Invalid Input.");
			}
		} while (choice != 11);
	}

	LocalDateTime getDateTime(boolean update) {
		LocalDateTime dateTime = null;
		String dateString = null;
		String timeString = null; 

		if (!update) {
			dateString = getString("Please enter a date dd/MM/yyyy");
			timeString = getString("Please enter a time HH");
		} else { 
			dateString = getString("Please enter a new date dd/MM/yyyy");
			timeString = getString("Please enter a new time HH");
		}
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH");
		// Try block to check for exceptions
		try {

			// Getting the Date from String
			dateTime = LocalDateTime.parse(dateString + " " + timeString, format);
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

	@Override
	protected void assignUIManager(Manager m) {
		tableManager = (TableManager) m;
	}

}
