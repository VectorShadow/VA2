package menu;

import contract.menu.Menu;
import contract.menu.MenuBuilder;
import contract.menu.MenuOption;
import main.Session;

/**
 * Contains the definitions for each Menu which exists in the game.
 */
public class MenuDefinitions {

    public static final int MAIN_MENU_NEW_GAME = 0;
    public static final int MAIN_MENU_LOAD_GAME = 1;
    public static final int MAIN_MENU_DELETE_GAME = 2;
    public static final int MAIN_MENU_VIEW_PROFILE = 3;
    public static final int MAIN_MENU_EXIT = 4;

    public static Menu getMainMenu() {
        boolean saveFileExists = Session.getFileManager().loadSavedGame();
        MenuOption newGame = new MenuOption("Begin New Game", !saveFileExists);
        MenuOption loadGame = new MenuOption("Continue Existing Game", saveFileExists);
        MenuOption deleteGame = new MenuOption("Delete Existing Game", saveFileExists);
        MenuOption viewProfile = new MenuOption("View Player Profile", false);
        MenuOption exit = new MenuOption("Exit");
        return MenuBuilder.newMenu("Main Menu")
                .addOption(newGame)
                .addOption(loadGame)
                .addOption(deleteGame)
                .addOption(viewProfile)
                .addOption(exit)
                .build();
    }
}
