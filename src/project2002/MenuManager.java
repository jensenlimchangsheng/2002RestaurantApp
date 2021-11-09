package project2002;

import project2002.Restaurant.handlerType;


import project2002.Restaurant.UIType;

/**
Represents the menu manager from the restaurant.
The menu manager bridges and coordinates the communication between menuUI and menuHandler.
@author Jiam Tzi Yu
@version 1.0
@since 2021-11-09
*/
public class MenuManager extends Manager {

	/**
	 * Creating a new MenuHandler.
	 */
	MenuHandler menu = new MenuHandler();
	/*
	 * The menu UI for this menu manager.
	 */
	MenuUI menuUI;

	/*
	 * Creates a new menu manager
	 */
	public MenuManager() {
		handlerList.add(handlerType.MENU);
		type = UIType.MENU;

	}

	/**
	 * Assigns handler to this menu manager with a handler.
	 * @param h the menu manager's handler.
	 */
	public void assignHandler(Handler h) {
		if (h.getType() == handlerType.MENU)
			menu = (MenuHandler) h;
		return;
	}

	/**
	 * Assigns UI to this menu manager with ui.
	 * @param ui This menu manager's UI.
	 */
	public void assignUI(UI ui) {
		menuUI = (MenuUI) ui;
	}

	/**
	 * Prints menu.
	 */
	public void printMenu() {
		menu.printMenu();
		return; 
	}


	/**
	 * Creates a new menu item with name, price, type and description.
	 * @param name This menu item's name.
	 * @param price This menu item's price.
	 * @param itemType This menu item's type.
	 * @param description This menu item's description.
	 */
	public MenuItem addMenuItem(String name, double price, String description, MenuUI.ItemType i) {
		return menu.addMenuItem(name, price, description, i);
	}


	/**
	 * Removes a menu item from menu.
	 * @return removed menu item's name.
	 */
	public String removeMenuItem() {
		menu.printMenu();
		int id = menuUI.getItemID();
		if (menu.checkExist(id) == 0) {
			return null;
		}
		return menu.removeItem(id);
	}


	/**
	 * Update a menu item in the menu.
	 * @return updated menu item's name.
	 */
	public String updateMenuItem() {
		menu.printMenu();
		int id = menuUI.getItemID();
		if (menu.checkExist(id) == 0) {
			return null;
		}
		String name = menu.getName(id);
		menuUI.updateItem(name, id);
		return name;
	}


	/**
	 * Adds a new promo set to the menu.
	 * @param name The name of this promo set.
	 * @param price The price of this promo set.
	 * @param description The description of this promo set.
	 * @param i the type of this menu item.
	 * @return menu item.
	 */
	public MenuItem addPromoSet(String name, double price, String description, MenuUI.ItemType i) {
		return menu.addMenuItem(name, price, description, i);
	}


	/**
	 * Removes a promo set from the menu.
	 * @return promo set's name.
	 */
	public String removePromoSet() {
		menu.printMenu();
		int id = menuUI.getItemID();
		if(id>600 || id<500){
			return null;
		}
		return menu.removeItem(id);
	}


	/**
	 * Updates a promo set in the menu.
	 * @return promo set's name.
	 */
	public String updatePromoSet() {
		menu.printMenu();
		int id = menuUI.getItemID();
		if (menu.checkExist(id) == 0) {
			return null;
		}
		if(id>600 || id<500){
			return null;
		}
		String name = menu.getName(id);
		menuUI.updateItem(name, id);
		return name;
	}



	/**
	 * Updates the name of a menu item.
	 * @param ID The menu item's ID.
	 * @param newname The menu item's new name.
	 */
	public void updateName(int ID, String newname) {
		menu.updateName(ID, newname);

	}

	/**
	 * Updates the price of a menu item.
	 * @param ID The menu item's ID.
	 * @param price The menu item's new price.
	 */
	public void updatePrice(int ID, double price) {
		menu.updatePrice(ID, price);

	}

	/**
	 * Updates the description of a menu item.
	 * @param ID The menu item's ID.
	 * @param description The menu item's new description.
	 */
	public void updateDescription(int ID, String description) {
		menu.updateDescription(ID, description);

	}
}
