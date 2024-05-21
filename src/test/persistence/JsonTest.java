package persistence;

import model.Book;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkBook(String name, String author, int bookNumber, boolean isAvailable, Book book) {
        assertEquals(name, book.getName());
        assertEquals(author, book.getAuthor());
        assertEquals(bookNumber, book.getBookNumber());
        assertEquals(isAvailable, book.getIsAvailable());
    }
}
