package project2002;

import java.util.ArrayList;

import project2002.Restaurant.UIType;
import project2002.Restaurant.handlerType;

/**
 * Abstract manager class which the different manager classes will inherit from
 * 
 * @author Zhi Kai
 * @version 1.0
 * @since 2021-11-07
 */
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
	 * Used to retrieve the necessary handlers for that manager
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
	 * Used to retrieve the necessary UI for that manager
	 * 
	 * @return UIType
	 */
	public UIType getUIType() {
		return this.type;
	}
}
