package project2002;

import project2002.Restaurant.UIType;
import project2002.Restaurant.handlerType;
/**
 * A Manager that helps to connect the orderhandler which contains all the orders of the restaurant
 * and also the menu of the restaurant
 * @author Jensen Lim
 * @version 1.0
 * @since 2021-11-01
 */
public class OrderManager extends Manager {
	OrderHandler orderHandler;
	MenuHandler menu;

	OrderUI orderUI;

	/**
	 * Constructor for Ordermanager 
	 */
	public OrderManager() {
		handlerList.add(handlerType.MENU);
		handlerList.add(handlerType.ORDER);
		type = UIType.ORDER;
	}

	/**
	 * to add an order item into an order
	 * @param orderID the orderID in which you want to add an item to
	 * @return the value to states whether the addition was successful
	 */
	public int addOrderItem(int orderID) {
		int itemID = 0;
		menu.printMenu();
		itemID = orderUI.getItemID();
		int quantity = orderUI.getQty();
		MenuItem MenuItem = menu.getItem(itemID); // Takes in the itemID and returns the menuitem
		if (MenuItem == null) {
			return -1; // item does not exist
		}
		if (orderHandler.addItem(orderID, MenuItem, quantity))
			return 1; // successful
		System.out.println("OrderID doesnt Exist");
		return 0; // unsuccessful
	}
	/**
	 * to remove an order item from an order
	 * @param orderID the order ID in which you want to remove an item from
	 * @return a boolean valyue that states whether the removal was succesful
	 */
	public boolean removeOrderItem(int orderID) {
		int itemID = 0;
		orderHandler.printOrder(orderID);
		itemID = orderUI.getItemID();
		int quantity = orderUI.getQty();
		MenuItem MenuItem = menu.getItem(itemID); // Takes in the itemID and returns the menuitem
		if (MenuItem == null) {
			System.out.println("There is no such Menu Item");
		}
		return orderHandler.RemoveItem(orderID, MenuItem, quantity); // Add menuitem to the particular orderID
	}
	/**
	 * to view the Order of a specific orderID
	 * @param orderID the orderID in which the user would like to view
	 */
	public void viewOrder(int orderID) {
		orderHandler.printOrder(orderID);
	}

	/**
	 * to assign a handler to this Manager
	 */
	@Override
	public void assignHandler(Handler h) {
		if (h.getType() == handlerType.ORDER)
			orderHandler = (OrderHandler) h;
		else if (h.getType() == handlerType.MENU)
			menu = (MenuHandler) h;
		return;
	}

	/**
	 * to assign a UI to this Order Manager
	 */
	@Override
	public void assignUI(UI ui) {
		orderUI = (OrderUI) ui;
	}

}
