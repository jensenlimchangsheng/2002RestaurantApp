package project2002;

import java.time.LocalDateTime;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.time.temporal.ChronoUnit;

import project2002.Restaurant.handlerType;

/**
 * ReservationHandler class for managing reservations of the restaurant
 * 
 * @author Jermyn
 * @version 2.0
 * @since 2021-11-14
 */

public class ReservationHandler extends Handler {

	/**
	 * Mapping reservation time to a list of reservations booked for that timing.
	 */
	private TreeMap<LocalDateTime, ArrayList<Reservation>> reservations;

	/**
	 * Mapping available table sizes to its quantity every datetime.
	 */
	private TreeMap<LocalDateTime, int[]> availTableSizes;

	/**
	 * Mapping available table sizes to its quantity
	 */
	private int[] tableSizes = { 2, 3, 1, 1, 1 }; // [qty for pax size: 2, qty for pax size: 4, qty for pax size: 6, qty
													// for pax
	// size: 8, qty for pax size: 10].

	/**
	 * Initializes variables
	 */
	public ReservationHandler() {
		this.reservations = new TreeMap<LocalDateTime, ArrayList<Reservation>>();
		this.availTableSizes = new TreeMap<LocalDateTime, int[]>();

		type = handlerType.RESERVATION;
	}

	/**
	 * Adds a new reservation to the restaurant. Checks if the datetime exists as a
	 * key in existing reservations. if it doesn't exist, the arraylist of
	 * reservations will be initialized Subsequently, the values will be appended to
	 * the arraylist, reservationList accordingly
	 * 
	 * @param cust
	 * @param pax
	 * @param dateTime
	 * @return -1 if invalid pax size, -2 if the reservations are full and 1 for
	 *         success.
	 */
	public int addReservation(Customer cust, int pax, LocalDateTime dateTime) {
		ArrayList<Reservation> reservationList;
		if (!reservations.containsKey(dateTime)) {
			reservations.put(dateTime, new ArrayList<Reservation>());
			availTableSizes.put(dateTime, this.tableSizes);
		}

		if (pax > 10) {
			return -1;
		}
		if (pax % 2 == 1) {
			pax++;
		}

		// checking for maxed reservation.
		if (this.availTableSizes.get(dateTime)[Math.floorDiv(pax, 2) - 1] == 0) { // if there is not more available
																					// table for
																					// the pax,
			return -2;
		}

		reservationList = reservations.get(dateTime);
		reservationList.add(new Reservation(pax, cust));
		availTableSizes.get(dateTime)[Math.floorDiv(pax, 2) - 1] -= 1;
		return 1;
	}

	/**
	 * Checks if a customer has made a reservation at the given datetime
	 * 
	 * @param cust
	 * @param dateTime
	 * @return whether there is a reservation - True of reservation exists, False
	 *         otherwise.
	 */
	public boolean checkReservation(Customer cust, LocalDateTime dateTime) {
		ArrayList<Reservation> reservationList;
		dateTime = dateTime.truncatedTo(ChronoUnit.HOURS);
		reservationList = reservations.get(dateTime);
		if (reservationList != null) {
			for (Reservation r : reservationList) {
				Customer reservationCust = r.getCustomer();
				if (reservationCust.equals(cust)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Cancel a customer's reservation given their information and reservation
	 * datetime.
	 * 
	 * @param cust
	 * @param dateTime
	 * @return the pax size of the reservation or -1 if reservation doesn't exist.
	 */
	public int removeReservation(Customer cust, LocalDateTime dateTime) {
		ArrayList<Reservation> reservationList;
		reservationList = reservations.get(dateTime);
		if (reservationList != null) {
			for (Iterator<Reservation> it = reservationList.iterator(); it.hasNext();) {
				Reservation currRes = it.next();
				Customer reservationCust = currRes.getCustomer();
				int pax = currRes.getPax();
				if (reservationCust.equals(cust)) {
					// this will need to be updated for customer class
					it.remove();
					availTableSizes.get(dateTime)[Math.floorDiv(pax, 2) - 1] += 1;
					return pax;
				}
			}
		}
		return -1;
	}

	/**
	 * Retrieve list of reservation at current datetime
	 * 
	 * @param dateTime
	 * @return ArrayList<Reservation> of all reservations at the current dateTime
	 */
	public ArrayList<Reservation> retrieveReservationList(LocalDateTime dateTime) {
		return reservations.get(dateTime);
	}

	/**
	 * Retrieve list of reservation 1 hour after current datetime
	 * 
	 * @param dateTime
	 * @return ArrayList<Reservation> of all reservations 1 hour before c1urrent
	 *         dateTime
	 */
	public ArrayList<Reservation> retrieveNextReservationList(LocalDateTime dateTime) {
		dateTime = dateTime.plusHours(1);
		System.out.printf(dateTime.toString() + "\n");
		return reservations.get(dateTime);
	}

	/**
	 * Retrieve list of reservation 1 hour before current datetime
	 * 
	 * @param dateTime
	 * @return ArrayList<Reservation> of all reservations 1 before after current
	 *         dateTime
	 */
	public ArrayList<Reservation> retrieveBeforeReservationList(LocalDateTime dateTime) {
		dateTime = dateTime.minusHours(1);
		return reservations.get(dateTime);
	}

	/**
	 * Setter function for table sizes
	 * 
	 * @param tableSizes
	 */
	public void setTableSize(int[] tableSizes) {
		this.tableSizes = tableSizes;
	}

	/**
	 * Update the capacity tracker for table available for each timing and for the
	 * initialization
	 * 
	 * @param pax
	 */
	public void addTableToSize(int pax) {
		this.tableSizes[Math.floorDiv(pax, 2) - 1] += 1;
		for (var dateTimeTable : availTableSizes.entrySet()) {
			int[] tableSize = dateTimeTable.getValue();
			tableSize[Math.floorDiv(pax, 2) - 1] += 1;
		}
	}

}
