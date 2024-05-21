package ui;

import model.Book;
import model.Library;
import model.LibraryTransaction;
import model.Patron;

import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// Represents the application for Bibliotech
public class BibliotechApp {
    private static final String JSON_STORE = "./data/library.json";
    private Scanner scanner;
    private Library library;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: creates the application that instantiates the library and scanner class
    public BibliotechApp() {
        scanner = new Scanner(System.in);
        library = new Library();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        startLibraryMenu();
    }

    // EFFECTS: Starts the console menu for user to input into
    public void startLibraryMenu() {
        System.out.println("Welcome to Bibliotech!");

        boolean check = true;
        while (check) {
            System.out.println("Type '1' to login as a Librarian, '2' to enter as a patron, "
                    + "'3' to save the current library, '4' to load the library from file, "
                    + "or any other number to QUIT.");
            int response = scanner.nextInt();
            if (response < 1 || response > 4) {
                System.out.println("Quitting application.");
                check = false;
            }
            loginMenuOptions(response);
        }
    }

    public void loginMenuOptions(int response) {
        switch (response) {
            case 1:
                handleLoginAsLibrarian();
                break;
            case 2:
                handleLoginAsPatron();
                break;
            case 3:
                saveLibrary();
                break;
            case 4:
                loadLibrary();
                break;
        }
    }

    // EFFECTS: prints out menu options for librarian
    private void handleLoginAsLibrarian() {
        boolean check = true;
        while (check) {
            librarianMenu();
            int input = scanner.nextInt();
            if (input < 1 || input > 4) {
                System.out.println("Logging out.\n");
                check = false;
            } else {
                callMenuOptionsForLibrarian(input);
            }
        }
    }

    // EFFECTS: prints out menu options for patron
    private void handleLoginAsPatron() {
        boolean check = true;
        while (check) {
            patronMenu();
            int input = scanner.nextInt();
            if (input < 1 || input > 3) {
                System.out.println("Logging out.\n");
                check = false;
            } else {
                callMenuOptionsForPatron(input);
            }
        }
    }

    // EFFECTS: takes input from the user to create a new book with a name, author, and ISBN
    public Book createBookFromUserInput() {
        System.out.println("Enter the name of the book:");
        scanner.nextLine();
        String name = scanner.nextLine();
        System.out.println("Enter the author of the book:");
        String author = scanner.nextLine();
        System.out.println("Enter the ISBN of the book:");
        int bookNum = scanner.nextInt();
        return new Book(name, author, bookNum);
    }

    // EFFECTS: takes input from the user to create a new Patron with a name and library card number
    public Patron createPatronFromUserInput() {
        System.out.println("Enter your name:");
        scanner.nextLine();
        String name = scanner.nextLine();
        System.out.println("Enter your library card number:");
        int cardNum = scanner.nextInt();
        return new Patron(name, cardNum);
    }

    // EFFECTS: prints out the menu for the librarian
    public void librarianMenu() {
        System.out.println("\nYou have logged in as a Librarian. Please choose an option:\n"
                + "1: Display all books within the library\n" + "2: Display all library transactions\n"
                + "3: Add a book to the library\n" + "4: Remove a book from the library\n"
                + "Any number: Quit");
    }

    // EFFECTS: prints out menu for librarian
    public void patronMenu() {
        System.out.println("\nYou have entered as a patron. Please choose an option:\n"
                + "1: Display all books within the library\n" + "2: Checkout a book\n"
                + "3: Check in a book\n" + "Any number: Quit");
    }

    // EFFECTS: librarian menu methods are called based upon user input
    public void callMenuOptionsForLibrarian(int input) {
        switch (input) {
            case 1:
                listAllBooks();
                break;
            case 2:
                listAllTransactions();
                break;
            case 3:
                library.addBook(createBookFromUserInput());
                break;
            case 4:
                library.removeBook(createBookFromUserInput());
                break;
        }
    }

    // EFFECTS: patron menu methods are called based upon user input
    public void callMenuOptionsForPatron(int input) {
        switch (input) {
            case 1:
                listAllBooks();
                break;
            case 2:
                library.checkOutBook(createBookFromUserInput(), createPatronFromUserInput());
                break;
            case 3:
                library.checkInBook(createBookFromUserInput(), createPatronFromUserInput());
                break;
        }
    }

    // EFFECTS: prints out all the books within the collection of books
    public void listAllBooks() {
        System.out.println("\n---List of Books in the Library---");
        for (Book b : library.getBooks()) {
            System.out.println(b.toString());
        }
    }

    // EFFECTS: prints out all the transactions within the collection of library transactions
    public void listAllTransactions() {
        System.out.println("\n---List of Transactions in the Library---");
        for (LibraryTransaction transaction : library.getTransactions()) {
            System.out.println(transaction.toString());
        }
    }

    // MODIFIES: this
    // EFFECTS: saves the workroom to file
    private void saveLibrary() {
        try {
            jsonWriter.open();
            jsonWriter.write(library);
            jsonWriter.close();
            System.out.println("Saved library to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadLibrary() {
        try {
            library = jsonReader.read();
            System.out.println("Loaded library from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
