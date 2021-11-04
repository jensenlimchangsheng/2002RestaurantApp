package project2002;

/**
 * ReservationHandler class for managing reservations of the restaurant
 * @author 	Jermyn
 * @version 1.0
 * @since	2021-11-02 
 */


public class Reservation {
    private int Pax;
    private Customer customer; 

    public Reservation(int Pax, Customer customer) {
        this.Pax = Pax;
        this.customer = customer;
    }
    
    public Customer getCustomer() {
        return customer;
    }

    public int getPax() {
        return Pax;
    }
    

}