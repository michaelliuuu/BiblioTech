package model;

// Represents a library transaction between the patron and the library
public class LibraryTransaction {
    private Book book;
    private Patron patron;
    private boolean isCheckedOut;

    // REQUIRES: The book and patron to not be empty
    // EFFECTS: Creates a library transaction with a book, patron, and if it has been checked out or not
    public LibraryTransaction(Book book, Patron patron, boolean isCheckedOut) {
        this.book = book;
        this.patron = patron;
        this.isCheckedOut = isCheckedOut;
    }

    // EFFECTS: returns the book name, patron name, and if it has been checked out or not
    public String toString() {
        return "Book: " + book.getName() + ", Patron: " + patron.getName() + ", Checked out?: " + isCheckedOut;
    }

    // GETTERS
    public Book getBook() {
        return book;
    }

    public Patron getPatron() {
        return patron;
    }

    public boolean isCheckedOut() {
        return isCheckedOut;
    }
}
