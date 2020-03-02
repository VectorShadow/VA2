package io.out;

import main.Session;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class AutoSaveWindowListener implements WindowListener {
    @Override
    public void windowOpened(WindowEvent e) {

    }

    /**
     * Save relevant data when the user tries to close this window.
     */
    @Override
    public void windowClosing(WindowEvent e) {
        Session.getFileManager().saveGameState();
        Session.getFileManager().saveProfile();
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
