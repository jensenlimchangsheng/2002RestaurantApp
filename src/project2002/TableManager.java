package project2002;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;
import project2002.Restaurant.UIType;
import project2002.Restaurant.handlerType;

/**
 * TableManager class for managing table bookings for the Restaurant.
 * 
 * @author Jermyn
 * @author Shun Yao
 * @version 1.0
 * @since 2021-11-08
 */

public class TableManager extends Manager {
	TableHandler tableHandler;
	ReservationHandler reservationHandler;
	TableUI tableUI;

	/**
	 * Constructor for table manager
	 */
	public TableManager() {

		handlerList.add(handlerType.TABLE);
		handlerList.add(handlerType.RESERVATION);
		type = UIType.TABLE;
	}

	/**
	 * Adds a table to the restaurant with default status "VACANT". This table can
	 * now be booked.
	 * 
	 * @param tableID String tableID used to uniquely identify a table 
	 * @param pax int pax to indicate the capacity of the table
	 * @return tableID or error string.
	 */
	public String addNewTable(String tableID, int pax) {
		String result = tableHandler.addNewTable(tableID, pax);
		if (result.equals(tableID)) {
			reservationHandler.addTableToSize(pax);
		}
		return result;
	}

	/**
	 * Removes a table from the restaurant. Only permitted if the table is "VACANT".
	 * 
	 * @param tableID String tableID used to uniquely identify a table 
	 * @return table removal status code: 1 if table is removed. 0 if table is not
	 *         present -1 if table is currently booked or occupied.
	 */
	public int removeTable(String tableID) {
		return tableHandler.removeTable(tableID);
	}

	/**
	 * Update table pax size. This allows the table size to be increased.
	 * 
	 * @param tableID String tableID used to uniquely identify a table 
	 * @param pax int pax to indicate the capacity of the table
	 * @return table update status code: 1 if table is updated. 0 if table is not
	 *         present -1 if table is currently booked or occupied, -2 for fatal
	 *         error (unexpected result)
	 */
	public int updateTable(String tableID, int pax) {
		return tableHandler.updateTable(tableID, pax);
	}

	/**
	 * Add reservation with customer's details, table size required and the
	 * associated date time.
	 * 
	 * @param pax      number of customers to be seated. Can be both odd or even
	 *                 values, classes will round up to nearest even number.
	 * @param name     customer name string to use when retrival for the record.
	 *                 Needs to be exact.
	 * @param number   phone number as a secondary tool for verification.
	 * @param dateTime LocalDateTime object for which the reservation is booked for,
	 *                 will be passed in by TableUI.
	 * @return whether there reservation is successfully added. -1 suggests invaid pax size, -2 suggests reservation for new datetime is full, 1 suggest successful update
	 */
	public int addReservation(int pax, String name, int number, LocalDateTime dateTime) {
		Customer cust = new Customer(name, number);

		// check if there is available table
		return reservationHandler.addReservation(cust, pax, dateTime); // will check if there is an available
																		// reservation
	}

	/**
	 * Remove reservation and update table status to vacant.
	 * 
	 * @param name     customer name string to use when retrival for the record.
	 *                 Needs to be exact.
	 * @param number   phone number as a secondary tool for verification.
	 * @param dateTime LocalDateTime object for which the reservation is booked for,
	 *                 will be passed in by TableUI.
	 * @return the number of guests originally booked for if successful, else -1.
	 */
	public int removeReservation(String name, int number, LocalDateTime dateTime) {
		Customer cust = new Customer(name, number);

		int tablePax = reservationHandler.removeReservation(cust, dateTime);
		// get reservation pax.
		return tablePax;
	}

	/**
	 * Checks if a customer has made a reservation at the given dateTime
	 * 
	 * @param name     customer name string to use when retrival for the record.
	 *                 Needs to be exact.
	 * @param number   phone number as a secondary tool for verification.
	 * @param dateTime LocalDateTime object for which the reservation is booked for,
	 *                 will be passed in by TableUI.
	 * @return whether there is a reservation - True if reservation exists, False
	 *         otherwise.
	 */
	public boolean checkReservation(String name, int number, LocalDateTime dateTime) {
		Customer cust = new Customer(name, number);
		return reservationHandler.checkReservation(cust, dateTime);
	}

