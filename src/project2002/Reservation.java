package project2002;

/**
 * ReservationHandler class for managing reservations of the restaurant
 * 
 * @author Jermyn
 * @version 1.0
 * @since 2021-11-02
 */

public class Reservation {

    private Customer customer;
    private int pax;

    public Reservation(int pax, Customer customer) {
        this.pax = pax;
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public int getPax() {
        return pax;
    }

}
