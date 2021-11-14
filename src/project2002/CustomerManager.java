package project2002;

import java.time.LocalDateTime;

import project2002.Restaurant.UIType;
import project2002.Restaurant.handlerType;

/**
 * CustomerManager class for managing new customer for the Restaurant.
 * 
 * @author Zhi Kai
 * @version 1.0
 * @since 2021-11-09
 */

public class CustomerManager extends Manager {
	/**
	 * To create and close orders for the customer
	 */
	OrderHandler order;

	/**
	 * To assign the necessary tables to the customer
	 */
	TableHandler table;

	/**
	 * To check if the customer has made a prior reservation
	 */
	ReservationHandler reservation;

	/**
	 * To interact with the user to get inputs
	 */
	CustomerUI customerUI;

	/**
	 * Constructor for customer manager
	 * 
	 * @return CustomerManager object
	 */
	public CustomerManager() {
		handlerList.add(handlerType.ORDER);
		handlerList.add(handlerType.TABLE);
		handlerList.add(handlerType.RESERVATION);
		type = UIType.CUSTOMER;
	}

	/**
	 * Creating an order for a walk in customer and assigns a staff member to the
	 * customer
	 * 
	 * @param pax
	 * @param staff_name
	 * @param staff_id
	 * @param staff_title
	 * @return order id that is linked to the new customer
	 */
	int newCustomerOrder(int pax, String name, int id, String title) { // new customer that did not reserve beforehand
		int orderID = 0;
		Staff staff = new Staff(name, id, title);
		String tableID = table.seatNewCustomer(pax); // assigns a table and returns the tableID
		if (tableID.equals("NoTablesAvailable"))
			return 0;
		else
			System.out.printf("The following table has been assigned %s\n", tableID);
		orderID = order.createOrder(tableID, staff);// create an order
		return orderID;
	}

	/**
	 * Creating an order for a customer with a prior reservation and assigns a staff
	 * member to the customer
	 * 
	 * @param pax
	 * @param staff_id
	 * @param staff_name
	 * @param staff_title
	 * @param customer_name
	 * @param customer_phone_number
	 * @return order id that is linked to the new customer
	 */
	int reservedCustomerOrder(int pax, String name, int id, String title, String customername, int number) {
		int orderID = 0;
		Staff staff = new Staff(name, id, title);
		Customer cust = new Customer(customername, number);
		LocalDateTime time = LocalDateTime.now();
		if (reservation.checkReservation(cust, time)) { // Check if the customer has made a reservatiion
			String tableID = table.seatBookedCustomer(pax);
			System.out.printf("The following table has been assigned %s\n", tableID);
			reservation.removeReservation(cust, time); // remove the reservation
			orderID = order.createOrder(tableID, staff);
		} else
			return 0;
		return orderID;
	}

	/**
	 * Closes the order for a customer and checks if there are any discount
	 * available for the customer
	 * 
	 * 
	 * @param orderID
	 * @return -1 if invalid order and 0 if valid order
	 */
	int closeCustomerOrder(int orderID) {
		Double discount = customerUI.getDiscount();
		Order closedOrder = order.printInvoice(orderID, discount);
		if (closedOrder == null) {
			System.out.println("Invalid Order ID");
			return -1;
		} else {
			SalesReportManager.addOrder(closedOrder);
			table.unseatCustomer(closedOrder.getTableID());
			return 0;
		}
	}

	/**
	 * Assigns handlers to customer manager
	 * 
	 */
	@Override
	public void assignHandler(Handler h) {
		if (h.getType() == handlerType.ORDER)
			order = (OrderHandler) h;
		else if (h.getType() == handlerType.TABLE)
			table = (TableHandler) h;
		else if (h.getType() == handlerType.RESERVATION)
			reservation = (ReservationHandler) h;
		return;

	}

	/**
	 * Assigns UIs to customer manager
	 * 
	 */
	@Override
	public void assignUI(UI ui) {
		customerUI = (CustomerUI) ui;
	}

}