	/**
	 * Update customer reservation
	 * 
	 * @param name     customer name string to use when retrival for the record.
	 *                 Needs to be exact.
	 * @param number   phone number as a secondary tool for verification.
	 * @param dateTime LocalDateTime object for which the reservation is booked for,
	 *                 will be passed in by TableUI.
	 * @param newPax   new number of guests expected at the table
	 * @param dateTime LocalDateTime object for which the reservation is booked for,
	 *                 will be passed in by TableUI.
	 * @return Reseration update status. -1 suggests invaid pax size, -2 suggests reservation for new datetime is full, 1 suggest successful update and -3 suggesets that old reservation cannot be removed.
	 */
	public int updateReservation(String name, int number, LocalDateTime dateTime, int newPax,
			LocalDateTime newDateTime) {

		Customer cust = new Customer(name, number);
		if (reservationHandler.removeReservation(cust, dateTime) != -1) {
			int result = reservationHandler.addReservation(cust, newPax, newDateTime);
			if (result < 0) {
				reservationHandler.addReservation(cust, newPax, dateTime); // revert the old record if the adding of a
																			// new record fails.
			}
			return result;
		}
		return -3;
	}

	/**
	 * Automatically reserve tables an hour after given dateTime (now). 
	 * Extracts the reservation list 1 hour prior to current datetime. 
	 * Removes these reservations as they have been overdue / lapsed. 
	 * 
	 * @return TableIDs that have been reserved.
	 */
	public ArrayList<String> reserveTables() {
		LocalDateTime dateTime = LocalDateTime.now();
		DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH");
		String strDateTime = dateTime.format(dtFormat);

		dateTime = LocalDateTime.parse(strDateTime, dtFormat);

		ArrayList<Reservation> reservationList = reservationHandler.retrieveNextReservationList(dateTime);
		if (reservationList == null) {
			return null;
		}
		return tableHandler.reserveTables(reservationList);
	}

	/**
	 * Automatically remove tables an hour before given dateTime (now). 
	 * Extracts the reservation list 1 hour prior to current datetime. 
	 * Removes these reservations as they have been overdue / lapsed. 
	 * 
	 * @return TableIDs that have been removed.
	 */

	public ArrayList<String> removeReservedTables() {
		LocalDateTime dateTime = LocalDateTime.now();
		DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH");
		String strDateTime = dateTime.format(dtFormat);

		dateTime = LocalDateTime.parse(strDateTime, dtFormat);

		ArrayList<Reservation> reservationList = reservationHandler.retrieveBeforeReservationList(dateTime);
		if (reservationList == null) {
			return null;
		}
		return tableHandler.removeReservations(reservationList);
	}

	/**
	 * Return current table availability for a given pax size
	 * 
	 * @param pax number of customers expected.
	 * @return True if table is available, else false.
	 */
	boolean checkTableAvailability(int pax) {
		return tableHandler.checkAvailability(pax);
	}

	/**
	 * Print all tables and their current status
	 * 
	 */
	void printTablesNow() {
		tableHandler.printAllTablesNow(); // shows the list of all available tables
	}

	@Override
	public void assignHandler(Handler h) {
		if (h.getType() == handlerType.TABLE) {
			tableHandler = (TableHandler) h;
		} else if (h.getType() == handlerType.RESERVATION)
			reservationHandler = (ReservationHandler) h;
		if (tableHandler != null && reservationHandler != null) {
			int[] tableSizes = tableHandler.getTableSizes();
			reservationHandler.setTableSize(tableSizes);
		}
		return;
	}

	@Override
	public void assignUI(UI ui) {
		tableUI = (TableUI) ui;
	}

}
