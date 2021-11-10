package project2002;

public class Staff {

	/**
	 * Staff name
	 */
	private String name;

	/**
	 * Staff id
	 */
	private int id;

	/**
	 * Staff title
	 */
	private String title;

	/**
	 * Constructor for the customer class
	 * 
	 * @param staffName
	 * @param staffID
	 * @param staffTitle
	 * @return Staff object
	 */
	public Staff(String name2, int id, String title) {
		this.name = name2;
		this.id = id;
		this.title = title;
	}

	/**
	 * Setter for staff name
	 * 
	 * @param staffName
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter for staff name
	 * 
	 * @return staffID
	 */
	public int getId() {
		return id;
	}

	/**
	 * Setter for staff id
	 * 
	 * @param staffID
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Getter for staff title
	 * 
	 * @return staffTitle
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Setter for title
	 * 
	 * @param staffTitle
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Getter for staff name
	 * 
	 * @return staffName
	 */
	public String getName() {
		return this.name;
	}

}
