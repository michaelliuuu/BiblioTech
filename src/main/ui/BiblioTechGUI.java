package ui;

import model.Library;
import model.Book;
import persistence.JsonReader;
import persistence.JsonWriter;
import model.Event;
import model.EventLog;
import model.ConsolePrinter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class BiblioTechGUI extends JFrame {
    // save/reload
    private static final String JSON_STORE = "./data/library.json";
    private Library library;
    private JsonReader reader;
    private JsonWriter writer;

    // Card Panel
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private CardLayout cardLayoutForLibrarian;
    private JPanel cardPanelForLibrarian;
    private JPanel displayLibraryPanel;
    private JPanel addBookPanel;
    private JPanel librarianImagePanel;
    private JPanel removeBookPanel;

    // Login Panel
    private JButton loginLibrarianBtn;
    private JButton loginPatronBtn;
    private JButton saveBtn;
    private JButton reloadBtn;
    private JButton loginMenuButton;

    // Librarian Panel
    private JButton displayLibraryBtn;
    private JButton displayLibraryTransactionsBtn;
    private JButton addBookBtn;
    private JButton removeBookBtn;
    private JTextField nameTF;
    private JTextField authorTF;
    private JTextField bookNumTF;
    private JTextField nameTF1;
    private JTextField authorTF1;
    private JTextField bookNumTF1;
    private JButton submitBtn;
    private JButton removeBtn;
    private JTextArea displayLibraryTA;
    private JPanel librarianPanel;
    private JButton sortDisplayLibraryBtn;
    private JPanel librarianBtnPanel;

    // Patron menu
    private JButton checkOutBtn;
    private JButton checkInBtn;
    private TextField patronName;
    private TextField libraryCardNum;

    // Images
    private ImageIcon loginLibraryImage;
    private ImageIcon checkOutDeskImage;
    private ImageIcon librarianImage;
    private JPanel imagePanel;
    private JLabel imageAsLabel;
    private JLabel imageAsLabel2;

    // EFFECTS: loads the GUI of the Bibliotech application
    public BiblioTechGUI() {
        // Creates the GUI application
        super("BiblioTech");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(740, 400));

        // instantiates save/reload
        library = new Library();
        reader = new JsonReader(JSON_STORE);
        writer = new JsonWriter(JSON_STORE);
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // loads images
        loadImages();
        addWindowActionListener();

        // Creates the login panel
        JPanel loginPanel = createLoginPanel();
        cardPanel.add(loginPanel, "login");

        // Creates the librarian panel
        JPanel librarianPanel = createLibrarianPanel();
        cardPanel.add(librarianPanel, "librarianPanel");

        // Creates the patron panel
        JPanel patronPanel = createPatronPanel();
        cardPanel.add(patronPanel, "patronPanel");

        // Adds and shows the login panel
        add(cardPanel);
        cardLayout.show(cardPanel, "login");

        // etc
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    // EFFECTS: creates the login panel where you can log in as a librarian/patron, or save/reload the application
    private JPanel createLoginPanel() {
        JPanel loginPanel = new JPanel(new FlowLayout());
        JPanel loginBtnPanel = new JPanel();

        JLabel loginTitle = new JLabel("Welcome to Bibliotech!");
        loginLibrarianBtn = new JButton("Login as Librarian");
        loginPatronBtn = new JButton("Login as Patron");
        saveBtn = new JButton("Save Application");
        reloadBtn = new JButton("Reload Application");
        imageAsLabel = new JLabel(loginLibraryImage);
        imagePanel.add(imageAsLabel);
        loginBtnPanel.add(loginLibrarianBtn);
        loginBtnPanel.add(loginPatronBtn);
        loginBtnPanel.add(saveBtn);
        loginBtnPanel.add(reloadBtn);

        loginMenuActionListeners();
        saveReloadActionListeners();

        loginPanel.add(loginTitle);
        loginPanel.add(loginBtnPanel);
        loginPanel.add(imagePanel);
        return loginPanel;
    }

    // EFFECTS: creates the librarian panel where you can display the library,
    // display the library transaction, add a book, remove a book
    public JPanel createLibrarianPanel() {
        // Fields for librarian menu
        librarianPanel = new JPanel(new FlowLayout());
        librarianBtnPanel = new JPanel();

        JLabel librarianTitle = new JLabel("Librarian Menu");
        intializeCreateLibrarianPanelElements();

        // Adds buttons to librarianBtnPanel
        addButtonsToLibrarianPanel();

        // Card Layout for the buttons
        cardLayoutForLibrarian = new CardLayout();
        cardPanelForLibrarian = new JPanel(cardLayoutForLibrarian);

        JPanel displayLibraryPanel = createDisplayLibraryPanel();
        cardPanelForLibrarian.add(displayLibraryPanel, "displayLibrarianPanel");

        JPanel addBookPanel = createAddBookPanel();
        cardPanelForLibrarian.add(addBookPanel, "addBookPanel");

        JPanel librarianImagePanel = createDefaultLibrarianPanel();
        cardPanelForLibrarian.add(librarianImagePanel, "librarianImagePanel");

        JPanel removeBookPanel = createRemoveBookPanel();
        cardPanelForLibrarian.add(removeBookPanel, "removeBookPanel");

        // Action Listeners
        librarianPanelActionListeners();

        // Adding to librarian panel
        librarianPanel.add(librarianTitle);
        librarianPanel.add(librarianBtnPanel);

        librarianPanel.add(cardPanelForLibrarian);
        cardLayoutForLibrarian.show(cardPanelForLibrarian, "librarianImagePanel");

        return librarianPanel;
    }

    // EFFECTS: creates the patron panel where you can display the library,
    // checkout a book, check in a book
    public JPanel createPatronPanel() {
        JPanel patronPanel = new JPanel(new FlowLayout());

        JLabel patronTitle = new JLabel("Patron Menu");
        displayLibraryBtn = new JButton("Display Library");
        checkOutBtn = new JButton("Checkout Book");
        checkInBtn = new JButton("Checkin Book");
        loginMenuButton = new JButton("Back to Login");

        switchBackToLoginMenuActionListener();

        patronPanel.add(patronTitle);
        patronPanel.add(displayLibraryBtn);
        patronPanel.add(checkOutBtn);
        patronPanel.add(checkInBtn);
        patronPanel.add(loginMenuButton);
        return patronPanel;
    }

    // MODIFIES: this
    // EFFECTS: creates the image librarian card panel
    public JPanel createDefaultLibrarianPanel() {
        librarianImagePanel = new JPanel();

        imageAsLabel2 = new JLabel(librarianImage);
        librarianImagePanel.add(imageAsLabel2);
        librarianImagePanel.setPreferredSize(new Dimension(200, 200));

        return librarianImagePanel;
    }

    // MODIFIES: this
    // EFFECTS: creates the display library card panel
    public JPanel createDisplayLibraryPanel() {
        displayLibraryPanel = new JPanel(new FlowLayout());

        displayLibraryTA = new JTextArea();
        displayLibraryTA.setPreferredSize(new Dimension(300, 250));
        displayLibraryTA.setEditable(false);
        displayLibraryTA.setLineWrap(true);
        displayLibraryTA.setVisible(true);
        displayLibraryPanel.add(displayLibraryTA);
        displayLibraryPanel.add(sortDisplayLibraryBtn);

        updateDisplayLibraryTextArea();

        return displayLibraryPanel;
    }

    // MODIFIES: this
    // EFFECTS: creates the add book card panel
    public JPanel createAddBookPanel() {
        addBookPanel = new JPanel(new FlowLayout());

        nameTF.setPreferredSize(new Dimension(200, 30));
        authorTF.setPreferredSize(new Dimension(200, 30));
        bookNumTF.setPreferredSize(new Dimension(200, 30));
        addBookPanel.add(nameTF);
        addBookPanel.add(authorTF);
        addBookPanel.add(bookNumTF);
        addBookPanel.add(submitBtn);

        return addBookPanel;
    }

    // MODIFIES: this
    // EFFECTS: creates the remove book panel card panel
    public JPanel createRemoveBookPanel() {
        removeBookPanel = new JPanel(new FlowLayout());

        nameTF1.setPreferredSize(new Dimension(200, 30));
        authorTF1.setPreferredSize(new Dimension(200, 30));
        bookNumTF1.setPreferredSize(new Dimension(200, 30));
        removeBookPanel.add(nameTF1);
        removeBookPanel.add(authorTF1);
        removeBookPanel.add(bookNumTF1);
        removeBookPanel.add(removeBtn);

        return removeBookPanel;
    }

    // EFFECTS: adds action to login as librarian or patron button where it switches to librarian or patron card panel
    public void loginMenuActionListeners() {
        loginLibrarianBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "librarianPanel");
            }
        });

        loginPatronBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "patronPanel");
            }
        });
    }

    // EFFECTS: adds action to save and reload buttons where it can save
    // the current library and can reload the saved library
    public void saveReloadActionListeners() {
        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    writer.open();
                    writer.write(library);
                    writer.close();
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        reloadBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // reload the application
                try {
                    library = reader.read();
                    updateDisplayLibraryTextArea();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    // EFFECTS: adds action to button where it switches back to login card panel
    public void switchBackToLoginMenuActionListener() {
        loginMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "login");
            }
        });
    }

    // EFFECTS: action listeners for librarian panel
    public void librarianPanelActionListeners() {
        switchBackToLoginMenuActionListener();
        displayLibraryActionListener();
        addingBookActionListener();
        removingBookActionListener();
        submittingBookActionListener();
        removingBookBtnActionListener();
        sortDisplayLibraryBtnActionListener();
    }

    // EFFECTS: adds action textfields so it can be created into a book to be added into library
    public void addingBookActionListener() {
        addBookBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayoutForLibrarian.show(cardPanelForLibrarian, "addBookPanel");
            }
        });
    }

    // EFFECTS: adds action textfields, so it can be created into a book to be removed from library
    public void removingBookActionListener() {
        removeBookBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayoutForLibrarian.show(cardPanelForLibrarian, "removeBookPanel");
            }
        });
    }

    //MODIFIES: this
    // EFFECTS: adds action to submit button where it removes the book to the library
    public void removingBookBtnActionListener() {
        removeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Book book = new Book(nameTF1.getText(), authorTF1.getText(), Integer.parseInt(bookNumTF1.getText()));
                library.removeBook(book);
                updateDisplayLibraryTextArea();
                nameTF1.setText("");
                authorTF1.setText("");
                bookNumTF1.setText("");
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: adds action to submit button where it adds the book to the library
    public void submittingBookActionListener() {
        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Book book = new Book(nameTF.getText(), authorTF.getText(), Integer.parseInt(bookNumTF.getText()));
                library.addBook(book);
                updateDisplayLibraryTextArea();
                nameTF.setText("");
                authorTF.setText("");
                bookNumTF.setText("");
            }
        });
    }

    // EFFECTS: adds action to display library button where it displays the library in a textarea
    public void displayLibraryActionListener() {
        displayLibraryBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayoutForLibrarian.show(cardPanelForLibrarian, "displayLibrarianPanel");
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: updates the display library textarea with the books in the library
    public void updateDisplayLibraryTextArea() {
        displayLibraryTA.setText("");
        for (Book book : library.getBooks()) {
            displayLibraryTA.append("Book Title: " + book.getName() + " , Author: " + book.getAuthor()
                    + " , Book Number: " + book.getBookNumber()
                    + " , Availability: " + book.getIsAvailable() + "\n");
        }
    }

    // MODIFIES: this
    // EFFECTS: adds action to sort library button where it sorts the library and updates the textarea
    public void sortDisplayLibraryBtnActionListener() {
        sortDisplayLibraryBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                library.sortBooks();
                updateDisplayLibraryTextArea();
            }
        });
    }

    // EFFECTS: instantiates elements needed for creating librarian panel
    public void intializeCreateLibrarianPanelElements() {
        displayLibraryBtn = new JButton("Display Library");
        displayLibraryTransactionsBtn = new JButton("Display Library Transactions");
        addBookBtn = new JButton("Add Book");
        removeBookBtn = new JButton("Remove Book");
        loginMenuButton = new JButton("Back to Login");
        nameTF = new JTextField();
        authorTF = new JTextField();
        bookNumTF = new JTextField();
        nameTF1 = new JTextField();
        authorTF1 = new JTextField();
        bookNumTF1 = new JTextField();
        submitBtn = new JButton("Submit");
        removeBtn = new JButton("Remove");
        sortDisplayLibraryBtn = new JButton("Sort");
    }

    // MODIFIES: this
    // EFFECTS: adds librarian menu buttons to a panel
    public void addButtonsToLibrarianPanel() {
        librarianBtnPanel.add(displayLibraryBtn);
        librarianBtnPanel.add(displayLibraryTransactionsBtn);
        librarianBtnPanel.add(addBookBtn);
        librarianBtnPanel.add(removeBookBtn);
        librarianBtnPanel.add(loginMenuButton);
    }

    // MODIFIES: this
    // EFFECTS: loads and displays images
    private void loadImages() {
        String sep = System.getProperty("file.separator");
        loginLibraryImage = new ImageIcon(System.getProperty("user.dir") + sep + "images" + sep + "loginlibrary.jpeg");
        checkOutDeskImage = new ImageIcon(System.getProperty("user.dir") + sep + "images" + sep + "checkoutdesk.jpeg");
        librarianImage = new ImageIcon(System.getProperty("user.dir") + sep + "images" + sep + "librarian.jpeg");

        imagePanel = new JPanel();
        imagePanel.setPreferredSize(new Dimension(500, 250));
        imagePanel.setBorder(new EmptyBorder(25, 0, 0, 0));
    }

    // EFFECTS: adds action to addWindowActionListener by calling printLogsAndExit()
    public void addWindowActionListener() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                printLogsAndExit();
            }
        });
    }

    // EFFECTS: prints out the events in the event log
    private void printLogsAndExit() {
        EventLog eventLog = EventLog.getInstance();

        ConsolePrinter logPrinter = new ConsolePrinter();
        logPrinter.printLog(eventLog);

        System.exit(0);
    }

    public static void main(String[] args) {
        new BiblioTechGUI();
    }
}
