package project2002;

import java.util.ArrayList;
import java.util.TreeMap;

import project2002.Restaurant.handlerType;

/**
 * TableHandler class for managing tables at the current point in time.
 * 
 * @author Shun Yao
 * @version 1.1
 * @since 2021-11-07
 */

public class TableHandler extends Handler {

	/**
	 * Mapping table to tableID
	 */
	private TreeMap<String, Table> curTableList;

	/**
	 * Creates a new list of tables with mapping to their tableID
	 */
	public TableHandler() {
		type = handlerType.TABLE;
		this.curTableList = new TreeMap<String, Table>();
	}

	/**
	 * Checks for an available table for a certain number of pax at the current timing.
	 * Assumes that for pax of size n, they can only be seated at tables of size (n+ n%2).
	 * 
	 * @param pax
	 * @return True or False depending on table availability
	 */
	public boolean checkAvailability(int pax) {
		for (Table table : curTableList.values()) {
			if (table.getTableSize() == pax + pax % 2 && table.getStatus() == TableStatus.VACANT) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Prints all available tables now
	 */
	public void printAvailableTablesNow() {
		for (Table table : curTableList.values()) {
			if (table.getStatus() == TableStatus.VACANT)
				System.out.println(table);
		}
	}

	/**
	 * Passes back to TableManager the current table list
	 * 
	 * @return an int array of size 5 with index[i] = number of tables of capacity i
	 */

	public int[] getTableSizes() {
		int tableStats[] = new int[] { 0, 0, 0, 0, 0 };
		for (Table table : curTableList.values()) {
			tableStats[table.getTableSize() / 2 - 1]++;
		}
		return tableStats;
	}

	/**
	 * Generic method for setting a table's status, to be called by other methods
	 * seatNewCustomer, seatBookedCustomer and reserveTables
	 * Assumes that for pax of size n, they can only be seated at tables of size (n+ n%2).
	 * @param pax
	 * @param required status to match
	 * @param desired  status to set to
	 * @return TableID if table status setting is successful, else "TableNotFound"
	 */
	private String setTableStatus(int pax, TableStatus required, TableStatus desired) {
		for (Table table : curTableList.values()) {
			if (table.getTableSize() == pax + pax % 2 && table.getStatus() == required) {
				table.setStatus(desired);
				return table.getTableID();
			}
		}
		return "TableNotFound";
	}

	/**
	 * Seats a walk-in customer with no booking.
	 * Assumes that for pax of size n, they can only be seated at tables of size (n+ n%2).
	 * @param pax
	 * @return TableID if the table is assigned, else "NoTablesAvailable"
	 */
	public String seatNewCustomer(int pax) {
		if (this.checkAvailability(pax)) {
			return this.setTableStatus(pax, TableStatus.VACANT, TableStatus.OCCUPIED);
		}
		return "NoTablesAvailable";
	}

	/**
	 * Seats a customer with booking.
	 * Assumes that for pax of size n, they can only be seated at tables of size (n+ n%2).
	 * @param pax
	 * @return TableID if the table is assigned, else "NoTablesAvailable"
	 */
	public String seatBookedCustomer(int pax) {
		return this.setTableStatus(pax, TableStatus.RESERVED, TableStatus.OCCUPIED);
	}

	/**
	 * Adds a new possible table to the list of all tables.
	 * 
	 * @param tableID
	 * @param pax
	 * @return whether table adding is successful - any present table with the same
	 *         ID will return false.
	 */
	public String addNewTable(String tableID, int pax) {
		if (this.curTableList.putIfAbsent(tableID, new Table(tableID, pax)) != null)
			return "TableAlreadyExists";
		return tableID;
	}

	/**
	 * Removes a table from tables that can be booked Requires the table to be
	 * `vacant` at the moment
	 * 
	 * @param tableID
	 * @return 1 if table is removed. 0 if table is not present -1 if table is
	 *         currently booked or occupied,
	 */
	public int removeTable(String tableID) {
		Table desiredTable = this.curTableList.get(tableID);
		if (desiredTable == null)
			return 0;
		if (this.curTableList.get(tableID).getStatus() != TableStatus.VACANT)
			return -1;
		this.curTableList.remove(tableID);
		return 1;
	}

	/**
	 * Update number of pax available for all tables, present and future. Requires
	 * the table to be `vacant` at the moment
	 * 
	 * @param tableID
	 * @param pax
	 * @return 1 if table is updated. 0 if table is not present -1 if table is
	 *         currently booked or occupied, -2 for fatal error (unexpected result)
	 */
	public int updateTable(String tableID, int pax) {
		switch (this.removeTable(tableID)) {
		case 0:
			return 0;
		case -1:
			return -1;
		case 1:
			if (!this.addNewTable(tableID, pax).equals("TableAlreadyExists"))
				return 1;
		default:
			return -2;
		}
	}

	/**
	 * Reserves tables for the current timeslot.
	 * 
	 * @param reservationList
	 * @return true if successfully reserved (should always be the case)
	 */
	public boolean reserveTables(ArrayList<Reservation> reservationList) {
		for (Reservation reservation : reservationList) {
			if (this.setTableStatus(reservation.getPax(), TableStatus.VACANT, TableStatus.RESERVED)
					.equals("NoTablesAvailable"))
				return false;
		}
		return true;
	}

	/**
	 * Resets all table status to Vacant
	 */
	protected void resetTables() {
		for (Table table : curTableList.values()) {
			table.setStatus(TableStatus.VACANT);
		}
	}

	/**
	 * Reverses the effects of reserveTables by unreserving them
	 * 
	 * @param reservationList
	 * @return true if successfully reserved (should always be the case)
	 */
	public boolean removeReservations(ArrayList<Reservation> reservationList) {
		for (Reservation reservation : reservationList) {
			if (this.setTableStatus(reservation.getPax(), TableStatus.RESERVED, TableStatus.VACANT)
					.equals("NoTablesAvailable"))
				return false;
		}
		return true;
	}

	public String assignTable(int pax) {
		return null;
	}

}