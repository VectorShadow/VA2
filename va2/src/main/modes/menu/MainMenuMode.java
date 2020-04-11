package main.modes.menu;

import io.out.GUIManager;
import io.out.message.MessageCenter;
import io.out.message.MessageType;
import main.Session;
import main.modes.ConfirmDeleteGameMode;
import main.modes.MainGameViewMode;
import main.modes.OperatingMode;
import util.Coordinate;
import world.lore.LockLeaf;
import world.lore.LoreDefinitions;

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
        switch (index) {
            case MenuDefinitions.MAIN_MENU_NEW_GAME:
                Session.getMessageCenter().clearNewMessages();
                Session.getMessageCenter().sendMessage("Welcome to Chronicles of the Abyss!", MessageType.GAME, MessageCenter.PRIORITY_HIGH);
                //hardcode - player should begin the game at the Library instead of the default spawn tile
                Session.addActor(Session.getPlayer().getActor(), new Coordinate(9, 2));
                OperatingMode targetMode = new MainGameViewMode();
                if (((LockLeaf)LoreDefinitions.getLockTree().get(
                        LoreDefinitions.THEME_GENERAL,
                        LoreDefinitions.GENERAL_NEW_CHARACTER
                )).isLocked()) {
                    Session.unlockLore(LoreDefinitions.THEME_GENERAL, LoreDefinitions.GENERAL_NEW_CHARACTER, targetMode);
                } else
                    Session.getModeManager().transitionTo(targetMode);
                return;
            case MenuDefinitions.MAIN_MENU_LOAD_GAME:
                Session.getMessageCenter().clearNewMessages();
                Session.getMessageCenter().sendMessage("Welcome back.", MessageType.GAME, MessageCenter.PRIORITY_HIGH);
                Session.getModeManager().transitionTo(new MainGameViewMode());
                return;
            case MenuDefinitions.MAIN_MENU_DELETE_GAME:
                Session.getModeManager().transitionTo(new ConfirmDeleteGameMode());
                return;
            case MenuDefinitions.MAIN_MENU_VIEW_PROFILE:
                Session.getModeManager().transitionTo(new ProfileMenuMode());
                return;
            case MenuDefinitions.MAIN_MENU_EXIT:
                Session.getModeManager().revert();
                return;
                default:
                    out();
        }
    }
}
