package menu;

import contract.menu.Menu;
import contract.menu.MenuBuilder;
import contract.menu.MenuOption;
/**
 * Contains the definitions for each Menu which exists in the game.
 */
public class MenuDefinitions {

    public static final int MAIN_MENU_NEW_GAME = 0;
    public static final int MAIN_MENU_LOAD_GAME = 1;
    public static final int MAIN_MENU_VIEW_PROFILE = 2;
    public static final int MAIN_MENU_EXIT = 3;

    public static Menu getMainMenu() {
        //todo - determine which game option should be enabled based on whether a savefile exists.
        // Session.getFileManager() should provide this information.
        MenuOption newGame = new MenuOption("Begin New Game");
        MenuOption loadGame = new MenuOption("Continue Existing Game", false);
        MenuOption viewProfile = new MenuOption("View Player Profile", false);
        MenuOption exit = new MenuOption("Exit");
        return MenuBuilder.newMenu("Main Menu")
                .addOption(newGame)
                .addOption(loadGame)
                .addOption(viewProfile)
                .addOption(exit)
                .build();
    }
}
