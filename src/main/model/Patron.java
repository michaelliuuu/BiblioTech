package model;

// Represents a patron that has a name and a library card number
public class Patron {
    private String name;
    private int libraryCardNumber;

    // REQUIRES: The name and library card number to be not empty
    // EFFECTS: Creates a patron with the given name and library card number
    public Patron(String name, int cardNum) {
        this.name = name;
        this.libraryCardNumber = cardNum;
    }

    // EFFECTS: returns the name and library card number of the patron
    public String toString() {
        return "Name: " + this.name + ", Library Card Number: " + this.libraryCardNumber;
    }

    // GETTER
    public String getName() {
        return this.name;
    }
}
