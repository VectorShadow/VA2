package main.modes;

import contract.menu.Menu;
import io.out.GUIManager;
import main.Session;

import java.awt.*;
import java.awt.event.KeyEvent;

public class MainMenuMode implements Mode {

    private Menu menu;

    public MainMenuMode(Menu menu) {
        this.menu = menu;
    }

    @Override
    public void to() {
        GUIManager gm = Session.getGuiManager();
        gm.changeChannelToFullscreenText();
        gm.clearScreen();
        out();
    }

    @Override
    public void in(KeyEvent ke) {
        //todo - map ke to menu functions
        // on up, regress
        // on down, advance
        // on enter, check menu.getSelectedOptionIndex against MenuDefinitions constants and handle accordingly
        // on exit, Session.getModeManager().revert();
    }

    @Override
    public void out() {
        GUIManager gm = Session.getGuiManager();
        gm.printMenu(0.35, menu, Color.BLACK, Color.GREEN);
    }

    @Override
    public void from() {
        //nothing to do
    }
}
