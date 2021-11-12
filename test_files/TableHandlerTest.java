/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import project2002.Customer;
import project2002.Reservation;
import project2002.TableHandler;

/**
 * Tests TableHandler
 * 
 * @author shunyao
 */
public class TableHandlerTest {

    @Test
    public void testCannotAddTableWithDuplicateTableID() {
        TableHandler tableHandler = new TableHandler();
        assertEquals("T001", tableHandler.addNewTable("T001", 2));
        assertEquals("T002", tableHandler.addNewTable("T002", 4));
        assertEquals("TableAlreadyExists", tableHandler.addNewTable("T001", 6));
    }

    @Test
    public void testRemoveTablesRemovesOnlyTargetTables() {
        TableHandler tableHandler = new TableHandler();
        tableHandler.addNewTable("T001", 4);
        tableHandler.addNewTable("T002", 4);
        tableHandler.addNewTable("T003", 4);
        tableHandler.addNewTable("T004", 4);
        assertEquals("TableAlreadyExists", tableHandler.addNewTable("T001", 6));
        tableHandler.removeTable("T001");
        assertEquals("T001", tableHandler.addNewTable("T001", 6));
        assertEquals("TableAlreadyExists", tableHandler.addNewTable("T002", 6));
        assertEquals("TableAlreadyExists", tableHandler.addNewTable("T003", 6));
        assertEquals("TableAlreadyExists", tableHandler.addNewTable("T004", 6));
    }

    @Test
    public void testTableSizeCanBeUpdated() {
        TableHandler tableHandler = new TableHandler();
        tableHandler.addNewTable("T001", 4);
        tableHandler.addNewTable("T002", 4);
        tableHandler.addNewTable("T003", 4);
        tableHandler.addNewTable("T004", 4);
        assertEquals(1, tableHandler.updateTable("T001", 6));
        assertEquals(0, tableHandler.updateTable("T005", 6));
    }

    @Test
    public void testTableSizesReturnCorrectly1() {
        TableHandler tableHandler = new TableHandler();
        tableHandler.addNewTable("T001", 2);
        tableHandler.addNewTable("T002", 2);
        tableHandler.addNewTable("T003", 4);
        tableHandler.addNewTable("T004", 8);
        int[] expectedTables = new int[] { 2, 1, 0, 1, 0 };
        assertArrayEquals(expectedTables, tableHandler.getTableSizes());
    }

    @Test
    public void testTableSizesReturnCorrectly2() {
        TableHandler tableHandler = new TableHandler();
        tableHandler.addNewTable("T001", 4);
        tableHandler.addNewTable("T002", 4);
        tableHandler.addNewTable("T003", 6);
        tableHandler.addNewTable("T004", 10);
        int[] expectedTables = new int[] { 0, 2, 1, 0, 1 };
        assertArrayEquals(expectedTables, tableHandler.getTableSizes());
    }

    @Test
    public void testIndividualTableAvailabilityIsFound() {
        TableHandler tableHandler = new TableHandler();

        assertFalse(tableHandler.checkAvailability(4));

        tableHandler.addNewTable("T001", 4);
        tableHandler.addNewTable("T002", 6);

        assertFalse(tableHandler.checkAvailability(2));
        assertTrue(tableHandler.checkAvailability(3));
        assertTrue(tableHandler.checkAvailability(4));
        assertTrue(tableHandler.checkAvailability(5));
        assertFalse(tableHandler.checkAvailability(7));
    }

    @Test
    public void testOnlySeatCustomersIfTableAvailable1() {
        TableHandler tableHandler = new TableHandler();
        tableHandler.addNewTable("T001", 2);
        tableHandler.addNewTable("T002", 2);
        tableHandler.addNewTable("T003", 4);
        tableHandler.addNewTable("T004", 8);

        assertEquals("T001", tableHandler.seatNewCustomer(2));
        assertEquals("T002", tableHandler.seatNewCustomer(1));
        assertEquals("NoTablesAvailable", tableHandler.seatNewCustomer(2));
        assertEquals("T003", tableHandler.seatNewCustomer(3));
        assertEquals("NoTablesAvailable", tableHandler.seatNewCustomer(5));
    }

    @Test
    public void testOnlySeatCustomersIfTableAvailable2() {
        TableHandler tableHandler = new TableHandler();
        tableHandler.addNewTable("T001", 6);
        tableHandler.addNewTable("T002", 4);
        tableHandler.addNewTable("T003", 2);
        tableHandler.addNewTable("T004", 4);

        assertEquals("T003", tableHandler.seatNewCustomer(2));
        assertEquals("NoTablesAvailable", tableHandler.seatNewCustomer(1));
        assertEquals("NoTablesAvailable", tableHandler.seatNewCustomer(2));
        assertEquals("T002", tableHandler.seatNewCustomer(3));
        assertEquals("T001", tableHandler.seatNewCustomer(5));
        assertEquals("T004", tableHandler.seatNewCustomer(3));
    }

