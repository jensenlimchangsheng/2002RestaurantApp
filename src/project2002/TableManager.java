package project2002;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import project2002.Restaurant.UIType;
import project2002.Restaurant.handlerType;

/**
 * TableManager class for managing table bookings for the Restaurant.
 * 
 * @author Jermyn, Shun Yao
 * @version 1.0
 * @since 2021-11-07
 */

public class TableManager extends Manager {
	TableHandler tableHandler;
	ReservationHandler reservationHandler;
	TableUI tableUI;

	public TableManager() {

		handlerList.add(handlerType.TABLE);
		handlerList.add(handlerType.RESERVATION);
		type = UIType.TABLE;
	}

	/**
	 * Adds a table to the restaurant
	 * 
	 * @param tableID
	 * @param pax
	 * @return tableID or error string.
	 */
	public String addNewTable(String tableID, int pax) {
		String result = tableHandler.addNewTable(tableID, pax);
		if (result == tableID) {
			reservationHandler.addTableToSize(pax);
		}
		return result;
	}

	/**
	 * Removes a table from the restaurant
	 * 
	 * @param tableID
	 * @return table removal status
	 */
	public int removeTable(String tableID) {
		return tableHandler.removeTable(tableID);
	}

	/**
	 * Update table pax
	 * 
	 * @param tableID
	 * @param pax
	 * @return Table update status
	 */
	public int updateTable(String tableID, int pax) {
		return tableHandler.updateTable(tableID, pax);
	}

	/**
	 * Add reservation
	 * 
	 * @param pax
	 * @param name
	 * @param number
	 * @param dateTime
	 * @return whether there reservation is successfully added.
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
	 * @param name
	 * @param number
	 * @param dateTime
	 * @return whether there reservation is successfully removed.
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
	 * @param cust
	 * @param dateTime
	 * @return whether there is a reservation - True of reservation exists, False
	 *         otherwise.
	 */
	public boolean checkReservation(String name, int number, LocalDateTime dateTime) {
		Customer cust = new Customer(name, number);
		return reservationHandler.checkReservation(cust, dateTime);
	}

	/**
	 * Update customer reservation
	 * 
	 * @param cust
	 * @param dateTime
	 * @return Reseration update status.
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
	 * Automatically update tables at given dateTime
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
	 * Automatically remove tables at given dateTime
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
	 * Return table availability for a given pax size
	 * 
	 * @param pax
	 * @return table availability.
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
