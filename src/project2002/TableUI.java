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
		int number;

		Scanner scan = new Scanner(System.in);
		do {
			System.out.printf("----Table and Reservation Options----\n" + "Please select one of this 9 options: \n"
					+ "1.	Print Available Tables Now\n" + "2.	Add New Tables\n" + "3.	Remove Table\n"
					+ "4.	Update Table\n" + "5.	Book Table\n" + "6.	Remove Reservation\n"
					+ "7.	Update Reservation\n" + "8.	Check Reservation\n" + "9.	Reserve Tables For The Day\n"
					+ "10.	Remove Reserved Tables\n" + "11.	Quit\n");
			choice = this.getInt("Please enter your choice: ");
			switch (choice) {
			case 1: // Print Available Tables Now
				tableManager.printAvailableTablesNow(); // print current table status
				break;
			case 2: // Add New Tables
				pax = getPax(scan);
				name = getTableID(scan);
				switch (pax) {
				case 2:
				case 4:
				case 6:
				case 8:
				case 10:
					String result = tableManager.addNewTable(name, pax);
					if (result.equals("TableAlreadyExists"))
						System.out.println("TableID already exists. Retry with new ID!");
					else {
						System.out.println(name + " created for " + pax + " pax.");
					}
					break;
				default:
					System.out.println("Invalid pax size. Only 2, 4, 6, 8, 10 allowed.");
				}
				break;
			case 3: // Remove Table
				name = getTableID(scan);
				switch (tableManager.removeTable(name)) {
				case 1:
					System.out.println("Table " + name + " removed successfully!");
					break;
				case 0:
					System.out.println("Table " + name + " cannot be found!");
					break;
				case -1:
					System.out.println("Table " + name + " is currently booked or occupied, try again later!");
					break;
				}
				break;
			case 4: // Update Table
				pax = getPax(scan);
				name = getTableID(scan);
				switch (tableManager.updateTable(name, pax)) {
				case 1:
					System.out.println("Table " + name + " updated successfully!");
					break;
				case 0:
					System.out.println("Table " + name + " cannot be found!");
					break;
				case -1:
					System.out.println("Table " + name + " is currently booked or occupied, try again later!");
					break;
				case -2:
					System.out.println("Bug in the update table code!");
					break;
				}
				break;
			case 5: // Book Table
				name = getName(scan);
				number = getNumber(scan);
				pax = getPax(scan);
				dateTime = getDateTime(scan);
				if (tableManager.addReservation(pax, name, number, dateTime)) {
					System.out.println("Reservation for " + name + " at " + dateTime + " for " + pax
							+ " people has been successfully added.");
				} else {
					System.out.println("Reservations for " + dateTime + " is full.");
				}
				break;
			case 6: // Remove Reservation
				name = getName(scan);
				number = getNumber(scan);
				dateTime = getDateTime(scan);
				if (tableManager.removeReservation(name, number, dateTime)) {
					System.out.println(
							"Reservation for " + name + " at " + dateTime + " has been successfully cancelled.");
				} else {
					System.out.println("Reservation for " + name + " at " + dateTime + " does not exist.");
				}
				break;
			case 7: // Update Reservation
				name = getName(scan);
				number = getNumber(scan);
				dateTime = getDateTime(scan);
				int newPax = getPax(scan);
				LocalDateTime newDateTime = getDateTime(scan);
				switch (tableManager.updateReservation(name, number, dateTime, newPax, newDateTime)) {
				case 1:
					System.out.println(
							"Reservation for " + name + " for " + newPax + " people has been successfully updated.");
					break;
				case -1:
					System.out.println("Removing old reservation failed.");
					break;
				case -2:

					System.out.println("Adding new reservation failed.");
					break;
				}
				break;
			case 8: // Check Reservation
				name = getName(scan);
				number = getNumber(scan);
				dateTime = getDateTime(scan);
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

	/**
	 * Get number of diners from user
	 * 
	 * @return number of pax input by user
	 */
	int getPax(Scanner sc) {
		System.out.println("Please enter number of pax: ");
		int pax = sc.nextInt();
		return pax;
	}

	/**
	 * Get customer name from user
	 * 
	 * @return name of customer input by user
	 */
	String getName(Scanner sc) {
		System.out.println("Please enter customer name: ");
		String name = sc.next();
		return name;
	}

	/**
	 * Get table ID from user
	 * 
	 * @return tableID input by user
	 */
	String getTableID(Scanner sc) {
		System.out.println("Please enter table ID: ");
		String name = sc.next();
		return name;
	}

	int getNumber(Scanner sc) {
		System.out.println("Please enter customer phone number: ");
		int number = sc.nextInt();
		return number;
	}

	LocalDateTime getDateTime(Scanner sc) {
		LocalDateTime dateTime = null;
		System.out.println("Please enter a date dd/MM/yyyy");
		String datestring = sc.next();
		System.out.println("Please enter a time HH:mm");
		String timeString = sc.next();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		// Try block to check for exceptions
		try {

			// Getting the Date from String
			dateTime = LocalDateTime.parse(datestring + " " + timeString, format);
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
