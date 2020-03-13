package main;

import error.ErrorLogger;
import main.progression.Reward;

public class Driver {
    public static void main(String[] args) {
        try {
            Session.start();
        } catch (Exception e) {
            ErrorLogger.logFatalException(e);
        }
    }
}
