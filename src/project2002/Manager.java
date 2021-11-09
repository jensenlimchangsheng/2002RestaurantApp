package project2002;

import java.util.ArrayList;

import project2002.Restaurant.UIType;
import project2002.Restaurant.handlerType;

public abstract class Manager {
	/**
	 * Type of UI assigned to manager
	 */
	protected UIType type;
	/**
	 * ArrayList of handler types assigned to manager
	 */
	protected ArrayList<handlerType> handlerList = new ArrayList<handlerType>();

	/**
	 * Getter for the ArrayList of handler types
	 * 
	 * @return ArrayList of handler types
	 */
	public ArrayList<handlerType> getHandlerList() {
		return handlerList;
	}

	/**
	 * Abstract method to assign handlers
	 * 
	 * @param handler
	 */
	abstract public void assignHandler(Handler h);

	/**
	 * Abstract method to assign uis
	 * 
	 * @param UI
	 */
	abstract public void assignUI(UI ui);

	/**
	 * Getter for the type of UI assigned to manager
	 * 
	 * @return UIType
	 */
	public UIType getUIType() {
		return this.type;
	}
}
