package project2002;

import java.util.ArrayList;

import project2002.Restaurant.UIType;

public abstract class Manager {
	protected UIType type;
	protected ArrayList<Handler> handlerList=new ArrayList<Handler>();

	public ArrayList<Handler> getHandlerList() {
		return handlerList;
	}
	
	abstract public void assignHandler(Handler h);
	abstract public void assignUI(UI ui);
	public UIType getUIType() {
		return this.type;
	}
}
