package project2002;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
// import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;

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

		LocalDateTime cDateTime;
		LocalDateTime validDateTime;
		
		do {
			System.out.printf("----Table and Reservation Options----\n" + "Please select one of these 11 options: \n"
					+ "1.	Print All Tables\n" + "2.	Add New Tables\n" + "3.	Remove Table\n" + "4.	Update Table\n"
					+ "5.	Book Table\n" + "6.	Cancel Reservation\n" + "7.	Update Reservation\n"
					+ "8.	Check Reservation\n" + "9.	Reserve Tables\n"
					+ "10.	Remove Reserved Tables\n" + "11.	Quit\n");
			choice = getInt("Please enter your choice: ");
			switch (choice) {
			case 1: // Print Tables Now
				tableManager.printTablesNow(); // print current table status
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
				
				while (dateTime == null) {
					System.out.println("Wrong date and time format");
					dateTime = getDateTime(false);
				}
				
				cDateTime = getCurrDateTime();
				validDateTime = cDateTime.plusHours(1);
				

				if (dateTime.isBefore(validDateTime)) {
					System.out.println("Reservations have to be made at least 1 hour after system time.\nCurrent system time is: " + cDateTime);
				} else {
					switch (tableManager.addReservation(pax, name, number, dateTime)) {
						case 1:
							System.out.println(
									"Reservation for " + name + " for " + pax + " people has been successfully added.");
							break;
						case -1:
							System.out.println(
									"This restaurant does not support group size of more than 10. Please do split bookings.");
							break;
						case -2:
							System.out.println("Reservations for " + dateTime + " is full.");
							break;
					}
				}
				break;
			case 6: // Remove Reservation
				name = getString("Please enter customer name: ");
				number = getInt("Please enter customer phone number: ");
				dateTime = getDateTime(false);

				while (dateTime == null) {
					System.out.println("Wrong date and time format");
					dateTime = getDateTime(false);
				}
				
				cDateTime = getCurrDateTime();
				validDateTime = cDateTime.plusHours(1);

				if (dateTime.isBefore(validDateTime)) {
					System.out.println("Reservations is overdue.\nCurrent system time is: " + cDateTime);
				} else {
					switch (tableManager.removeReservation(name, number, dateTime)) {
						case -1:
							System.out.println("Reservation for " + name + " at " + dateTime + " does not exist.");
							break;
						default:
							System.out.println(
									"Reservation for " + name + " at " + dateTime + " has been successfully cancelled.");
							break;
						}
				}
				break;
			case 7: // Update Reservation
				name = getString("Please enter customer name: ");
				number = getInt("Please enter customer phone number: ");
				dateTime = getDateTime(false);
				
				while (dateTime == null) {
					System.out.println("Wrong date and time format");
					dateTime = getDateTime(false);
				}


				int newPax = getInt("Please enter number of pax: ");
				LocalDateTime newDateTime = getDateTime(true);

				while (newDateTime == null) {
					System.out.println("Wrong date and time format");
					newDateTime = getDateTime(false);
				}

				cDateTime = getCurrDateTime();
				validDateTime = cDateTime.plusHours(1);
				
				
				if (newDateTime.isBefore(validDateTime)) {
					System.out.println("Reservations have to be made at least 1 hour after system time.\nCurrent system time is: " + cDateTime);
				} else {
					switch (tableManager.updateReservation(name, number, dateTime, newPax, newDateTime)) {
						case 1:
							System.out.println(
									"Reservation for " + name + " for " + newPax + " people has been successfully updated.");
							break;
						case -1:
							System.out.println(
									"This restaurant does not support group size of more than 10. Please do split bookings.");
							break;
						case -2:
							System.out.println("Reservations for " + dateTime + " is full.");
							break;
						case -3:
							System.out.println("Initial reservation doesn't exist");
							break;
					}
				}
				break;
			case 8: // Check Reservation
				name = getString("Please enter customer name: ");
				number = getInt("Please enter customer phone number: ");
				dateTime = getDateTime(false);
				
				while (dateTime == null) {
					System.out.println("Wrong date and time format");
					dateTime = getDateTime(false);
				}

				cDateTime = getCurrDateTime();
				validDateTime = cDateTime.plusHours(1);
				
				if (dateTime.isBefore(validDateTime)) {
					System.out.println("Reservation is overdue.\nCurrent system time is: " + cDateTime);
				} else  {
					if (tableManager.checkReservation(name, number, dateTime)) {
						System.out.println("Reservation for " + name + " at " + dateTime + " found.");
					} else {
						System.out.println("Reservation for " + name + " at " + dateTime + " not found.");
					}
				}
				break;
			case 9: // Reserve Tables For The Day
				System.out.println("The following tables are now reserved:");
				ArrayList<String> reservationsStatus = tableManager.reserveTables();
				if (reservationsStatus != null) {
					for (String reservedTableID : reservationsStatus)
					System.out.println(reservedTableID);
				}
				System.out.println("Table reservation complete!");
				break;
			case 10: // Remove Reserved Tables
				System.out.println("The following tables are no longer reserved:");
				ArrayList<String> clearReservationsStatus = tableManager.removeReservedTables();
				if (clearReservationsStatus != null) {
					for (String reservedTableID : clearReservationsStatus)
					System.out.println(reservedTableID);
				}
				System.out.println("Reserved tables updated!");
				break;
			default:
				System.out.println("Invalid Input.");
			}
		} while (choice != 11);
	}

	LocalDateTime getCurrDateTime(){
		LocalDateTime cDateTime = LocalDateTime.now(); 
		DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		String strDateTime = cDateTime.format(dtFormat);

		cDateTime = LocalDateTime.parse(strDateTime, dtFormat);
		return cDateTime;
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
