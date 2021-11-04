package project2002;

// import java.util.Objects;

public class Table {
	private int tableSize;
	private String tableID;
	private TableStatus status;
	
	/* should not be required
	private Table(String tableID) {
		this.tableID = Objects.requireNonNull(tableID, "TableID cannot be null");
		this.tableSize = 2;
		setStatus(TableStatus.VACANT);
	}
	
	protected Table(String tableID, int tableSize) {
		// only for initialising
		this(tableID);
		setTableSize(tableSize);
		setStatus(TableStatus.VACANT);
	}
	

	public Table(String tableID, TableStatus tableStatus) {
		// to be called on the fly
		this(tableID);
		setStatus(tableStatus);
	} */
	
	public Table(String tableID, int tableSize) {
		this.tableSize = tableSize;
		this.tableID = tableID;
		this.status = TableStatus.VACANT;
	}
	
	public int getTableSize() {
		return tableSize;
	}
	/* should not be required
	public void setTableSize(int tableSize) {
		switch(tableSize) {
		case 2: case 4: case 6: case 8: case 10:
			this.tableSize = tableSize;
			return;
		default: 
			System.out.println("Table size can only be 2, 4, 6, 8 or 10. No update made.");
		}
	}*/
	public String getTableID() {
		return tableID;
	}
	/* should not be required
	public void setTableID(String tableID) {
		this.tableID = tableID;
	}
	*/
	public TableStatus getStatus() {
		return status;
	}
	public void setStatus(TableStatus tableStatus) {
		this.status = tableStatus;
		// this.status = Objects.requireNonNull(tableStatus, "TableStatus cannot be null");
	}
	
	@Override
	public String toString() {
		return this.tableID + " of size " + tableSize + " is currently "+this.status+".";
	}

}
