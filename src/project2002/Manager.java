package project2002;

import java.util.ArrayList;

import project2002.Restaurant.UIType;
import project2002.Restaurant.handlerType;

public abstract class Manager {
	protected UIType type;
	protected ArrayList<handlerType> handlerList = new ArrayList<handlerType>();

	public ArrayList<handlerType> getHandlerList() {
		return handlerList;
	}

	abstract public void assignHandler(Handler h);

	abstract public void assignUI(UI ui);

	public UIType getUIType() {
		return this.type;
	}
}
