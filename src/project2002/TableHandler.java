package project2002;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * TableHandler class for managing tables at the current point in time.
 * @author 	Shun Yao
 * @version 1.0
 * @since	2021-11-02 
 */

public class TableHandler {
	
	/**
	 * Mapping table to tableID
	 */
	private TreeMap<String, Table> curTableList;
	
	
	/**
	 * Creates a new list of tables with mapping to their tableID
	 */
	public TableHandler() {
		this.curTableList = new TreeMap<String,Table>();
	}
	
	/**
	 * Checks for an available table for a certain number of pax at the current timing
	 * @param pax
	 * @return True or False depending on table availability
	 */
	public boolean checkAvailability(int pax) {		
		for (Table table: curTableList.values()) {
			if (table.getTableSize() == pax + pax%2 && table.getStatus() == TableStatus.VACANT) {
				return true;
			}
		}
		return false;
	}
	
	public void printAvailableTablesNow() {
		for (Table table: curTableList.values()) {
			if (table.getStatus() == TableStatus.VACANT)
				System.out.println(table);
		}
	}

	/**
	 * Generic method for setting a table's status, to be called by other methods seatNewCustomer, seatBookedCustomer and reserveTables 
	 * @param pax
	 * @param status
	 * @return TableID if table status setting is successful, else "TableNotFound"
	 */
	private String setTableStatus(int pax, TableStatus required, TableStatus desired) {
		for (Table table: curTableList.values()) {
			if (table.getTableSize() == pax + pax%2 && table.getStatus() == required) {
				table.setStatus(desired);
				return table.getTableID();
			}
		}
		return "TableNotFound";
	}
	
	/**
	 * Seats a walk-in customer with no booking
	 * @param pax
	 * @return TableID if the table is assigned, else "NoTablesAvailable"
	 */
	public String seatNewCustomer(int pax) {
		if (this.checkAvailability(pax)) {
			return this.setTableStatus(pax, TableStatus.VACANT, TableStatus.OCCUPIED);
		} return "NoTablesAvailable";
	}
	
	/**
	 * Seats a customer with booking
	 * ASSUMES THAT THE CUSTOMER HAS A VALID BOOKING
	 * @param pax 
	 * @return TableID if the table is assigned, else "NoTablesAvailable"
	 */
	public String seatBookedCustomer(int pax) {
		return this.setTableStatus(pax, TableStatus.RESERVED, TableStatus.OCCUPIED);
	}
	
	/**
	 * Adds a new possible table to the list of all tables.
	 * @param tableID 
	 * @param pax
	 * @return whether table adding is successful - any present table with the same ID will return false.
	 */
	public String addNewTable(String tableID, int pax) {
		if (this.curTableList.putIfAbsent(tableID, new Table(tableID, pax)) != null)
			return "TableAlreadyExists";
		return tableID;
	}

	/**
	 * Removes a table from tables that can be booked
	 * Requires the table to be `vacant` at the moment
	 * @param tableID
	 * @return 
	 *  1 if table is removed.
	 *  0 if table is not present
	 * -1 if table is currently booked or occupied, 
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
	 * Update number of pax available for all tables, present and future.
	 * Requires the table to be `vacant` at the moment
	 * @param tableID
	 * @param pax
	 * @return 
	 *  1 if table is updated.
	 *  0 if table is not present
	 * -1 if table is currently booked or occupied,
	 * -2 for fatal error (unexpected result)
	 */
	
	public int updateTable(String tableID, int pax) {
		switch(this.removeTable(tableID)) {
		case 0:
			return 0;
		case -1: 
			return -1;
		case 1:
			if (!this.addNewTable(tableID, pax).equals("TableAlreadyExists"))
				return 1;
		default: return -2;
		}
	}

	/**
	 * Reserves tables for the current timeslot.
	 * @param reservationList
	 * @return true if successfully reserved (should always be the case)
	 */
	public boolean reserveTables(ArrayList<Reservation> reservationList) {
		for (Reservation reservation: reservationList) {
			if (this.setTableStatus(reservation.getNumOfPax(), TableStatus.VACANT, TableStatus.RESERVED).equals("NoTablesAvailable"))
				return false;
		}
		return true;
	}
	
	/**
	 * Resets all table status to Vacant
	 */
	protected void resetTables() {
		for (Table table: curTableList.values()) {
			table.setStatus(TableStatus.VACANT);
		}
	}

	/**
	 * Reverses the effects of reserveTables by unreserving them
	 * @param reservationList
	 * @return true if successfully reserved (should always be the case)
	 */
	public boolean removeReservations(ArrayList<Reservation> reservationList) {
		for (Reservation reservation: reservationList) {
			if (this.setTableStatus(reservation.getNumOfPax(), TableStatus.RESERVED, TableStatus.VACANT).equals("NoTablesAvailable"))
				return false;
		}
		return true;
	}

}
