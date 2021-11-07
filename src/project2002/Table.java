package project2002;

// import java.util.Objects;

public class Table {

    private int tableSize;
    private String tableID;
    private TableStatus status;

    public Table(String tableID, int tableSize) {
        this.tableSize = tableSize;
        this.tableID = tableID;
        this.status = TableStatus.VACANT;
    }

    public int getTableSize() {
        return tableSize;
    }

    public String getTableID() {
        return tableID;
    }

    public TableStatus getStatus() {
        return status;
    }

    public void setStatus(TableStatus tableStatus) {
        this.status = tableStatus;
    }

    @Override
    public String toString() {
        return this.tableID + " of size " + tableSize + " is currently " + this.status + ".";
    }

}
