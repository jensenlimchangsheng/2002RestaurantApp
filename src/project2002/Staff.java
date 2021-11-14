package project2002;

/**
 * Staff class that will contain staff details
 * 
 * @author Zhi Kai
 * @version 1.0
 * @since 2021-11-07
 */

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
	 * Creates a staff object using the name, id and title
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
	 * To retrieve the staff id to be displayed in the order invoice
	 * 
	 * @return staffID
	 */
	public int getId() {
		return id;
	}

	/**
	 * To retrieve the staff title to be displayed in the order invoice
	 * 
	 * @return staffTitle
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * To retrieve the staff name to be displayed in the order invoice
	 * 
	 * @return staffName
	 */
	public String getName() {
		return this.name;
	}

}
