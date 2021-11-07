package project2002;

public class Customer {

	private String custName;
	private int number;

	public Customer(String customerName, int number) {
		this.custName = customerName;
		this.number = number;
	}

	public String getCustName() {
		return this.custName;
	}

	public int getNumber() {
		return this.number;
	}

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