    @Test
    public void testReserveTablesTakesInReservations() {
        TableHandler tableHandler = new TableHandler();
        tableHandler.addNewTable("T001", 6);
        tableHandler.addNewTable("T002", 4);
        tableHandler.addNewTable("T003", 2);
        tableHandler.addNewTable("T004", 4);

        ArrayList<String> tableIDList = new ArrayList<>();
        tableIDList.add("T003");
        tableIDList.add("T002");
        tableIDList.add("T004");
        tableIDList.add("T001");

        ArrayList<Reservation> reservationList = new ArrayList<>();
        reservationList.add(new Reservation(2, new Customer("John Tan", 91234567)));
        reservationList.add(new Reservation(4, new Customer("Jane Lim", 91234568)));
        reservationList.add(new Reservation(4, new Customer("Jack Wong", 91234569)));
        reservationList.add(new Reservation(6, new Customer("Jill Sim", 91234570)));

        assertEquals(tableHandler.reserveTables(reservationList), tableIDList);
    }

    @Test
    public void testReservationsOfWrongTableSizeRejected1() {
        TableHandler tableHandler = new TableHandler();
        tableHandler.addNewTable("T001", 6);
        tableHandler.addNewTable("T002", 4);
        tableHandler.addNewTable("T003", 2);
        tableHandler.addNewTable("T004", 4);

        ArrayList<Reservation> reservationList = new ArrayList<>();
        reservationList.add(new Reservation(8, new Customer("Jill Sim", 91234570)));

        assertEquals(tableHandler.reserveTables(reservationList), new ArrayList<String>());
    }

    @Test
    public void testReservationsOfWrongTableSizeRejected2() {
        TableHandler tableHandler = new TableHandler();
        tableHandler.addNewTable("T001", 6);
        tableHandler.addNewTable("T002", 4);
        tableHandler.addNewTable("T003", 4);
        tableHandler.addNewTable("T004", 4);

        ArrayList<Reservation> reservationList = new ArrayList<>();
        reservationList.add(new Reservation(2, new Customer("Jill Sim", 91234570)));

        assertEquals(tableHandler.reserveTables(reservationList), new ArrayList<String>());
    }

    @Test
    public void testOnlyReservedCustomersCanSitIfFull1() {
        TableHandler tableHandler = new TableHandler();
        tableHandler.addNewTable("T001", 6);
        tableHandler.addNewTable("T002", 4);
        tableHandler.addNewTable("T003", 2);
        tableHandler.addNewTable("T004", 4);

        ArrayList<Reservation> reservationList = new ArrayList<>();
        reservationList.add(new Reservation(2, new Customer("John Tan", 91234567)));
        reservationList.add(new Reservation(4, new Customer("Jane Lim", 91234568)));
        reservationList.add(new Reservation(4, new Customer("Jack Wong", 91234569)));

        tableHandler.reserveTables(reservationList);

        for (int i = 1; i < 5; i++)
            assertEquals("NoTablesAvailable", tableHandler.seatNewCustomer(i));

        assertEquals("T001", tableHandler.seatNewCustomer(5));
        assertEquals("NoTablesAvailable", tableHandler.seatNewCustomer(5));
        assertEquals("NoTablesAvailable", tableHandler.seatNewCustomer(7));
    }

    @Test
    public void testOnlyReservedCustomersCanSitIfFull2() {
        TableHandler tableHandler = new TableHandler();
        tableHandler.addNewTable("T001", 6);
        tableHandler.addNewTable("T002", 4);
        tableHandler.addNewTable("T003", 4);
        tableHandler.addNewTable("T004", 2);

        ArrayList<Reservation> reservationList = new ArrayList<>();
        reservationList.add(new Reservation(4, new Customer("John Tan", 91234567)));
        reservationList.add(new Reservation(2, new Customer("Jane Lim", 91234568)));
        reservationList.add(new Reservation(4, new Customer("Jack Wong", 91234569)));
        reservationList.add(new Reservation(6, new Customer("Jill Sim", 91234570)));

        tableHandler.reserveTables(reservationList);

        for (int i = 1; i < 5; i++)
            assertEquals("NoTablesAvailable", tableHandler.seatNewCustomer(i));

        assertEquals("T004", tableHandler.seatBookedCustomer(2));
        assertEquals("T002", tableHandler.seatBookedCustomer(3));
        assertEquals("T003", tableHandler.seatBookedCustomer(4));
        assertEquals("T001", tableHandler.seatBookedCustomer(5));

    }

    @Test
    public void testOnlyReservedCustomersCanSitIfFull3() {
        TableHandler tableHandler = new TableHandler();
        tableHandler.addNewTable("T001", 6);
        tableHandler.addNewTable("T002", 4);
        tableHandler.addNewTable("T003", 4);
        tableHandler.addNewTable("T004", 2);

        ArrayList<Reservation> reservationList = new ArrayList<>();
        reservationList.add(new Reservation(2, new Customer("Jane Lim", 91234568)));
        reservationList.add(new Reservation(4, new Customer("Jack Wong", 91234569)));

        tableHandler.reserveTables(reservationList);

        assertEquals("NoTablesAvailable", tableHandler.seatNewCustomer(2));
        assertEquals("T004", tableHandler.seatBookedCustomer(2));
        assertNotEquals("NoTablesAvailable", tableHandler.seatNewCustomer(4));
        assertEquals("NoTablesAvailable", tableHandler.seatNewCustomer(4));
        assertNotEquals("NoTablesAvailable", tableHandler.seatBookedCustomer(4));
        assertEquals("NoTablesAvailable", tableHandler.seatNewCustomer(4));
        assertNotEquals("NoTablesAvailable", tableHandler.seatNewCustomer(5));
        assertEquals("NoTablesAvailable", tableHandler.seatNewCustomer(6));
    }

}
