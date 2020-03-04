package main.modes.menu;

import contract.menu.Menu;
import contract.menu.MenuBuilder;
import contract.menu.MenuOption;
import main.Session;

/**
 * Contains the definitions for each Menu which exists in the game.
 */
public class MenuDefinitions {

    static final int MAIN_MENU_NEW_GAME = 0;
    static final int MAIN_MENU_LOAD_GAME = 1;
    static final int MAIN_MENU_DELETE_GAME = 2;
    static final int MAIN_MENU_VIEW_PROFILE = 3;
    static final int MAIN_MENU_EXIT = 4;

    static Menu getMainMenu() {
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

    public static final int LIBRARY_OPTIONS_TEXTS = 0;
    public static final int LIBRARY_OPTIONS_LORE = 1;
    public static final int LIBRARY_OPTIONS_LANGUAGES = 2;
    public static final int LIBRARY_OPTIONS_EXIT = 3;

    public static Menu getLibraryOptions() {
        MenuOption studyTexts = new MenuOption("Study New Texts", false);
        MenuOption browseLore = new MenuOption("Browse Known Lore", false);
        MenuOption checkLanguages = new MenuOption("Show Language Collection", false);
        MenuOption exitLibrary = new MenuOption("Exit Library", true);
        return MenuBuilder.newMenu("Library Options")
                .addOption(studyTexts)
                .addOption(browseLore)
                .addOption(checkLanguages)
                .addOption(exitLibrary)
                .build();
    }
}
