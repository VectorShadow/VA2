package main.modes.menu.estate;

import main.Session;
import main.modes.ScrollingTextMode;
import main.modes.menu.EstateRoomMenuMode;
import main.modes.menu.MenuDefinitions;

public class WarehouseMenuMode extends EstateRoomMenuMode {
    @Override
    protected void setEstateMenu() {
        setMenu(MenuDefinitions.getWarehouseOptions());
    }

    @Override
    protected void handleMenuOptionIndex(int index) {
        switch (index) {
            case MenuDefinitions.WAREHOUSE_OPTIONS_LEGACY_STOCKPILES:
                Session.getModeManager().transitionTo(new ScrollingTextMode("" + Session.getLegacyResources()));
                return;
            case MenuDefinitions.WAREHOUSE_OPTIONS_TRANSIENT_STOCKPILES:
                //todo
                return;
            case MenuDefinitions.WAREHOUSE_OPTIONS_UPGRADE:
                //todo
                return;
            case MenuDefinitions.WAREHOUSE_OPTIONS_EXIT:
                Session.getModeManager().revert();
                return;
            default:
                out();
        }
    }
}
