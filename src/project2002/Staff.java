package project2002;

public class Staff {
	private String name;
	private int id;
	private String title;

	public Staff(String name2, int id, String title) {
		this.name = name2;
		this.id = id;
		this.title = title;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getName() {
		return this.name;
	}

}
