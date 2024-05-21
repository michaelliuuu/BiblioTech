package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestBook {
    private Book b1;

    @BeforeEach
    public void runBefore() {
        b1 = new Book("The Great Gatsby", "Fitzgerald", 1);
    }

    @Test
    public void testConstructor() {
        assertEquals("The Great Gatsby", b1.getName());
        assertEquals("Fitzgerald", b1.getAuthor());
        assertEquals(1, b1.getBookNumber());
    }

    @Test
    public void testSetAvailabilityFalse() {
        assertTrue(b1.getIsAvailable());
        b1.setAvailability(false);
        assertFalse(b1.getIsAvailable());
    }
//
//    @Test
//    public void testCheckOut() {
//        b1.checkOut();
//        assertFalse(b1.getIsAvailable());
//    }
//
//    @Test
//    public void testCheckIn() {
//        b1.checkIn();
//        assertTrue(b1.getIsAvailable());
//    }
}
