package project2002;

import java.util.ArrayList;
import java.util.HashMap;

public class Restaurant {
	public enum handlerType { MENU, SALES, ORDER, TABLE, RESERVATION }
	public enum UIType { MENU, SALES, ORDER, TABLE, CUSTOMER }
	private HashMap<UIType,UI> UIdict= new HashMap<UIType,UI>();
	private ArrayList<Manager> managerList=new ArrayList<Manager>();
	private HashMap<handlerType,Handler> handlerdict= new HashMap<handlerType,Handler>();
	
	public Restaurant(){
		createHandler();
		createManager();
		assignHandler(managerList);
	}
	private void createManager() {
		MenuManager menuManager= new MenuManager();
		managerList.add(menuManager);
	}
	
	private void createHandler() {
		MenuHandler menuHandler= new MenuHandler();
		handlerdict.put(handlerType.MENU,menuHandler);
	}
	
	private void assignHandler(ArrayList<Manager> mlist) {
		System.out.println("Assigning managers:");
		for (Manager m : mlist) 
		{ 
			System.out.println(m.getUIType().toString());
		    ArrayList<Handler> hlist=m.getHandlerList();
		    for(Handler h: hlist) {
		    	m.assignHandler(handlerdict.get(h.getType()));
		    }
		}
	}
	public void assignUI(ArrayList<UI> uiList) {
		for (UI u : uiList) 
		{ 
		   UIdict.put(u.getType(),u);
		}
		for (Manager m : managerList) 
		{ 
			m.assignUI(UIdict.get(m.getUIType()));
			UIdict.get(m.getUIType()).assignUIManager(m);
		}
		
	}

}
