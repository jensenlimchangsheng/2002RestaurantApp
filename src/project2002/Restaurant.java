package project2002;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import project2002.MenuUI.ItemType;

public class Restaurant {
	private OrderHandler orderHandler;
	private ReservationHandler reservationHandler;
	private TableHandler tableHandler;
	private Menu menu;
	private MenuUI menuUI;
	private TableUI tableUI;
	private OrderUI orderUI;

	Restaurant(){ //Initialize the different components
		orderHandler=new OrderHandler();
		reservationHandler=new ReservationHandler();
		tableHandler=new TableHandler();
		menu=new Menu();
	}

	public void addUI(TableUI tableUI, MenuUI menuUI, OrderUI orderUI) {
		this.menuUI=menuUI;
		this.tableUI=tableUI;
		this.orderUI=orderUI;
	}
	
	//---------------------------ORDER UI-------------------------------------------------

	int newCustomerOrder(int pax , String name, int id, String title){ // new customer that did not reserve beforehand
		int orderID=0;
		Staff staff= new Staff(name,id,title);
		if(tableHandler.checkAvailability(pax)) { // check if there is an available table for that particular number of pax
			int tableID = tableHandler.assignTable(pax); // assigns a table and returns the tableID
			orderID=orderHandler.createOrder(tableID,staff);//create an order
		}
		return orderID;
	}

	int reservedCustomerOrder(int pax , String name, int id, String title, String customername, int number){ //Customer that has already reserved prior
		int orderID=0;
		Staff staff= new Staff(name,id,title);
		Customer cust= new Customer(customername,number);
		LocalDateTime time=LocalDateTime.now();
		if(reservationHandler.checkReservation(cust,pax,time)) { // Check if the customer has made a reservatiion
			int tableID=tableHandler.assignTable(pax);
			reservationHandler.removeReservation(cust,pax,time); //remove the reservation
			orderID=orderHandler.createOrder(tableID,staff);
		}
		return orderID;
	}
	
	int addOrderItem(int orderID){
		int itemID=0;
		printMenu();
		itemID=orderUI.getItemID();
		int quantity=orderUI.getQty();
		MenuItem MenuItem=menu.getItem(itemID); //Takes in the itemID and returns the menuitem
		if (MenuItem==null) return -1; //item does not exist
		if(orderHandler.addItem(orderID,MenuItem,quantity)) return 1; //successful
		return 0; //unsuccessful
	}

	boolean removeOrderItem(int orderID){
		int itemID=0;
		orderHandler.printOrder(orderID);
		itemID=orderUI.getItemID();
		int quantity=orderUI.getQty();
		MenuItem MenuItem=menu.getItem(itemID); //Takes in the itemID and returns the menuitem
		return orderHandler.removeItem(orderID,MenuItem,quantity); //Add menuitem to the particular orderID
	}

	void printOrderInvoice(int orderID){
		orderHandler.printInvoice(orderID);
	}

	void printSalesRevenue(int days, int months){
		salesReport.printRevenue(days,months);
	}
	
	void printSalesRevenue(int days){ //since java has no method overloading
		printSalesRevenue(days,0);
	}
	
	//---------------------------------------------------------------------------------------------
	
	//--------------------------------Menu UI----------------------------------------------------
	public String printMenu(){
		return menu.PrintMenu(); // Prints out the whole menu
	}
	
	public int addMenuItem(String name,double price, String description,ItemType type) {
		return 0;
		// TODO Auto-generated method stub
		
	}

	public int removeMenuItem(String name,double price, String description) {
		return 0;
		// TODO Auto-generated method stub
		
	}

	public int updateMenuItem(String name,double price, String description,ItemType type) {
		return 0;
		// TODO Auto-generated method stub
		
	}

	public int addPromoSet() {
		return 0;
		// TODO Auto-generated method stub
		
	}

	public int removePromoSet() {
		return 0;
		// TODO Auto-generated method stub
		
	}

	public int updatePromoSet() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	//---------------------------------------------------------------------------------------------
	

	
	//----------------------Table UI------------------------------------------------------------------
	/**
	 * Adds a table to the restaurant
	 * @param tableID 
	 * @param pax
	 * @return tableID or error string.
	 */
	public String addNewTable(String tableID, int pax) {
		return tableHandler.addNewTable(tableID, pax);
	}

	/**
	 * Removes a table from the restaurant
	 * @param tableID 
	 * @return table removal status
	 */
	public int removeTable(String tableID) {
		return tableHandler.removeTable(tableID);
	}

	/**
	 * Update table pax
	 * @param tableID 
	 * @param pax
	 * @return Table update status
	 */
	public int updateTable(String tableID,int pax) {
		return tableHandler.updateTable(tableID,pax);
	}

	/**
	 * Add reservation
	 * @param pax 
	 * @param name
	 * @param number
	 * @param dateTime
	 * @return whether there reservation is successfully added.
	 */
	public boolean addReservation(int pax,String name,int number,LocalDateTime dateTime){
		Customer cust= new Customer(name,number);

		// check if there is available table 
		return reservationHandler.addReservation(cust, pax, dateTime); // will check if there is an available reservation
	}

	
	/**
	 * Add reservation
	 * @param name
	 * @param number
	 * @param dateTime
	 * @return whether there reservation is successfully removed.
	 */
	public boolean removeReservation(String name,int number,LocalDateTime dateTime){
		Customer cust= new Customer(name, number);
		return reservationHandler.removeReservation(cust, dateTime);
	}

	/**
	 * Checks if a customer has made a reservation at the given time 
	 * @param cust 
	 * @param dateTime
	 * @return whether there is a reservation - True of reservation exists, False otherwise.
	 */
	public boolean checkReservation(String name,int number,LocalDateTime dateTime){
		Customer cust= new Customer(name,number);
		return reservationHandler.checkReservation(cust, dateTime);
	}

	/**
	 * Update customer reservation
	 * @param cust 
	 * @param dateTime
	 * @return Reseration update status.
	 */
	public int updateReservation(String name, int number, LocalDateTime dateTime) {
		Customer cust= new Customer(name,number);
		int newpax=tableUI.getPax();
		LocalDateTime newDateTime = tableUI.getDateTime();
		if(reservationHandler.addReservation(cust, newpax, newDateTime)){
			if (reservationHandler.removeReservation(cust, dateTime)) {
				return 1;
			}
			return -1;
		}
		return -2;
	}

	public boolean reserveTables() {
		LocalDateTime time=LocalDateTime.now();
		ArrayList<Reservation> reservationList = reservationHandler.retrieveNextReservationList(time);
		return tableHandler.reserveTables(reservationList);
	}

	public boolean removeReservedTables() {
		LocalDateTime time=LocalDateTime.now();
		ArrayList<Reservation> reservationList = reservationHandler.retrieveBeforeReservationList(time);
		return tableHandler.removeReservation(reservationList);
	}

	boolean checkTableAvailability(int pax){ 
		return tableHandler.checkAvailability(pax);
	}

	void printAvailableTablesNow(){
		tableHandler.printAvailableTablesNow(); // shows the list of all available tables
	}

}
