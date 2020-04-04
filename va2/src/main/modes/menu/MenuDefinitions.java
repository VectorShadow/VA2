package main.modes.menu;

import contract.menu.Menu;
import contract.menu.MenuBuilder;
import contract.menu.MenuOption;
import main.Session;
import main.progression.estate.EstateProgressionNode;
import main.progression.estate.EstateProgressionRoom;
import world.item.inventory.ItemSlot;
import world.lore.*;

import static main.progression.estate.EstateProgression.*;

/**
 * Contains the definitions for each Menu which exists in the game.
 */
public class MenuDefinitions {

    public static final String CANCEL = "Cancel";

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
        MenuOption viewProfile = new MenuOption("View Player Profile", true);
        MenuOption exit = new MenuOption("Exit");
        return MenuBuilder.newMenu("Main Menu")
                .addOption(newGame)
                .addOption(loadGame)
                .addOption(deleteGame)
                .addOption(viewProfile)
                .addOption(exit)
                .build();
    }
    static Menu getLoreMenu(int themeBranchIndex) {
        LoreTree lt = LoreDefinitions.getLockTree();
        MenuBuilder mb;
        if (themeBranchIndex < 0) {
            mb = MenuBuilder.newMenu("Lore - Select Theme:");
            for (int i = 0; i < lt.size(); ++i) {
                boolean available = LoreDefinitions.anyUnlocked(i);
                mb.addOption(new MenuOption(available ? lt.getBranch(i).name() : "<locked>", available));
            }
        } else {
            mb = MenuBuilder.newMenu("Select Lore:");
            ThemeBranch tb = lt.getBranch(themeBranchIndex);
            for (int i = 0; i < tb.size(); ++i) {
                LockLeaf ll = (LockLeaf)tb.get(i);
                mb.addOption(new MenuOption(ll.isLocked() ? "<locked>" : LoreDefinitions.nameAt(themeBranchIndex, i), !ll.isLocked()));
            }
        }
        mb.addOption(new MenuOption("Exit", true));
        return mb.build();
    }

    static final int PROFILE_MENU_KNOWN_LANGUAGES = 0;
    static final int PROFILE_MENU_KNOWN_RECIPES = 1;
    static final int PROFILE_MENU_KILL_COUNT_AND_ENEMY_INFO = 2;
    static final int PROFILE_MENU_FULL_SCORES = 3;
    static final int PROFILE_MENU_RESOURCES_AND_UPGRADES = 4;
    static final int PROFILE_MENU_LORE = 5;
    static final int PROFILE_MENU_EXIT = 6;

    static Menu getProfileMenu() {
        MenuOption languages = new MenuOption("Review Known Languages", true);
        MenuOption recipes = new MenuOption("Review Known Formulae & Blueprints", false);
        MenuOption kills = new MenuOption("Review Monster Kills & Info", false);
        MenuOption scores = new MenuOption("Review Game Scores & Progress", false);
        MenuOption legacy = new MenuOption("Review Legacy Resources & Room Upgrades", true);
        MenuOption lore = new MenuOption("Review Discovered Lore", true);
        MenuOption exit = new MenuOption("Exit");
        return MenuBuilder.newMenu("Player Profile Menu")
                .addOption(languages)
                .addOption(recipes)
                .addOption(kills)
                .addOption(scores)
                .addOption(legacy)
                .addOption(lore)
                .addOption(exit)
                .build();
    }

    public static final int LIBRARY_OPTIONS_TEXTS = 0;
    public static final int LIBRARY_OPTIONS_LORE = 1;
    public static final int LIBRARY_OPTIONS_LANGUAGES = 2;
    public static final int LIBRARY_OPTIONS_UPGRADE = 3;
    public static final int LIBRARY_OPTIONS_EXIT = 4;

    public static Menu getLibraryOptions() {
        MenuOption studyTexts = new MenuOption("Study New Texts",
                Session.getEstateProgression().nodeAt(INDEX_LIBRARY, LIBRARY_OPTIONS_TEXTS).isUnlocked());
        MenuOption browseLore = new MenuOption("Browse Known Lore", true);
        MenuOption studyLanguages = new MenuOption("Show Language Knowledge", true);
        MenuOption upgrade = new MenuOption("Upgrade Library", true);
        MenuOption exitLibrary = new MenuOption("Exit Library", true);
        return MenuBuilder.newMenu("Library Options")
                .addOption(studyTexts)
                .addOption(browseLore)
                .addOption(studyLanguages)
                .addOption(upgrade)
                .addOption(exitLibrary)
                .build();
    }

    public static Menu getTextResearchOptions() {
        MenuBuilder mb = MenuBuilder.newMenu("Available Texts: ");
        for (ItemSlot itemSlot : Session.getPlayer().getUnresearchedTexts()) {
            mb.addOption(new MenuOption(itemSlot.peekItem().getTemplate().getName(), true));
        }
        return mb.addOption(new MenuOption(CANCEL, true)).build();
    }

    public static final int HALL_OF_ARMS_OPTIONS_TRAIN = 0;
    public static final int HALL_OF_ARMS_OPTIONS_FORMS = 1;
    public static final int HALL_OF_ARMS_OPTIONS_UPGRADE = 2;
    public static final int HALL_OF_ARMS_OPTIONS_EXIT = 3;

    public static Menu getHallOfArmsOptions() {
        MenuOption train = new MenuOption("Conduct Arms Training", false);
        MenuOption forms = new MenuOption("Assume An Arms Form", false);
        MenuOption upgrade = new MenuOption("Upgrade Hall Of Arms", false);
        MenuOption exit = new MenuOption("Exit Hall Of Arms", true);
        return MenuBuilder.newMenu("Hall Of Arms Options")
                .addOption(train)
                .addOption(forms)
                .addOption(upgrade)
                .addOption(exit)
                .build();
    }

    public static final int ARCHERY_RANGE_OPTIONS_TRAIN = 0;
    public static final int ARCHERY_RANGE_OPTIONS_TECHNIQUES = 1;
    public static final int ARCHERY_RANGE_OPTIONS_UPGRADE = 2;
    public static final int ARCHERY_RANGE_OPTIONS_EXIT = 3;

    public static Menu getArcheryRangeOptions() {
        MenuOption train = new MenuOption("Conduct Ranged Training", false);
        MenuOption techniques = new MenuOption("Prepare A Ranged Technique", false);
        MenuOption upgrade = new MenuOption("Upgrade Archery Range", false);
        MenuOption exit = new MenuOption("Exit Archery Range", true);
        return MenuBuilder.newMenu("Archery Range Options")
                .addOption(train)
                .addOption(techniques)
                .addOption(upgrade)
                .addOption(exit)
                .build();
    }

    public static final int LABORATORY_OPTIONS_RESEARCH_FORMULAE = 0;
    public static final int LABORATORY_OPTIONS_PREPARE_FORMULAE = 1;
    //todo - mix elixir, mix fuel, prepare poison, prepare bomb, prepare treatment as separate sub-menu
    public static final int LABORATORY_OPTIONS_PERFORM_REAGENT_ALCHEMY = 2;
    public static final int LABORATORY_OPTIONS_UPGRADE = 3;
    public static final int LABORATORY_OPTIONS_EXIT = 4;

    public static Menu getLaboratoryOptions() {
        MenuOption research = new MenuOption("Research Alchemical Formulae", false);
        MenuOption prepare = new MenuOption("Prepare Alchemical Formulae", false);
        MenuOption reagents = new MenuOption("Perform Reagent Alchemy", false);
        MenuOption upgrade = new MenuOption("Upgrade Laboratory", false);
        MenuOption exit = new MenuOption("Exit Laboratory", true);
        return MenuBuilder.newMenu("Laboratory Options")
                .addOption(research)
                .addOption(prepare)
                .addOption(reagents)
                .addOption(upgrade)
                .addOption(exit)
                .build();
    }

    public static final int TROPHY_HALL_OPTIONS_AUTOPSY = 0;
    public static final int TROPHY_HALL_OPTIONS_INSPECT_TROPHY = 1;
    public static final int TROPHY_HALL_OPTIONS_KILL_RECORDS = 2;
    public static final int TROPHY_HALL_OPTIONS_UPGRADE = 3;
    public static final int TROPHY_HALL_OPTIONS_EXIT = 4;

    public static Menu getTrophyHallOptions() {
        MenuOption autopsy = new MenuOption("Perform Autopsy", false);
        MenuOption inspect = new MenuOption("Inspect Trophy", false);
        MenuOption killCount = new MenuOption("Review Combat Records", false);
        MenuOption upgrade = new MenuOption("Upgrade Trophy Hall", false);
        MenuOption exit = new MenuOption("Exit Trophy Hall", true);
        return MenuBuilder.newMenu("Trophy Hall Options")
                .addOption(autopsy)
                .addOption(inspect)
                .addOption(killCount)
                .addOption(upgrade)
                .addOption(exit)
                .build();
    }

    public static final int MAUSOLEUM_OPTIONS_HIGH_SCORES = 0;
    public static final int MAUSOLEUM_OPTIONS_CHALLENGE_MODES = 1;
    public static final int MAUSOLEUM_OPTIONS_DUNGEON_COMPLETION = 2;
    public static final int MAUSOLEUM_OPTIONS_EXIT = 3;

    public static Menu getMausoleumOptions() {
        MenuOption highScores = new MenuOption("Visit Crypt", false);
        MenuOption challengeModes = new MenuOption("Reflect On Ancestral Feats", false);
        MenuOption gameProgress = new MenuOption("Review Travel Records", false);
        MenuOption exit = new MenuOption("Exit Mausoleum", true);
        return MenuBuilder.newMenu("Mausoleum Options")
                .addOption(highScores)
                .addOption(challengeModes)
                .addOption(gameProgress)
                .addOption(exit)
                .build();
    }

    public static final int FORGE_OPTIONS_REPAIR = 0;
    public static final int FORGE_OPTIONS_CONSTRUCT = 1;
    public static final int FORGE_OPTIONS_COAT = 2;
    public static final int FORGE_OPTIONS_UPGRADE = 3;
    public static final int FORGE_OPTIONS_EXIT = 4;

    public static Menu getForgeOptions() {
        MenuOption repair = new MenuOption("Repair Metallic Item", false);
        MenuOption construct = new MenuOption("Construct From Blueprint", false);
        MenuOption coat = new MenuOption("Coat Metallic Item", false);
        MenuOption upgrade = new MenuOption("Upgrade Forge", false);
        MenuOption exit = new MenuOption("Exit Forge", true);
        return MenuBuilder.newMenu("Forge Options")
                .addOption(repair)
                .addOption(construct)
                .addOption(coat)
                .addOption(upgrade)
                .addOption(exit)
                .build();
    }

    public static final int WORKSHOP_OPTIONS_REPAIR = 0;
    public static final int WORKSHOP_OPTIONS_CONSTRUCT = 1;
    public static final int WORKSHOP_OPTIONS_COAT = 2;
    public static final int WORKSHOP_OPTIONS_UPGRADE = 3;
    public static final int WORKSHOP_OPTIONS_EXIT = 4;

    public static Menu getWorkshopOptions() {
        MenuOption repair = new MenuOption("Repair Non-Metallic Item", false);
        MenuOption construct = new MenuOption("Construct From Blueprint", false);
        MenuOption coat = new MenuOption("Coat Non-Metallic Item", false);
        MenuOption upgrade = new MenuOption("Upgrade Workshop", false);
        MenuOption exit = new MenuOption("Exit Workshop", true);
        return MenuBuilder.newMenu("Workshop Options")
                .addOption(repair)
                .addOption(construct)
                .addOption(coat)
                .addOption(upgrade)
                .addOption(exit)
                .build();
    }

    public static final int WAREHOUSE_OPTIONS_LEGACY_STOCKPILES = 0;
    public static final int WAREHOUSE_OPTIONS_TRANSIENT_STOCKPILES = 1;
    public static final int WAREHOUSE_OPTIONS_UPGRADE = 2;
    public static final int WAREHOUSE_OPTIONS_EXIT = 3;

    public static Menu getWarehouseOptions() {
        MenuOption legacyStocks = new MenuOption("Review Legacy Stockpiles", true);
        MenuOption transientStocks = new MenuOption("Review Transient Stockpiles", false);
        MenuOption upgrade = new MenuOption("Upgrade Warehouse", false);
        MenuOption exit = new MenuOption("Exit Warehouse", true);
        return MenuBuilder.newMenu("Warehouse Options")
                .addOption(legacyStocks)
                .addOption(transientStocks)
                .addOption(upgrade)
                .addOption(exit)
                .build();
    }

    public static final int ARMORY_OPTIONS_EQUIP = 0;
    public static final int ARMORY_OPTIONS_PACK = 1;
    public static final int ARMORY_OPTIONS_SCRAP = 2;
    public static final int ARMORY_OPTIONS_UPGRADE = 3;
    public static final int ARMORY_OPTIONS_EXIT = 4;

    public static Menu getArmoryOptions() {
        MenuOption equip = new MenuOption("Outfit Equipment", false);
        MenuOption pack = new MenuOption("Pack Supplies", false);
        MenuOption scrap = new MenuOption("Scrap For Resources", false);
        MenuOption upgrade = new MenuOption("Upgrade Armory", false);
        MenuOption exit = new MenuOption("Exit Armory", true);
        return MenuBuilder.newMenu("Armory Options")
                .addOption(equip)
                .addOption(pack)
                .addOption(scrap)
                .addOption(upgrade)
                .addOption(exit)
                .build();
    }
    public static final int RITUAL_CHAMBER_OPTIONS_PERFORM_RITUAL = 0;
    public static final int RITUAL_CHAMBER_OPTIONS_UPGRADE = 1;
    public static final int RITUAL_CHAMBER_OPTIONS_EXIT = 2;

    public static Menu getRitualChamberOptions() {
        MenuOption performRitual = new MenuOption("Perform a Ritual", false);
        MenuOption upgrade = new MenuOption("Upgrade Ritual Chamber", false);
        MenuOption exit = new MenuOption("Exit Ritual Chamber", true);
        return MenuBuilder.newMenu("Ritual Chamber Options")
                .addOption(performRitual)
                .addOption(upgrade)
                .addOption(exit)
                .build();
    }

    public static Menu getUpgradeOptions(int estateRoomIndex) {
        EstateProgressionRoom libraryProgression = Session.getEstateProgression().get(estateRoomIndex);
        MenuBuilder menuBuilder = MenuBuilder.newMenu("Available Upgrades");
        for (EstateProgressionNode epn : libraryProgression) {
            if (!epn.isUnlocked() && epn.shouldDisplay(Session.getEstateProgression())) {
                menuBuilder.addOption(new MenuOption("" + epn, epn.canUnlock(Session.getLegacyResources(), Session.getEstateProgression())));
            }
        }
        return menuBuilder.addOption(new MenuOption(CANCEL, true)).build();
    }
}
