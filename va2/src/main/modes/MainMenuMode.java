package main.modes;

import contract.menu.Menu;
import io.out.GUIManager;
import main.Session;
import menu.MenuDefinitions;
import world.actor.Actor;
import world.actor.ActorDefinitions;

import java.awt.*;
import java.awt.event.KeyEvent;

import static java.awt.event.KeyEvent.*;

public class MainMenuMode implements OperatingMode {

    private Menu menu;

    public MainMenuMode(Menu menu) {
        this.menu = menu;
    }

    @Override
    public void to() {
        GUIManager gm = Session.getGuiManager();
        gm.changeChannelToFullscreenText();
        out();
    }

    @Override
    public void in(KeyEvent ke) {
        if (OperatingMode.overrideHandleInput(ke)) return;
        switch (ke.getKeyCode()) {
            case VK_UP: case VK_NUMPAD8:
                menu.regress();
                break;
            case VK_DOWN: case VK_NUMPAD2:
                menu.advance();
                break;
            case VK_ENTER:
                switch (menu.getSelectedOptionIndex()) {
                    case MenuDefinitions.MAIN_MENU_NEW_GAME:
                        //todo - hack: for now, create a new player and transition to mainGameViewMode
                        Session.setPlayerActor(
                                new Actor(ActorDefinitions.PLAYER_TEMPLATE)
                        );
                        Session.getModeManager().transitionTo(new MainGameViewMode());
                        return;
                    case MenuDefinitions.MAIN_MENU_LOAD_GAME:
                        //todo
                        break;
                    case MenuDefinitions.MAIN_MENU_VIEW_PROFILE:
                        //todo
                        break;
                    case MenuDefinitions.MAIN_MENU_EXIT:
                        Session.getModeManager().revert();
                        return;
                }
                break;
        }
        out();
    }

    @Override
    public void out() {
        GUIManager gm = Session.getGuiManager();
        gm.clearScreen();
        gm.printMenu(0.35, menu, Color.BLACK, Color.GREEN);
    }

    @Override
    public void from() {
        //nothing to do
    }
}
