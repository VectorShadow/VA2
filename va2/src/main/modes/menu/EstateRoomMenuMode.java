package main.modes.menu;

import io.out.GUIManager;
import main.Session;
import main.modes.menu.estate.LibraryMenuMode;
import world.terrain.TerrainDefinitions;
import world.terrain.TerrainTemplate;

public abstract class EstateRoomMenuMode extends MenuMode {

    abstract protected void setEstateMenu();

    @Override
    public void to() {
        setEstateMenu();
        GUIManager gm = Session.getGuiManager();
        gm.changeChannelToFullscreenText();
        out();
    }

    @Override
    public void from() {
        //todo - nothing
    }

    public static EstateRoomMenuMode interpretTerrain(TerrainTemplate tt) {
        if (tt.equals(TerrainDefinitions.LIBRARY_PORTAL))
            return new LibraryMenuMode();
        else if (tt.equals(TerrainDefinitions.HALL_OF_ARMS_PORTAL))
            return null;
        else if (tt.equals(TerrainDefinitions.ARCHERY_RANGE_PORTAL))
            return null;
        else if (tt.equals(TerrainDefinitions.LABORATORY_PORTAL))
            return null;
        else if (tt.equals(TerrainDefinitions.MAUSOLEUM_PORTAL))
            return null;
        else if (tt.equals(TerrainDefinitions.TROPHY_HALL_PORTAL))
            return null;
        else if (tt.equals(TerrainDefinitions.FORGE_PORTAL))
            return null;
        else if (tt.equals(TerrainDefinitions.WORKSHOP_PORTAL))
            return null;
        else if (tt.equals(TerrainDefinitions.WAREHOUSE_PORTAL))
            return null;
        else if (tt.equals(TerrainDefinitions.ARMORY_PORTAL))
            return null;
        else if (tt.equals(TerrainDefinitions.RITUAL_CHAMBER_PORTAL))
            return null;
        else
            return null; //unsupported terrain need not generate a menu
    }
}
