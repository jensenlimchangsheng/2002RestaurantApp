package project2002;

import static org.junit.Assert.*;

public class TableHandlerTest {

    @Test
    public void testAddNewTable() {
        TableHandler tableHandler = new TableHandler();

        tableHandler.addNewTable("T001", 2);
        tableHandler.addNewTable("T002", 2);
        tableHandler.addNewTable("T003", 4);
        tableHandler.addNewTable("T004", 8);

        int[] expectedTables = new int[] { 2, 1, 0, 1, 0 };
        assertArrayEquals(expectedTables, tableHandler.getTableSizes());

    }

}
