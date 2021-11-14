package project2002;

/**
 * Table is an entity class which stores information relating to the number of
 * guests it can seat, the assigned TableID and its current status. Possible
 * statuses are "VACANT", "RESERVED" and "OCCUPIED".
 * 
 * @author Shun Yao
 * @version 1.0
 * @since 2021-11-01
 */
public class Table {
    /**
     * The number of guests this table can seat. Needs to be an integer.
     */
    private int tableSize;
    /**
     * The assigned TableID for this table. This can take the form of any string. It
     * should be unique and should not be changed.
     */
    private String tableID;
    /**
     * The status of the current table. Possible statuses are "VACANT", "RESERVED"
     * and "OCCUPIED" enumerations.
     */
    private TableStatus status;

    /**
     * Creates a new Table with the given TableID and the number of people it can
     * seat.
     * 
     * @param tableID   This table's unique ID.
     * @param tableSize Maximium number of guests this table can seat.
     */
    public Table(String tableID, int tableSize) {
        this.tableSize = tableSize;
        this.tableID = tableID;
        this.status = TableStatus.VACANT;
    }

    /**
     * Returns the number of guests this table can seat.
     * 
     * @return This table's capacity
     */
    public int getTableSize() {
        return tableSize;
    }

    /**
     * Returns the assigned TableID of this table. This value should be unique.
     * 
     * @return This table's unique identifier.
     */
    public String getTableID() {
        return tableID;
    }

    /**
     * Returns this table's current status in the form of an enumeration of one of
     * the following: "VACANT", "RESERVED", "OCCUPIED"/
     * 
     * @return Whether this table is presently vacant, reserved or occupied.
     */
    public TableStatus getStatus() {
        return status;
    }

    /**
     * Updates this table's status to a new status.
     * 
     * @param tableStatus to be updated
     */
    public void setStatus(TableStatus tableStatus) {
        this.status = tableStatus;
    }

    /**
     * Configures the to string method of the table to print the ID, size and status
     * of the table
     * 
     * @return String that contains the id,size and status of the table
     */
    @Override
    public String toString() {
        return this.tableID + " of size " + tableSize + " is currently " + this.status + ".";
    }

}
