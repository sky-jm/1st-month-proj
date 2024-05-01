package ch.jakubma.cokedispenser.DispenserLogger;

import java.util.logging.Logger;

public class CustomLogger {

    private static CustomLogger clInstance;
    private CustomLogger() {}

    public synchronized static CustomLogger getInstance() {
        if (clInstance == null) {
            clInstance = new CustomLogger();
        }
        return clInstance;
    }

    public void log(String message) {
        System.out.println(message);
    }

    public void error(String message) {
        System.err.println(message);
    }

}
