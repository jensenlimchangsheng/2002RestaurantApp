package project2002;

import project2002.Restaurant.handlerType;
import project2002.Restaurant.UIType;

public class MenuManager extends Manager {

	MenuHandler menu = new MenuHandler();
	MenuUI menuUI;

	public MenuManager() {
		handlerList.add(handlerType.MENU);
		type = UIType.MENU;

	}

	public void assignHandler(Handler h) {
		if (h.getType() == handlerType.MENU)
			menu = (MenuHandler) h;
		return;
	}

	public void assignUI(UI ui) {
		menuUI = (MenuUI) ui;
	}

	// Prints out the whole menu
	//SETTLED
	public void printMenu() {
		menu.printMenu();
		return; 
	}


	//SETTLED
	public MenuItem addMenuItem(String name, double price, String description, MenuUI.ItemType i) {
		return menu.addMenuItem(name, price, description, i);
	}


	//SETTLED
	public String removeMenuItem() {
		menu.printMenu();
		int id = menuUI.getItemID();
		if (menu.checkExist(id) == 0) {
			return null;
		}
		return menu.removeItem(id);
	}


	//SETTLED
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


	//SETTLED
	public MenuItem addPromoSet(String name, double price, String description, MenuUI.ItemType i) {
		return menu.addMenuItem(name, price, description, i);
	}


	//SETTLED
	public String removePromoSet() {
		menu.printMenu();
		int id = menuUI.getItemID();
		if(id>600 || id<500){
			return null;
		}
		return menu.removeItem(id);
	}


	//SETTLED
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




	public void updateName(int ID, String newname) {
		menu.updateName(ID, newname);

	}

	public void updatePrice(int ID, double price) {
		menu.updatePrice(ID, price);

	}

	public void updateDescription(int ID, String description) {
		menu.updateDescription(ID, description);

	}
}
