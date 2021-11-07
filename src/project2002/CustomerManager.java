package project2002;

import java.time.LocalDateTime;

import project2002.Restaurant.UIType;
import project2002.Restaurant.handlerType;

public class CustomerManager extends Manager {
	OrderHandler order;
	TableHandler table;
	ReservationHandler reservation;
	CustomerUI customerUI;

	public CustomerManager() {
		handlerList.add(handlerType.ORDER);
		handlerList.add(handlerType.TABLE);
		handlerList.add(handlerType.RESERVATION);
		type = UIType.CUSTOMER;
	}

	int newCustomerOrder(int pax, String name, int id, String title) { // new customer that did not reserve beforehand
		int orderID = 0;
		Staff staff = new Staff(name, id, title);
		String tableID = table.seatNewCustomer(pax); // assigns a table and returns the tableID
		orderID = order.createOrder(tableID, staff);// create an order
		return orderID;
	}

	int reservedCustomerOrder(int pax, String name, int id, String title, String customername, int number) {
		int orderID = 0;
		Staff staff = new Staff(name, id, title);
		Customer cust = new Customer(customername, number);
		LocalDateTime time = LocalDateTime.now();
		if (reservation.checkReservation(cust, time)) { // Check if the customer has made a reservatiion
			String tableID = table.seatBookedCustomer(pax);
			reservation.removeReservation(cust, time); // remove the reservation
			orderID = order.createOrder(tableID, staff);
		}
		return orderID;
	}

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

	@Override
	public void assignUI(UI ui) {
		customerUI = (CustomerUI) ui;
	}

}
