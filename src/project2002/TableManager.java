package project2002;

import java.time.LocalDateTime;
import java.util.ArrayList;

import project2002.Restaurant.UIType;
import project2002.Restaurant.handlerType;

/**
 * TableManager class for managing table bookings for the Restaurant.
 * 
 * @author Jermyn
 * @version 1.0
 * @since 2021-11-07
 */

public class TableManager extends Manager {
	TableHandler tableHandler;
	ReservationHandler reservationHandler;
	TableUI tableUI;

	public TableManager() {
		this.tableHandler = new TableHandler();

		// @ shun yao return the tableSizes here. Type: int[] = new int[5]. index 0 >
		// capacity for pax size 2; index 1 > capacity for pax size 4 and so on.
		int[] tableSizes = tableHandler.getTableSizes();
		this.reservationHandler = new ReservationHandler(tableSizes);

		handlerList.add(tableHandler);
		handlerList.add(reservationHandler);
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
		return tableHandler.addNewTable(tableID, pax);
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
	public boolean addReservation(int pax, String name, int number, LocalDateTime dateTime) {
		Customer cust = new Customer(name, number);

		// check if there is available table
		return reservationHandler.addReservation(cust, pax, dateTime); // will check if there is an available
																		// reservation
	}

	/**
	 * Add reservation
	 * 
	 * @param name
	 * @param number
	 * @param dateTime
	 * @return whether there reservation is successfully removed.
	 */
	public boolean removeReservation(String name, int number, LocalDateTime dateTime) {
		Customer cust = new Customer(name, number);
		return reservationHandler.removeReservation(cust, dateTime);
	}

	/**
	 * Checks if a customer has made a reservation at the given time
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
		if (reservationHandler.addReservation(cust, newPax, newDateTime)) {
			if (reservationHandler.removeReservation(cust, dateTime)) {
				return 1;
			}
			return -1;
		}
		return -2;
	}

	public boolean reserveTables() {
		LocalDateTime time = LocalDateTime.now();
		ArrayList<Reservation> reservationList = reservationHandler.retrieveNextReservationList(time);
		return tableHandler.reserveTables(reservationList);
	}

	public boolean removeReservedTables() {
		LocalDateTime time = LocalDateTime.now();
		ArrayList<Reservation> reservationList = reservationHandler.retrieveBeforeReservationList(time);
		return tableHandler.removeReservations(reservationList);
	}

	boolean checkTableAvailability(int pax) {
		return tableHandler.checkAvailability(pax);
	}

	void printAvailableTablesNow() {
		tableHandler.printAvailableTablesNow(); // shows the list of all available tables
	}

	@Override
	public void assignHandler(Handler h) {
		if (h.getType() == handlerType.TABLE)
			tableHandler = (TableHandler) h;
		else if (h.getType() == handlerType.RESERVATION)
			reservationHandler = (ReservationHandler) h;
		return;
	}

	@Override
	public void assignUI(UI ui) {
		tableUI = (TableUI) ui;
	}

}
