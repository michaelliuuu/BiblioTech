package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a book containing information that a Librarian or patron can look at.
public class Book implements Writable, Comparable<Book> {
    private String name;
    private String author;
    private int bookNumber;
    private boolean isAvailable;

    // REQUIRES: The name, author, and book number to not be empty
    // EFFECTS: Creates a book with the given name, author, and book number
    public Book(String name, String author, int bookNumber) {
        this.name = name;
        this.author = author;
        this.bookNumber = bookNumber;
        this.isAvailable = true;
    }

    // EFFECTS: returns the name, author, and ISBN of the book
    @Override
    public String toString() {
        return "Name: " + this.name + ", Author: " + this.author + ", ISBN: " + this.bookNumber + ", Available: "
                + this.isAvailable;
    }

    // GETTERS/SETTERS
    public String getName() {
        return this.name;
    }

    public String getAuthor() {
        return this.author;
    }

    public int getBookNumber() {
        return this.bookNumber;
    }

    public boolean getIsAvailable() {
        return this.isAvailable;
    }

    public void setAvailability(boolean state) {
        this.isAvailable = state;
    }

    // EFFECTS saves book information to json
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", this.name);
        json.put("author", this.author);
        json.put("book number", this.bookNumber);
        json.put("availability", this.isAvailable);
        return json;
    }

    // EFFECTS: compares book name
    @Override
    public int compareTo(Book o) {
        return this.getName().compareTo(o.getName());
    }
}
