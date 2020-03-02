package main.modes;

import contract.menu.Menu;
import io.out.GUIManager;
import io.out.message.MessageType;
import main.Session;
import menu.MenuDefinitions;
import util.Coordinate;
import world.Lore;
import world.actor.Actor;
import world.actor.ActorDefinitions;
import world.dungeon.theme.ThemeDefinitions;

import java.awt.event.KeyEvent;

import static java.awt.event.KeyEvent.*;

public class MainMenuMode implements OperatingMode {

    private Menu menu;

    @Override
    public void to() {
        menu = MenuDefinitions.getMainMenu();
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
                        Session.getPlayer().setActor(new Actor(ActorDefinitions.PLAYER_TEMPLATE));
                        Session.getMessageCenter().sendMessage("Welcome to Chronicles of the Abyss!", MessageType.GAME);
                        Session.setCurrentFloor(ThemeDefinitions.YSIAN_ESTATE.generateFloor(0));
                        OperatingMode targetMode = new MainGameViewMode();
                        if (!Session.getLore().isUnlocked(Lore.GENERAL, Lore.GENERAL_NEW_GAME)) {
                            Session.getModeManager().transitionTo(
                                    new TransitiveTextMode(
                                            Session.getLore().lore(Lore.GENERAL, Lore.GENERAL_NEW_GAME), targetMode));
                        } else
                            Session.getModeManager().transitionTo(targetMode);
                        //hack - generate a test ai
                        Actor aiTest = new Actor(ActorDefinitions.PLAYER_TEMPLATE);
                        Session.addActor(aiTest, new Coordinate(8,5));
                        return;
                    case MenuDefinitions.MAIN_MENU_LOAD_GAME:
                        Session.getMessageCenter().sendMessage("Welcome back.", MessageType.GAME);
                        Session.getModeManager().transitionTo(new MainGameViewMode());
                        return;
                    case MenuDefinitions.MAIN_MENU_DELETE_GAME:
                        Session.getModeManager().transitionTo(new ConfirmLastInputMode());
                        return;
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
        gm.printMenu(0.35, menu);
    }

    @Override
    public void from() {
        //nothing to do
    }
}
