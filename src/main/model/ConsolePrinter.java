package model;

// Represents a console printer
public class ConsolePrinter {

    // EFFECTS: prints out the events within the event log
    public void printLog(EventLog el) {
        for (Event event: el) {
            System.out.println(event.toString());
        }
    }
}
