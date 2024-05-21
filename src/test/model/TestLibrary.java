package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestLibrary {
    private Library testLibrary;
    private Book b1;
    private Book b2;
    private Book b3;
    private Book b4;
    private Patron p1;
    private Patron p2;

    @BeforeEach
    public void runBefore() {
        testLibrary = new Library();
        b1 = new Book("The Great Gatsby", "Fitzgerald", 1);
        b2 = new Book("Illegal", "Bettany Restrepo", 2);
        b3 = new Book("Song of Achilles", "Madeline Miller", 3);
        b4 = new Book("Silent Patient", "Alex Michaelides", 4);
        p1 = new Patron("Tom", 5123);
        p2 = new Patron("Mary", 1009);
    }

    // Test to see if the lists within the constructor is empty.
    @Test
    public void testConstructor() {
        assertEquals(0, testLibrary.getBooks().size());
        assertEquals(0, testLibrary.getTransactions().size());
    }

    @Test
    public void testAddOneBook() {
        testLibrary.addBook(b1);
        List<Book> listOfBooks = testLibrary.getBooks();
        assertEquals(1, listOfBooks.size());
        assertEquals(b1, listOfBooks.get(0));
        assertEquals("The Great Gatsby", listOfBooks.get(0).getName());
        assertEquals("Fitzgerald", listOfBooks.get(0).getAuthor());
        assertEquals(1, listOfBooks.get(0).getBookNumber());
        assertTrue(listOfBooks.get(0).getIsAvailable());
        assertEquals("Name: The Great Gatsby, Author: Fitzgerald, ISBN: 1, Available: true", listOfBooks.get(0).toString());
    }

    @Test
    public void testAddMultipleBooks() {
        testLibrary.addBook(b1);
        testLibrary.addBook(b2);
        testLibrary.addBook(b3);
        testLibrary.addBook(b4);
        List<Book> listOfBooks = testLibrary.getBooks();
        assertEquals(4, listOfBooks.size());
        assertEquals(b1, listOfBooks.get(0));
        assertEquals(b2, listOfBooks.get(1));
        assertEquals(b3, listOfBooks.get(2));
        assertEquals(b4, listOfBooks.get(3));
    }

    @Test void testRemoveBookInEmptyLibrary() {
        testLibrary.removeBook(b1);
        List<Book> listOfBooks = testLibrary.getBooks();
        assertTrue(listOfBooks.isEmpty());
    }

    @Test
    public void testRemoveOneBook() {
        testLibrary.addBook(b1);
        List<Book> listOfBooks = testLibrary.getBooks();
        assertEquals(1, listOfBooks.size());
        assertEquals(b1, listOfBooks.get(0));

        testLibrary.removeBook(b1);
        listOfBooks = testLibrary.getBooks();
        assertEquals(0, listOfBooks.size());
    }

    @Test
    public void testRemoveMultipleBooks() {
        testLibrary.addBook(b1);
        testLibrary.addBook(b2);
        testLibrary.addBook(b3);
        testLibrary.addBook(b4);
        List<Book> listOfBooks = testLibrary.getBooks();
        assertEquals(4, listOfBooks.size());
        assertEquals(b1, listOfBooks.get(0));
        assertEquals(b2, listOfBooks.get(1));
        assertEquals(b3, listOfBooks.get(2));
        assertEquals(b4, listOfBooks.get(3));

        testLibrary.removeBook(b4);
        testLibrary.removeBook(b3);
        listOfBooks = testLibrary.getBooks();
        assertEquals(2, listOfBooks.size());
        assertEquals(b1, listOfBooks.get(0));
        assertEquals(b2, listOfBooks.get(1));
    }

    @Test
    public void testCheckOutWhenEmpty() {
        testLibrary.checkOutBook(b1, p1);

        List<LibraryTransaction> listOfTransactions = testLibrary.getTransactions();
        assertEquals(1, listOfTransactions.size());
        assertEquals(b1, listOfTransactions.get(0).getBook());
        assertEquals(p1, listOfTransactions.get(0).getPatron());
        assertTrue(listOfTransactions.get(0).isCheckedOut());
        assertEquals("Book: The Great Gatsby, Patron: Tom, Checked out?: true", listOfTransactions.get(0).toString());
        assertEquals("Name: Tom, Library Card Number: 5123", listOfTransactions.get(0).getPatron().toString());
    }

    @Test
    public void testCheckOutOneBook() {
        testLibrary.addBook(b1);
        testLibrary.checkOutBook(b1, p1);
        assertFalse(b1.getIsAvailable());
    }

    @Test
    public void testCheckOutBookWhenBookNotInLibrary() {
        testLibrary.addBook(b1);
        testLibrary.checkOutBook(b2, p1);
        assertTrue(b1.getIsAvailable());
        assertTrue(b2.getIsAvailable());
    }

    @Test
    public void testCheckInOneBook() {
        testLibrary.addBook(b1);
        testLibrary.checkOutBook(b1, p1);

        List<LibraryTransaction> listOfTransactions = testLibrary.getTransactions();
        assertEquals(1, listOfTransactions.size());
        assertEquals(b1, listOfTransactions.get(0).getBook());
        assertEquals(p1, listOfTransactions.get(0).getPatron());

        testLibrary.checkInBook(b1, p1);

        listOfTransactions = testLibrary.getTransactions();
        assertEquals(2, listOfTransactions.size());
        assertEquals(b1, listOfTransactions.get(0).getBook());
        assertEquals(b1, listOfTransactions.get(1).getBook());
        assertEquals(p1, listOfTransactions.get(0).getPatron());
        assertEquals(p1, listOfTransactions.get(1).getPatron());
        assertTrue(listOfTransactions.get(0).isCheckedOut());
        assertFalse(listOfTransactions.get(1).isCheckedOut());
    }

    @Test
    public void testCheckInWhenEmpty() {
        testLibrary.checkOutBook(b1, p1);
        testLibrary.checkInBook(b1, p1);
        assertTrue(b1.getIsAvailable());
    }

    @Test
    public void testCheckInBookWhenBookNotInLibrary() {
        testLibrary.addBook(b1);
        testLibrary.checkOutBook(b1, p1);
        testLibrary.checkInBook(b2, p1);
        assertFalse(b1.getIsAvailable());
        assertTrue(b2.getIsAvailable());
    }

    @Test void testSortBooks() {
        testLibrary.addBook(b1);
        testLibrary.addBook(b2);
        testLibrary.sortBooks();
        List<Book> listOfBooks = testLibrary.getBooks();
        assertEquals(b1, listOfBooks.get(1));
        assertEquals(b2, listOfBooks.get(0));
    }
}