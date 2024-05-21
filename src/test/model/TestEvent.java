package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Event class
 */
public class TestEvent {
    private Event e1;
    private Event e2;
    private Event e3;
    private Date d1;
    private static final int HASH_CONSTANT = 13;


    //NOTE: these tests might fail if time at which line (2) below is executed
    //is different from time that line (1) is executed.  Lines (1) and (2) must
    //run in same millisecond for this test to make sense and pass.

    @BeforeEach
    public void runBefore() {
        e1 = new Event("Add book to library");   // (1)
        d1 = Calendar.getInstance().getTime();   // (2)
        e2 = new Event("Add book to library");
        e3 = new Event("Remove book to library");
    }

    @Test
    public void testEvent() {
        assertEquals("Add book to library", e1.getDescription());
        assertEquals(d1, e1.getDate());
    }

    @Test
    public void testToString() {
        assertEquals(d1.toString() + "\n" + "Add book to library", e1.toString());
    }

    @Test
    public void testHashCode() {

        int hashcode = HASH_CONSTANT * e1.getDate().hashCode() + e1.getDescription().hashCode();
        assertEquals(hashcode, e1.hashCode());
    }

    @Test
    public void testEquals() {
        assertTrue(e1.equals(e2));
        assertFalse(e1.equals(e3));
        assertFalse(e1.equals(null));
        assertTrue(e1.equals(e1));
        assertFalse(e1.equals("Not an Event"));
    }

}

