package main.modes.menuModes;

import io.out.GUIManager;
import io.out.message.MessageType;
import main.Session;
import main.modes.ConfirmLastInputMode;
import main.modes.MainGameViewMode;
import main.modes.OperatingMode;
import main.modes.TransitiveTextMode;
import menu.MenuDefinitions;
import util.Coordinate;
import world.Lore;
import world.actor.Actor;
import world.actor.ActorDefinitions;
import world.dungeon.theme.ThemeDefinitions;

public class MainMenuMode extends MenuMode {

    @Override
    public void to() {
        setMenu(MenuDefinitions.getMainMenu());
        GUIManager gm = Session.getGuiManager();
        gm.changeChannelToFullscreenText();
        out();
    }

    @Override
    public void from() {
        //nothing to do
    }

    @Override
    protected void handleMenuOptionIndex(int index) {
        System.out.println("Index: " + index);
        switch (index) {
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
            return;
        case MenuDefinitions.MAIN_MENU_EXIT:
            Session.getModeManager().revert();
            return;
            default:
                out();
        }
    }
}
