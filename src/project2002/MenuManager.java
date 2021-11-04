package project2002;

import project2002.Restaurant.handlerType;
import project2002.Restaurant.UIType;

public class MenuManager extends Manager {

	MenuHandler menu= new MenuHandler();
	MenuUI menuUI;
	
	public MenuManager() {
		handlerList.add(menu);
		type=UIType.MENU;
	}
	
	public void assignHandler(Handler h) {
		if(h.getType()==handlerType.MENU) menu=(MenuHandler) h;
		return;
	}
	
	public void assignUI(UI ui) {
		menuUI=(MenuUI) ui;
	}

	public void printMenu(){
		menu.printMenu();
		return;  // Prints out the whole menu
	}
	
	public int addMenuItem(String name,double price, String description,MenuUI.ItemType i) {
		return 0;
		// TODO Auto-generated method stub
		
	}

	public int removeMenuItem() {
		menu.printMenu();
		int id = menuUI.getItemID();
		return menu.removeItem(id);
		// TODO Auto-generated method stub
		
	}

	public void updateMenuItem() {
		menu.printMenu();
		int id = menuUI.getItemID();
		String name=menu.getName(id);
		menuUI.updateItem(name, id);
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

	public void updateName(int ID, String newname) {
		menu.updateName(ID,newname);
		
	}

	public void updatePrice(int ID, double price) {
		menu.updatePrice(ID,price);
		
	}

	public void updateDescription(int ID, String description) {
		menu.updateDescription(ID,description);
		
	}
}
