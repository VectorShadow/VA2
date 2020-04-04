package main.modes.menu.estate.library;

import io.out.GUIManager;
import main.Session;
import main.modes.menu.MenuDefinitions;
import main.modes.menu.MenuMode;
import world.item.Text;
import world.item.inventory.Inventory;
import world.item.inventory.ItemSlot;

public class ResearchTextsMenuMode extends MenuMode {
    @Override
    protected void handleMenuOptionIndex(int index) {
        Inventory texts = Session.getPlayer().getUnresearchedTexts();
        ItemSlot itemSlot;
        Text text;
        Session.getModeManager().revert();
        if (index != texts.size()) {
            itemSlot = texts.get(index);
            text = (Text)itemSlot.peekItem();
            if (text.study()) {
                texts.remove(index, 1);
            }
        }
    }

    @Override
    public void to() {
        setMenu(MenuDefinitions.getTextResearchOptions());
        GUIManager gm = Session.getGuiManager();
        gm.changeChannelToFullscreenText();
        out();
    }

    @Override
    public void from() {
        //todo - nothing
    }
}
