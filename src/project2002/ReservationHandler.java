package project2002;

import java.time.LocalDateTime;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.Iterator;
import project2002.Restaurant.handlerType;

/**
 * ReservationHandler class for managing reservations of the restaurant
 * 
 * @author Jermyn
 * @version 1.0
 * @since 2021-11-02
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
	private int[] tableSizes = {3,3,3,3,3}; // [qty for pax size: 2, qty for pax size: 4, qty for pax size: 6, qty for pax
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
	 * Adds a new reservation to the restaurant
	 * 
	 * @param cust
	 * @param pax
	 * @param dateTime
	 * @return whether adding a reservation is successful - False if reservations
	 *         are maxed, True otherwise.
	 */
	public int addReservation(Customer cust, int pax, LocalDateTime dateTime) {
		ArrayList<Reservation> reservationList;
		if (!reservations.containsKey(dateTime)) {
			reservations.put(dateTime, new ArrayList<Reservation>());
			availTableSizes.put(dateTime, this.tableSizes);
		}

		if ( pax > 10) { return -1; }
		if ( pax % 2 == 0) { pax ++; }

		// checking for maxed reservation.
		if (this.availTableSizes.get(dateTime)[Math.floorDiv(pax, 2) - 1] == 0) { // if there is not more available table for
																				// the pax,
			return -2;
		}

		reservationList = reservations.get(dateTime);
		reservationList.add(new Reservation(pax, cust));
		availTableSizes.get(dateTime)[Math.floorDiv(pax, 2) - 1] -= 1;
		return 1;
	}

	/**
	 * Checks if a customer has made a reservation at the given time
	 * 
	 * @param cust
	 * @param dateTime
	 * @return whether there is a reservation - True of reservation exists, False
	 *         otherwise.
	 */
	public boolean checkReservation(Customer cust, LocalDateTime dateTime) {
		ArrayList<Reservation> reservationList;
		reservationList = reservations.get(dateTime);
		for (Reservation r : reservationList) {
			Customer reservationCust = r.getCustomer();
			if (reservationCust.equals(cust)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Remove a customer's reservation at the given time
	 * 
	 * @param cust
	 * @param dateTime
	 * @return whether removal of reservation is succsesful
	 */
	public boolean removeReservation(Customer cust, LocalDateTime dateTime) {
		ArrayList<Reservation> reservationList;
		reservationList = reservations.get(dateTime);
		if (reservationList != null){
			for (Iterator<Reservation> it = reservationList.iterator(); it.hasNext();) {
				Reservation currRes = it.next();
				Customer reservationCust = currRes.getCustomer();
				int pax = currRes.getPax();
				if (reservationCust.equals(cust)) {
					// this will need to be updated for customer class
					it.remove();
					availTableSizes.get(dateTime)[Math.floorDiv(pax, 2) - 1] += 1;
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Retrieve list of reservation
	 * 
	 * @param dateTime
	 * @return ArrayList<Reservation> of all reservations at the current dateTime
	 */
	public ArrayList<Reservation> retrieveReservationList(LocalDateTime dateTime) {
		// TODO Auto-generated method stub
		return reservations.get(dateTime);
	}

	/**
	 * Retrieve list of reservation
	 * 
	 * @param dateTime
	 * @return ArrayList<Reservation> of all reservations 1 hour before current
	 *         dateTime
	 */
	public ArrayList<Reservation> retrieveNextReservationList(LocalDateTime dateTime) {
		// TODO Auto-generated method stub
		dateTime = dateTime.minusMinutes(60);
		return reservations.get(dateTime);
	}

	/**
	 * Retrieve list of reservation
	 * 
	 * @param dateTime
	 * @return ArrayList<Reservation> of all reservations 1 hour after current
	 *         dateTime
	 */
	public ArrayList<Reservation> retrieveBeforeReservationList(LocalDateTime dateTime) {
		// TODO Auto-generated method stub
		dateTime = dateTime.plusMinutes(60);
		return reservations.get(dateTime);
	}

	public void setTableSize(int[] tableSizes) {
		this.tableSizes = tableSizes;
	}

}
