package project2002;

import java.util.ArrayList;
import java.util.HashMap;

public class Restaurant {
	public enum handlerType {
		MENU, SALES, ORDER, TABLE, RESERVATION
	}

	public enum UIType {
		MENU, SALES, ORDER, TABLE, CUSTOMER
	}

	private HashMap<UIType, UI> UIdict = new HashMap<UIType, UI>();
	private ArrayList<Manager> managerList = new ArrayList<Manager>();
	private HashMap<handlerType, Handler> handlerdict = new HashMap<handlerType, Handler>();

	public Restaurant() {
		createHandler();
		createManager();
		assignHandler(managerList);
	}

	private void createManager() {
		MenuManager menuManager = new MenuManager();
		managerList.add(menuManager);
		OrderManager orderManager = new OrderManager();
		managerList.add(orderManager);
		TableManager tableManager = new TableManager();
		managerList.add(tableManager);
		CustomerManager customerManager = new CustomerManager();
		managerList.add(customerManager);
		SalesReportManager srManager = new SalesReportManager();
		managerList.add(srManager);
	}

	private void createHandler() {
		MenuHandler menuHandler = new MenuHandler();
		handlerdict.put(handlerType.MENU, menuHandler);
		TableHandler tableHandler = new TableHandler();
		handlerdict.put(handlerType.TABLE, tableHandler);
		ReservationHandler reservationHandler = new ReservationHandler();
		handlerdict.put(handlerType.RESERVATION, reservationHandler);
		OrderHandler orderHandler = new OrderHandler();
		handlerdict.put(handlerType.ORDER, orderHandler);
	}

	private void assignHandler(ArrayList<Manager> mlist) {
		System.out.println("Assigning managers:");
		for (Manager m : mlist) {
			System.out.println(m.getUIType().toString());
			ArrayList<handlerType> hlist = m.getHandlerList();
			for (handlerType h : hlist) {
				m.assignHandler(handlerdict.get(h));
			}
		}
	}

	public void assignUI(ArrayList<UI> uiList) {
		for (UI u : uiList) {
			UIdict.put(u.getType(), u);
		}
		for (Manager m : managerList) {
			m.assignUI(UIdict.get(m.getUIType()));
			UIdict.get(m.getUIType()).assignUIManager(m);
		}

	}

}
