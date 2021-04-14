package main.modes.menu.estate.library;

import main.Session;
import main.modes.menu.estate.ItemSelectionExecutor;
import world.item.Text;
import world.item.inventory.Inventory;
import world.item.inventory.ItemSlot;

public class ResearchTextsSelectionExecutor extends ItemSelectionExecutor {
    public ResearchTextsSelectionExecutor(Inventory inventory) {
        super(inventory);
    }

    @Override
    public void handleSelection() {
        Inventory texts = Session.getPlayer().getUnresearchedTexts();
        ItemSlot itemSlot;
        Text text;
        itemSlot = texts.get(selectedIndex);
        text = (Text)itemSlot.peekItem();
        if (text.study()) {
            texts.remove(selectedIndex, 1);
        }
    }
}
