package io.out;

import main.Session;
import main.modes.BottomMode;

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
        if (Session.getModeManager().getCurrentMode() instanceof BottomMode)
            return; //nothing has been loaded which needs to be saved at this point.
        if (Session.isPlaying())//if we've begun a game, save it
            Session.getFileManager().saveGameState();
        else //otherwise just save the profile
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
