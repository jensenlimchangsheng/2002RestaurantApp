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

		// /**
		//  * initialize dummy variables for testing.
		//  */
		// DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH");
		// LocalDateTime overdue = LocalDateTime.parse("12/11/2021 23", format);
		// LocalDateTime full = LocalDateTime.parse("13/11/2021 21", format);
		// LocalDateTime reservenext = LocalDateTime.parse("13/11/2021 01", format);
		// LocalDateTime toremove = LocalDateTime.parse("13/11/2021 12", format);
		// Customer cust1 = new Customer("jenny", 81239123);
		// Customer cust2 = new Customer("john", 81239113);
		// Customer cust3 = new Customer("bob", 81239124);
		// Customer cust4 = new Customer("a", 81239124);
		// Customer cust5 = new Customer("b", 81239124);
		// Customer cust6 = new Customer("c", 81239124);
		// Customer cust7 = new Customer("zhi kai", 12345678);
		// Customer cust8 = new Customer("e", 81239124);
		// Customer cust9 = new Customer("zhi kai", 12345678);

		// ArrayList<Reservation> reservations1 = new ArrayList<Reservation>();
		// reservations1.add(new Reservation(2, cust1));
		// reservations1.add(new Reservation(6, cust2));

		// ArrayList<Reservation> reservations3 = new ArrayList<Reservation>();
		// reservations3.add(new Reservation(4, cust7));
		// reservations3.add(new Reservation(2, cust8));

		// ArrayList<Reservation> reservations2 = new ArrayList<Reservation>();
		// reservations2.add(new Reservation(4, cust3));
		// reservations2.add(new Reservation(6, cust4));
		// reservations2.add(new Reservation(2, cust5));
		// reservations2.add(new Reservation(2, cust6));

		// ArrayList<Reservation> reservations4 = new ArrayList<Reservation>();
		// reservations4.add(new Reservation(2, cust9));

		// this.reservations.put(overdue, reservations1);
		// this.reservations.put(full, reservations2);
		// this.reservations.put(reservenext, reservations3);
		// this.reservations.put(toremove, reservations4);

		// this.availTableSizes = new TreeMap<LocalDateTime, int[]>();

		// int[] tableSizes = { 2, 2, 0, 0, 0 };
		// this.availTableSizes.put(full, tableSizes);
		// this.availTableSizes.put(toremove, tableSizes);

		type = handlerType.RESERVATION;
	}

	/**
	 * Adds a new reservation to the restaurant. Checks if the datetime exists as a
	 * key in existing reservations. if it doesn't exist, the arraylist of
	 * reservations will be initialized Subsequently, the values will be appended to
	 * the arraylist, reservationList accordingly
	 * 
	 * @param cust customer information as a customer object.
	 * @param pax number of customers for the reservation
	 * @param dateTime reservation datetime in LocalDateTime format.
	 * @return -1 if invalid pax size, -2 if the reservations are full and 1 for success. 
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
	 * @param cust customer information as a customer object.
	 * @param dateTime reservation datetime in LocalDateTime format.
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
	 * @param cust customer information as a customer object.
	 * @param dateTime reservation datetime in LocalDateTime format.
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
	 * @param dateTime reservation datetime in LocalDateTime format.
	 * @return ArrayList<Reservation> of all reservations at the current dateTime
	 */
	public ArrayList<Reservation> retrieveReservationList(LocalDateTime dateTime) {
		return reservations.get(dateTime);
	}

	/**
	 * Retrieve list of reservation 1 hour after current datetime
	 * 
	 * @param dateTime reservation datetime in LocalDateTime format.
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
	 * @param dateTime reservation datetime in LocalDateTime format.
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
	 * @param tableSizes int[] of table sizes, available table count for: [pax = 2, pax = 4, pax = 6, pax = 8, pax = 10]. 
	 */
	public void setTableSize(int[] tableSizes) {
		this.tableSizes = tableSizes;
	}

	/**
	 * Update the capacity tracker for table available for each timing and for the
	 * initialization
	 * 
	 * @param pax number of customers for the reservation to be cancelled
	 */
	public void addTableToSize(int pax) {
		this.tableSizes[Math.floorDiv(pax, 2) - 1] += 1;
		for (var dateTimeTable : availTableSizes.entrySet()) {
			int[] tableSize = dateTimeTable.getValue();
			tableSize[Math.floorDiv(pax, 2) - 1] += 1;
		}
	}

}
