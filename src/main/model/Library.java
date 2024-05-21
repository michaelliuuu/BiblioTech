package model;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

// Represents a library that contains books.
public class Library implements Writable {
    private List<Book> books;
    private List<LibraryTransaction> transactions;

    // EFFECTS: Creates a library that contains an empty list of books and transactions.
    // Also instantiates the scanner for user inputs
    public Library() {
        this.books = new ArrayList<>();
        this.transactions = new ArrayList<>();
    }

    // REQUIRES: a book to have a name, author, and ISBN.
    // No duplicates
    // MODIFIES: this
    // EFFECTS: adds a book to the collection of books
    public void addBook(Book book) {
        EventLog.getInstance().logEvent(new Event("Added the book '" + book.getName() + "' to the library."));
        this.books.add(book);
    }

    // REQUIRES: a book to have a name, author, and ISBN
    // MODIFIES: this
    // EFFECTS: removes a book from the collection of books
    public void removeBook(Book book) {
        for (int i = 0; i < books.size(); i++) {
            if (Objects.equals(book.getName(), books.get(i).getName())) {
                EventLog.getInstance().logEvent(new Event("Removed the book '" + book.getName() + "' to the library."));
                books.remove(i);
                return;
            }
        }
    }

    // REQUIRES: book and patron to have appropriate name, author, ISBN, and availability is true of book
    // and name and library card number of the patron
    // MODIFIES: this
    // EFFECTS: if book is available, changes the book availability to false.
    // Adds the transaction to the collection of transactions.
    public void checkOutBook(Book book, Patron patron) {
        for (Book b : books) {
            if (Objects.equals(b.getName(), book.getName())) {
                b.setAvailability(false);
            }
        }
        EventLog.getInstance().logEvent(new Event("Checked out '" + book.getName() + "' from the library."));
        transactions.add(new LibraryTransaction(book, patron, true));
    }

    // REQUIRES: book and patron to have appropriate name, author, ISBN, and availability is false of book
    // and name and library card number of patron
    // MODIFIES: this
    // EFFECTS: if book is not available, changes the book availability to true.
    // Adds the transaction to the collection of transactions.
    public void checkInBook(Book book, Patron patron) {
        for (Book b : books) {
            if (Objects.equals(b.getName(), book.getName())) {
                b.setAvailability(true);
            }
        }
        EventLog.getInstance().logEvent(new Event("Checked in '" + book.getName() + "' back into the library."));
        transactions.add(new LibraryTransaction(book, patron, false));
    }

    // EFFECTS: sorts the books in the library
    public void sortBooks() {
        EventLog.getInstance().logEvent(new Event("Sorted the books in the library."));
        Collections.sort(books);
    }

    // GETTERS
    public List<Book> getBooks() {
        return this.books;
    }

    public List<LibraryTransaction> getTransactions() {
        return this.transactions;
    }

    // EFFECTS: adds books to json
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", "Bibliotech's library");
        json.put("books", booksToJson());
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray booksToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Book b : books) {
            jsonArray.put(b.toJson());
        }
        return jsonArray;
    }
}
