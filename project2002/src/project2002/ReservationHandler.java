package project2002;

import java.time.LocalDateTime;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * ReservationHandler class for managing reservations of the restaurant
 * @author 	Jermyn
 * @version 1.0
 * @since	2021-11-02 
 */


public class ReservationHandler {

	/**
	 * Mapping reservation time to a list of reservations booked for that timing.
	 */
	private TreeMap<LocalDateTime, ArrayList<Reservation>> reservations;

	/**
	 * Initializes the treemap 
	 */
	public ReservationHandler() {
		this.reservations = new TreeMap<LocalDateTime, ArrayList<Reservation>>();
	}

	/**
	 * Adds a new reservation to the restaurant
	 * @param cust 
	 * @param pax
	 * @param dateTime
	 * @return whether adding a reservation is successful - False if reservations are maxed, True otherwise.
	 */
	public boolean addReservation(Customer cust, int pax, LocalDateTime dateTime) {
		// TODO Auto-generated method stub
		ArrayList<Reservation> reservationList;
		if (!reservations.containsKey(dateTime)) {
			reservations.put(dateTime, new ArrayList<Reservation>()); 
		} 
		
		reservationList = reservations.get(dateTime);
		reservationList.add(new Reservation(pax, cust));
		return true;

		// check for maxed res. 
	}

	/**
	 * Checks if a customer has made a reservation at the given time 
	 * @param cust 
	 * @param dateTime
	 * @return whether there is a reservation - True of reservation exists, False otherwise.
	 */
	public boolean checkReservation(Customer cust, LocalDateTime dateTime) {
		ArrayList<Reservation> reservationList;
		reservationList = reservations.get(dateTime);
		for (Reservation r: reservationList){ 
			if (r.getCustomer() == cust) {
				return true; 
			}
		}
		return false;
	}

	/**
	 * Remove a customer's reservation at the given time 
	 * @param cust 
	 * @param dateTime
	 * @return whether removal of reservation is succsesful
	 */
	public boolean removeReservation(Customer cust, LocalDateTime dateTime) {
		ArrayList<Reservation> reservationList;
		reservationList = reservations.get(dateTime);
		for (Iterator<Reservation> it = reservationList.iterator(); it.hasNext();) {
			if (it.next().getCustomer() == cust) {
				it.remove();
				return true;
			}
		}
		return false;
	}

	/**
	 * Retrieve list of reservation
	 * @param dateTime
	 * @return ArrayList<Reservation> of all reservations at the current dateTime
	 */
	public ArrayList<Reservation> retrieveReservationList(LocalDateTime dateTime) {
		// TODO Auto-generated method stub
		return reservations.get(dateTime);
	}


	/**
	 * Retrieve list of reservation
	 * @param dateTime
	 * @return ArrayList<Reservation> of all reservations 1 hour before current dateTime
	 */
	public ArrayList<Reservation> retrieveNextReservationList(LocalDateTime dateTime) {
		// TODO Auto-generated method stub
		dateTime = dateTime.minusMinutes(60);
		return reservations.get(dateTime);
	}


	/**
	 * Retrieve list of reservation
	 * @param dateTime
	 * @return ArrayList<Reservation> of all reservations 1 hour after current dateTime
	 */
	public ArrayList<Reservation> retrieveBeforeReservationList(LocalDateTime dateTime) {
		// TODO Auto-generated method stub
		dateTime = dateTime.plusMinutes(60);
		return reservations.get(dateTime);
	}

}
