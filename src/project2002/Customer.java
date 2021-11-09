package project2002;

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
	 * Constructor for the customer class
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
	 * Getter for the customer name
	 * 
	 * @return Customer name
	 */
	public String getCustName() {
		return this.custName;
	}

	/**
	 * Getter for the phone number
	 * 
	 * @return Phone number
	 */
	public int getNumber() {
		return this.number;
	}

	/**
	 * Check if customer object is equal to parameter
	 * 
	 * @param object
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
