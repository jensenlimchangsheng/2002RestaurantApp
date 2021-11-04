package project2002;

import java.time.LocalDateTime;
import java.util.ArrayList;

import project2002.Restaurant.UIType;
import project2002.Restaurant.handlerType;

public class TableManager extends Manager{
	OrderHandler order= new OrderHandler();
	TableHandler tableHandler= new TableHandler();
	ReservationHandler reservationHandler= new ReservationHandler();
	TableUI tableUI;
	
	public TableManager() {
		handlerList.add(order);
		handlerList.add(tableHandler);
		handlerList.add(reservationHandler);
		type=UIType.TABLE;
	}
	boolean addReservation(int pax,String name,int number,LocalDateTime date,LocalDateTime time){
		Customer cust= new Customer(name,number);
		return reservationHandler.addReservation(cust,pax,date,time); // will check if there is an available reservation
	}

	boolean removeReservation(int pax,String name,int number,LocalDateTime date,LocalDateTime time){
		Customer cust= new Customer(name,number);
		return reservationHandler.removeReservation(cust,pax,date,time);
	}

	boolean checkReservation(int pax,String name,int number,LocalDateTime date,LocalDateTime time){
		Customer cust= new Customer(name,number);
		return reservationHandler.checkReservation(cust,pax,date,time);
	}
	
	public boolean addNewTable(int pax) {
		reservationHandler.addNewTable(pax);
		return tableHandler.addNewTable(pax);
		// TODO Auto-generated method stub
		
	}

	public boolean removeTable(int pax) {
		reservationHandler.removeTable(pax);
		return tableHandler.removeTable(pax);
		
	}

	public boolean updateTable(int tableID,int pax) {
		int initialpax=tableHandler.updateTable(tableID,pax);
		return reservationHandler.updateTable(initialpax,pax);
		
	}

	public boolean updateReservation(int pax, String name, int number, LocalDateTime date, LocalDateTime time) {
		Customer cust= new Customer(name,number);
		int newpax=tableUI.getPax();
		LocalDateTime newdate= tableUI.getTime();
		LocalDateTime newtime= tableUI.getTime();
		if(reservationHandler.addReservation(cust,pax,newdate,newtime))
			reservationHandler.removeReservation(cust,pax,date,time);
		else return false;
		return true; // will check if there is an available reservation

	}

	public boolean reserveTables() {
		LocalDateTime time=LocalDateTime.now();
		ArrayList<Reservations> reservationList=reservationHandler.retrieveNextReservationList(time);
		return tableHandler.reserveTables(reservationList);
	}

	public boolean removeReservedTables() {
		LocalDateTime time=LocalDateTime.now();
		ArrayList<Reservations> reservationList=reservationHandler.retrieveBeforeReservationList(time);
		return tableHandler.removeReservations(reservationList);
	}
	boolean checkTableAvailability(int pax){ 
		return tableHandler.checkAvailability(pax);
	}

	void printAvailableTablesNow(){
		tableHandler.printAvailableTablesNow(); // shows the list of all available tables
	}
	
	@Override
	public void assignHandler(Handler h) {
		if(h.getType()==handlerType.ORDER) order=(OrderHandler) h;
		else if(h.getType()==handlerType.TABLE) tableHandler=(TableHandler) h;
		else if(h.getType()==handlerType.RESERVATION) reservationHandler=(ReservationHandler) h;
		return;
	}

	@Override
	public void assignUI(UI ui) {
		tableUI=(TableUI) ui;
	}

}
