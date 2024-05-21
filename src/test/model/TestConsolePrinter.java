package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestConsolePrinter {
    private Event e1;
    private ConsolePrinter logPrinter;
    private EventLog el;

    @BeforeEach
    public void runBefore() {
        logPrinter = new ConsolePrinter();
        e1 = new Event("A1");
        el = EventLog.getInstance();
        el.logEvent(e1);
    }

    @Test
    public void testPrintLog() {
        logPrinter.printLog(el);
        assertEquals(e1.getDate().toString() + "\n" + e1.getDescription(), e1.toString());
    }

}
