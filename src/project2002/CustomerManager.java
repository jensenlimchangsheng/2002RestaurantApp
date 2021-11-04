package project2002;

import java.time.LocalDateTime;

import project2002.Restaurant.UIType;
import project2002.Restaurant.handlerType;

public class CustomerManager extends Manager {
	OrderHandler order= new OrderHandler();
	TableHandler table= new TableHandler();
	ReservationHandler reservation= new ReservationHandler();
	CustomerUI customerUI;
	
	public CustomerManager() {
		handlerList.add(order);
		handlerList.add(table);
		handlerList.add(reservation);
		type=UIType.CUSTOMER;
	}

	int newCustomerOrder(int pax , String name, int id, String title){ // new customer that did not reserve beforehand
		int orderID=0;
		Staff staff= new Staff(name,id,title);
		if(table.checkAvailability(pax)) { // check if there is an available table for that particular number of pax
			String tableID = table.assignTable(pax); // assigns a table and returns the tableID
			orderID=order.createOrder(tableID,staff);//create an order
		}
		return orderID;
	}

	int reservedCustomerOrder(int pax , String name, int id, String title, String customername, int number){ //Customer that has already reserved prior
		int orderID=0;
		Staff staff= new Staff(name,id,title);
		Customer cust= new Customer(customername,number);
		LocalDateTime time=LocalDateTime.now();
		if(reservation.checkReservation(cust,pax,time)) { // Check if the customer has made a reservatiion
			String tableID=table.assignTable(pax);
			reservation.removeReservation(cust,pax,time); //remove the reservation
			orderID=order.createOrder(tableID,staff);
		}
		return orderID;
	}

	@Override
	public void assignHandler(Handler h) {
		if(h.getType()==handlerType.ORDER) order=(OrderHandler) h;
		else if(h.getType()==handlerType.TABLE) table=(TableHandler) h;
		else if(h.getType()==handlerType.RESERVATION) reservation=(ReservationHandler) h;
		return;
		
	}

	@Override
	public void assignUI(UI ui) {
		customerUI=(CustomerUI) ui;
	}

}
