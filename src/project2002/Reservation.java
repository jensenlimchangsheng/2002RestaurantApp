package project2002;

/**
 * ReservationHandler class for managing reservations of the restaurant
 * 
 * @author Jermyn
 * @version 1.0
 * @since 2021-11-02
 */

public class Reservation {

	/**
	 * Track customer information for the reservation
	 */
    private Customer customer;
    
	/**
	 * Number of customers for this reservation
	 */
    private int pax;

	/**
	 * Initialize reservation
     * 
     * @param pax The number of customers for this reservation
     * @param pax Customer information for this reservation
	 */
    public Reservation(int pax, Customer customer) {
        this.pax = pax;
        this.customer = customer;
    }

    /**
	 * Getter function for customer information
	 */
    public Customer getCustomer() {
        return customer;
    }

    /**
	 * Getter function for the number of pax. 
	 */
    public int getPax() {
        return pax;
    }

}
