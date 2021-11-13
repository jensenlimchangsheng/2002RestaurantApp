package project2002;

import project2002.Restaurant.handlerType;

/**
 * Handler class which the different handler classes will inherit from
 * 
 * @author Zhi Kai
 * @version 1.0
 * @since 2021-11-07
 */
public class Handler {
	/**
	 * Type of handler.
	 */
	protected handlerType type;

	/**
	 * To retrieve the handler type that will be used in the assignment by the
	 * Restaurant
	 * 
	 * @return handlerType
	 */
	public handlerType getType() {
		return type;
	}

}
