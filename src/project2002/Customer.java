package project2002;

/**
 * Customer class to store customer information.
 * 
 * @author Zhi Kai
 * @version 1.0
 * @since 2021-11-07
 */
public class Customer {

	/**
	 * Customer name
	 */
	private String custName;
	/**
	 * Phone number
	 */
	private int number;

	/**
	 * Creates a customer object using the name and phone number
	 * 
	 * @param customerName
	 * @param phoneNumber
	 * @return Customer object with customer name and phone number
	 */
	public Customer(String customerName, int number) {
		this.custName = customerName;
		this.number = number;
	}

	/**
	 * Provide the customer name for verification purposes
	 * 
	 * @return Customer name
	 */
	public String getCustName() {
		return this.custName;
	}

	/**
	 * Provide the customer number for verification purposes
	 * 
	 * @return Phone number
	 */
	public int getNumber() {
		return this.number;
	}

	/**
	 * Check if customer object is equal to the object passed by comparing the
	 * associated name and phone number
	 * 
	 * @param object customer object
	 * @return True if customer object is equal, False if customer object is not
	 *         equal
	 */
	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;

		if (!(o instanceof Customer))
			return false;

		Customer c = (Customer) o;
		return this.number == c.getNumber() && this.custName.equals(c.getCustName());
	}

}
